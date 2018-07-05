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

public class DataSourceRequest extends AbstractDataSource {
    private DataSourceRequest() {
        super();
    }
    private DataSourceRequest(Parcel in){
        super(in);
    }

    private DataSourceRequest(Builder dataSourceBuilder) {
        super.setDataSourceType(dataSourceBuilder.dataSourceType);
        super.setDataSourceId(dataSourceBuilder.dataSourceId);
        super.setPlatformType(dataSourceBuilder.platformType);
        super.setPlatformId(dataSourceBuilder.platformId);
        super.setPlatformAppType(dataSourceBuilder.platformAppType);
        super.setPlatformAppId(dataSourceBuilder.platformAppId);
        super.setApplicationType(dataSourceBuilder.applicationType);
        super.setApplicationId(dataSourceBuilder.applicationId);
    }
    public String getDataSourceType(){
        return super.getDataSourceType();
    }
    public String getDataSourceId(){
        return super.getDataSourceId();
    }
    public String getPlatformType(){
        return super.getPlatformType();
    }
    public String getPlatformId(){
        return super.getPlatformId();
    }
    public String getPlatformAppType(){
        return super.getPlatformAppType();
    }
    public String getPlatformAppId(){
        return super.getPlatformAppId();
    }
    public String getApplicationType(){
        return super.getApplicationType();
    }
    public String getApplicationId(){
        return super.getApplicationId();
    }

    public static Builder builder(){
        return new Builder();
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
        Builder() {
        }
        public Builder setDataSourceType(String dataSourceType) {
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
        public DataSourceRequest build() {
            return new DataSourceRequest(this);
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<DataSourceRequest> CREATOR = new Creator<DataSourceRequest>() {
        @Override
        public DataSourceRequest createFromParcel(Parcel in) {
            return new DataSourceRequest(in);
        }

        @Override
        public DataSourceRequest[] newArray(int size) {
            return new DataSourceRequest[size];
        }
    };

}

