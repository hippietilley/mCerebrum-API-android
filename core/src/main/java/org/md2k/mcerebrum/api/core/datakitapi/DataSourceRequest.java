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

/**
 * This class uses a <code>Builder</code> to create a <code>DataSourceRequest</code> object.
 */
public class DataSourceRequest extends AbstractDataSource {
    /**
     * Constructor
     * This constructor just calls <code>super();</code>.
     */
    private DataSourceRequest() {
        super();
    }

    /**
     * Creates an <code>DataSourceRequest</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSourceRequest</code> object.
     */
    private DataSourceRequest(Parcel in) {
        super(in);
    }

    /**
     * Constructor
     * This constructor creates a <code>DataSourceRequest</code> from a <code>Builder</code>.
     *
     * @param dataSourceBuilder <code>Builder</code> modeling the desired <code>DataSourceRequest</code>.
     */
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

    /**
     * Returns the data source type.
     *
     * @return The data source type.
     */
    public String getDataSourceType() {
        return super.getDataSourceType();
    }

    /**
     * Returns the data source id.
     *
     * @return The data source id.
     */
    public String getDataSourceId() {
        return super.getDataSourceId();
    }

    /**
     * Returns the platform type.
     *
     * @return The platform type.
     */
    public String getPlatformType() {
        return super.getPlatformType();
    }

    /**
     * Returns the platform id.
     *
     * @return The platform id.
     */
    public String getPlatformId() {
        return super.getPlatformId();
    }

    /**
     * Returns the platform app type.
     *
     * @return The platform app type.
     */
    public String getPlatformAppType() {
        return super.getPlatformAppType();
    }

    /**
     * Returns the platform app id.
     *
     * @return The platform app id.
     */
    public String getPlatformAppId() {
        return super.getPlatformAppId();
    }

    /**
     * Returns the application type.
     *
     * @return The application type.
     */
    public String getApplicationType() {
        return super.getApplicationType();
    }

    /**
     * Returns the application id.
     *
     * @return The application id.
     */
    public String getApplicationId() {
        return super.getApplicationId();
    }

    /**
     * Creates a new <code>Builder</code>.
     *
     * @return A new <code>Builder</code>.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Embedded <code>Builder</code> class.
     */
    public static class Builder {
        private String dataSourceType = null;
        private String dataSourceId = null;
        private String platformType = null;
        private String platformId = null;
        private String platformAppType = null;
        private String platformAppId = null;
        private String applicationType = null;
        private String applicationId = null;

        /**
         * Constructor
         * This constructor is empty.
         */
        Builder() {
        }

        /**
         * Sets the <code>dataSourceType</code>.
         *
         * @param dataSourceType Type of data source.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataSourceType(String dataSourceType) {
            this.dataSourceType = dataSourceType;
            return this;
        }

        /**
         * Sets the <code>dataSourceId</code>.
         *
         * @param datasourceId Id of the data source.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataSourceId(String datasourceId) {
            this.dataSourceId = datasourceId;
            return this;
        }

        /**
         * Sets the <code>platformType</code>.
         *
         * @param platformType Type of the platform.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformType(String platformType) {
            this.platformType = platformType;
            return this;
        }

        /**
         * Sets the <code>platformId</code>.
         *
         * @param platformId Id of the platform.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformId(String platformId) {
            this.platformId = platformId;
            return this;
        }

        /**
         * Sets the <code>platformAppType</code>.
         *
         * @param platformApp Type of platform application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformAppType(String platformApp) {
            this.platformAppType = platformApp;
            return this;
        }

        /**
         * Sets the <code>platformAppId</code>.
         *
         * @param platformAppId Id for the platform application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformAppId(String platformAppId) {
            this.platformAppId = platformAppId;
            return this;
        }

        /**
         * Sets the <code>applicationType</code>.
         *
         * @param applicationType Type of the application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setApplicationType(String applicationType) {
            this.applicationType = applicationType;
            return this;
        }

        /**
         * Sets the <code>applicationId</code>.
         *
         * @param applicationId Id of the application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setApplicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        /**
         * Passes the calling <code>Builder</code> to the <code>DataSourceRequest</code> constructor.
         *
         * @return A new <code>DataSourceRequest</code> modeled on the <code>Builder</code>.
         */
        public DataSourceRequest build() {
            return new DataSourceRequest(this);
        }
    }

    /**
     * Always returns 0 because this parcel doesn't contain any special objects.
     * From <a href = https://developer.android.com/reference/android/os/Parcelable>Google's Android documentation</a>:
     * Describe the kinds of special objects contained in this Parcelable instance's marshaled representation.
     * For example, if the object will include a file descriptor in the output of
     * writeToParcel(Parcel, int), the return value of this method must include the CONTENTS_FILE_DESCRIPTOR bit.
     *
     * @return 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataSourceRequest</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataSourceRequest> CREATOR = new Creator<DataSourceRequest>() {

        /**
         * Creates an <code>DataSourceRequest</code> object from a <code>Parcel</code>.
         *
         * @param in <code>Parcel</code> containing the <code>DataSourceRequest</code>.
         */
        @Override
        public DataSourceRequest createFromParcel(Parcel in) {
            return new DataSourceRequest(in);
        }

        /**
         * Creates an array for <code>DataSourceRequest</code> of the given size.
         *
         * @param size Size of the array to create.
         */
        @Override
        public DataSourceRequest[] newArray(int size) {
            return new DataSourceRequest[size];
        }
    };

    /**
     * Compares the passed object to the calling object.
     * If the passed object is not an instance of this class, false is returned.
     *
     * @param toCompare Object to compare.
     * @return True if the objects are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object toCompare) {
        if ((toCompare instanceof DataSourceRequest))
            return (this.getDataSourceType().equals(((DataSourceRequest) toCompare).getDataSourceType())) &&
                    (this.getDataSourceId().equals(((DataSourceRequest) toCompare).getDataSourceId())) &&
                    (this.getPlatformType().equals(((DataSourceRequest) toCompare).getPlatformType())) &&
                    (this.getPlatformId().equals(((DataSourceRequest) toCompare).getPlatformId())) &&
                    (this.getPlatformAppType().equals(((DataSourceRequest) toCompare).getPlatformAppType())) &&
                    (this.getPlatformAppId().equals(((DataSourceRequest) toCompare).getPlatformAppId())) &&
                    (this.getApplicationType().equals(((DataSourceRequest) toCompare).getApplicationType())) &&
                    (this.getApplicationId() == null && ((DataSourceRequest) toCompare).getApplicationId() == null) ||
                    (this.getApplicationId().equals(((DataSourceRequest) toCompare).getApplicationId()));
        else
            return false;
    }

    /**
     * Calculates and returns a hash code for the calling object.
     * The hash code is calculated using the method denoted in "Effective Java" and described in this Medium
     * <a href="https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c">post</a>.
     *
     * @return The hash code of the calling object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
