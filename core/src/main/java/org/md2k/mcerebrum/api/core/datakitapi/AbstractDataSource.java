package org.md2k.mcerebrum.api.core.datakitapi;

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
    private int dsId = -1;
    private long creationTime = -1;
    private long modifiedTime = -1;
    private int status;

    private String dataSourceType = null;
    private String dataSourceId = null;
    private String platformType = null;
    private String platformId = null;
    private String platformAppType = null;
    private String platformAppId = null;
    private String applicationType = null;
    private String applicationId = null;

    private DataSourceMetaData dataSourceMetaData = null;
    private PlatformMetaData platformMetaData = null;
    private PlatformAppMetaData platformAppMetaData = null;
    private ApplicationMetaData applicationMetaData = null;

    private ArrayList<DataDescriptor> dataDescriptors = null;
    private String dataType = null;
    private String dataRate = null;
    private String appId = null;

    AbstractDataSource() {

    }

    protected void readFromParcel(Parcel in) {
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

    protected int getDsId() {
        return dsId;
    }

    protected void setDsId(int dsId) {
        this.dsId = dsId;
    }

    protected long getCreationTime() {
        return creationTime;
    }

    protected void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    protected long getModifiedTime() {
        return modifiedTime;
    }

    protected void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    protected int getStatus() {
        return status;
    }

    protected void setStatus(int status) {
        this.status = status;
    }

    protected String getDataSourceType() {
        return dataSourceType;
    }

    protected void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    protected String getDataSourceId() {
        return dataSourceId;
    }

    protected void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    protected String getPlatformType() {
        return platformType;
    }

    protected void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    protected String getPlatformId() {
        return platformId;
    }

    protected void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    protected String getPlatformAppType() {
        return platformAppType;
    }

    protected void setPlatformAppType(String platformAppType) {
        this.platformAppType = platformAppType;
    }

    protected String getPlatformAppId() {
        return platformAppId;
    }

    protected void setPlatformAppId(String platformAppId) {
        this.platformAppId = platformAppId;
    }

    protected String getApplicationType() {
        return applicationType;
    }

    protected void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    protected String getApplicationId() {
        return applicationId;
    }

    protected void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    protected DataSourceMetaData getDataSourceMetaData() {
        return dataSourceMetaData;
    }

    protected void setDataSourceMetaData(DataSourceMetaData dataSourceMetaData) {
        this.dataSourceMetaData = dataSourceMetaData;
    }

    protected PlatformMetaData getPlatformMetaData() {
        return platformMetaData;
    }

    protected void setPlatformMetaData(PlatformMetaData platformMetaData) {
        this.platformMetaData = platformMetaData;
    }

    protected PlatformAppMetaData getPlatformAppMetaData() {
        return platformAppMetaData;
    }

    protected void setPlatformAppMetaData(PlatformAppMetaData platformAppMetaData) {
        this.platformAppMetaData = platformAppMetaData;
    }

    protected ApplicationMetaData getApplicationMetaData() {
        return applicationMetaData;
    }

    protected void setApplicationMetaData(ApplicationMetaData applicationMetaData) {
        this.applicationMetaData = applicationMetaData;
    }

    protected ArrayList<DataDescriptor> getDataDescriptors() {
        return dataDescriptors;
    }

    protected void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        this.dataDescriptors = dataDescriptors;
    }

    protected String getDataType() {
        return dataType;
    }

    protected void setDataType(String dataType) {
        this.dataType = dataType;
    }

    protected String getDataRate() {
        return dataRate;
    }

    protected void setDataRate(String dataRate) {
        this.dataRate = dataRate;
    }

    protected String getAppId() {
        return appId;
    }

    protected void setAppId(String appId) {
        this.appId = appId;
    }

    AbstractDataSource(Parcel in) {
        readFromParcel(in);
    }


    protected DataSourceReadWrite toDataSourceReadWrite() {
        DataSourceReadWrite d = new DataSourceReadWrite();
        d.setDsId(this.dsId);
        d.setCreationTime(this.creationTime);
        d.setModifiedTime(this.modifiedTime);
        d.setStatus(this.status);

        d.setDataSourceType(this.dataSourceType);
        d.setDataSourceId(this.dataSourceId);
        d.setPlatformType(this.platformType);
        d.setPlatformId(this.platformId);
        d.setPlatformAppType(this.platformAppType);
        d.setPlatformAppId(this.platformAppId);
        d.setApplicationType(this.applicationType);
        d.setApplicationId(this.applicationId);
        d.setDataSourceMetadata(this.dataSourceMetaData);
        d.setPlatformMetadata(this.platformMetaData);
        d.setPlatformAppMetadata(this.platformAppMetaData);
        d.setApplicationMetadata(this.applicationMetaData);

        d.setDataDescriptors(this.dataDescriptors);
        d.setDataType(this.dataType);
        d.setDataRate(this.dataRate);
        d.setAppId(this.appId);
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
