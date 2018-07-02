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

package org.md2k.mcerebrum.api.datakitapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Creates <code>DataSource</code> objects containing information about the platform and application
 * the data was collected from.
 */
public class DataSourceReadWrite extends DataSource implements Parcelable{
    DataSourceReadWrite() {
    }


    protected DataSourceReadWrite(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public static final Creator<DataSourceReadWrite> CREATOR = new Creator<DataSourceReadWrite>() {
        @Override
        public DataSourceReadWrite createFromParcel(Parcel in) {
            return new DataSourceReadWrite(in);
        }

        @Override
        public DataSourceReadWrite[] newArray(int size) {
            return new DataSourceReadWrite[size];
        }
    };

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
    public void setPlatformAppType(String platformAppType) {
        this.platformAppType = platformAppType;
    }
    public void setPlatformAppId(String platformAppId) {
        this.platformAppId = platformAppId;
    }
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setDataSourceMetadata(DataSourceMetaData dataSourceMetadata) {
        this.dataSourceMetaData = dataSourceMetadata;
    }
    public void setPlatformMetadata(PlatformMetaData platformMetadata) {
        this.platformMetaData = platformMetadata;
    }
    public void setPlatformAppMetadata(PlatformAppMetaData platformAppMetadata) {
        this.platformAppMetaData = platformAppMetadata;
    }
    public void setApplicationMetadata(ApplicationMetaData applicationMetadata) {
        this.applicationMetaData = applicationMetadata;
    }
    public void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        this.dataDescriptors = dataDescriptors;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

