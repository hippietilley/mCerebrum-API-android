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

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;

/**
 * This class manages the service connections between mCerebrum and DataKit.
 */
class MyServiceConnection implements ServiceConnection {
    private boolean connected;
    private IDataKitRemoteService mService;
    private InsertionManager insertionManager;
    private SubscriptionManager subscriptionManager;
    private ConnectionCallback connectionCallback;

    /**
     * Constructor
     *
     * @param connectionCallback Callback for connecting to <code>DataKit</code>.
     */
    MyServiceConnection(ConnectionCallback connectionCallback) {
        this.connectionCallback = connectionCallback;
    }

    /**
     * Calls <code>connectionCallback.onDisconnected()</code> when the service is disconnected.
     *
     * @param name <code>ComponentName</code>.
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("abc", "onServiceDisconnected");
        connected = false;
        connectionCallback.onDisconnected();
    }

    /**
     * Binds an <code>IBinder</code> to the <code>IDataKitRemoteService</code> and creates
     * a new <code>InsertionManager</code> and <code>SubscriptionManager</code>.
     *
     * @param name    <code>ComponentName</code>.
     * @param service <code>IBinder</code>.
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("abc", "onServiceConnected");
        connected = true;
        mService = IDataKitRemoteService.Stub.asInterface(service);
        insertionManager = new InsertionManager(mService, connectionCallback);
        subscriptionManager = new SubscriptionManager(mService, connectionCallback);
        connectionCallback.onConnected();
    }

    /**
     * Registers the given <code>DataSourceReadWrite</code> with <code>DataKit</code>.
     *
     * @param dataSourceAIDL <code>DataSourceReadWrite</code> to register.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int register(DataSourceReadWrite dataSourceAIDL) {
        try {
            if (MCerebrumAPI.getContext() == null)
                return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
            if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if (dataSourceAIDL.getDataSourceType() == null)
                return MCerebrumStatus.MISSING_DATA_SOURCE_TYPE;
            if (dataSourceAIDL.getDataType() == null || dataSourceAIDL.getDataType().length() == 0)
                return MCerebrumStatus.MISSING_DATA_TYPE;
            return mService.register(dataSourceAIDL);

        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Unregisters the given <code>DataSourceReadWrite</code> from <code>DataKit</code>.
     *
     * @param dataSourceAIDL <code>DataSourceReadWrite</code> to unregister.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int unregister(DataSourceReadWrite dataSourceAIDL) {
        try {
            if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
            if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED;
            return mService.unregister(dataSourceAIDL.getDsId());
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Finds the <code>DataSource</code>s specified in the <code>DataSourceReadWrite</code>.
     *
     * @param dataSource  <code>DataSource</code> to find.
     * @param dataSources Array of <code>DataSource</code>s found.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int find(DataSourceReadWrite dataSource, DataSourceReadWrite[] dataSources) {
        try {
            if (dataSource == null) return MCerebrumStatus.INVALID_PARAMETER;
            return mService.find(dataSource, dataSources);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Inserts the given data into the database.
     *
     * @param dataSource <code>DataSource</code> of the data.
     * @param data       Array of data to insert.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int insert(DataSource dataSource, Data[] data) {
        if (dataSource == null) return MCerebrumStatus.INVALID_PARAMETER;
        if (dataSource.getDsId() < 0) return MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED;
        if (data == null || data.length == 0) return MCerebrumStatus.INVALID_PARAMETER;
        DataType dataType = DataType.valueOf(dataSource.getDataType());
        for (Data aData : data) {
            if (aData.getClass().hashCode() != dataType.getHashCode())
                return MCerebrumStatus.INCONSISTENT_DATA_TYPE;
        }
        return insertionManager.insert(dataSource.getDsId(), data);
    }

    /**
     * Queries the database and returns the number of entries between two timestamps.
     *
     * @param dataSourceAIDL <code>DataSourceReadWrite</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @param dataSet        <code>DataSet</code>.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int queryCount(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet) {
        try {
            if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
            if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if (startTimestamp > endTimestamp || endTimestamp == -1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.queryCount(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Queries the database for a <code>DataSet</code> of the "last n points".
     *
     * @param dataSourceReadWrite <code>DataSource</code> to query data from.
     * @param lastNSample         Number of data points to query for.
     * @param dataSet             <code>DataSet</code>.
     * @return The resulting <code>DataSet</code>.
     */
    protected int queryByNumber(DataSourceReadWrite dataSourceReadWrite, int lastNSample, DataSet dataSet) {
        try {
            if (dataSourceReadWrite == null) return MCerebrumStatus.INVALID_PARAMETER;
            if (dataSourceReadWrite.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if (lastNSample <= 0) return MCerebrumStatus.INVALID_PARAMETER;
            if (lastNSample > 10000) return MCerebrumStatus.DATA_SIZE_TOO_LARGE;
            return mService.queryByNumber(dataSourceReadWrite.getDsId(), lastNSample, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Queries the database for a <code>DataSet</code> of all data points between two timestamps.
     *
     * @param dataSourceAIDL <code>DataSource</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int queryByTime(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet) {
        try {
            if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
            if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if (startTimestamp > endTimestamp || endTimestamp == -1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.queryByTime(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Queries the database for a summary of the data points between two timestamps.
     *
     * @param dataSourceAIDL <code>DataSource</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int querySummary(DataSourceReadWrite dataSourceAIDL, long startTimestamp, long endTimestamp, DataSet dataSet) {
        try {
            if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
            if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;
            if (startTimestamp > endTimestamp || endTimestamp == -1)
                return MCerebrumStatus.INVALID_TIMESTAMP;
            return mService.querySummary(dataSourceAIDL.getDsId(), startTimestamp, endTimestamp, dataSet);
        } catch (RemoteException e) {
            return MCerebrumStatus.CONNECTION_ERROR;
        }
    }

    /**
     * Subscribes the given <code>DataCallback</code> to the specified <code>DataSource</code> input stream.
     * This provides a way to see what data is being sent to <code>DataKit</code>.
     *
     * @param dataSourceAIDL <code>DataSourceReadWrite</code> to subscribe to.
     * @param callback       Callback for data.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int subscribe(DataSourceReadWrite dataSourceAIDL, DataCallback callback) {
        if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
        if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;
        if (callback == null) return MCerebrumStatus.INVALID_PARAMETER;
        return subscriptionManager.subscribe(dataSourceAIDL.getDsId(), callback);
    }

    /**
     * Unsubscribes the <code>DataCallback</code> from the specified <code>DataSource</code> input stream.
     *
     * @param dataSourceAIDL <code>DataSourceReadWrite</code> to unsubscribe.
     * @param callback       Callback for the data.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    protected int unsubscribe(DataSourceReadWrite dataSourceAIDL, DataCallback callback) {
        if (dataSourceAIDL == null) return MCerebrumStatus.INVALID_PARAMETER;
        if (dataSourceAIDL.getDsId() < 0) return MCerebrumStatus.INVALID_DATA_SOURCE;

        if (callback == null) return MCerebrumStatus.INVALID_PARAMETER;
        return subscriptionManager.unsubscribe(dataSourceAIDL.getDsId(), callback);
    }

    /**
     * Returns whether <code>DataKit</code> is connected or not.
     *
     * @return Whether <code>DataKit</code> is connected or not.
     */
    protected boolean isConnected() {
        return connected;
    }
}
