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

/**
 * Handles exception checks and handles outside method calls for <code>DataKitAPIExecute</code>.
 */
class DataManager {
/*
    private boolean connected;
    private SubscriptionManager subscribe;
    private InsertionManager dataInsert;

    DataManager() {
    }

    DataSourceClient register(DataSource dataSource) {
        Log.d("abc", "register...");
        try {
            return mService.register(dataSource);
        } catch (RemoteException e) {
            return null;
        }
    }

    MCStatus unregister(int dsId) {
        try {
            dataInsert.sync();
            int m = mService.unregister(dsId);
            return MCStatus.findByCode(m);
        } catch (RemoteException e) {
            Log.e("abc", "unregister error=" + e.getMessage());
            return MCStatus.CONNECTION_ERROR;
        }
    }

    DataSourceClient[] find(DataSource dataSource) {
        try {
            return mService.find(dataSource);
        } catch (RemoteException e) {
            Log.e("abc", "find error=" + e.getMessage());
            return null;
        }
    }

    void insert(Data[] data) {
        dataInsert.insert(data);
    }

    DataSet summary(int dsId, long startTimestamp, long endTimestamp) {
        try {
            return mService.getSummary(dsId, startTimestamp, endTimestamp);
        } catch (RemoteException e) {
            Log.e("abc", "summary error=" + e.getMessage());
            return new DataSet(MCStatus.CONNECTION_ERROR.getCode(), null);
        }
    }

    DataSet query(int dsId, int lastNPoint) {
        try {
            return mService.queryN(dsId, lastNPoint);
        } catch (RemoteException e) {
            Log.e("abc", "queryN error=" + e.getMessage());
            return new DataSet(MCStatus.CONNECTION_ERROR.getCode(), null);
        }
    }

    DataSet query(int dsId, long startTimestamp, long endTimestamp) {
        try {
            return mService.queryT(dsId, startTimestamp, endTimestamp);
        } catch (RemoteException e) {
            Log.e("abc", "queryT error=" + e.getMessage());
            return new DataSet(MCStatus.CONNECTION_ERROR.getCode(), null);
        }
    }

    MCStatus subscribe(final int dsId, final DataCallback dataCallback) {
        try {
            return subscribe.subscribe(dsId, dataCallback);
        } catch (RemoteException e) {
            Log.e("abc", "subscribe error=" + e.getMessage());
            return MCStatus.CONNECTION_ERROR;
        }
    }

    MCStatus unsubscribe(int dsId, DataCallback dataCallback) {
        try {
            return subscribe.unsubscribe(dsId, dataCallback);
        } catch (RemoteException e) {
            Log.e("abc", "unsubscribe error=" + e.getMessage());
            return MCStatus.CONNECTION_ERROR;
        }
    }
*/
}