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

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;

import org.md2k.mcerebrum.api.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.MCerebrumAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DataSourceCreator extends DataSource implements Parcelable {
    private DataSourceCreator() {
    }

    private DataSourceCreator(Builder dataSourceBuilder) {
        super.setDataSourceType(dataSourceBuilder.dataSourceType);
        super.setDataSourceId(dataSourceBuilder.dataSourceId);
        super.setPlatformType(dataSourceBuilder.platformType);
        super.setPlatformId(dataSourceBuilder.platformId);
        super.setPlatformAppType(dataSourceBuilder.platformAppType);
        super.setPlatformAppId(dataSourceBuilder.platformAppId);
        super.setApplicationType(dataSourceBuilder.applicationType);
        super.setApplicationId(dataSourceBuilder.applicationId);
        super.setDataSourceMetaData(dataSourceBuilder.dataSourceMetaData);
        super.setPlatformMetaData(dataSourceBuilder.platformMetaData);
        super.setPlatformAppMetaData(dataSourceBuilder.platformAppMetaData);
        super.setApplicationMetaData(dataSourceBuilder.applicationMetaData);
        super.setDataDescriptors(dataSourceBuilder.dataDescriptors);
        super.setDataType(dataSourceBuilder.dataType);
        super.setDataRate(dataSourceBuilder.dataRate);
        try {
            Context context = MCerebrumAPI.getContext();
            if (context != null) {
                super.setAppId(context.getPackageName());
                String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                int versionNumber = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                if (super.getApplicationMetaData() == null)
                    super.setApplicationMetaData(ApplicationMetaData.builder().setVersionNumber(versionNumber).setVersionName(versionName).build());
                else {
                    super.getApplicationMetaData().setVersionName(versionName);
                    super.getApplicationMetaData().setVersionNumber(versionNumber);
                }
            }
        } catch (Exception ignored) {

        }
    }

    protected DataSourceCreator(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataSourceCreator> CREATOR = new Creator<DataSourceCreator>() {
        @Override
        public DataSourceCreator createFromParcel(Parcel in) {
            return new DataSourceCreator(in);
        }

        @Override
        public DataSourceCreator[] newArray(int size) {
            return new DataSourceCreator[size];
        }
    };

    public static Builder builder(String dataSourceType, DataType dataType) {
        Builder b = new Builder();
        b.dataSourceType = dataSourceType;
        b.dataType = dataType.name();
        return b;
    }

    public static class Builder {
        private String dataSourceType = null;
        private String dataSourceId = null;
        private String platformType = null;
        private String platformId = null;
        private String platformAppType = null;
        private String platformAppId = null;
        private String applicationType = null;
        private String applicationId = null;
        private DataSourceMetaData dataSourceMetaData;
        private PlatformMetaData platformMetaData;
        private PlatformAppMetaData platformAppMetaData;
        private ApplicationMetaData applicationMetaData;

        private ArrayList<DataDescriptor> dataDescriptors = new ArrayList<>();
        private String dataType = null;
        private String dataRate = null;

        Builder() {
        }

        private Builder setDataSourceType(String dataSourceType) {
            this.dataSourceType = dataSourceType;
            return this;
        }

        public Builder setDataSourceId(String datasourceId) {
            this.dataSourceId = datasourceId;
            return this;
        }

        public Builder setPlatformType(String platformType) {
            this.platformType = platformType;
            return this;
        }

        public Builder setPlatformId(String platformId) {
            this.platformId = platformId;
            return this;
        }

        public Builder setPlatformAppType(String platformApp) {
            this.platformAppType = platformApp;
            return this;
        }

        public Builder setPlatformAppId(String platformAppId) {
            this.platformAppId = platformAppId;
            return this;
        }

        public Builder setApplicationType(String applicationType) {
            this.applicationType = applicationType;
            return this;
        }

        public Builder setApplicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder setDataSourceMetadata(DataSourceMetaData dataSourceMetaData) {
            this.dataSourceMetaData = dataSourceMetaData;
            return this;
        }

        public Builder setPlatformMetadata(PlatformMetaData platformMetaData) {
            this.platformMetaData = platformMetaData;
            return this;
        }

        public Builder setPlatformAppMetadata(PlatformAppMetaData platformAppMetaData) {
            this.platformAppMetaData = platformAppMetaData;
            return this;
        }

        public Builder setApplicationMetaData(ApplicationMetaData applicationMetaData) {
            this.applicationMetaData = applicationMetaData;
            return this;
        }

        private Builder setDataType(DataType dataType) {
            this.dataType = dataType.name();
            return this;
        }

        public Builder setDataRate(int sampleNo, TimeUnit timeUnit) {
            switch (timeUnit) {
                case DAYS:
                    dataRate = Double.toString((double) (sampleNo) / (24.0 * 60.0 * 60.0));
                    break;
                case HOURS:
                    dataRate = Double.toString((double) (sampleNo) / (60.0 * 60.0));
                    break;
                case MINUTES:
                    dataRate = Double.toString((double) (sampleNo) / (60.0));
                    break;
                case SECONDS:
                    dataRate = Double.toString((double) (sampleNo));
                    break;
                default:
                    dataRate = Double.toString((double) (sampleNo));
                    break;

            }
            return this;
        }

        @SuppressLint("HardwareIds")
        public Builder setPlatformAsPhone() {
            Context context = MCerebrumAPI.getContext();
            this.platformType = PLATFORM.TYPE.PHONE;
            this.platformId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            if (this.platformMetaData == null)
                this.platformMetaData = new PlatformMetaData();
            this.platformMetaData.setTitle("Android Phone");
//        this.metadata.put(PlatformMetaData.SUMMARY,"Android Phone");
            this.platformMetaData.setOperationSystem("Android " + Build.VERSION.RELEASE);
            this.platformMetaData.setManufacturer(Build.MANUFACTURER);
            this.platformMetaData.setModel(Build.MODEL);
            return this;
        }

        public Builder setDataDescriptor(int index, DataDescriptor dataDescriptor) {
            this.dataDescriptors.add(index, dataDescriptor);
            return this;
        }

        public DataSourceCreator build() {
            return new DataSourceCreator(this);
        }
    }

}

