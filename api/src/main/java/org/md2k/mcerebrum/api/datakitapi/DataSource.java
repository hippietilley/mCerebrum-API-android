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
import java.util.HashMap;

/**
 * Creates <code>DataSource</code> objects containing information about the platform and application
 * the data was collected from.
 */
public abstract class DataSource extends AbstractDataSource implements Parcelable{
    DataSource() {
    }
    DataSource(Parcel in){
        super(in);
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getPlatformAppType() {
        return platformAppType;
    }

    public String getPlatformAppId() {
        return platformAppId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public String getApplicationId() {
        return applicationId;
    }


    public DataSourceMetaData getDataSourceMetaData() {
        return dataSourceMetaData;
    }

    public PlatformMetaData getPlatformMetaData() {
        return platformMetaData;
    }

    public PlatformAppMetaData getPlatformAppMetaData() {
        return platformAppMetaData;
    }

    public ApplicationMetaData getApplicationMetaData() {
        return applicationMetaData;
    }


    public ArrayList<DataDescriptor> getDataDescriptors() {
        return dataDescriptors;
    }

    public String getDataType() {
        return dataType;
    }
    public String getDataRate(){
        return dataRate;
    }

    public String getAppId() {
        return appId;
    }

    public int getDsId() {
        return dsId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public int getStatus() {
        return status;
    }


}

