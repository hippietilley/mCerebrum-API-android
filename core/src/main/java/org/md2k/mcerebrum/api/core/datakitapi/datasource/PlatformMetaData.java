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

/**
 * This class defines the <code>PlatformMetaData</code> object. This object provides a structure
 * for organizing the metadata related to the platform that collects a data point. This class
 * implements <code>Parcelable</code> so that the <code>PlatformMetaData</code> objects can be
 * parceled with their data points. Metadata of note includes the application title, summary, description,
 * operating system, manufacturer, model, version firmware, version hardware, and device id.
 * These fields are stored in a hash map of strings.
 */
public class PlatformMetaData implements Parcelable {
    private static final String TITLE = "TITLE";
    private static final String SUMMARY = "SUMMARY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String OPERATING_SYSTEM = "OPERATING_SYSTEM";
    private static final String MANUFACTURER = "MANUFACTURER";
    private static final String MODEL = "MODEL";
    private static final String VERSION_FIRMWARE = "VERSION_FIRMWARE";
    private static final String VERSION_HARDWARE = "VERSION_HARDWARE";
    private static final String DEVICE_ID = "DEVICE_ID";
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
     * Returns the operating system.
     *
     * @return The operating system.
     */
    public String getOperationSystem() {
        return metaData.get(OPERATING_SYSTEM);
    }

    /**
     * Returns the manufacturer.
     *
     * @return The manufacturer.
     */
    public String getManufacturer() {
        return metaData.get(MANUFACTURER);
    }

    /**
     * Returns the model.
     *
     * @return The model.
     */
    public String getModel() {
        return metaData.get(MODEL);
    }

    /**
     * Returns the version firmware.
     *
     * @return The version firmware.
     */
    public String getVersionFirmware() {
        return metaData.get(VERSION_FIRMWARE);
    }

    /**
     * Returns the version hardware.
     *
     * @return The version hardware.
     */
    public String getVersionHardware() {
        return metaData.get(VERSION_HARDWARE);
    }

    /**
     * Returns the device id.
     *
     * @return The device id.
     */
    public String getDeviceId() {
        return metaData.get(DEVICE_ID);
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
     * @param builder Builder object defining how to construct the <code>PlatformMetaData</code>.
     */
    private PlatformMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }

    /**
     * Creates a new <code>Builder</code> object to define an <code>PlatformMetaData</code> object.
     *
     * @return A new <code>Builder</code>.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a new <code>Builder</code> object from an existing <code>PlatformMetaData</code> object.
     * If the <code>PlatformMetaData</code> that is passed is null, then a new <code>Builder</code> is
     * returned.
     *
     * @param platformMetaData Object to model the <code>Builder</code> on.
     * @return A new <code>Builder</code> from the passed <code>PlatformMetaData</code>.
     */
    public static Builder builder(PlatformMetaData platformMetaData) {
        if (platformMetaData == null)
            return new Builder();
        else return new Builder(platformMetaData.metaData);
    }

    /**
     * Embedded class that defines the <code>Builder</code> for <code>PlatformMetaData</code>.
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
         * Sets the <code>OPERATING_SYSTEM</code> key of the hash map.
         *
         * @param operationSystem Value to associate <code>OPERATING_SYSTEM</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setOperationSystem(String operationSystem) {
            metaData.put(OPERATING_SYSTEM, operationSystem);
            return this;
        }

        /**
         * Sets the <code>MANUFACTURER</code> key of the hash map.
         *
         * @param manufacturer Value to associate <code>MANUFACTURER</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setManufacturer(String manufacturer) {
            metaData.put(MANUFACTURER, manufacturer);
            return this;
        }

        /**
         * Sets the <code>MODEL</code> key of the hash map.
         *
         * @param model Value to associate <code>MODEL</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setModel(String model) {
            metaData.put(MODEL, model);
            return this;
        }

        /**
         * Sets the <code>VERSION_FIRMWARE</code> key of the hash map.
         *
         * @param versionFirmware Value to associate <code>VERSION_FIRMWARE</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionFirmware(String versionFirmware) {
            metaData.put(VERSION_FIRMWARE, versionFirmware);
            return this;
        }

        /**
         * Sets the <code>VERSION_HARDWARE</code> key of the hash map.
         *
         * @param versionHardware Value to associate <code>VERSION_HARDWARE</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionHardware(String versionHardware) {
            metaData.put(VERSION_HARDWARE, versionHardware);
            return this;
        }

        /**
         * Sets the <code>DEVICE_ID</code> key of the hash map.
         *
         * @param deviceId Value to associate <code>DEVICE_ID</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDeviceId(String deviceId) {
            metaData.put(DEVICE_ID, deviceId);
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
         * Passes the <code>Builder</code> to the <code>PlatformMetaData</code> constructor.
         *
         * @return The resulting <code>PlatformMetaData</code> object.
         */
        public PlatformMetaData build() {
            return new PlatformMetaData(this);
        }
    }

    /**
     * Constructor
     * This constructor creates an <code>PlatformMetaData</code> object from a <code>Parcel</code>.
     *
     * @param in <code>Parcel</code> containing the <code>PlatformMetaData</code>.
     */
    private PlatformMetaData(Parcel in) {
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
     * Embedded <code>CREATOR</code> class for generating instances of <code>PlatformMetaData</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<PlatformMetaData> CREATOR = new Creator<PlatformMetaData>() {
        /**
         * Creates an <code>PlatformMetaData</code> object from a <code>Parcel</code>.
         * @param in <code>Parcel</code> containing the <code>PlatformMetaData</code>.
         * @return The constructed <code>PlatformMetaData</code>.
         */
        @Override
        public PlatformMetaData createFromParcel(Parcel in) {
            return new PlatformMetaData(in);
        }

        /**
         * Creates an array for <code>PlatformMetaData</code> of the given size.
         * @param size Size of the array to create.
         * @return Returns an array for <code>PlatformMetaData</code> objects.Th
         */
        @Override
        public PlatformMetaData[] newArray(int size) {
            return new PlatformMetaData[size];
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
     * Writes the calling <code>PlatformMetaData</code> to the passed <code>Parcel</code>.
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
        if (toCompare instanceof PlatformMetaData) {
            return ((this.getTitle().equals(((PlatformMetaData) toCompare).getTitle())) &&
                    ((this.getSummary() == null && ((PlatformMetaData) toCompare).getSummary() == null) ||
                            (this.getSummary().equals(((PlatformMetaData) toCompare).getSummary()))) &&
                    (this.getDescription().equals(((PlatformMetaData) toCompare).getDescription())) &&
                    (this.getOperationSystem().equals(((PlatformMetaData) toCompare).getOperationSystem())) &&
                    (this.getManufacturer().equals(((PlatformMetaData) toCompare).getManufacturer())) &&
                    (this.getModel().equals(((PlatformMetaData) toCompare).getModel())) &&
                    ((this.getVersionFirmware() == null && ((PlatformMetaData) toCompare).getVersionFirmware() == null) ||
                            (this.getVersionFirmware().equals(((PlatformMetaData) toCompare).getVersionFirmware()))) &&
                    ((this.getVersionHardware() == null && ((PlatformMetaData) toCompare).getVersionHardware() == null) ||
                            (this.getVersionHardware().equals(((PlatformMetaData) toCompare).getVersionHardware()))) &&
                    ((this.getDeviceId() == null && ((PlatformMetaData) toCompare).getDeviceId() == null) ||
                            (this.getDeviceId().equals(((PlatformMetaData) toCompare).getDeviceId()))));
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
