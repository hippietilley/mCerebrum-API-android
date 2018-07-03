package org.md2k.mcerebrum.api;

import android.annotation.SuppressLint;
import android.content.Context;

import org.md2k.mcerebrum.api.datakitapi.DataKitAPI;
import org.md2k.mcerebrum.api.datakitapi.DataSource;
import org.md2k.mcerebrum.api.datakitapi.DataSourceCreator;
import org.md2k.mcerebrum.api.datakitapi.DataSourceRequest;
import org.md2k.mcerebrum.api.datakitapi.DataSourceSet;
import org.md2k.mcerebrum.api.datakitapi.Registration;
import org.md2k.mcerebrum.api.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.datakitapi.callback.DataCallback;
import org.md2k.mcerebrum.api.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.datakitapi.status.MCerebrumStatus;
import org.md2k.mcerebrum.api.pluginapi.MCPlugin;

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
public final class mCerebrumAPI {
    @SuppressLint("StaticFieldLeak")
    private static mCerebrumAPI instance = null;
    private MCPlugin plugin;
    private Context context;
    private DataKitAPI dataKitAPI;
    public static void init(Context context) {
        init(context, null);
    }

    public static void init(Context context, MCPlugin mcPlugin) {
        if (context == null) return;
        if (instance == null) {
            instance = new mCerebrumAPI(context.getApplicationContext());
        }
        instance.plugin = mcPlugin;
    }

    public static MCPlugin getPlugin() {
        if(instance!=null)
        return instance.plugin;
        else return null;
    }

    private mCerebrumAPI(Context context) {
        this.context = context;
        dataKitAPI = new DataKitAPI(instance);
    }
    public static Context getContext() {
        if (instance == null) return null;
        return instance.context;
    }
    public static int connect(ConnectionCallback connectionCallback) {
        if(connectionCallback==null) return MCerebrumStatus.INVALID_PARAMETER;
        if (instance == null) {
            connectionCallback.onError(new MCerebrumException(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED));
            return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        }
        else return instance.dataKitAPI.connect(connectionCallback);
    }

    public static int disconnect(ConnectionCallback connectionCallback){
        if(connectionCallback==null) return MCerebrumStatus.INVALID_PARAMETER;
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return instance.dataKitAPI.disconnect(connectionCallback);
    }
    public static Registration register(DataSourceCreator dataSourceCreator){
        if(instance==null) return null;
        return instance.dataKitAPI.register(dataSourceCreator);
    }

    public static int unregister(Registration registration){
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return instance.dataKitAPI.unregister(registration);


    }
    public static DataSourceSet find(DataSourceRequest dataSourceRequest){
        if(instance==null) return null;
        return instance.dataKitAPI.find(dataSourceRequest);
    }
    public static int insert(Registration registration, Data[] data){
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return instance.dataKitAPI.insert(registration, data);
    }
    public static int insert(Registration registration, Data data){
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return insert(registration, new Data[]{data});
    }

    public static DataSet query(DataSource dataSource, int lastNPoint){
        if(instance==null) return null;
        return instance.dataKitAPI.query(dataSource, lastNPoint);

    }
    public static DataSet query(DataSource dataSource, long startTimestamp, long endTimestamp){
        if(instance==null) return null;
        return instance.dataKitAPI.query(dataSource, startTimestamp, endTimestamp);
    }
    public static DataSet querySummary(DataSource dataSource, long startTimestamp, long endTimestamp){
        if(instance==null) return null;
        return instance.dataKitAPI.querySummary(dataSource, startTimestamp, endTimestamp);

    }
    public static int queryCount(DataSource dataSource, long startTimestamp, long endTimestamp){
        if(instance==null) return -1;
        return instance.dataKitAPI.queryCount(dataSource, startTimestamp, endTimestamp);

    }
    public static int subscribe(DataSource dataSource, DataCallback callback){
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return instance.dataKitAPI.subscribe(dataSource, callback);
    }
    public static int unsubscribe(DataSource dataSource, DataCallback callback){
        if(instance==null) return MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED;
        return instance.dataKitAPI.unsubscribe(dataSource, callback);
    }
}
