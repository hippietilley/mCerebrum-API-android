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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class defines the <code>ApplicationMetaData</code> object. This object provides a structure
 * for organizing the metadata related to the application that collects a data point. This class
 * implements <code>Parcelable</code> so that the <code>ApplicationMetaData</code> objects can be
 * parceled with their data points. Metadata of note includes the application title, summary, description,
 * version name and version number. These fields are stored in a hash map of strings.
 */
public class ApplicationMetaData implements Parcelable {
    private static final String TITLE = "TITLE";
    private static final String SUMMARY = "SUMMARY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String VERSION_NAME = "VERSION_NAME";
    private static final String VERSION_NUMBER = "VERSION_NUMBER";

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
     * Returns the version name.
     *
     * @return The version name.
     */
    public String getVersionName() {
        return metaData.get(VERSION_NAME);
    }

    /**
     * Returns the version number of the application. If the key <code>"VERSION_NUMBER"</code> isn't
     * in the hash map a -1 is returned.
     *
     * @return The version number of the application as an integer.
     */
    public int getVersionNumber() {
        if (metaData.containsKey(VERSION_NUMBER))
            return Integer.valueOf(metaData.get(VERSION_NUMBER));
        else return -1;
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
     * Iterates through the hash map to create an array of keys and then sorts the array.
     *
     * @return The sorted array of keys in the hash map.
     */
    public String[] getKeys() {
        String[] res = new String[metaData.size()];
        int i = -1;

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            res[++i] = entry.getKey();
        }
        Arrays.sort(res);
        return res;
    }

    /**
     * Constructor
     *
     * @param builder Builder object defining how to construct the <code>ApplicationMetaData</code>.
     */
    private ApplicationMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }

    /**
     * Creates a new <code>Builder</code> object to define an <code>ApplicationMetaData</code> object.
     *
     * @return A new <code>Builder</code>.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a new <code>Builder</code> object from an existing <code>ApplicationMetaData</code> object.
     * If the <code>ApplicationMetaData</code> that is passed is null, then a new <code>Builder</code> is
     * returned.
     *
     * @param applicationMetaData Object to model the <code>Builder</code> on.
     * @return A new <code>Builder</code> from the passed <code>ApplicationMetaData</code>.
     */
    public static Builder builder(ApplicationMetaData applicationMetaData) {
        if (applicationMetaData == null)
            return new Builder();
        else return new Builder(applicationMetaData.metaData);
    }

    /**
     * Embedded class that defines the <code>Builder</code> for <code>ApplicationMetaData</code>.
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
            metaData.put(TITLE, title);
            return this;
        }

        /**
         * Sets the <code>SUMMARY</code> key of the hash map.
         *
         * @param summary Value to associate <code>SUMMARY</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setSummary(String summary) {
            metaData.put(SUMMARY, summary);
            return this;
        }

        /**
         * Sets the <code>DESCRIPTION</code> key of the hash map.
         *
         * @param description Value to associate <code>DESCRIPTION</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDescription(String description) {
            metaData.put(DESCRIPTION, description);
            return this;
        }

        /**
         * Sets the <code>VERSION_NAME</code> key of the hash map.
         *
         * @param versionName Value to associate <code>VERSION_NAME</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionName(String versionName) {
            metaData.put(VERSION_NAME, versionName);
            return this;
        }

        /**
         * Sets the <code>VERSION_NUMBER</code> key of the hash map. The version number is passed in
         * as an integer and converted to a string via <code>Integer.toString()</code>.
         *
         * @param versionNumber Value to associate <code>VERSION_NUMBER</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionNumber(int versionNumber) {
            metaData.put(VERSION_NUMBER, Integer.toString(versionNumber));
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
         * Passes the <code>Builder</code> to the <code>ApplicationMetaData</code> constructor.
         *
         * @return The resulting <code>ApplicationMetaData</code> object.
         */
        public ApplicationMetaData build() {
            return new ApplicationMetaData(this);
        }

    }

    /**
     * Constructor
     * This constructor creates an <code>ApplicationMetaData</code> object from a <code>Parcel</code>.
     *
     * @param in <code>Parcel</code> containing the <code>ApplicationMetaData</code>.
     */
    protected ApplicationMetaData(Parcel in) {
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
     * Embedded <code>CREATOR</code> class for generating instances of <code>ApplicationMetaData</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<ApplicationMetaData> CREATOR = new Creator<ApplicationMetaData>() {
        /**
         * Creates an <code>ApplicationMetaData</code> object from a <code>Parcel</code>.
         * @param in <code>Parcel</code> containing the <code>ApplicationMetaData</code>.
         * @return The constructed <code>ApplicationMetaData</code>.
         */
        @Override
        public ApplicationMetaData createFromParcel(Parcel in) {
            return new ApplicationMetaData(in);
        }

        /**
         * Creates an array for <code>ApplicationMetaData</code> of the given size.
         * @param size Size of the array to create.
         * @return Returns an array for <code>ApplicationMetaData</code> objects.Th
         */
        @Override
        public ApplicationMetaData[] newArray(int size) {
            return new ApplicationMetaData[size];
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
     * Writes the calling <code>ApplicationMetaData</code> to the passed <code>Parcel</code>.
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
        if (toCompare instanceof ApplicationMetaData) {
            for (String thisKey : this.metaData.keySet()) {
                if (!this.metaData.get(thisKey).equalsIgnoreCase(((ApplicationMetaData) toCompare).metaData.get(thisKey)))
                    return false;
            }
            for (String toCompareKey : ((ApplicationMetaData) toCompare).metaData.keySet()) {
                if (!((ApplicationMetaData) toCompare).metaData.get(toCompareKey).equalsIgnoreCase(this.metaData.get(toCompareKey)))
                    return false;
            }
            return ((this.getTitle().equals(((ApplicationMetaData) toCompare).getTitle())) &&
                    (this.getSummary().equals(((ApplicationMetaData) toCompare).getSummary())) &&
                    (this.getDescription().equals(((ApplicationMetaData) toCompare).getDescription())));
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
