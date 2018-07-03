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

    int getDsId() {
        return dsId;
    }

    void setDsId(int dsId) {
        this.dsId = dsId;
    }

    long getCreationTime() {
        return creationTime;
    }

    void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    long getModifiedTime() {
        return modifiedTime;
    }

    void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    int getStatus() {
        return status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    String getDataSourceType() {
        return dataSourceType;
    }

    void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    String getDataSourceId() {
        return dataSourceId;
    }

    void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    String getPlatformType() {
        return platformType;
    }

    void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    String getPlatformId() {
        return platformId;
    }

    void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    String getPlatformAppType() {
        return platformAppType;
    }

    void setPlatformAppType(String platformAppType) {
        this.platformAppType = platformAppType;
    }

    String getPlatformAppId() {
        return platformAppId;
    }

    void setPlatformAppId(String platformAppId) {
        this.platformAppId = platformAppId;
    }

    String getApplicationType() {
        return applicationType;
    }

    void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    String getApplicationId() {
        return applicationId;
    }

    void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    DataSourceMetaData getDataSourceMetaData() {
        return dataSourceMetaData;
    }

    void setDataSourceMetaData(DataSourceMetaData dataSourceMetaData) {
        this.dataSourceMetaData = dataSourceMetaData;
    }

    PlatformMetaData getPlatformMetaData() {
        return platformMetaData;
    }

    void setPlatformMetaData(PlatformMetaData platformMetaData) {
        this.platformMetaData = platformMetaData;
    }

    PlatformAppMetaData getPlatformAppMetaData() {
        return platformAppMetaData;
    }

    void setPlatformAppMetaData(PlatformAppMetaData platformAppMetaData) {
        this.platformAppMetaData = platformAppMetaData;
    }

    ApplicationMetaData getApplicationMetaData() {
        return applicationMetaData;
    }

    void setApplicationMetaData(ApplicationMetaData applicationMetaData) {
        this.applicationMetaData = applicationMetaData;
    }

    ArrayList<DataDescriptor> getDataDescriptors() {
        return dataDescriptors;
    }

    void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        this.dataDescriptors = dataDescriptors;
    }

    String getDataType() {
        return dataType;
    }

    void setDataType(String dataType) {
        this.dataType = dataType;
    }

    String getDataRate() {
        return dataRate;
    }

    void setDataRate(String dataRate) {
        this.dataRate = dataRate;
    }

    String getAppId() {
        return appId;
    }

    void setAppId(String appId) {
        this.appId = appId;
    }

    AbstractDataSource(Parcel in) {
        readFromParcel(in);
    }


    DataSourceReadWrite toDataSourceReadWrite() {
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
