/*
 * Copyright (c) 2018, The University of Memphis, MD2K Center of Excellence
 *
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

package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Handler;
import android.os.RemoteException;
import android.util.SparseArray;

import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
class InsertionManager {
    private IDataKitRemoteService mService;

    //    private SparseArray<List<Data>> data;
    private SparseArray<ArrayList<Long>> bufferTime;
    private Map<Integer, List<Data>> data;
    private Handler handler;
    private static final long SYNC_TIME = 1000; //1 second
    private static final int BUFFER_SIZE = 10;
    private static final double HIGH_FREQUENCY_LIMIT = 2.0;
    private boolean isSyncScheduled;

    private ConnectionCallback connectionCallback;
    private Lock lock;

    /**
     * Constructor
     *
     * @param mService           Remote DataKit Service
     * @param connectionCallback Callback for connections
     */
    InsertionManager(IDataKitRemoteService mService, ConnectionCallback connectionCallback) {
        this.mService = mService;
        handler = new Handler();
        data = new HashMap<>();
        bufferTime = new SparseArray<>();
        isSyncScheduled = false;
        this.connectionCallback = connectionCallback;
        lock = new ReentrantLock();
    }

    /**
     * Runnable that calls <code>sync()</code>.
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sync();
            isSyncScheduled = false;
        }
    };

    /**
     * @param dsId
     * @param data
     * @return
     */
    protected int insert(int dsId, Data[] data) {
        lock.lock();
        ArrayList<Long> a = bufferTime.get(dsId, new ArrayList<Long>());
        List<Data> list = this.data.get(dsId);
        if (list == null) list = new ArrayList<Data>();
        for (Data aData : data) {
            list.add(aData.clone());
            a.add(aData.getTimestamp());
            if (a.size() > BUFFER_SIZE) a.remove(0);
        }
        this.data.put(dsId, list);
        bufferTime.put(dsId, a);
        if (a.size() == 1 || (a.size() * 1000.0) / (a.get(a.size() - 1) - a.get(0)) < HIGH_FREQUENCY_LIMIT) {
            if (isSyncScheduled) {
                handler.removeCallbacks(runnable);
                isSyncScheduled = false;
            }
            handler.post(runnable);
        } else {
            if (!isSyncScheduled) {
                isSyncScheduled = true;
                handler.postDelayed(runnable, SYNC_TIME);
            }
        }
        lock.unlock();
        return MCerebrumStatus.SUCCESS;
    }

    /**
     * Tries to pass data to <code>mService</code> via <code>insert()</code>.
     */
    private void sync() {
        lock.lock();
        try {
            int r = mService.insert(data);
            if (r != MCerebrumStatus.SUCCESS)
                connectionCallback.onError(new MCerebrumException(MCerebrumStatus.getStatusCodeString(r)));
            data.clear();
        } catch (RemoteException e) {
            connectionCallback.onError(new MCerebrumException(MCerebrumStatus.CONNECTION_ERROR));
        }
        lock.unlock();
    }
}
