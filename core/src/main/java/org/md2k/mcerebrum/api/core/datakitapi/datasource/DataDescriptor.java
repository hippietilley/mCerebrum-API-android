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
 * This class defines the <code>DataDescriptor</code> object. The <code>DataDescriptor</code>
 * provides metadata about what the expected values of a datapoint are. This class
 * implements <code>Parcelable</code> so that the <code>DataDescriptor</code> objects can be
 * parceled with their data points. Metadata of note includes the application title, summary, and
 * description. These fields are stored in a hash map of strings. Other metadata fields, such as
 * minimum value, maximum value, unit, and arrays of possible values as strings and integers are not
 * stored in the hash map.
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

    /**
     * Constructor
     * This constructor creates a <code>DataDescriptor</code> from a <code>Parcel</code>.
     *
     * @param in <code>Parcel</code> containing the <code>DataDescriptor</code>.
     */
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

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>ApplicationMetaData</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataDescriptor> CREATOR = new Creator<DataDescriptor>() {
        /**
         * Creates an <code>DataDescriptor</code> object from a <code>Parcel</code>.
         * @param in <code>Parcel</code> containing the <code>DataDescriptor</code>.
         * @return The constructed <code>ApplicationMetaData</code>.
         */
        @Override
        public DataDescriptor createFromParcel(Parcel in) {
            return new DataDescriptor(in);
        }

        /**
         * Creates an array for <code>DataDescriptor</code> of the given size.
         * @param size Size of the array to create.
         * @return Returns an array for <code>DataDescriptor</code> objects.Th
         */
        @Override
        public DataDescriptor[] newArray(int size) {
            return new DataDescriptor[size];
        }
    };

    /**
     * Returns the title.
     *
     * @return The title.
     */
    public String getTitle() {
        return descriptor.get(TITLE);
    }


    /**
     * Returns the summary.
     *
     * @return The summary
     */
    public String getSummary() {
        return descriptor.get(SUMMARY);
    }


    /**
     * Returns the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return descriptor.get(DESCRIPTION);
    }


    /**
     * Returns the minimum allowable value for the data.
     *
     * @return The minimum value.
     */
    public double getMinValue() {
        return minValue;
    }


    /**
     * Returns the maximum allowable value for the data.
     *
     * @return The maximum value.
     */
    public double getMaxValue() {
        return maxValue;
    }


    /**
     * Returns the unit of measurement for the data.
     *
     * @return The unit of measurement.
     */
    public String getUnit() {
        return unit;
    }


    /**
     * Returns an array of possible values as strings.
     *
     * @return An array of possible values.
     */
    public String[] getPossibleValuesAsString() {
        return possibleValuesAsString;
    }


    /**
     * Returns an array of possible values as integers.
     *
     * @return An array of possible values.
     */
    public int[] getPossibleValuesAsInt() {
        return possibleValuesAsInt;
    }

    /**
     * Returns the value of a custom key.
     *
     * @param key The key to get the value of.
     * @return The value for the given key.
     */
    public String getValue(String key) {
        if (descriptor == null) return null;
        return descriptor.get(key);
    }

    /**
     * Constructor
     *
     * @param builder Builder object defining how to construct the <code>DataDescriptor</code>.
     */
    private DataDescriptor(Builder builder) {
        minValue = builder.minValue;
        maxValue = builder.maxValue;
        unit = builder.unit;
        possibleValuesAsString = builder.possibleValuesAsString;
        possibleValuesAsInt = builder.possibleValuesAsInt;
        descriptor = builder.descriptor;
    }

    /**
     * Creates a new <code>Builder</code> object to define an <code>DataDescriptor</code> object.
     *
     * @return A new <code>Builder</code>.
     */
    public static Builder builder() {
        return new Builder();
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
     * Writes the calling <code>DataDescriptor</code> to the passed <code>Parcel</code>.
     *
     * @param parcel <code>Parcel</code> to write to.
     * @param i      This should always be the value returned from <code>describeContents()</code>.
     */
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

    /**
     * Embedded class that defines the <code>Builder</code> for <code>DataDescriptor</code>.
     */
    public static class Builder {

        private double minValue;
        private double maxValue;
        private String unit;
        private String[] possibleValuesAsString;
        private int[] possibleValuesAsInt;
        private HashMap<String, String> descriptor = new HashMap<>();

        public Builder() {
        }

        /**
         * Sets the <code>TITLE</code> key of the hash map.
         *
         * @param title Value to associate <code>TITLE</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setTitle(String title) {
            descriptor.put(TITLE, title);
            return this;
        }

        /**
         * Sets the <code>SUMMARY</code> key of the hash map.
         *
         * @param summary Value to associate <code>SUMMARY</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setSummary(String summary) {
            descriptor.put(SUMMARY, summary);
            return this;
        }

        /**
         * Sets the <code>DESCRIPTION</code> key of the hash map.
         *
         * @param description Value to associate <code>DESCRIPTION</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDescription(String description) {
            descriptor.put(DESCRIPTION, description);
            return this;
        }

        /**
         * Sets the <code>minValue</code> field of the object.
         *
         * @param minValue Minimum allowable value for the data point.
         * @return The modified <code>Builder</code>.
         */
        public Builder setMinValue(double minValue) {
            this.minValue = minValue;
            return this;
        }

        /**
         * Sets the <code>maxValue</code> field of the object.
         *
         * @param maxValue Maximum allowable value for the data point.
         * @return The modified <code>Builder</code>.
         */
        public Builder setMaxValue(double maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        /**
         * Sets the <code>possibleValuesAsString</code> field of the object.
         *
         * @param possibleValues String array of possible values.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPossibleValues(String[] possibleValues) {
            this.possibleValuesAsString = possibleValues;
            return this;
        }

        /**
         * Sets the <code>possibleValuesAsInt</code> field of the object.
         *
         * @param possibleValues Integer array of possible values.
         * @return The modified <code>Builder</code>.
         */
        public Builder setPossibleValues(int[] possibleValues) {
            this.possibleValuesAsInt = possibleValues;
            return this;
        }

        /**
         * Puts a custom key and value into the hash map.
         *
         * @param key   Key to add to the hash map.
         * @param value Value to add to the hash map.
         * @return The modified <code>Builder</code>.
         */
        public Builder setValue(String key, String value) {
            this.descriptor.put(key, value);
            return this;
        }

        /**
         * Sets the <code>unit</code> field, which defines the unit of measure used for the data.
         *
         * @param unit Unit of measure used.
         * @return The modified <code>Builder</code>.
         */
        public Builder setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        /**
         * Passes the <code>Builder</code> to the <code>DataDescriptor</code> constructor.
         *
         * @return The resulting <code>DataDescriptor</code> object.
         */
        public DataDescriptor build() {
            return new DataDescriptor(this);
        }
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
        long minValueLong = Double.doubleToLongBits(minValue);
        result = 31 * result + (int) (minValueLong ^ (minValueLong >>> 32));
        long maxValueLong = Double.doubleToLongBits(maxValue);
        result = 31 * result + (int) (maxValueLong ^ (maxValueLong >>> 32));
        result = 31 * result + unit.hashCode();
        result = 31 * result + Arrays.hashCode(possibleValuesAsString);
        result = 31 * result + Arrays.hashCode(possibleValuesAsInt);
        result = 31 * result + descriptor.hashCode();
        return result;
    }
}
