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
import android.content.Context;
import android.content.Intent;

import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;

import java.util.ArrayList;

/**
 * This class provides methods for interacting with <code>DataKit</code>.
 */
public final class DataKitAPI {
    private static final String SERVICE_NAME = "org.md2k.mcerebrum.datakit.ServiceDataKit";
    private static final String PACKAGE_NAME = "org.md2k.mcerebrum";
    private ArrayList<ConnectionCallback> connectionCallbacks;
    private MyServiceConnection mServiceConnection;

    /**
     * Constructor.
     *
     * @param m Instance of <code>MCerebrumAPI</code>.
     */
    public DataKitAPI(MCerebrumAPI m) {
        if (m == null || MCerebrumAPI.getContext() == null)
            return;
        connectionCallbacks = new ArrayList<>();
        mServiceConnection = new MyServiceConnection(new ConnectionCallback() {
            /**
             * Calls <code>afterConnected()</code>.
             */
            @Override
            public void onConnected() {
                afterConnected();
            }

            /**
             * Calls <code>afterError(e)</code>.
             *
             * @param e <code>MCerebrumException</code> that was thrown.
             */
            @Override
            public void onError(MCerebrumException e) {
                afterError(e);
            }

            /**
             * Calls <code>afterDisconnected()</code>.
             */
            @Override
            public void onDisconnected() {
                afterDisconnected();
            }
        });
    }

    /**
     * Connects to <code>DataKit</code>.
     *
     * @param connectionCallback Callback interface.
     * @return The <code>MCerebrumStatus</code> of the connection.
     */
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

    /**
     * After <code>DataKit</code> is disconnected this method clears the <code>connectionCallbacks</code> arraylist.
     */
    private void afterDisconnected() {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onDisconnected();
        connectionCallbacks.clear();
    }

    /**
     * After <code>DataKit</code> throws an exception this method clears the <code>connectionCallbacks</code> arraylist.
     */
    private void afterError(MCerebrumException e) {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onError(e);
        connectionCallbacks.clear();
    }

    /**
     * After <code>DataKit</code> is connected this method calls the <code>onConnected()</code> method of the callback.
     */
    private void afterConnected() {
        for (int i = 0; i < connectionCallbacks.size(); i++)
            connectionCallbacks.get(i).onConnected();
    }

    /**
     * Disconnects <code>DataKit</code>.
     *
     * @param connectionCallback Callback interface.
     * @return The <code>MCerebrumStatus</code> of the connection.
     */
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

    /**
     * Registers the given <code>DataSourceCreator</code> with <code>DataKit</code> which creates
     * a corresponding <code>DataSourceReadWrite</code> and <code>Registration</code> object.
     *
     * @param dataSourceCreator <code>DataSourceCreator</code> to register.
     * @return A new <code>Registration</code>.
     */
    public Registration register(DataSourceCreator dataSourceCreator) {
        if (dataSourceCreator == null)
            return new Registration(null, MCerebrumStatus.INVALID_PARAMETER);
        DataSourceReadWrite dataSourceReadWrite = dataSourceCreator.toDataSourceReadWrite();
        dataSourceReadWrite.setDsId(-1);
        int r = mServiceConnection.register(dataSourceReadWrite);
        return new Registration(dataSourceReadWrite, r);
    }

    /**
     * Unregisters the give <code>Registration</code> from <code>DataKit</code>.
     *
     * @param registration <code>Registration</code> to unregister.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    public int unregister(Registration registration) {
        if (registration == null) return MCerebrumStatus.INVALID_PARAMETER;
        if (registration.getStatus() != MCerebrumStatus.SUCCESS) return registration.getStatus();
        return mServiceConnection.unregister(registration.getDataSource().toDataSourceReadWrite());
    }

    /**
     * Finds the <code>DataSource</code>s specified in the <code>DataSourceRequest</code>.
     * The <code>DataSource</code>s are returned as a <code>DataSourceSet</code> object.
     *
     * @param dataSourceRequest Object defining the requested <code>DataSource</code>s.
     * @return The <code>DataSourceSet</code> of <code>DataSource</code>s matching the request.
     */
    public DataSourceSet find(DataSourceRequest dataSourceRequest) {
        if (dataSourceRequest == null)
            return new DataSourceSet(null, MCerebrumStatus.INVALID_PARAMETER);
        DataSourceReadWrite[] dataSources = new DataSourceReadWrite[0];
        int r = mServiceConnection.find(dataSourceRequest.toDataSourceReadWrite(), dataSources);
        return new DataSourceSet(dataSources, r);
    }

    /**
     * Inserts data into the <code>DataKit</code> system.
     *
     * @param registration <code>Registration</code> to insert data into.
     * @param data         Array of <code>Data</code> to add.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    public int insert(Registration registration, Data[] data) {
        if (registration == null) return MCerebrumStatus.INVALID_PARAMETER;
        return mServiceConnection.insert(registration.getDataSource(), data);
    }

    /**
     * Returns whether <code>DataKit</code> is connected or not.
     *
     * @return Whether <code>DataKit</code> is connected or not.
     */
    public boolean isConnected() {
        return mServiceConnection.isConnected();
    }

    /**
     * Queries the database for a <code>DataSet</code> of the "last n points".
     *
     * @param dataSource <code>DataSource</code> to query data from.
     * @param lastNPoint Number of data points to query for.
     * @return The resulting <code>DataSet</code>.
     */
    public DataSet query(DataSource dataSource, int lastNPoint) {
        DataSet dataSet = new DataSet();
        int r = mServiceConnection.queryByNumber(dataSource.toDataSourceReadWrite(), lastNPoint, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    /**
     * Queries the database for a <code>DataSet</code> of all data points between two timestamps.
     *
     * @param dataSource     <code>DataSource</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @return The resulting <code>DataSet</code>.
     */
    public DataSet query(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet = new DataSet();
        int r = mServiceConnection.queryByTime(dataSource.toDataSourceReadWrite(), startTimestamp, endTimestamp, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    /**
     * Queries the database for a summary of the data points between two timestamps.
     *
     * @param dataSource     <code>DataSource</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @return The resulting <code>DataSet</code>.
     */
    public DataSet querySummary(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet = new DataSet();
        int r = mServiceConnection.querySummary(dataSource.toDataSourceReadWrite(), startTimestamp, endTimestamp, dataSet);
        dataSet.setStatus(r);
        return dataSet;
    }

    /**
     * Queries the database and returns the number of entries between two timestamps.
     *
     * @param dataSource     <code>DataSource</code> to query data from.
     * @param startTimestamp Starting timestamp for the window.
     * @param endTimestamp   Ending timestamp for the window.
     * @return The number of entries between two timestamps.
     */
    public int queryCount(DataSource dataSource, long startTimestamp, long endTimestamp) {
        DataSet dataSet = new DataSet();
        int r = mServiceConnection.queryCount(dataSource.toDataSourceReadWrite(), startTimestamp, endTimestamp, dataSet);
        if (r != MCerebrumStatus.SUCCESS) return -1;
        return dataSet.getActualDataSize();
    }

    /**
     * Subscribes the given <code>DataCallback</code> to the specified <code>DataSource</code> input stream.
     * This provides a way to see what data is being sent to <code>DataKit</code>.
     *
     * @param dataSource <code>DataSource</code> to subscribe to.
     * @param callback   Callback for data.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    public int subscribe(DataSource dataSource, DataCallback callback) {
        return mServiceConnection.subscribe(dataSource.toDataSourceReadWrite(), callback);
    }

    /**
     * Unsubscribes the <code>DataCallback</code> from the specified <code>DataSource</code> input stream.
     *
     * @param dataSource <code>DataSource</code> to unsubscribe from.
     * @param callback   Callback for the data.
     * @return The <code>MCerebrumStatus</code> of the operation.
     */
    public int unsubscribe(DataSource dataSource, DataCallback callback) {
        return mServiceConnection.unsubscribe(dataSource.toDataSourceReadWrite(), callback);
    }
}
