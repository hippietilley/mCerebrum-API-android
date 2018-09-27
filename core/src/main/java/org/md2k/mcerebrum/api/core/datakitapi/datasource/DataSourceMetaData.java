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
import java.util.concurrent.TimeUnit;

/**
 * This class defines the <code>DataSourceMetaData</code> object. This object provides a structure
 * for organizing the metadata related to the data source that collects a data point. This class
 * implements <code>Parcelable</code> so that the <code>DataSourceMetaData</code> objects can be
 * parceled with their data points. Metadata of note includes the application title, summary, description,
 * and data collection rate (denoted as data rate). These fields are stored in a hash map of strings.
 */
public class DataSourceMetaData implements Parcelable {
    private static final String TITLE = "TITLE";
    private static final String SUMMARY = "SUMMARY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String DATA_RATE = "DATA_RATE";

    private HashMap<String, String> metaData;

    /**
     * Returns the title.
     *
     * @return The title.
     */
    public String getTitle() {
        return metaData.get(TITLE);
    }

    /**
     * Returns the summary.
     *
     * @return The summary.
     */
    public String getSummary() {
        return metaData.get(SUMMARY);
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return metaData.get(DESCRIPTION);
    }

    /**
     * Returns the metadata field for the given key.
     *
     * @param key Key of the the metadata to return. This key should be the name of the field in all
     *            capital letters, any spaces should be underscores.
     * @return The metadata field for the given key.
     */
    public String getMetaData(String key) {
        return metaData.get(key);
    }

    /**
     * Constructor
     *
     * @param builder Builder object defining how to construct the <code>DataSourceMetaData</code>.
     */
    private DataSourceMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }

    /**
     * Creates a new <code>Builder</code> object to define an <code>DataSourceMetaData</code> object.
     *
     * @return A new <code>Builder</code>.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a new <code>Builder</code> object from an existing <code>DataSourceMetaData</code> object.
     * If the <code>DataSourceMetaData</code> that is passed is null, then a new <code>Builder</code> is
     * returned.
     *
     * @param dataSourceMetaData Object to model the <code>Builder</code> on.
     * @return A new <code>Builder</code> from the passed <code>DataSourceMetaData</code>.
     */
    public static Builder builder(DataSourceMetaData dataSourceMetaData) {
        if (dataSourceMetaData == null)
            return new Builder();
        else return new Builder(dataSourceMetaData.metaData);
    }

    /**
     * Embedded class that defines the <code>Builder</code> for <code>DataSourceMetaData</code>.
     */
    public static class Builder {
        private HashMap<String, String> metaData;

        /**
         * Constructor
         * This constructor initializes a new hash map.
         */
        public Builder() {
            metaData = new HashMap<>();
        }

        /**
         * Constructor
         *
         * @param metaData Hash map of metadata to add to the <code>Builder</code>.
         */
        Builder(HashMap<String, String> metaData) {
            this.metaData = new HashMap<>();
            this.metaData.putAll(metaData);
        }

        /**
         * Sets the <code>TITLE</code> key of the hash map.
         *
         * @param title Value to associate <code>TITLE</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setTitle(String title) {
            this.metaData.put(TITLE, title);
            return this;
        }

        /**
         * Sets the <code>SUMMARY</code> key of the hash map.
         *
         * @param summary Value to associate <code>SUMMARY</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setSummary(String summary) {
            this.metaData.put(SUMMARY, summary);
            return this;
        }

        /**
         * Sets the <code>DESCRIPTION</code> key of the hash map.
         *
         * @param description Value to associate <code>DESCRIPTION</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDescription(String description) {
            this.metaData.put(DESCRIPTION, description);
            return this;
        }

        /**
         * Sets the <code>DATA_RATE</code> key to the value <code>"ON_CHANGE"</code>.
         *
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataRateAsOnChange() {
            this.metaData.put(DATA_RATE, "ON_CHANGE");
            return this;
        }

        /**
         * Sets the <code>DATA_RATE</code> key to the appropriate value based on the given <code>TimeUnit</code>.
         *
         * @param sampleNo Number of samples.
         * @param timeUnit Standard Java <code>TimeUnit</code> object. Can be <code>DAYS</code>,
         *                 <code>HOURS</code>, <code>MINUTES</code>, <code>SECONDS</code>,
         *                 <code>MILLISECONDS</code>, <code>MICROSECONDS</code>, and <code>NANOSECONDS</code>.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDataRate(int sampleNo, TimeUnit timeUnit) {
            String dataRate;
            switch (timeUnit) {
                case DAYS:
                    dataRate = Double.toString((double) (sampleNo) / (24.0 * 60.0 * 60.0));
                    break;
                case HOURS:
                    dataRate = Double.toString((double) (sampleNo) / (60.0 * 60.0));
                    break;
                case MINUTES:
                    dataRate = Double.toString((double) (sampleNo) / (60.0));
                    break;
                case SECONDS:
                    dataRate = Double.toString((double) (sampleNo));
                    break;
                case MILLISECONDS:
                    dataRate = Double.toString((double) (sampleNo) * 1000);
                    break;
                case MICROSECONDS:
                    dataRate = Double.toString((double) (sampleNo) * 1000 * 1000);
                    break;
                case NANOSECONDS:
                    dataRate = Double.toString((double) (sampleNo) * 1000 * 1000 * 1000);
                    break;
                default:
                    dataRate = "UNKNOWN";
                    break;
            }
            this.metaData.put(DATA_RATE, dataRate);
            return this;
        }

        /**
         * Puts a custom key and value into the hash map.
         *
         * @param key   Key to add to the hash map.
         * @param value Value to add to the hash map.
         * @return The modified <code>Builder</code>.
         */
        public Builder setMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }

        /**
         * Takes an existing hash map and merges it into the <code>Builder</code>'s hash map.
         *
         * @param metaData Hash map to add to the <code>Builder</code>.
         * @return The modified <code>Builder</code>.
         */
        public Builder setMetaData(HashMap<String, String> metaData) {
            for (HashMap.Entry<String, String> entry : metaData.entrySet())
                this.metaData.put(entry.getKey(), entry.getValue());
            return this;
        }

        /**
         * Passes the <code>Builder</code> to the <code>DataSourceMetaData</code> constructor.
         *
         * @return The resulting <code>DataSourceMetaData</code> object.
         */
        public DataSourceMetaData build() {
            return new DataSourceMetaData(this);
        }
    }

    /**
     * Constructor
     * This constructor creates an <code>DataSourceMetaData</code> object from a <code>Parcel</code>.
     *
     * @param in <code>Parcel</code> containing the <code>DataSourceMetaData</code>.
     */
    private DataSourceMetaData(Parcel in) {
        int size = in.readInt();
        if (size == -1) metaData = null;
        else {
            metaData = new HashMap<>();
            for (int j = 0; j < size; j++) {
                metaData.put(in.readString(), in.readString());
            }
        }
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>DataSourceMetaData</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<DataSourceMetaData> CREATOR = new Creator<DataSourceMetaData>() {

        /**
         * Creates an <code>DataSourceMetaData</code> object from a <code>Parcel</code>.
         * @param in <code>Parcel</code> containing the <code>DataSourceMetaData</code>.
         * @return The constructed <code>DataSourceMetaData</code>.
         */
        @Override
        public DataSourceMetaData createFromParcel(Parcel in) {
            return new DataSourceMetaData(in);
        }

        /**
         * Creates an array for <code>DataSourceMetaData</code> of the given size.
         * @param size Size of the array to create.
         * @return Returns an array for <code>DataSourceMetaData</code> objects.Th
         */
        @Override
        public DataSourceMetaData[] newArray(int size) {
            return new DataSourceMetaData[size];
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
     * Writes the calling <code>DataSourceMetaData</code> to the passed <code>Parcel</code>.
     *
     * @param parcel <code>Parcel</code> to write to.
     * @param i      This should always be the value returned from <code>describeContents()</code>.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (metaData == null)
            parcel.writeInt(-1);
        else {
            int size = metaData.size();
            parcel.writeInt(size);
            for (HashMap.Entry<String, String> entry : metaData.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
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
        if (toCompare instanceof DataSourceMetaData) {
            return ((this.getTitle().equals(((DataSourceMetaData) toCompare).getTitle())) &&
                    (this.getSummary().equals(((DataSourceMetaData) toCompare).getSummary())) &&
                    (this.getDescription().equals(((DataSourceMetaData) toCompare).getDescription())) &&
                    (this.getMetaData(DATA_RATE).equals(((DataSourceMetaData) toCompare).getMetaData(DATA_RATE))));
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
        result = 31 * result + metaData.hashCode();
        return result;
    }
}