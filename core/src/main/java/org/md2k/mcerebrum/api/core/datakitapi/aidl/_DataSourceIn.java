package org.md2k.mcerebrum.api.core.datakitapi.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;

import java.util.ArrayList;
import java.util.HashMap;

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
public class _DataSourceIn implements Parcelable {
    private String dataSourceType = null;
    private String dataSourceId = null;
    private String platformType = null;
    private String platformId = null;
    private String platformAppType = null;
    private String platformAppId = null;
    private String applicationType = null;
    private String applicationId = null;

    private HashMap<String, String> dataSourceMetaData = null;
    private HashMap<String, String> platformMetaData = null;
    private HashMap<String, String> platformAppMetaData = null;
    private HashMap<String, String> applicationMetaData = null;

    private String dataType = null;
    private ArrayList<DataDescriptor> dataDescriptors = null;

    private int accessLevel =-1; //0: ALL, 1: with permission 5:
    private int priorityLevel =-1; //0: None, Very Low, Low, Medium, High, Very High

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<_DataSourceIn> CREATOR = new Creator<_DataSourceIn>() {
        @Override
        public _DataSourceIn createFromParcel(Parcel in) {
            return new _DataSourceIn(in);
        }

        @Override
        public _DataSourceIn[] newArray(int size) {
            return new _DataSourceIn[size];
        }
    };

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformAppType() {
        return platformAppType;
    }

    public void setPlatformAppType(String platformAppType) {
        this.platformAppType = platformAppType;
    }

    public String getPlatformAppId() {
        return platformAppId;
    }

    public void setPlatformAppId(String platformAppId) {
        this.platformAppId = platformAppId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public HashMap<String, String> getDataSourceMetaData() {
        return dataSourceMetaData;
    }

    public void setDataSourceMetaData(HashMap<String, String> dataSourceMetaData) {
        this.dataSourceMetaData = dataSourceMetaData;
    }

    public HashMap<String, String> getPlatformMetaData() {
        return platformMetaData;
    }

    public void setPlatformMetaData(HashMap<String, String> platformMetaData) {
        this.platformMetaData = platformMetaData;
    }

    public HashMap<String, String> getPlatformAppMetaData() {
        return platformAppMetaData;
    }

    public void setPlatformAppMetaData(HashMap<String, String> platformAppMetaData) {
        this.platformAppMetaData = platformAppMetaData;
    }

    public HashMap<String, String> getApplicationMetaData() {
        return applicationMetaData;
    }

    public void setApplicationMetaData(HashMap<String, String> applicationMetaData) {
        this.applicationMetaData = applicationMetaData;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public ArrayList<DataDescriptor> getDataDescriptors() {
        return dataDescriptors;
    }

    public void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        this.dataDescriptors = dataDescriptors;
    }


    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public _DataSourceIn() {

    }

    public void readFromParcel(Parcel in) {
        dataSourceType = in.readString();
        dataSourceId = in.readString();
        platformType = in.readString();
        platformId = in.readString();
        platformAppType = in.readString();
        platformAppId = in.readString();
        applicationType = in.readString();
        applicationId = in.readString();
        dataType = in.readString();
        accessLevel = in.readInt();
        priorityLevel =in.readInt();

        dataSourceMetaData = readMetaDataFromParcel(in);
        platformMetaData = readMetaDataFromParcel(in);
        platformAppMetaData = readMetaDataFromParcel(in);
        applicationMetaData = readMetaDataFromParcel(in);
        dataDescriptors = in.createTypedArrayList(DataDescriptor.CREATOR);
    }
    private HashMap[] readMetaDataArrayFromParcel(Parcel in){
        HashMap[] hashMaps;
        int size = in.readInt();
        hashMaps=new HashMap[size];
        for(int i = 0;i<size;i++)
            hashMaps[i]=readMetaDataFromParcel(in);
        return hashMaps;
    }
    private HashMap<String, String> readMetaDataFromParcel(Parcel in){
        HashMap<String, String> metaData=new HashMap<>();
        int size = in.readInt();
        for (int j = 0; j < size; j++) {
            metaData.put(in.readString(), in.readString());
        }
        return metaData;
    }


    _DataSourceIn(Parcel in) {
        readFromParcel(in);
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dataSourceType);
        parcel.writeString(dataSourceId);
        parcel.writeString(platformType);
        parcel.writeString(platformId);
        parcel.writeString(platformAppType);
        parcel.writeString(platformAppId);
        parcel.writeString(applicationType);
        parcel.writeString(applicationId);

        parcel.writeString(dataType);
        parcel.writeInt(accessLevel);
        parcel.writeInt(priorityLevel);

        writeToParcelMetaData(parcel, dataSourceMetaData);
        writeToParcelMetaData(parcel, platformMetaData);
        writeToParcelMetaData(parcel, platformAppMetaData);
        writeToParcelMetaData(parcel, applicationMetaData);
        parcel.writeTypedList(dataDescriptors);
    }
    private void writeToParcelMetaData(Parcel parcel, HashMap<String, String> metaData){
        int size = metaData.size();
        parcel.writeInt(size);
        for (HashMap.Entry<String, String> entry : metaData.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
    }

    public String toUuid() {
        String id ="";
        if(dataSourceType!=null) id+=dataSourceType;id+="-";
        if(dataSourceId!=null) id+=dataSourceId;id+="-";
        if(platformType!=null) id+=platformType;id+="-";
        if(platformId!=null) id+=platformId;id+="-";
        if(platformAppType!=null) id+=platformAppType;id+="-";
        if(platformAppId!=null) id+=platformAppId;id+="-";
        if(applicationType!=null) id+=applicationType;id+="-";
        if(applicationId!=null) id+=applicationId;
        return id;
    }
    public boolean equals(_DataSourceIn dataSource){
        boolean e;
        Gson gson = new Gson();
        e = gson.toJson(this).equals(gson.toJson(dataSource));
        return e;
    }
    @Override
    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
