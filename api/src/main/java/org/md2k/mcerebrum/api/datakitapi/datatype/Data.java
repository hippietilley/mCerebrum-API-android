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

package org.md2k.mcerebrum.api.datakitapi.datatype;

import android.os.Parcel;
import android.os.Parcelable;

import org.md2k.mcerebrum.api.datakitapi.datatype.dataannotation.DataAnnotationEnum;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointBoolean;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointByte;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointEnum;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointInt;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointLong;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointObject;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointString;

/**
 * This class provides the methods that all <code>DataType</code> objects use.
 */
public class Data implements Parcelable{

    /** The timestamp for when the data was collected */
    private long timestamp;

    /**
     * Constructs a <code>DataType</code> object with a <code>dataTime</code>.
     *
     * @param timestamp The timestamp for when the data was collected.
     */
    public Data(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Constructs a <code>DataType</code> object from a <code>Parcel</code>.
     *
     * @param in Parceled <code>DataType</code> object.
     */
    protected Data(Parcel in) {
        timestamp = in.readLong();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    /**
     * Writes the <code>DataType</code> to a parcel.
     *
     * @param dest The parcel to which the application should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
    }

    /**
     * @return Always returns 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return The timestamp of the <code>DataType</code>.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * <code>Creator</code> for <code>DataType</code> objects.
     */

    public Data clone(){
        return new Data(timestamp);
    }
    public DataType getType(){
        if(this instanceof DataPointBoolean) return DataType.DATAPOINT_BOOLEAN;
        if(this instanceof DataPointByte) return DataType.DATAPOINT_BYTE;
        if(this instanceof DataPointInt) return DataType.DATAPOINT_INT;
        if(this instanceof DataPointLong) return DataType.DATAPOINT_LONG;
        if(this instanceof DataPointDouble) return DataType.DATAPOINT_DOUBLE;
        if(this instanceof DataPointString) return DataType.DATAPOINT_STRING;
        if(this instanceof DataPointObject) return DataType.DATAPOINT_OBJECT;
        if(this instanceof DataPointEnum) return DataType.DATAPOINT_ENUM;
        if(this instanceof DataAnnotationEnum) return DataType.DATAANNOTATION_ENUM;
        return DataType.UNKNOWN;
    }
}
