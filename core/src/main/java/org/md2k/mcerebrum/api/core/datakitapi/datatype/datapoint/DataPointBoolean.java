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
 * This class creates <code>DataType</code> objects for samples that have boolean data types in an array.
 */
public class DataPointBoolean extends Data implements Parcelable {

    /**
     * The data point collected from the data source.
     */
    private boolean[] sample;

    /**
     * Constructor
     *
     * @param timestamp The timestamp for when the data was collected.
     * @param sample The data point sampled from the data source.
     */
    public DataPointBoolean(long timestamp, boolean[] sample) {
        super(timestamp);
        this.sample = new boolean[sample.length];
        System.arraycopy(sample, 0, this.sample, 0, sample.length);
    }
    public DataPointBoolean(long timestamp, boolean sample) {
        super(timestamp);
        this.sample = new boolean[1];
        this.sample[0] = sample;
    }
    /**
     * Constructs a <code>DataTypeBooleanArray</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataTypeBooleanArray</code> object.
     */
    private DataPointBoolean(Parcel in) {
        super(in);
        sample = in.createBooleanArray();
    }

    /**
     * Writes the <code>DataTypeBooleanArray</code> to a parcel.
     *
     * @param dest  The parcel to which the application should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeBooleanArray(sample);
    }

    /**
     * @return Always returns 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return The the value of the sample.
     */
    public boolean[] getSample() {
        return sample;
    }
    public DataPointBoolean clone(){
        return new DataPointBoolean(getTimestamp(), sample);
    }

    /**
     * <code>Creator</code> for <code>DataTypeBooleanArray</code> objects.
     */
    public static final Creator<DataPointBoolean> CREATOR = new Creator<DataPointBoolean>() {

        /**
         * Creates a new <code>DataTypeBooleanArray</code> object from a <code>Parcel</code>.
         *
         * @param in The parcel holding the data type.
         * @return The constructed <code>DataTypeBooleanArray</code> object
         */
        @Override
        public DataPointBoolean createFromParcel(Parcel in) {
            return new DataPointBoolean(in);
        }

        /**
         * Creates a new array of the specified size for <code>DataTypeBooleanArray</code> objects.
         *
         * @param size The size of the new <code>DataTypeBooleanArray</code> array.
         * @return The <code>DataTypeBooleanArray</code> array.
         */
        @Override
        public DataPointBoolean[] newArray(int size) {
            return new DataPointBoolean[size];
        }
    };

    @Override
    public boolean equals(Object toCompare) {
        if (super.equals(toCompare)) {
            if (toCompare instanceof DataPointBoolean) {
                for (int i = 0; i < this.getSample().length; i++) {
                    if (this.getSample()[i] != ((DataPointBoolean) toCompare).getSample()[i]) {
                        return false;
                    }
                }
                return true;
            } else
                return false;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + Arrays.hashCode(sample);
        return result;
    }
}
