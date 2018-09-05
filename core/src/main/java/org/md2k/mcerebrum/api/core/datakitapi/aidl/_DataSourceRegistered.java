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

package org.md2k.mcerebrum.api.core.datakitapi.aidl;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Container object class for data sources.
 */
public class _DataSourceRegistered implements Parcelable{
    private _DataSourceOut _dataSourceOut;
    private int status;

    public _DataSourceRegistered(_DataSourceOut dataSource, int status) {
        this._dataSourceOut = dataSource;
        this.status = status;
    }


    protected _DataSourceRegistered(Parcel in) {
        _dataSourceOut = in.readParcelable(_DataSourceOut.class.getClassLoader());
        status = in.readInt();
    }

    public static final Creator<_DataSourceRegistered> CREATOR = new Creator<_DataSourceRegistered>() {
        @Override
        public _DataSourceRegistered createFromParcel(Parcel in) {
            return new _DataSourceRegistered(in);
        }

        @Override
        public _DataSourceRegistered[] newArray(int size) {
            return new _DataSourceRegistered[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public _DataSourceOut getDataSource() {
        return _dataSourceOut;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(_dataSourceOut, i);
        parcel.writeInt(status);
    }
}
