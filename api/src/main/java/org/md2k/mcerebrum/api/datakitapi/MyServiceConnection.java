package org.md2k.mcerebrum.api.datakitapi;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import org.md2k.mcerebrum.api.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.datakitapi.status.MCerebrumStatus;
import org.md2k.mcerebrum.api.mCerebrumAPI;

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
class MyServiceConnection implements ServiceConnection {
    private boolean connected;
    private IDataKitRemoteService mService;
    private InsertionManager insertionManager;
    private SubscriptionManager subscriptionManager;
    private ConnectionCallback connectionCallback;
    MyServiceConnection(ConnectionCallback connectionCallback){
        this.connectionCallback = connectionCallback;

    }
    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("abc", "onServiceDisconnected");
        connected = false;
        connectionCallback.onDisconnected();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("abc", "onServiceConnected");
        connected = true;
        mService = IDataKitRemoteService.Stub.asInterface(service);
        insertionManager = new InsertionManager(mService, connectionCallback);
        subscriptionManager = new SubscriptionManager(mService, connectionCallback);
        connectionCallback.onConnected();
    }

    int register(DataSourceReadWrite dataSourceAIDL){
        try {
            if(mCerebrumAPI.getContext()==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
            if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if(dataSourceAIDL.getDataSourceType()==null) return MCerebrumStatus.MISSING_DATA_SOURCE_TYPE;
            if(dataSourceAIDL.getDataType()==null || dataSourceAIDL.getDataType().length()==0) return MCerebrumStatus.MISSING_DATA_TYPE;
            dataSourceAIDL.setAppId(mCerebrumAPI.getContext().getPackageName());
            return mService.register(dataSourceAIDL);

        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }
    int unregister(DataSourceReadWrite dataSourceAIDL){
        try {
            if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
            if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED;
            return mService.unregister(dataSourceAIDL.getDsId());
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }
    int find(DataSourceReadWrite dataSource, DataSourceReadWrite[] dataSources){
        try {
            if (dataSource == null) return MCerebrumStatus.INVALID_PARAMETER;
            return mService.find(dataSource, dataSources);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    int insert(DataSource dataSource, Data[] data){
        if(dataSource==null) return MCerebrumStatus.INVALID_PARAMETER;
        if(dataSource.getDsId()<0) return MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED;
        if(data==null || data.length==0) return MCerebrumStatus.INVALID_PARAMETER;
        DataType dataType=DataType.valueOf(dataSource.getDataType());
        for (Data aData : data) {
            if (aData.getType() != dataType) return MCerebrumStatus.INCONSISTENT_DATA_TYPE;
        }
        return insertionManager.insert(dataSource.getDsId(), data);
    }

    int queryCount(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet){
        try {
            if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
            if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if(startTimestamp>endTimestamp || endTimestamp==-1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.queryCount(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }
    int queryByNumber(DataSourceReadWrite dataSourceReadWrite, int lastNSample, DataSet dataSet){
        try {
            if(dataSourceReadWrite==null) return MCerebrumStatus.INVALID_PARAMETER;
            if(dataSourceReadWrite.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if(lastNSample<=0) return MCerebrumStatus.INVALID_PARAMETER;
            if(lastNSample>10000) return MCerebrumStatus.DATA_SIZE_TOO_LARGE;
            return mService.queryByNumber(dataSourceReadWrite.getDsId(), lastNSample, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }
    int queryByTime(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet){
        try {
            if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
            if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if(startTimestamp>endTimestamp || endTimestamp==-1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.queryByTime(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }
    int querySummary(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet){
        try {
            if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
            if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if(startTimestamp>endTimestamp || endTimestamp==-1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.querySummary(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    int subscribe(DataSourceReadWrite dataSourceAIDL, DataCallback callback){
        if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
        if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;
        if(callback==null) return MCerebrumStatus.INVALID_PARAMETER;
     return subscriptionManager.subscribe(dataSourceAIDL.getDsId(), callback);
    }
    int unsubscribe(DataSourceReadWrite dataSourceAIDL, DataCallback callback){
        if(dataSourceAIDL==null) return MCerebrumStatus.INVALID_PARAMETER;
        if(dataSourceAIDL.getDsId()<0) return MCerebrumStatus.INVALID_DATA_SOURCE;

        if(callback==null) return MCerebrumStatus.INVALID_PARAMETER;
        return subscriptionManager.unsubscribe(dataSourceAIDL.getDsId(), callback);
    }

    boolean isConnected() {
        return connected;
    }
}
