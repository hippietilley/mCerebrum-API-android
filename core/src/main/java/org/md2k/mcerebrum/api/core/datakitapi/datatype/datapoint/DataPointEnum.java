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

package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.os.Parcelable;

import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;

import java.util.Arrays;

/**
 * This class creates <code>DataPoint</code> objects for samples that have an enum data type.
 * The sample for this <code>DataPoint</code> is stored as a byte array.
 */
public class DataPointEnum extends Data implements Parcelable {
    private byte[] sample;

    /**
     * Constructor
     * This constructor takes a single byte value for the sample.
     *
     * @param timestamp The timestamp for when the data was collected.
     * @param sample    The data point sampled from the data source.
     */
    public DataPointEnum(long timestamp, byte sample) {
        super(timestamp);
        this.sample = new byte[1];
        this.sample[0] = sample;
    }

    /**
     * Constructor
     * This constructor takes an array of bytes for a sample.
     *
     * @param timestamp The timestamp for when the data was collected.
     * @param sample    The data point sampled from the data source.
     */
    public DataPointEnum(long timestamp, byte[] sample) {
        super(timestamp);
        this.sample = new byte[sample.length];
        System.arraycopy(sample, 0, this.sample, 0, sample.length);
    }

    /**
     * Creates a new <code>DataPointEnum</code> object with the fields of the calling object.
     *
     * @return A new <code>DataPointEnum</code>.
     */
    public DataPointEnum clone() {
        return new DataPointEnum(getTimestamp(), sample);
    }

    /**
     * Constructs a <code>DataPointEnum</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataPointEnum</code> object.
     */
    protected DataPointEnum(Parcel in) {
        super(in);
        sample = in.createByteArray();
    }

    /**
     * Writes the <code>DataPointEnum</code> to a parcel.
     *
     * @param dest  The parcel to which the application should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByteArray(sample);
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
     * Returns the value of the sample.
     *
     * @return The the value of the sample.
     */
    public byte[] getSample() {
        return sample;
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataPointEnum</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataPointEnum> CREATOR = new Creator<DataPointEnum>() {

        /**
         * Creates an <code>DataPointEnum</code> object from a <code>Parcel</code>.
         *
         * @param in <code>Parcel</code> containing the <code>DataPointEnum</code>.
         * @return The constructed <code>DataPointEnum</code>.
         */
        @Override
        public DataPointEnum createFromParcel(Parcel in) {
            return new DataPointEnum(in);
        }

        /**
         * Creates an array for <code>DataPointEnum</code> of the given size.
         *
         * @param size Size of the array to create.
         * @return Returns an array for <code>DataPointEnum</code> objects.Th
         */
        @Override
        public DataPointEnum[] newArray(int size) {
            return new DataPointEnum[size];
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
        if (super.equals(toCompare)) {
            if (toCompare instanceof DataPointEnum) {
                if (this.getTimestamp() != ((DataPointEnum) toCompare).getTimestamp())
                    return false;

                for (int i = 0; i < this.getSample().length; i++) {
                    if (this.getSample()[i] != ((DataPointEnum) toCompare).getSample()[i]) {
                        return false;
                    }
                }
                return true;
            } else
                return false;
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
        result = 31 * result + super.hashCode();
        result = 31 * result + Arrays.hashCode(sample);
        return result;
    }
}
