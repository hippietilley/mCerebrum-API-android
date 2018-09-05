package org.md2k.mcerebrum.api.core.pluginapi;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

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
public class MCPluginParam implements Parcelable {
    private String title;
    private String id;
    private JSONObject params;

    public MCPluginParam(String title, String id, JSONObject params) {
        if (title == null) this.title = "";
        else
            this.title = title;
        if (id == null) this.id = "";
        else
            this.id = id;
        if (params == null)
            this.params = new JSONObject();
        else
            this.params = params;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public JSONObject getParams() {
        return params;
    }

    private MCPluginParam(Parcel in) {
        title = in.readString();
        id = in.readString();
        try {
            params = new JSONObject(in.readString());
        } catch (JSONException e) {
            params = new JSONObject();
        }
    }

    public static final Creator<MCPluginParam> CREATOR = new Creator<MCPluginParam>() {
        @Override
        public MCPluginParam createFromParcel(Parcel in) {
            return new MCPluginParam(in);
        }

        @Override
        public MCPluginParam[] newArray(int size) {
            return new MCPluginParam[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(id);
        parcel.writeString(params.toString());
    }

    @Override
    public boolean equals (Object toCompare) {
        if (toCompare instanceof MCPluginParam) {
            if (!(this.title.equals(((MCPluginParam) toCompare).title)))
                return false;
            if (!(this.id.equals(((MCPluginParam) toCompare).id)))
                return false;
            // TODO: fix this equality
            return (this.params.equals(((MCPluginParam) toCompare).params));
        } else
            return false;
    }
}
