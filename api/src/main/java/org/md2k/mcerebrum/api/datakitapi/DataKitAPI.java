package org.md2k.mcerebrum.api.datakitapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import org.md2k.mcerebrum.api.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.datakitapi.status.MCerebrumStatus;
import org.md2k.mcerebrum.api.MCerebrumAPI;

import java.util.ArrayList;

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
public final class DataKitAPI {

    /**
     * Name of the service.
     * <p>
     * <code>"org.md2k.mcerebrum.datakit.ServiceDataKit"</code>
     * </p>
     */
    private static final String SERVICE_NAME = "org.md2k.mcerebrum.datakit.ServiceDataKit";

    /**
     * Name of the package.
     * <p>
     * <code>"org.md2k.mcerebrum"</code>
     * </p>
     */
    private static final String PACKAGE_NAME = "org.md2k.mcerebrum";

    private ArrayList<ConnectionCallback> connectionCallbacks;
    private MyServiceConnection mServiceConnection;

    public DataKitAPI(MCerebrumAPI m) {
        if (m == null || MCerebrumAPI.getContext() == null)
            return;
        connectionCallbacks = new ArrayList<>();
        mServiceConnection = new MyServiceConnection(new ConnectionCallback() {
            @Override
            public void onConnected() {
                afterConnected();
            }

            @Override
            public void onError(MCerebrumException e) {
                afterError(e);
            }

            @Override
            public void onDisconnected() {
                afterDisconnected();
            }
        });
    }

    public int connect(ConnectionCallback connectionCallback) {
        try {
            if (connectionCallback == null) return MCerebrumStatus.INVALID_PARAMETER;
            connectionCallbacks.add(connectionCallback);
            if (isConnected()) {
                connectionCallback.onConnected();
                return MCerebrumStatus.SUCCESS;
            } else {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(PACKAGE_NAME, SERVICE_NAME));
                boolean r = MCerebrumAPI.getContext().bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                if (!r) {
                    connectionCallback.onError(new MCerebrumException(MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED));
                    return MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED;
                } else {
                    connectionCallbacks.add(connectionCallback);
                }
                return MCerebrumStatus.SUCCESS;
            }
        } catch (Exception e) {
            connectionCallback.onError(new MCerebrumException(MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED));
            return MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED;
        }

    }

    private void afterDisconnected() {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onDisconnected();
        connectionCallbacks.clear();
    }

    private void afterError(MCerebrumException e) {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onError(e);
        connectionCallbacks.clear();
    }

    private void afterConnected() {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onConnected();
    }

    public int disconnect(ConnectionCallback connectionCallback) {
        if (connectionCallback == null) return MCerebrumStatus.INVALID_PARAMETER;
        for (int i = 0; i < connectionCallbacks.size(); i++) {
            if (connectionCallbacks.get(i).equals(connectionCallback)) {
                connectionCallbacks.remove(i);
            }
        }
        if (mServiceConnection.isConnected() && connectionCallbacks.size() == 0) {
            MCerebrumAPI.getContext().unbindService(mServiceConnection);
        }
        connectionCallback.onDisconnected();
        return MCerebrumStatus.SUCCESS;
    }

    public Registration register(DataSourceCreator dataSourceCreator) {
        if(dataSourceCreator==null) return new Registration(null, MCerebrumStatus.INVALID_PARAMETER);
        DataSourceReadWrite dataSourceReadWrite = dataSourceCreator.toDataSourceReadWrite();
        dataSourceReadWrite.setDsId(-1);
        int r =  mServiceConnection.register(dataSourceReadWrite);
        return new Registration(dataSourceReadWrite, r);
    }

    public int unregister(Registration registration) {
        if(registration==null) return MCerebrumStatus.INVALID_PARAMETER;
        if(registration.getStatus()!=MCerebrumStatus.SUCCESS) return registration.getStatus();
        return mServiceConnection.unregister(registration.getDataSource().toDataSourceReadWrite());
    }

    public DataSourceSet find(DataSourceRequest dataSourceRequest) {
        if(dataSourceRequest==null) return new DataSourceSet(null, MCerebrumStatus.INVALID_PARAMETER);
        DataSourceReadWrite[] dataSources=new DataSourceReadWrite[0];
        int r= mServiceConnection.find(dataSourceRequest.toDataSourceReadWrite(), dataSources);
        return new DataSourceSet(dataSources,r);
    }

    public int insert(Registration registration, Data[] data) {
        if(registration==null) return MCerebrumStatus.INVALID_PARAMETER;
        return mServiceConnection.insert(registration.getDataSource(), data);
    }

    public boolean isConnected() {
        return mServiceConnection.isConnected();
    }

    public DataSet query(DataSource dataSource, int lastNPoint) {
        DataSet dataSet=new DataSet();
        int r = mServiceConnection.queryByNumber(dataSource.toDataSourceReadWrite(), lastNPoint, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    public DataSet query(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet=new DataSet();
        int r = mServiceConnection.queryByTime(dataSource.toDataSourceReadWrite(), startTimestamp,endTimestamp, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    public DataSet querySummary(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet=new DataSet();
        int r = mServiceConnection.querySummary(dataSource.toDataSourceReadWrite(), startTimestamp,endTimestamp, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    public int queryCount(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet=new DataSet();
        int r = mServiceConnection.queryCount(dataSource.toDataSourceReadWrite(), startTimestamp,endTimestamp, dataSet);
        if(r!=MCerebrumStatus.SUCCESS) return -1;
        return dataSet.getActualDataSize();
    }

    public int subscribe(DataSource dataSource, DataCallback callback) {
        return mServiceConnection.subscribe(dataSource.toDataSourceReadWrite(), callback);
    }

    public int unsubscribe(DataSource dataSource, DataCallback callback) {
        return mServiceConnection.unsubscribe(dataSource.toDataSourceReadWrite(), callback);
    }
}
