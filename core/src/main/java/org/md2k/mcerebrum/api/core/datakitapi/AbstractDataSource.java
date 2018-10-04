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
 * Base class of the <code>DataSource</code> class.
 * This class is inherited by <code>DataSource</code>,
 * <code>DataSourceReadWrite</code>, and <code>DataSourceCreator</code>.
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

    /**
     * Constructor
     * This constructor is empty.
     */
    AbstractDataSource() {

    }

    /**
     * Creates an <code>AbstractDataSource</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSet</code> object.
     */
    protected void readFromParcel(Parcel in) {
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
    }

    /**
     * Returns the data source id number.
     *
     * @return The data source id number.
     */
    protected int getDsId() {
        return dsId;
    }

    /**
     * Sets the data source id number.
     *
     * @param dsId Id number for the data source.
     */
    protected void setDsId(int dsId) {
        this.dsId = dsId;
    }

    /**
     * Returns the creation timestamp.
     *
     * @return The creation timestamp.
     */
    protected long getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param creationTime Timestamp denoting the time that the object was created.
     */
    protected void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Returns the modified timestamp.
     *
     * @return The modified timestamp.
     */
    protected long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * Sets the modified timestamp.
     *
     * @param modifiedTime Timestamp denoting the time that the object was last modified.
     */
    protected void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    /**
     * Returns the status of the object.
     *
     * @return The status of the object.
     */
    protected int getStatus() {
        return status;
    }

    /**
     * Sets the status of the object.
     *
     * @param status Status of the object.
     */
    protected void setStatus(int status) {
        this.status = status;
    }


    /**
     * Returns the data source type.
     *
     * @return The data source type.
     */
    protected String getDataSourceType() {
        return dataSourceType;
    }

    /**
     * Sets the data source type.
     *
     * @param dataSourceType Type of the data source.
     */
    protected void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    /**
     * Returns the data source id.
     *
     * @return The data source id.
     */
    protected String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * Sets the data source type.
     *
     * @param dataSourceId Data source id.
     */
    protected void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * Returns the platform type.
     *
     * @return The platform type.
     */
    protected String getPlatformType() {
        return platformType;
    }

    /**
     * Sets the platform type.
     *
     * @param platformType Platform type.
     */
    protected void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    /**
     * Returns the platform id.
     *
     * @return The platform id.
     */
    protected String getPlatformId() {
        return platformId;
    }

    /**
     * Sets the platform id.
     *
     * @param platformId Platform id.
     */
    protected void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * Returns the platform app type.
     *
     * @return The platform app type.
     */
    protected String getPlatformAppType() {
        return platformAppType;
    }

    /**
     * Sets the platform app type.
     *
     * @param platformAppType Platform app type.
     */
    protected void setPlatformAppType(String platformAppType) {
        this.platformAppType = platformAppType;
    }

    /**
     * Returns the platform app id.
     *
     * @return The platform app id.
     */
    protected String getPlatformAppId() {
        return platformAppId;
    }

    /**
     * Sets the platform app id.
     *
     * @param platformAppId Platform app id.
     */
    protected void setPlatformAppId(String platformAppId) {
        this.platformAppId = platformAppId;
    }

    /**
     * Returns the application type.
     *
     * @return The application type.
     */
    protected String getApplicationType() {
        return applicationType;
    }

    /**
     * Sets the application type.
     *
     * @param applicationType Application type.
     */
    protected void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    /**
     * Returns the application id.
     *
     * @return The application id.
     */
    protected String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the application id.
     *
     * @param applicationId Application id.
     */
    protected void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Returns the data source metadata.
     *
     * @return The data source metadata.
     */
    protected DataSourceMetaData getDataSourceMetaData() {
        return dataSourceMetaData;
    }

    /**
     * Sets the data source metadata.
     *
     * @param dataSourceMetaData data source metadata.
     */
    protected void setDataSourceMetaData(DataSourceMetaData dataSourceMetaData) {
        this.dataSourceMetaData = dataSourceMetaData;
    }

    /**
     * Returns the platform metadata.
     *
     * @return The platform metadata.
     */
    protected PlatformMetaData getPlatformMetaData() {
        return platformMetaData;
    }

    /**
     * Sets the platform metadata.
     *
     * @param platformMetaData platform metadata.
     */
    protected void setPlatformMetaData(PlatformMetaData platformMetaData) {
        this.platformMetaData = platformMetaData;
    }

    /**
     * Returns the platform app metadata.
     *
     * @return The platform app metadata.
     */
    protected PlatformAppMetaData getPlatformAppMetaData() {
        return platformAppMetaData;
    }

    /**
     * Sets the platform app metadata.
     *
     * @param platformAppMetaData Platform app metadata.
     */
    protected void setPlatformAppMetaData(PlatformAppMetaData platformAppMetaData) {
        this.platformAppMetaData = platformAppMetaData;
    }

    /**
     * Returns the application metadata.
     *
     * @return The application metadata.
     */
    protected ApplicationMetaData getApplicationMetaData() {
        return applicationMetaData;
    }

    /**
     * Sets the application metadata.
     *
     * @param applicationMetaData Application metadata.
     */
    protected void setApplicationMetaData(ApplicationMetaData applicationMetaData) {
        this.applicationMetaData = applicationMetaData;
    }

    /**
     * Returns the data descriptors.
     *
     * @return The data descriptors.
     */
    protected ArrayList<DataDescriptor> getDataDescriptors() {
        return dataDescriptors;
    }

    /**
     * Sets the data descriptors.
     *
     * @param dataDescriptors Data descriptors.
     */
    protected void setDataDescriptors(ArrayList<DataDescriptor> dataDescriptors) {
        this.dataDescriptors = dataDescriptors;
    }

    /**
     * Returns the data type.
     *
     * @return The data type.
     */
    protected String getDataType() {
        return dataType;
    }

    /**
     * Sets the data type.
     *
     * @param dataType Data type.
     */
    protected void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Constructor
     * This constructor makes an <code>AbstractDataSource</code> out of the passed <code>Parcel</code>.
     *
     * @param in
     */
    AbstractDataSource(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Creates a new <code>DataSourceReadWrite</code> object from the caller.
     *
     * @return The new <code>DataSourceReadWrite</code> object.
     */
    protected DataSourceReadWrite toDataSourceReadWrite() {
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
        return d;
    }

    /**
     * Writes the calling <code>AbstractDataSource</code> to the passed <code>Parcel</code>.
     *
     * @param parcel <code>Parcel</code> to write to.
     * @param i      This should always be the value returned from <code>describeContents()</code>.
     */
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
        if (toCompare instanceof AbstractDataSource) {
            return ((this.dsId == ((AbstractDataSource) toCompare).dsId)) &&
                    (this.creationTime == ((AbstractDataSource) toCompare).creationTime) &&
                    (this.modifiedTime == ((AbstractDataSource) toCompare).modifiedTime) &&
                    (this.status == ((AbstractDataSource) toCompare).status) &&
                    (this.getDataSourceType().equals(((AbstractDataSource) toCompare).getDataSourceType())) &&
                    (this.getDataSourceId().equals(((AbstractDataSource) toCompare).getDataSourceId())) &&
                    (this.getPlatformType().equals(((AbstractDataSource) toCompare).getPlatformType())) &&
                    (this.getPlatformId().equals(((AbstractDataSource) toCompare).getPlatformId())) &&
                    (this.getPlatformAppType().equals(((AbstractDataSource) toCompare).getPlatformAppType())) &&
                    (this.getPlatformAppId().equals(((AbstractDataSource) toCompare).getPlatformAppId())) &&
                    (this.getApplicationType().equals(((AbstractDataSource) toCompare).getApplicationType())) &&
                    ((this.getApplicationId() == null && ((AbstractDataSource) toCompare).getApplicationId() == null) ||
                            (this.getApplicationId().equals(((AbstractDataSource) toCompare).getApplicationId()))) &&
                    (this.getDataSourceMetaData().equals(((AbstractDataSource) toCompare).getDataSourceMetaData())) &&
                    (this.getPlatformMetaData().equals(((AbstractDataSource) toCompare).getPlatformMetaData())) &&
                    (this.getPlatformAppMetaData().equals(((AbstractDataSource) toCompare).getPlatformAppMetaData())) &&
                    (this.getApplicationMetaData().equals(((AbstractDataSource) toCompare).getApplicationMetaData()));
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
        int result = 17;
        result = 31 * result + dsId;
        result = 31 * result + (int) (creationTime ^ (creationTime >>> 32));
        result = 31 * result + (int) (modifiedTime ^ (modifiedTime >>> 32));
        result = 31 * result + status;
        result = 31 * result + dataSourceType.hashCode();
        result = 31 * result + dataSourceId.hashCode();
        result = 31 * result + platformType.hashCode();
        result = 31 * result + platformId.hashCode();
        result = 31 * result + platformAppType.hashCode();
        result = 31 * result + platformAppId.hashCode();
        result = 31 * result + applicationType.hashCode();
        //result = 31 * result + applicationId.hashCode();
        if (dataSourceMetaData != null)
            result = 31 * result + dataSourceMetaData.hashCode();
        if (platformMetaData != null)
            result = 31 * result + platformMetaData.hashCode();
        if (platformAppMetaData != null)
            result = 31 * result + platformAppMetaData.hashCode();
        if (applicationMetaData != null)
            result = 31 * result + applicationMetaData.hashCode();
        return result;
    }
}
