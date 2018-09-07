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

/**
 * This class creates <code>DataType</code> objects for samples that have double data types in an array.
 */
public class DataPointDouble extends Data implements Parcelable {

    /**
     * The data point collected from the data source.
     */
    private double[] sample;


    /**
     * Constructor
     *
     * @param timestamp The timestamp for when the data was collected.
     * @param sample    The data point sampled from the data source.
     */
    public DataPointDouble(long timestamp, double[] sample) {
        super(timestamp);
        this.sample = new double[sample.length];
        System.arraycopy(sample, 0, this.sample, 0, sample.length);
    }

    public DataPointDouble(long timestamp, double sample) {
        super(timestamp);
        this.sample = new double[1];
        this.sample[0] = sample;
    }

    public DataPointDouble clone() {
        return new DataPointDouble(getTimestamp(), sample);
    }

    /**
     * Constructs a <code>DataTypeDoubleArray</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataTypeDoubleArray</code> object.
     */
    protected DataPointDouble(Parcel in) {
        super(in);
        sample = in.createDoubleArray();

    }

    /**
     * Writes the <code>DataTypeDoubleArray</code> to a parcel.
     *
     * @param dest  The parcel to which the application should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDoubleArray(sample);
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
    public double[] getSample() {
        return sample;
    }


    /**
     * <code>Creator</code> for <code>DataTypeDouble</code> objects.
     */
    public static final Creator<DataPointDouble> CREATOR = new Creator<DataPointDouble>() {

        /**
         * Creates a new <code>DataTypeDouble</code> object from a <code>Parcel</code>.
         *
         * @param in The parcel holding the data type.
         * @return The constructed <code>DataTypeDouble</code> object
         */
        @Override
        public DataPointDouble createFromParcel(Parcel in) {
            return new DataPointDouble(in);
        }

        /**
         * Creates a new array of the specified size for <code>DataTypeDouble</code> objects.
         *
         * @param size The size of the new <code>DataTypeDouble</code> array.
         * @return The <code>DataTypeDouble</code> array.
         */
        @Override
        public DataPointDouble[] newArray(int size) {
            return new DataPointDouble[size];
        }
    };

    @Override
    public boolean equals(Object toCompare) {
        if (super.equals(toCompare)) {
            if (toCompare instanceof DataPointDouble) {
                for (int i = 0; i < this.getSample().length; i++) {
                    if (this.getSample()[i] != ((DataPointDouble) toCompare).getSample()[i]) {
                        return false;
                    }
                }
                return true;
            } else
                return false;
        } else
            return false;
    }
}
