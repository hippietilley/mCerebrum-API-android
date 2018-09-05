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

package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Builder class for <code>DataSource</code> objects
 */
public class PlatformMetaData implements Parcelable {
    /** Title of the platform */
    private static final String TITLE = "TITLE";
    /** Summary of the platform */
    private static final String SUMMARY = "SUMMARY";
    /** Description of the platform */
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String OPERATING_SYSTEM = "OPERATING_SYSTEM";
    private static final String MANUFACTURER = "MANUFACTURER";
    private static final String MODEL = "MODEL";
    private static final String VERSION_FIRMWARE = "VERSION_FIRMWARE";
    private static final String VERSION_HARDWARE = "VERSION_HARDWARE";
    private static final String DEVICE_ID = "DEVICE_ID";
    private HashMap<String, String> metaData;

    public String getTitle() {
        return metaData.get(TITLE);
    }

    public String getSummary() {
        return metaData.get(SUMMARY);
    }

    public String getDescription() {
        return metaData.get(DESCRIPTION);
    }

    public String getOperationSystem() {
        return metaData.get(OPERATING_SYSTEM);
    }

    public String getManufacturer() {
        return metaData.get(MANUFACTURER);
    }

    public String getModel() {
        return metaData.get(MODEL);
    }

    public String getVersionFirmware() {
        return metaData.get(VERSION_FIRMWARE);
    }

    public String getVersionHardware() {
        return metaData.get(VERSION_HARDWARE);
    }

    public String getDeviceId() {
        return metaData.get(DEVICE_ID);
    }

    public String getMetaData(String key) {
        return metaData.get(key);
    }

    private PlatformMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }


    public static Builder builder() {
        return new Builder();
    }
    public static Builder builder(PlatformMetaData platformMetaData){
        if(platformMetaData==null)
            return new Builder();
        else return new Builder(platformMetaData.metaData);
    }

    public static class Builder {
        private HashMap<String, String> metaData;

        public Builder() {
            metaData = new HashMap<>();
        }
        Builder(HashMap<String, String> metaData) {
            this.metaData = new HashMap<>();
            this.metaData.putAll(metaData);
        }

        public Builder setTitle(String title) {
            metaData.put(TITLE,title);
            return this;
        }

        public Builder setSummary(String summary) {
            metaData.put(SUMMARY,summary);
            return this;
        }

        public Builder setDescription(String description) {
            metaData.put(DESCRIPTION, description);
            return this;
        }

        public Builder setOperationSystem(String operationSystem) {
            metaData.put(OPERATING_SYSTEM,operationSystem);
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            metaData.put(MANUFACTURER,manufacturer);
            return this;
        }

        public Builder setModel(String model) {
            metaData.put(MODEL,model);
            return this;
        }

        public Builder setVersionFirmware(String versionFirmware) {
            metaData.put(VERSION_FIRMWARE,versionFirmware);
            return this;
        }

        public Builder setVersionHardware(String versionHardware) {
            metaData.put(VERSION_HARDWARE,versionHardware);
            return this;
        }

        public Builder setDeviceId(String deviceId) {
            metaData.put(DEVICE_ID,deviceId);
            return this;
        }

        public Builder setMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }
        public Builder setMetaData(HashMap<String, String> metaData){
            for (HashMap.Entry<String, String> entry : metaData.entrySet())
                this.metaData.put(entry.getKey(), entry.getValue());
            return this;
        }

        public PlatformMetaData build() {
            return new PlatformMetaData(this);
        }
    }
    private PlatformMetaData(Parcel in) {
        int size = in.readInt();
        if (size == -1) metaData = null;
        else {
            metaData = new HashMap<>();
            for (int j = 0; j < size; j++) {
                metaData.put(in.readString(), in.readString());
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
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (metaData == null)
            parcel.writeInt(-1);
        else {
            int size = metaData.size();
            parcel.writeInt(size);
            for (HashMap.Entry<String, String> entry : metaData.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }

    }



}
