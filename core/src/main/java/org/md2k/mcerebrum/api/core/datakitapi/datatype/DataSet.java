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

/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
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
public class DataSet implements Parcelable {
    private Data[] data;
    private int actualDataSize;
    private int receivedDataSize;
    private int samplingType;
    private int status;

    protected DataSet(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        data = in.createTypedArray(Data.CREATOR);
        actualDataSize = in.readInt();
        receivedDataSize = in.readInt();
        samplingType = in.readInt();
    }

    public static final Creator<DataSet> CREATOR = new Creator<DataSet>() {
        @Override
        public DataSet createFromParcel(Parcel in) {
            return new DataSet(in);
        }

        @Override
        public DataSet[] newArray(int size) {
            return new DataSet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(data, i);
        parcel.writeInt(actualDataSize);
        parcel.writeInt(receivedDataSize);
        parcel.writeInt(samplingType);
    }

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

    public DataSet() {

    }

    public DataSet(Data[] data, int actualDataSize, int receivedDataSize, int samplingType) {
        this.data = data;
        this.actualDataSize = actualDataSize;
        this.receivedDataSize = receivedDataSize;
        this.samplingType = samplingType;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data[] getData() {
        return data;
    }

    public int getActualDataSize() {
        return actualDataSize;
    }

    public int getReceivedDataSize() {
        return receivedDataSize;
    }

    public int getSamplingType() {
        return samplingType;
    }

    public int getStatus() {
        return status;
    }

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
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointByte)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointDouble)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointEnum)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointInt)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointLong)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointObject)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                    else if (this.data[i] instanceof DataPointString)
                        if (!(this.equals(((DataSet) toCompare).data[i])))
                            return false;
                }
                return true;
            }
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (Data dataPoint : data)
            result = 31 * result + dataPoint.hashCode();
        result = 31 * result + actualDataSize;
        result = 31 * result + receivedDataSize;
        result = 31 * result + status;
        return result;
    }
}
