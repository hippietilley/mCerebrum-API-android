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
 * Base class for <code>DataSourceReadWrite</code> and <code>DataSourceCreator</code>.
 * This class exposes the protected getter methods from <code>AbstractDataSource</code>.
 */
public abstract class DataSource extends AbstractDataSource implements Parcelable {
    /**
     * Constructor
     * This constructor is empty
     */
    DataSource() {
    }

    /**
     * Creates an <code>AbstractDataSource</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSet</code> object.
     */
    DataSource(Parcel in) {
        super(in);
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
     * Returns the data source metadata.
     *
     * @return The data source metadata.
     */
    public DataSourceMetaData getDataSourceMetaData() {
        return super.getDataSourceMetaData();
    }

    /**
     * Returns the platform metadata.
     *
     * @return The platform metadata.
     */
    public PlatformMetaData getPlatformMetaData() {
        return super.getPlatformMetaData();
    }

    /**
     * Returns the platform app metadata.
     *
     * @return The platform app metadata.
     */
    public PlatformAppMetaData getPlatformAppMetaData() {
        return super.getPlatformAppMetaData();
    }

    /**
     * Returns the application metadata.
     *
     * @return The application metadata.
     */
    public ApplicationMetaData getApplicationMetaData() {
        return super.getApplicationMetaData();
    }

    /**
     * Returns the data descriptors.
     *
     * @return The data descriptors.
     */
    public ArrayList<DataDescriptor> getDataDescriptors() {
        return super.getDataDescriptors();
    }

    /**
     * Returns the data type.
     *
     * @return The data type.
     */
    public String getDataType() {
        return super.getDataType();
    }

    /**
     * Returns the data source id number.
     *
     * @return The data source id number.
     */
    public int getDsId() {
        return super.getDsId();
    }

    /**
     * Returns the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public long getCreationTime() {
        return super.getCreationTime();
    }

    /**
     * Returns the modified timestamp.
     *
     * @return The modified timestamp.
     */
    public long getModifiedTime() {
        return super.getModifiedTime();
    }

    /**
     * Returns the status of the object.
     *
     * @return The status of the object.
     */
    public int getStatus() {
        return super.getStatus();
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

