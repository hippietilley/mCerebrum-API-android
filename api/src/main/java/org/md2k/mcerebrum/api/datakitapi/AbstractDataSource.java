package org.md2k.mcerebrum.api.datakitapi;

import android.os.Parcel;
import android.os.Parcelable;

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
public abstract class AbstractDataSource implements Parcelable {
    int dsId = -1;
    long creationTime = -1;
    long modifiedTime = -1;
    int status;

    String dataSourceType = null;
    String dataSourceId = null;
    String platformType = null;
    String platformId = null;
    String platformAppType = null;
    String platformAppId = null;
    String applicationType = null;
    String applicationId = null;

    DataSourceMetaData dataSourceMetaData = null;
    PlatformMetaData platformMetaData = null;
    PlatformAppMetaData platformAppMetaData = null;
    ApplicationMetaData applicationMetaData = null;

    ArrayList<DataDescriptor> dataDescriptors = null;
    String dataType = null;
    String dataRate = null;
    String appId = null;

    AbstractDataSource() {

    }

    void readFromParcel(Parcel in) {
        dsId = in.readInt();
        creationTime = in.readLong();
        modifiedTime = in.readLong();
        status = in.readInt();
        dataSourceType = in.readString();
        dataSourceId = in.readString();
        platformType = in.readString();
        platformId = in.readString();
        platformAppType = in.readString();
        platformAppId = in.readString();
        applicationType = in.readString();
        applicationId = in.readString();
        dataSourceMetaData = in.readParcelable(DataSourceMetaData.class.getClassLoader());
        platformMetaData = in.readParcelable(PlatformMetaData.class.getClassLoader());
        platformAppMetaData = in.readParcelable(PlatformAppMetaData.class.getClassLoader());
        applicationMetaData = in.readParcelable(ApplicationMetaData.class.getClassLoader());
        dataDescriptors = in.createTypedArrayList(DataDescriptor.CREATOR);
        dataType = in.readString();
        dataRate = in.readString();
        appId = in.readString();
    }

    AbstractDataSource(Parcel in) {
        readFromParcel(in);
    }

/*
    public static final Creator<AbstractDataSource> CREATOR = new Creator<AbstractDataSource>() {
        @Override
        public AbstractDataSource createFromParcel(Parcel in) {
            return new AbstractDataSource(in);
        }

        @Override
        public AbstractDataSource[] newArray(int size) {
            return new AbstractDataSource[size];
        }
    };
*/

    DataSourceReadWrite toDataSourceReadWrite() {
        DataSourceReadWrite d = new DataSourceReadWrite();
        d.dsId = this.dsId;
        d.creationTime = this.creationTime;
        d.modifiedTime = this.modifiedTime;
        d.status = this.status;

        d.dataSourceType = this.dataSourceType;
        d.dataSourceId = this.dataSourceId;
        d.platformType = this.platformType;
        d.platformId = this.platformId;
        d.platformAppType = this.platformAppType;
        d.platformAppId = this.platformAppId;
        d.applicationType = this.applicationType;
        d.applicationId = this.applicationId;
        d.dataSourceMetaData = this.dataSourceMetaData;
        d.platformMetaData = this.platformMetaData;
        d.platformAppMetaData = this.platformAppMetaData;
        d.applicationMetaData = this.applicationMetaData;

        d.dataDescriptors = this.dataDescriptors;
        d.dataType = this.dataType;
        d.dataRate = this.dataRate;
        d.appId = this.appId;
        return d;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dsId);
        parcel.writeLong(creationTime);
        parcel.writeLong(modifiedTime);
        parcel.writeInt(status);
        parcel.writeString(dataSourceType);
        parcel.writeString(dataSourceId);
        parcel.writeString(platformType);
        parcel.writeString(platformId);
        parcel.writeString(platformAppType);
        parcel.writeString(platformAppId);
        parcel.writeString(applicationType);
        parcel.writeString(applicationId);
        parcel.writeParcelable(dataSourceMetaData, i);
        parcel.writeParcelable(platformMetaData, i);
        parcel.writeParcelable(platformAppMetaData, i);
        parcel.writeParcelable(applicationMetaData, i);
        parcel.writeTypedList(dataDescriptors);
        parcel.writeString(dataType);
        parcel.writeString(dataRate);
        parcel.writeString(appId);
    }
}
