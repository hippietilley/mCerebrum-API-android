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
public class _DataSourceResults implements Parcelable{
    private _DataSourceOut[] dataSources;
    private int status;

    public _DataSourceResults(_DataSourceOut[] dataSources, int status) {
        this.dataSources = dataSources;
        this.status = status;
    }

    protected _DataSourceResults(Parcel in) {
        dataSources = in.createTypedArray(_DataSourceOut.CREATOR);
        status = in.readInt();
    }

    public static final Creator<_DataSourceResults> CREATOR = new Creator<_DataSourceResults>() {
        @Override
        public _DataSourceResults createFromParcel(Parcel in) {
            return new _DataSourceResults(in);
        }

        @Override
        public _DataSourceResults[] newArray(int size) {
            return new _DataSourceResults[size];
        }
    };

    public int getStatus() {
        return status;
    }

    public _DataSourceOut[] getDataSources() {
        return dataSources;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(dataSources, i);
        parcel.writeInt(status);
    }
}
