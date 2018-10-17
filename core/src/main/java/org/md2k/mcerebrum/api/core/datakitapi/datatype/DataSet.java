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

package org.md2k.mcerebrum.api.core.datakitapi.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointBoolean;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointByte;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointEnum;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointInt;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointLong;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointObject;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointString;

/**
 * This class allows <code>Data</code> objects to be grouped into sets and parceled.
 */
public class DataSet implements Parcelable {
    private Data[] data;
    private int actualDataSize;
    private int receivedDataSize;
    private int samplingType;
    private int status;

    /**
     * Constructor
     * This constructor creates a <code>DataSet</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSet</code> object.
     */
    protected DataSet(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Creates a <code>DataSet</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataSet</code> object.
     */
    public void readFromParcel(Parcel in) {
        data = in.createTypedArray(Data.CREATOR);
        actualDataSize = in.readInt();
        receivedDataSize = in.readInt();
        samplingType = in.readInt();
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataSet</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataSet> CREATOR = new Creator<DataSet>() {

        /**
         * Creates an <code>DataSet</code> object from a <code>Parcel</code>.
         *
         * @param in <code>Parcel</code> containing the <code>Data</code>.
         * @return The constructed <code>Data</code>.
         */
        @Override
        public DataSet createFromParcel(Parcel in) {
            return new DataSet(in);
        }

        /**
         * Creates an array for <code>Data</code> of the given size.
         *
         * @param size Size of the array to create.
         * @return Returns an array for <code>Data</code> objects.Th
         */
        @Override
        public DataSet[] newArray(int size) {
            return new DataSet[size];
        }
    };

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
     * Writes the calling <code>DataSet</code> to the passed <code>Parcel</code>.
     *
     * @param parcel <code>Parcel</code> to write to.
     * @param i      This should always be the value returned from <code>describeContents()</code>.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(data, i);
        parcel.writeInt(actualDataSize);
        parcel.writeInt(receivedDataSize);
        parcel.writeInt(samplingType);
    }

    /**
     * Embedded class enumerating the sampling types for the <code>DataSet</code>.
     * The types include:
     * <ul>
     * <li>ALL</li>
     * <li>FIRST_N_SAMPLE</li>
     * <li>DISTRIBUTED_N_SAMPLE</li>
     * </ul>
     */
    public enum SAMPLING_TYPE {
        ALL(0),
        FIRST_N_SAMPLE(1),
        DISTRIBUTED_N_SAMPLE(2);
        int id;

        SAMPLING_TYPE(int id) {
            this.id = id;
        }

        public int getCode() {
            return id;
        }
    }

    /**
     * Constructor
     * This constructor is empty.
     */
    public DataSet() {

    }

    /**
     * Constructor
     * This constructor takes an array of <code>Data</code> objects and integer values for
     * <code>actualDataSize</code>, <code>receivedDataSize</code>, and <code>samplingType</code>.
     *
     * @param data             Array of <code>Data</code> objects in the set.
     * @param actualDataSize   Actual size of the <code>Data</code> objects.
     * @param receivedDataSize Received size of the <code>Data</code> objects.
     * @param samplingType     Sampling type used for the <code>Data</code>. These types are enumerated
     *                         in <code>SAMPLING_TYPE</code>.
     */
    public DataSet(Data[] data, int actualDataSize, int receivedDataSize, int samplingType) {
        this.data = data;
        this.actualDataSize = actualDataSize;
        this.receivedDataSize = receivedDataSize;
        this.samplingType = samplingType;
    }

    /**
     * Sets the <code>status</code> of the <code>DataSet</code>.
     * Possible statuses can be found in the <code>MCerebrumStatus</code> class.
     *
     * @param status Value of the status to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Returns an array of <code>Data</code> objects that the caller contains.
     *
     * @return An array of <code>Data</code> objects.
     */
    public Data[] getData() {
        return data;
    }

    /**
     * Returns the <code>actualDataSize</code>.
     *
     * @return The <code>actualDataSize</code>.
     */
    public int getActualDataSize() {
        return actualDataSize;
    }

    /**
     * Returns the <code>receivedDataSize</code>.
     *
     * @return The <code>receivedDataSize</code>.
     */
    public int getReceivedDataSize() {
        return receivedDataSize;
    }

    /**
     * Returns the <code>samplingType</code>.
     *
     * @return The <code>samplingType</code>.
     */
    public int getSamplingType() {
        return samplingType;
    }

    /**
     * Returns the <code>status</code>.
     *
     * @return The <code>status</code>.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Compares the passed object to the calling object.
     * If the passed object is not an instance of this class, false is returned.
     * Since a <code>DataSet</code> may contain various types of <code>Data</code> objects,
     * this method iterates through the array of data and checks the <code>instanceof</code> the
     * objects for comparison.
     *
     * @param toCompare Object to compare.
     * @return True if the objects are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof DataSet) {
            if (this.actualDataSize != ((DataSet) toCompare).actualDataSize)
                return false;
            if (this.receivedDataSize != ((DataSet) toCompare).receivedDataSize)
                return false;
            if (this.samplingType != ((DataSet) toCompare).samplingType)
                return false;
            else {
                for (int i = 0; i < this.data.length; i++) {
                    if (this.data[i] instanceof DataPointBoolean)
                        if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                            return false;
                        else if (this.data[i] instanceof DataPointByte)
                            if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                return false;
                            else if (this.data[i] instanceof DataPointDouble)
                                if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                    return false;
                                else if (this.data[i] instanceof DataPointEnum)
                                    if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                        return false;
                                    else if (this.data[i] instanceof DataPointInt)
                                        if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                            return false;
                                        else if (this.data[i] instanceof DataPointLong)
                                            if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                                return false;
                                            else if (this.data[i] instanceof DataPointObject)
                                                if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                                    return false;
                                                else if (this.data[i] instanceof DataPointString)
                                                    if (!(this.data[i].equals(((DataSet) toCompare).data[i])))
                                                        return false;
                }
                return true;
            }
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
        for (Data dataPoint : data)
            if (dataPoint != null)
                result = 31 * result + dataPoint.hashCode();
        result = 31 * result + actualDataSize;
        result = 31 * result + receivedDataSize;
        result = 31 * result + status;
        return result;
    }
}
