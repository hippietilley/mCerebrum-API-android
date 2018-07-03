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

package org.md2k.mcerebrum.api.datakitapi.datatype.dataannotation;

import android.os.Parcel;
import android.os.Parcelable;

import org.md2k.mcerebrum.api.datakitapi.datatype.Data;

/**
 * This class provides the methods that all <code>DataType</code> objects use.
 */
public class DataAnnotationEnum extends Data implements Parcelable{

    /** The timestamp for when the data was collected */
    private long endTimestamp;
    private byte[] sample;

    /**
     * Constructs a <code>DataType</code> object with a <code>dataTime</code>.
     *
     * @param startTimestamp The timestamp for when the data was collected.
     */
    public DataAnnotationEnum(long startTimestamp, long endTimestamp, byte sample) {
        super(startTimestamp);
        this.endTimestamp = endTimestamp;
        this.sample = new byte[]{sample};
    }
    private DataAnnotationEnum(long timestamp, long endTimestamp, byte[] sample) {
        super(timestamp);
        this.sample = new byte[sample.length];
        System.arraycopy(sample, 0, this.sample, 0, sample.length);
        this.endTimestamp = endTimestamp;
    }

    private DataAnnotationEnum(Parcel in) {
        super(in);
        endTimestamp = in.readLong();
        sample = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(endTimestamp);
        dest.writeByteArray(sample);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataAnnotationEnum> CREATOR = new Creator<DataAnnotationEnum>() {
        @Override
        public DataAnnotationEnum createFromParcel(Parcel in) {
            return new DataAnnotationEnum(in);
        }

        @Override
        public DataAnnotationEnum[] newArray(int size) {
            return new DataAnnotationEnum[size];
        }
    };

    public DataAnnotationEnum clone(){
        return new DataAnnotationEnum(getTimestamp(), getEndTimestamp(), sample);
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public byte[] getSample() {
        return sample;
    }

}
