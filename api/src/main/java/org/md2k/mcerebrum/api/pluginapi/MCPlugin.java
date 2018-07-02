package org.md2k.mcerebrum.api.pluginapi;

import java.lang.reflect.Method;
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
public abstract class MCPlugin {
    //User Interface
    public MCPluginParam[] getUserInterfaces() {
        return new MCPluginParam[0];
    }

    public boolean openUserInterface(MCPluginParam mcPluginParam) {
        return true;
    }

    public boolean startBackgroundProcess() {
        return true;
    }

    public boolean stopBackgroundProcess() {
        return true;
    }

    public boolean isBackgroundProcessRunning() {
        return false;
    }

    public ConfigState getConfigurationState() {
        return ConfigState.NOT_CONFIGURABLE;
    }

    public MCPluginParam[] getConfigurationOptions() {
        return new MCPluginParam[0];
    }

    public boolean configure(MCPluginParam mcPluginParam) {
        return true;
    }

    //Clear data & internal state
    public boolean clear() {
        return true;
    }

    //Factory reset (data, internal state & configuration)
    public boolean reset() {
        return true;
    }

    public boolean getUserPermissions() {
        return true;
    }

    public boolean hasUserPermissions() {
        return true;
    }

    public MCPluginMessage registerNotificationCallback() {
        return null;
    }
    public String[] getDeclaredFunctions(){
        ArrayList<String> list = new ArrayList<>();
        Class c = this.getClass();
        Method[] methods= c.getMethods();
        for (Method method : methods) {
            try {
                Class declaredClass = c.getMethod(method.getName()).getDeclaringClass();
                if (c.getName().equals(declaredClass.getName()))
                    list.add(method.getName());
            } catch (Exception e){

            }
        }
        return list.toArray(new String[0]);
    }
/*
    void update(){
        Class c = this.getClass();
        try {
            Class cc = c.getMethod("init").getDeclaringClass();
            if(c.getName().equals(cc.getName()))
                Log.d("abc","-----------------init---------------equal");
            else
                Log.d("abc","-xxxxxxx---------init-xxxxxxxx------not equal");
            Log.d("abc","c="+c.getName()+" cc="+cc.getName()+" "+cc.getCanonicalName()+" "+cc.getSimpleName());
             cc = c.getMethod("settings").getDeclaringClass();
            if(c.getName().equals(cc.getName()))
                Log.d("abc","-----------------settings---------------equal");
            else
                Log.d("abc","-xxxxxxx---------settings----xxxxxxxx------not equal");

            Log.d("abc","c="+c.getName()+" cc="+cc.getName());
        } catch (NoSuchMethodException e) {
            Log.d("abc","error e="+e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
*/

}
