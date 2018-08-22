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
        super.setDsId(dsId);
    }
    public void setCreationTime(long creationTime) {
        super.setCreationTime(creationTime);
    }
    public void setModifiedTime(long modifiedTime) {
        super.setModifiedTime(modifiedTime);
    }
    public void setStatus(int status) {
        super.setStatus(status);
    }

    public void setDataSourceType(String dataSourceType) {
        super.setDataSourceType(dataSourceType);
    }
    public void setDataSourceId(String dataSourceId) {
        super.setDataSourceId(dataSourceId);
    }
    public void setPlatformType(String platformType) {
        super.setPlatformType(platformType);
    }
    public void setPlatformId(String platformId) {
        super.setPlatformId(platformId);
    }
    public void setPlatformAppType(String platformAppType) {
        super.setPlatformAppType(platformAppType);
    }
    public void setPlatformAppId(String platformAppId) {
        super.setPlatformAppId(platformAppId);
    }
    public void setApplicationType(String applicationType) {
        super.setApplicationType(applicationType);
    }
    public void setApplicationId(String applicationId) {
        super.setApplicationId(applicationId);
    }

    public void setDataSourceMetadata(DataSourceMetaData dataSourceMetadata) {
        super.setDataSourceMetaData(dataSourceMetadata);
    }
    public void setPlatformMetadata(PlatformMetaData platformMetadata) {
        super.setPlatformMetaData(platformMetadata);
    }
    public void setPlatformAppMetadata(PlatformAppMetaData platformAppMetadata) {
        super.setPlatformAppMetaData(platformAppMetadata);
    }
    public void setApplicationMetadata(ApplicationMetaData applicationMetadata) {
        super.setApplicationMetaData(applicationMetadata);
    }
    public void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        super.setDataDescriptors(dataDescriptors);
    }
    public void setDataType(String dataType) {
        super.setDataType(dataType);
    }
    public void setAppId(String appId) {
        super.setAppId(appId);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

