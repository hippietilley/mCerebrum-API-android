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

package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Arrays;

/**
 * Builder class for <code>DataSource</code> objects
 */
public class DataDescriptor implements Parcelable {
    private static final String TITLE = "TITLE";
    private static final String SUMMARY = "SUMMARY";
    private static final String DESCRIPTION = "DESCRIPTION";

    private double minValue;
    private double maxValue;
    private String[] possibleValuesAsString;
    private int[] possibleValuesAsInt;
    private String unit;
    private HashMap<String, String> descriptor = new HashMap<>();

    protected DataDescriptor(Parcel in) {
        minValue = in.readDouble();
        maxValue = in.readDouble();
        possibleValuesAsString = in.createStringArray();
        possibleValuesAsInt = in.createIntArray();
        unit = in.readString();
        int size = in.readInt();
        if (size == -1) descriptor = null;
        else {
            descriptor = new HashMap<>();
            for (int j = 0; j < size; j++) {
                descriptor.put(in.readString(), in.readString());
            }
        }

    }

    public static final Creator<DataDescriptor> CREATOR = new Creator<DataDescriptor>() {
        @Override
        public DataDescriptor createFromParcel(Parcel in) {
            return new DataDescriptor(in);
        }

        @Override
        public DataDescriptor[] newArray(int size) {
            return new DataDescriptor[size];
        }
    };

    public String getTitle() {
        return descriptor.get(TITLE);
    }

    public String getSummary() {
        return descriptor.get(SUMMARY);
    }

    public String getDescription() {
        return descriptor.get(DESCRIPTION);
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public String getUnit() {
        return unit;
    }

    public String[] getPossibleValuesAsString() {
        return possibleValuesAsString;
    }

    public int[] getPossibleValuesAsInt() {
        return possibleValuesAsInt;
    }

    public String getValue(String key) {
        if (descriptor == null) return null;
        return descriptor.get(key);
    }

    private DataDescriptor(Builder builder) {
        minValue = builder.minValue;
        maxValue = builder.maxValue;
        unit = builder.unit;
        possibleValuesAsString = builder.possibleValuesAsString;
        possibleValuesAsInt = builder.possibleValuesAsInt;
        descriptor = builder.descriptor;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(minValue);
        parcel.writeDouble(maxValue);
        parcel.writeStringArray(possibleValuesAsString);
        parcel.writeIntArray(possibleValuesAsInt);
        parcel.writeString(unit);
        if (descriptor == null)
            parcel.writeInt(-1);
        else {
            int size = descriptor.size();
            parcel.writeInt(size);
            for (HashMap.Entry<String, String> entry : descriptor.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }
    }

    public static class Builder {

        private double minValue;
        private double maxValue;
        private String unit;
        private String[] possibleValuesAsString;
        private int[] possibleValuesAsInt;
        private HashMap<String, String> descriptor = new HashMap<>();

        public Builder() {
        }

        public Builder setTitle(String title) {
            descriptor.put(TITLE, title);
            return this;
        }

        public Builder setSummary(String summary) {
            descriptor.put(SUMMARY, summary);
            return this;
        }

        public Builder setDescription(String description) {
            descriptor.put(DESCRIPTION, description);
            return this;
        }

        public Builder setMinValue(double minValue) {
            this.minValue = minValue;
            return this;
        }

        public Builder setMaxValue(double maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Builder setPossibleValues(String[] possibleValues) {
            this.possibleValuesAsString = possibleValues;
            return this;
        }

        public Builder setPossibleValues(int[] possibleValues) {
            this.possibleValuesAsInt = possibleValues;
            return this;
        }

        public Builder setValue(String key, String value) {
            this.descriptor.put(key, value);
            return this;
        }

        public Builder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public DataDescriptor build() {
            return new DataDescriptor(this);
        }
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof DataDescriptor) {
            return ((this.getTitle().equals(((DataDescriptor) toCompare).getTitle())) &&
                    (this.getSummary().equals(((DataDescriptor) toCompare).getSummary())) &&
                    (this.getDescription().equals(((DataDescriptor) toCompare).getDescription())) &&
                    (this.getMinValue() == ((DataDescriptor) toCompare).getMinValue()) &&
                    (this.getMaxValue() == ((DataDescriptor) toCompare).getMaxValue()) &&
                    (this.getUnit().equals(((DataDescriptor) toCompare).getUnit())) &&
                    (Arrays.equals(this.getPossibleValuesAsInt(), ((DataDescriptor) toCompare).getPossibleValuesAsInt())) &&
                    (Arrays.equals(this.getPossibleValuesAsString(), ((DataDescriptor) toCompare).getPossibleValuesAsString())));
        } else
            return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        long minValueLong = Double.doubleToLongBits(minValue);
        result = 31 * result + (int)(minValueLong ^ (minValueLong >>> 32));
        long maxValueLong = Double .doubleToLongBits(maxValue);
        result = 31 * result + (int)(maxValueLong ^ (maxValueLong >>> 32));
        result = 31 * result + unit.hashCode();
        result = 31 * result + Arrays.hashCode(possibleValuesAsString);
        result = 31 * result + Arrays.hashCode(possibleValuesAsInt);
        result = 31 * result + descriptor.hashCode();
        return result;
    }
}
