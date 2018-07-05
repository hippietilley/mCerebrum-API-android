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
public abstract class DataSource extends AbstractDataSource implements Parcelable{
    DataSource() {
    }
    DataSource(Parcel in){
        super(in);
    }

    public String getDataSourceType() {
        return super.getDataSourceType();
    }

    public String getDataSourceId() {
        return super.getDataSourceId();
    }

    public String getPlatformType() {
        return super.getPlatformType();
    }

    public String getPlatformId() {
        return super.getPlatformId();
    }

    public String getPlatformAppType() {
        return super.getPlatformAppType();
    }

    public String getPlatformAppId() {
        return super.getPlatformAppId();
    }

    public String getApplicationType() {
        return super.getApplicationType();
    }

    public String getApplicationId() {
        return super.getApplicationId();
    }


    public DataSourceMetaData getDataSourceMetaData() {
        return super.getDataSourceMetaData();
    }

    public PlatformMetaData getPlatformMetaData() {
        return super.getPlatformMetaData();
    }

    public PlatformAppMetaData getPlatformAppMetaData() {
        return super.getPlatformAppMetaData();
    }

    public ApplicationMetaData getApplicationMetaData() {
        return super.getApplicationMetaData();
    }


    public ArrayList<DataDescriptor> getDataDescriptors() {
        return super.getDataDescriptors();
    }

    public String getDataType() {
        return super.getDataType();
    }
    public String getDataRate(){
        return super.getDataRate();
    }

    public String getAppId() {
        return super.getAppId();
    }

    public int getDsId() {
        return super.getDsId();
    }

    public long getCreationTime() {
        return super.getCreationTime();
    }

    public long getModifiedTime() {
        return super.getModifiedTime();
    }

    public int getStatus() {
        return super.getStatus();
    }


}

