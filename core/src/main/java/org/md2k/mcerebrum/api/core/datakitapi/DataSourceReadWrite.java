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

import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;

import java.util.ArrayList;

/**
 * This class uses a <code>Builder</code> to create a <code>DataSourceReadWrite</code> object
 * to create a <code>DataSource</code>.
 */
public class DataSourceReadWrite extends DataSource implements Parcelable {
    /**
     * Constructor
     * This constructor is empty.
     */
    DataSourceReadWrite() {
    }

    /**
     * Creates an <code>DataSourceReadWrite</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSourceReadWrite</code> object.
     */
    protected DataSourceReadWrite(Parcel in) {
        super(in);
    }

    /**
     * Writes the calling <code>DataSourceReadWrite</code> to the passed <code>Parcel</code>.
     *
     * @param dest  <code>Parcel</code> to write to.
     * @param flags This should always be the value returned from <code>describeContents()</code>.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataSourceReadWrite</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataSourceReadWrite> CREATOR = new Creator<DataSourceReadWrite>() {

        /**
         * Creates an <code>DataSourceReadWrite</code> object from a <code>Parcel</code>.
         *
         * @param in <code>Parcel</code> containing the <code>DataSourceReadWrite</code>.
         */
        @Override
        public DataSourceReadWrite createFromParcel(Parcel in) {
            return new DataSourceReadWrite(in);
        }

        /**
         * Creates an array for <code>DataSourceReadWrite</code> of the given size.
         *
         * @param size Size of the array to create.
         */
        @Override
        public DataSourceReadWrite[] newArray(int size) {
            return new DataSourceReadWrite[size];
        }
    };

    /**
     * Sets the <code>dsId</code>.
     *
     * @param dsId Id number for the data source.
     */
    public void setDsId(int dsId) {
        super.setDsId(dsId);
    }

    /**
     * Sets the <code>creationTime</code>.
     *
     * @param creationTime Timestamp denoting the time that the object was created.
     */
    public void setCreationTime(long creationTime) {
        super.setCreationTime(creationTime);
    }

    /**
     * Sets the <code>modifiedTime</code>.
     *
     * @param modifiedTime Timestamp denoting the time that the object was last modified.
     */
    public void setModifiedTime(long modifiedTime) {
        super.setModifiedTime(modifiedTime);
    }

    /**
     * Sets the <code>status</code>.
     *
     * @param status Status of the object.
     */
    public void setStatus(int status) {
        super.setStatus(status);
    }

    /**
     * Sets the <code>dataSourceType</code>.
     *
     * @param dataSourceType Type of data source.
     */
    public void setDataSourceType(String dataSourceType) {
        super.setDataSourceType(dataSourceType);
    }

    /**
     * Sets the <code>dataSourceId</code>.
     *
     * @param dataSourceId Id of the data source.
     */
    public void setDataSourceId(String dataSourceId) {
        super.setDataSourceId(dataSourceId);
    }

    /**
     * Sets the <code>platformType</code>.
     *
     * @param platformType Type of the platform.
     */
    public void setPlatformType(String platformType) {
        super.setPlatformType(platformType);
    }

    /**
     * Sets the <code>platformId</code>.
     *
     * @param platformId Id of the platform.
     */
    public void setPlatformId(String platformId) {
        super.setPlatformId(platformId);
    }

    /**
     * Sets the <code>platformAppType</code>.
     *
     * @param platformAppType Type of platform application.
     */
    public void setPlatformAppType(String platformAppType) {
        super.setPlatformAppType(platformAppType);
    }

    /**
     * Sets the <code>platformAppId</code>.
     *
     * @param platformAppId Id for the platform application.
     */
    public void setPlatformAppId(String platformAppId) {
        super.setPlatformAppId(platformAppId);
    }

    /**
     * Sets the <code>applicationType</code>.
     *
     * @param applicationType Type of the application.
     */
    public void setApplicationType(String applicationType) {
        super.setApplicationType(applicationType);
    }

    /**
     * Sets the <code>applicationId</code>.
     *
     * @param applicationId Id of the application.
     */
    public void setApplicationId(String applicationId) {
        super.setApplicationId(applicationId);
    }

    /**
     * Sets the <code>dataSourceMetaData</code>.
     *
     * @param dataSourceMetadata Metadata object for the data source.
     */
    public void setDataSourceMetadata(DataSourceMetaData dataSourceMetadata) {
        super.setDataSourceMetaData(dataSourceMetadata);
    }

    /**
     * Sets the <code>platformMetaData</code>.
     *
     * @param platformMetadata Metadata object for the platform.
     */
    public void setPlatformMetadata(PlatformMetaData platformMetadata) {
        super.setPlatformMetaData(platformMetadata);
    }

    /**
     * Sets the <code>platformAppMetaData</code>.
     *
     * @param platformAppMetadata Metadata object for the platform application.
     */
    public void setPlatformAppMetadata(PlatformAppMetaData platformAppMetadata) {
        super.setPlatformAppMetaData(platformAppMetadata);
    }

    /**
     * Sets the <code>applicationMetaData</code>.
     *
     * @param applicationMetadata Metadata object for the application.
     */
    public void setApplicationMetadata(ApplicationMetaData applicationMetadata) {
        super.setApplicationMetaData(applicationMetadata);
    }

    /**
     * Adds the given array list <code>DataDescriptor</code>.
     *
     * @param dataDescriptors ArrayList of <code>DataDescriptor</code> to add.
     */
    public void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        super.setDataDescriptors(dataDescriptors);
    }

    /**
     * Sets the <code>dataType</code>.
     *
     * @param dataType Type of data.
     */
    public void setDataType(String dataType) {
        super.setDataType(dataType);
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
     * Compares the passed object to the calling object.
     * If the passed object is not an instance of this class, false is returned.
     *
     * @param toCompare Object to compare.
     * @return True if the objects are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof DataSourceReadWrite) {
            return super.equals(toCompare);
        } else
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
