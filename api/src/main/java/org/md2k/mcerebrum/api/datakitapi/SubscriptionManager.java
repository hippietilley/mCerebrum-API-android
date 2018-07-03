package org.md2k.mcerebrum.api.datakitapi;

import android.os.RemoteException;
import android.util.SparseArray;

import org.md2k.mcerebrum.api.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.datakitapi.status.MCerebrumStatus;

import java.util.HashSet;

/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
class SubscriptionManager {

    private SparseArray<HashSet<DataCallback>> receiveCallbacks;
    private SparseArray<IDataKitRemoteCallback> remoteCallbacks;

    private IDataKitRemoteService mService;
    private ConnectionCallback connectionCallback;

    SubscriptionManager(IDataKitRemoteService mService, ConnectionCallback connectionCallback) {
        this.mService = mService;
        this.receiveCallbacks = new SparseArray<>();
        this.remoteCallbacks = new SparseArray<>();
        this.connectionCallback = connectionCallback;
    }

    private void addToReceiveCallback(int dsId, DataCallback dataCallback) {
        HashSet<DataCallback> hs = receiveCallbacks.get(dsId, new HashSet<DataCallback>());
        if (!hs.contains(dataCallback))
            hs.add(dataCallback);
        receiveCallbacks.put(dsId, hs);
    }

    private void removeFromReceiveCallback(int dsId, DataCallback dataCallback) {
        HashSet<DataCallback> hs = receiveCallbacks.get(dsId, null);
        if (hs == null) return;
        hs.remove(dataCallback);
        if (hs.isEmpty()) receiveCallbacks.delete(dsId);
    }

    private void sendReceiveCallback(int dsId, Data data) {
        HashSet<DataCallback> hs = receiveCallbacks.get(dsId, new HashSet<DataCallback>());
        for (DataCallback h : hs)
            h.onReceived(data);
    }

    int subscribe(int dsId, DataCallback dataCallback) {
        try {
            boolean isEmpty = receiveCallbacks.get(dsId, new HashSet<DataCallback>()).isEmpty();
            addToReceiveCallback(dsId, dataCallback);
            if (isEmpty) {
                IDataKitRemoteCallback rc = createIDataKitRemoteCallback(dsId);
                remoteCallbacks.put(dsId, rc);
                return mService.subscribe(dsId, rc);
            } else return MCerebrumStatus.SUCCESS;
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    private IDataKitRemoteCallback createIDataKitRemoteCallback(final int ds_id) {
        return new IDataKitRemoteCallback.Stub() {
            @Override
            public void onReceived(Data[] data) {
                for (Data aData : data) {
                    sendReceiveCallback(ds_id, aData);
                }
            }
        };

    }

    int unsubscribe(int dsId, DataCallback dataCallback) {
        try {
            removeFromReceiveCallback(dsId, dataCallback);
            if (receiveCallbacks.get(dsId, null) == null) {
                IDataKitRemoteCallback rc = remoteCallbacks.get(dsId, null);
                if (rc != null) {
                    remoteCallbacks.delete(dsId);
                    return mService.unsubscribe(dsId, rc);
                }
            }
            return MCerebrumStatus.SUCCESS;
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    int unsubscribeAll() {
        int returnValue = MCerebrumStatus.SUCCESS;
        for (int i = 0; i < remoteCallbacks.size(); i++) {
            try {
                int dsId = remoteCallbacks.keyAt(i);
                int r = mService.unsubscribe(dsId, remoteCallbacks.valueAt(i));
                if(returnValue== MCerebrumStatus.SUCCESS)
                    returnValue=r;
            } catch (RemoteException e) {
                returnValue = MCerebrumStatus.CONNECTION_ERROR;
            }
        }
        remoteCallbacks.clear();
        receiveCallbacks.clear();
        return returnValue;
    }
}
