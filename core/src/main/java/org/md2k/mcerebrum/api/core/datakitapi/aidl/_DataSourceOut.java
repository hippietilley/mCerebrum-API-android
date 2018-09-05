package org.md2k.mcerebrum.api.core.datakitapi.aidl;

import android.os.Parcel;
import android.os.Parcelable;

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
public class _DataSourceOut implements Parcelable {
    private int dsId = -1;
    private long creationTime = -1;
    private long lastActiveTime = -1;
    private _DataSourceIn dataSourceIn;

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<_DataSourceOut> CREATOR = new Creator<_DataSourceOut>() {
        @Override
        public _DataSourceOut createFromParcel(Parcel in) {
            return new _DataSourceOut(in);
        }

        @Override
        public _DataSourceOut[] newArray(int size) {
            return new _DataSourceOut[size];
        }
    };

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public _DataSourceIn getDataSourceIn() {
        return dataSourceIn;
    }

    public void setDataSourceIn(_DataSourceIn dataSourceIn) {
        this.dataSourceIn = dataSourceIn;
    }

    public _DataSourceOut() {

    }

    public void readFromParcel(Parcel in) {
        dsId = in.readInt();
        creationTime = in.readLong();
        lastActiveTime = in.readLong();
        dataSourceIn = in.readParcelable(_DataSourceIn.class.getClassLoader());
    }

    _DataSourceOut(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dsId);
        parcel.writeLong(creationTime);
        parcel.writeLong(lastActiveTime);
        parcel.writeParcelable(dataSourceIn, i);
    }
}
