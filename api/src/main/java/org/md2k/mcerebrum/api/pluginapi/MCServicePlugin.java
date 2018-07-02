package org.md2k.mcerebrum.api.pluginapi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
public class MCServicePlugin extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IPluginRemoteService.Stub mBinder = new IPluginRemoteService.Stub() {

        @Override
        public boolean clear() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().clear();
        }
        @Override
        public boolean reset() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().reset();
        }
        @Override
        public MCPluginParam[] getUserInterfaces() {
            if(mCerebrumAPI.getPlugin()==null) return new MCPluginParam[0];
            return mCerebrumAPI.getPlugin().getUserInterfaces();
        }

        @Override
        public boolean openUserInterface(MCPluginParam mcPluginParam) {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().openUserInterface(mcPluginParam);
        }
        @Override
        public boolean configure(MCPluginParam mcPluginParam) {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().configure(mcPluginParam);
        }

        @Override
        public MCPluginParam[] getConfigurationOptions() {
            if(mCerebrumAPI.getPlugin()==null) return new MCPluginParam[0];
            return mCerebrumAPI.getPlugin().getConfigurationOptions();
        }

        @Override
        public int getConfigurationState() {
            if(mCerebrumAPI.getPlugin()==null) return ConfigState.NOT_CONFIGURABLE.getValue();
            return mCerebrumAPI.getPlugin().getConfigurationState().getValue();
        }

        @Override
        public boolean startBackgroundProcess() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().startBackgroundProcess();
        }

        @Override
        public boolean stopBackgroundProcess() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().stopBackgroundProcess();
        }

        @Override
        public boolean getUserPermissions() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().getUserPermissions();
        }

        @Override
        public boolean hasUserPermissions() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().hasUserPermissions();
        }

        @Override
        public boolean isBackgroundProcessRunning() {
            return mCerebrumAPI.getPlugin() != null && mCerebrumAPI.getPlugin().isBackgroundProcessRunning();
        }
        @Override
        public String[] getDeclaredFunctions() {
            if(mCerebrumAPI.getPlugin()==null) return new String[0];
            return mCerebrumAPI.getPlugin().getDeclaredFunctions();
        }
    };
}
