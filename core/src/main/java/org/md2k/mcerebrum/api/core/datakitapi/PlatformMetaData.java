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

import java.util.HashMap;

/**
 * Builder class for <code>DataSource</code> objects
 */
public class PlatformMetaData implements Parcelable{
    private String title;
    private String summary;
    private String description;
    private String operationSystem;
    private String manufacturer;
    private String model;
    private String versionFirmware;
    private String versionHardware;
    private String deviceId;
    private HashMap<String, String> custom = new HashMap<>();

    PlatformMetaData() {
    }

    private PlatformMetaData(Parcel in) {
        title = in.readString();
        summary = in.readString();
        description = in.readString();
        operationSystem = in.readString();
        manufacturer = in.readString();
        model = in.readString();
        versionFirmware = in.readString();
        versionHardware = in.readString();
        deviceId = in.readString();
        int size = in.readInt();
        if (size == -1) custom = null;
        else {
            custom = new HashMap<>();
            for (int j = 0; j < size; j++) {
                custom.put(in.readString(), in.readString());
            }
        }
    }

    public static final Creator<PlatformMetaData> CREATOR = new Creator<PlatformMetaData>() {
        @Override
        public PlatformMetaData createFromParcel(Parcel in) {
            return new PlatformMetaData(in);
        }

        @Override
        public PlatformMetaData[] newArray(int size) {
            return new PlatformMetaData[size];
        }
    };

    public String getTitle(){
        return title;
    }
    public String getSummary(){
        return summary;
    }
    public String getDescription(){
        return description;
    }
    public String getOperationSystem(){ return operationSystem;}
    public String getManufacturer(){return manufacturer;}

    public String getModel() {
        return model;
    }

    public String getVersionFirmware() {
        return versionFirmware;
    }

    public String getVersionHardware() {
        return versionHardware;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getValue(String key){
        if(custom==null) return null;
        return custom.get(key);
    }
    protected void setTitle(String title){
        this.title = title;
    }

    protected void setSummary(String summary) {
        this.summary = summary;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    protected void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    protected void setModel(String model) {
        this.model = model;
    }

    protected void setVersionFirmware(String versionFirmware) {
        this.versionFirmware = versionFirmware;
    }

    protected void setVersionHardware(String versionHardware) {
        this.versionHardware = versionHardware;
    }

    protected void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private PlatformMetaData(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        description = builder.description;
        operationSystem = builder.operationSystem;
        manufacturer = builder.manufacturer;
        model=builder.model;
        versionFirmware=builder.versionFirmware;
        versionHardware=builder.versionHardware;
        deviceId=builder.deviceId;
        custom= builder.custom;
    }


    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(summary);
        parcel.writeString(description);
        parcel.writeString(operationSystem);
        parcel.writeString(manufacturer);
        parcel.writeString(model);
        parcel.writeString(versionFirmware);
        parcel.writeString(versionHardware);
        parcel.writeString(deviceId);
        if (custom == null)
            parcel.writeInt(-1);
        else {
            int size = custom.size();
            parcel.writeInt(size);
            for (HashMap.Entry<String, String> entry : custom.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }

    }

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private String operationSystem;
        private String manufacturer;
        private String model;
        private String versionFirmware;
        private String versionHardware;
        private String deviceId;
        private HashMap<String, String> custom = new HashMap<>();

        Builder() {
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setOperationSystem(String operationSystem) {
            this.operationSystem = operationSystem;
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setVersionFirmware(String versionFirmware) {
            this.versionFirmware = versionFirmware;
            return this;
        }

        public Builder setVersionHardware(String versionHardware) {
            this.versionHardware = versionHardware;
            return this;
        }

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder setValue(String key, String value) {
            this.custom.put(key, value);
            return this;
        }
        public PlatformMetaData build() {
            return new PlatformMetaData(this);
        }
    }
}
