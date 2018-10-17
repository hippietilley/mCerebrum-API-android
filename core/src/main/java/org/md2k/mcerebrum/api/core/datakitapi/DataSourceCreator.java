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

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;

import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This class uses a <code>Builder</code> to create a <code>DataSourceCreator</code> object
 * to create a <code>DataSource</code>.
 */
public class DataSourceCreator extends DataSource implements Parcelable {
    /**
     * Constructor
     * This constructor is empty.
     */
    private DataSourceCreator() {
    }

    /**
     * Constructor
     * This constructor creates a <code>DataSourceCreator</code> from a <code>Builder</code>.
     *
     * @param dataSourceBuilder <code>Builder</code> modeling the desired <code>DataSourceCreator</code>.
     */
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

    }

    /**
     * Creates an <code>DataSourceCreator</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSourceCreator</code> object.
     */
    protected DataSourceCreator(Parcel in) {
        super(in);
    }

    /**
     * Writes the calling <code>DataSourceCreator</code> to the passed <code>Parcel</code>.
     *
     * @param dest  <code>Parcel</code> to write to.
     * @param flags This should always be the value returned from <code>describeContents()</code>.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
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
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataSourceCreator</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataSourceCreator> CREATOR = new Creator<DataSourceCreator>() {

        /**
         * Creates an <code>DataSourceCreator</code> object from a <code>Parcel</code>.
         *
         * @param in <code>Parcel</code> containing the <code>DataSourceCreator</code>.
         * @return The constructed <code>DataSourceCreator</code>.
         */
        @Override
        public DataSourceCreator createFromParcel(Parcel in) {
            return new DataSourceCreator(in);
        }

        /**
         * Creates an array for <code>DataSourceCreator</code> of the given size.
         *
         * @param size Size of the array to create.
         * @return Returns an array for <code>DataSourceCreator</code> objects.Th
         */
        @Override
        public DataSourceCreator[] newArray(int size) {
            return new DataSourceCreator[size];
        }
    };

    /**
     * Creates a new <code>Builder</code> with the given values for <code>dataSourceType</code>
     * and <code>dataType</code>.
     *
     * @param dataSourceType Type of the data source as a string.
     * @param dataType       <code>DataType</code>.
     * @return A new <code>Builder</code>.
     */
    public static Builder builder(String dataSourceType, DataType dataType) {
        Builder b = new Builder();
        b.dataSourceType = dataSourceType;
        b.dataType = dataType.name();
        return b;
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
        private DataSourceMetaData dataSourceMetaData;
        private PlatformMetaData platformMetaData;
        private PlatformAppMetaData platformAppMetaData;
        private ApplicationMetaData applicationMetaData;

        private ArrayList<DataDescriptor> dataDescriptors = new ArrayList<>();
        private String dataType = null;
        private String dataRate = null;

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
        private Builder setDataSourceType(String dataSourceType) {
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
         * Sets the <code>dataSourceMetaData</code>.
         *
         * @param dataSourceMetaData Metadata object for the data source.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataSourceMetadata(DataSourceMetaData dataSourceMetaData) {
            this.dataSourceMetaData = dataSourceMetaData;
            return this;
        }

        /**
         * Sets the <code>platformMetaData</code>.
         *
         * @param platformMetaData Metadata object for the platform.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformMetadata(PlatformMetaData platformMetaData) {
            this.platformMetaData = platformMetaData;
            return this;
        }

        /**
         * Sets the <code>platformAppMetaData</code>.
         *
         * @param platformAppMetaData Metadata object for the platform application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPlatformAppMetadata(PlatformAppMetaData platformAppMetaData) {
            this.platformAppMetaData = platformAppMetaData;
            return this;
        }

        /**
         * Sets the <code>applicationMetaData</code>.
         *
         * @param applicationMetaData Metadata object for the application.
         * @return The modified <code>Builder</code>.
         */
        public Builder setApplicationMetaData(ApplicationMetaData applicationMetaData) {
            this.applicationMetaData = applicationMetaData;
            return this;
        }

        /**
         * Sets the <code>dataType</code>.
         *
         * @param dataType Type of data.
         * @return The modified <code>Builder</code>.
         */
        private Builder setDataType(DataType dataType) {
            this.dataType = dataType.name();
            return this;
        }

        /**
         * Sets the <code>dataRate</code>.
         *
         * @param sampleNo Number of samples.
         * @param timeUnit <code>TimeUnit</code> denoting whether the samples are days, hours,
         *                 minutes, or seconds apart. The default case is handled like seconds.
         * @return The modified <code>Builder</code>.
         */
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

        /**
         * Sets the platform fields (<code>platformType</code>, <code>platformId</code>,
         * and <code>platformMetaData</code>) using the appropriate data from the phone.
         *
         * @return The modified <code>Builder</code>.
         */
        @SuppressLint("HardwareIds")
        public Builder setPlatformAsPhone() {
            Context context = MCerebrumAPI.getContext();
            this.platformType = PLATFORM.TYPE.PHONE;
            this.platformId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            this.platformMetaData = PlatformMetaData.builder(platformMetaData)
                    .setTitle("Android Phone")
                    .setDescription("Android Phone")
                    .setOperationSystem("Android " + Build.VERSION.RELEASE)
                    .setManufacturer(Build.MANUFACTURER)
                    .setModel(Build.MODEL).build();
            return this;
        }

        /**
         * Adds the given <code>DataDescriptor</code> to the hashmap at the given index.
         *
         * @param index          Index to add to.
         * @param dataDescriptor <code>DataDescriptor</code> to add.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataDescriptor(int index, DataDescriptor dataDescriptor) {
            this.dataDescriptors.add(index, dataDescriptor);
            return this;
        }

        /**
         * Passes the calling <code>Builder</code> to the <code>DataSourceCreator</code> constructor.
         *
         * @return A new <code>DataSourceCreator</code> modeled on the <code>Builder</code>.
         */
        public DataSourceCreator build() {
            return new DataSourceCreator(this);
        }
    }

    /**
     * Compares the passed object to the calling object.
     * If the passed object is not an instance of this class, false is returned.
     *
     * @param toCompare Object to compare.
     * @return True if the objects are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object toCompare) {
        return super.equals(toCompare);
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

