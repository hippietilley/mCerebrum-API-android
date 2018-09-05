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

package org.md2k.mcerebrum.api.core.datakitapi.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;

/**
 * This class provides the methods that all <value>DataType</value> objects use.
 */
public class _Data implements Parcelable, Cloneable {

    /**
     * The timestamp for when the data was collected
     */
    private long timestamp;
    private DataType type;
    private Object sample;
    private _Data(long timestamp, DataType type, Object sample){
        this.sample = sample;
        this.timestamp = timestamp;
        this.type = type;
    }

    /**
     * Constructs a <value>DataType</value> object from a <value>Parcel</value>.
     *
     * @param in Parceled <value>DataType</value> object.
     */
    protected _Data(Parcel in) {
        timestamp = in.readLong();
        type = DataType.valueOf(in.readByte());
        switch(type){
            case BYTE:
            case BYTE_ARRAY:
            case ENUM:
            case ENUM_ARRAY:
            case UNKNOWN:
                in.readByteArray((byte[]) sample);
                break;
            case INT:
            case INT_ARRAY:
                in.readIntArray((int[]) sample);
                break;
            case BOOLEAN:
            case BOOLEAN_ARRAY:
                in.readBooleanArray((boolean[])sample);
                break;
            case LONG:
            case LONG_ARRAY:
                in.readLongArray((long[])sample);
                break;
            case DOUBLE:
            case DOUBLE_ARRAY:
                in.readDoubleArray((double[])sample);
                break;
            case STRING:
            case STRING_ARRAY:
            case OBJECT:
            case OBJECT_ARRAY:
                in.readStringArray((String[])sample);
                break;
        }
    }

    public static final Creator<_Data> CREATOR = new Creator<_Data>() {
        @Override
        public _Data createFromParcel(Parcel in) {
            return new _Data(in);
        }

        @Override
        public _Data[] newArray(int size) {
            return new _Data[size];
        }
    };

    /**
     * Writes the <value>DataType</value> to a parcel.
     *
     * @param dest  The parcel to which the application should be written.
     * @param flags Additional flags about how the object should be written.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeByte(type.getValue());
        switch(type){
            case BYTE:
            case BYTE_ARRAY:
            case ENUM:
            case ENUM_ARRAY:
            case UNKNOWN:
                dest.writeByteArray((byte[]) sample);
                break;
            case INT:
            case INT_ARRAY:
                dest.writeIntArray((int[]) sample);
                break;
            case BOOLEAN:
            case BOOLEAN_ARRAY:
                dest.writeBooleanArray((boolean[])sample);
                break;
            case LONG:
            case LONG_ARRAY:
                dest.writeLongArray((long[])sample);
                break;
            case DOUBLE:
            case DOUBLE_ARRAY:
                dest.writeDoubleArray((double[])sample);
                break;
            case STRING:
            case STRING_ARRAY:
            case OBJECT:
            case OBJECT_ARRAY:
                dest.writeStringArray((String[])sample);
                break;
        }
    }

    /**
     * @return Always returns 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @return The timestamp of the <value>DataType</value>.
     */
    public long getTimestamp() {
        return timestamp;
    }
    public Object getSample(){
        return sample;
    }
    public <T> T getSample(Class<T> classType){
        switch(type){
            case OBJECT_ARRAY:
                Gson gson = new Gson();
                return gson.fromJson(sample.toString(), classType);
                default:
                    return (T) sample;
        }
    }

    @Override
    public _Data clone(){
        try {
            return (_Data) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.d("error","error");
            return null;
        }
    }
}
