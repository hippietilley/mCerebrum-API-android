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

package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * This class defines the <code>PlatformAppMetaData</code> object. This object provides a structure
 * for organizing the metadata related to the application that runs on the platform that collects a
 * data point. This class implements <code>Parcelable</code> so that the <code>PlatformAppMetaData</code>
 * objects can be parceled with their data points. Metadata of note includes the application title,
 * summary, description, operating system, manufacturer, model, version firmware, version hardware,
 * and device id. These fields are stored in a hash map of strings.
 */
public class PlatformAppMetaData implements Parcelable {
    private String title;
    private String summary;
    private String description;
    private String operationSystem;
    private String manufacturer;
    private String model;
    private String versionFirmware;
    private String versionHardware;
    private String deviceId;
    private HashMap<String, String> custom = new HashMap<>();

    /**
     * Constructor
     * This constructor is empty.
     */
    PlatformAppMetaData() {
    }

    /**
     * Constructor
     * This constructor creates an <code>PlatformAppMetaData</code> object from a <code>Parcel</code>.
     *
     * @param in <code>Parcel</code> containing the <code>PlatformAppMetaData</code>.
     */
    private PlatformAppMetaData(Parcel in) {
        title = in.readString();
        summary = in.readString();
        description = in.readString();
        operationSystem = in.readString();
        manufacturer = in.readString();
        model = in.readString();
        versionFirmware = in.readString();
        versionHardware = in.readString();
        deviceId = in.readString();
        int size = in.readInt();
        if (size == -1) custom = null;
        else {
            custom = new HashMap<>();
            for (int j = 0; j < size; j++) {
                custom.put(in.readString(), in.readString());
            }
        }
    }

    /**
     * Embedded <code>CREATOR</code> class for generating instances of <code>PlatformAppMetaData</code>
     * from a <code>Parcel</code>.
     */
    public static final Creator<PlatformAppMetaData> CREATOR = new Creator<PlatformAppMetaData>() {
        /**
         * Creates an <code>PlatformAppMetaData</code> object from a <code>Parcel</code>.
         * @param in <code>Parcel</code> containing the <code>PlatformAppMetaData</code>.
         * @return The constructed <code>PlatformAppMetaData</code>.
         */
        @Override
        public PlatformAppMetaData createFromParcel(Parcel in) {
            return new PlatformAppMetaData(in);
        }

        /**
         * Creates an array for <code>PlatformAppMetaData</code> of the given size.
         * @param size Size of the array to create.
         * @return Returns an array for <code>PlatformAppMetaData</code> objects.Th
         */
        @Override
        public PlatformAppMetaData[] newArray(int size) {
            return new PlatformAppMetaData[size];
        }
    };

    /**
     * Returns the title.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the summary.
     *
     * @return The summary.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the operating system.
     *
     * @return The operating system.
     */
    public String getOperationSystem() {
        return operationSystem;
    }

    /**
     * Returns the manufacturer.
     *
     * @return The manufacturer.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Returns the model.
     *
     * @return The model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the version firmware.
     *
     * @return The version firmware.
     */
    public String getVersionFirmware() {
        return versionFirmware;
    }

    /**
     * Returns the version hardware.
     *
     * @return The version hardware.
     */
    public String getVersionHardware() {
        return versionHardware;
    }

    /**
     * Returns the device id.
     *
     * @return The device id.
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Returns the value of a custom key.
     *
     * @param key The key to get the value of.
     * @return The value for the given key.
     */
    public String getValue(String key) {
        if (custom == null) return null;
        return custom.get(key);
    }

    /**
     * Constructor
     *
     * @param builder Builder object defining how to construct the <code>PlatformAppMetaData</code>.
     */
    private PlatformAppMetaData(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        description = builder.description;
        operationSystem = builder.operationSystem;
        manufacturer = builder.manufacturer;
        model = builder.model;
        versionFirmware = builder.versionFirmware;
        versionHardware = builder.versionHardware;
        deviceId = builder.deviceId;
        custom = builder.custom;
    }

    /**
     * Creates a new <code>Builder</code> object to define an <code>PlatformAppMetaData</code> object.
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
     * writeToParcel(Parcel, int), the return value of this method must include the CONTENTS_FILE_descriptionbit.
     *
     * @return 0.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes the calling <code>PlatformAppMetaData</code> to the passed <code>Parcel</code>.
     *
     * @param parcel <code>Parcel</code> to write to.
     * @param i      This should always be the value returned from <code>describeContents()</code>.
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(summary);
        parcel.writeString(description);
        parcel.writeString(operationSystem);
        parcel.writeString(manufacturer);
        parcel.writeString(model);
        parcel.writeString(versionFirmware);
        parcel.writeString(versionHardware);
        parcel.writeString(deviceId);
        if (custom == null)
            parcel.writeInt(-1);
        else {
            int size = custom.size();
            parcel.writeInt(size);
            for (HashMap.Entry<String, String> entry : custom.entrySet()) {
                parcel.writeString(entry.getKey());
                parcel.writeString(entry.getValue());
            }
        }

    }

    /**
     * Embedded class that defines the <code>Builder</code> for <code>PlatformAppMetaData</code>.
     */
    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private String operationSystem;
        private String manufacturer;
        private String model;
        private String versionFirmware;
        private String versionHardware;
        private String deviceId;
        private HashMap<String, String> custom = new HashMap<>();

        /**
         * Constructor
         * This constructor is empty.
         */
        Builder() {
        }

        /**
         * Sets the <code>title</code>.
         *
         * @param title Value to associate <code>title</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the <code>summary</code>.
         *
         * @param summary Value to associate <code>summary</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Sets the <code>description</code>.
         *
         * @param description Value to associate <code>description</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the <code>operationSystem</code>.
         *
         * @param operationSystem Value to associate <code>operationSystem</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setOperationSystem(String operationSystem) {
            this.operationSystem = operationSystem;
            return this;
        }

        /**
         * Sets the <code>manufacturer</code>.
         *
         * @param manufacturer Value to associate <code>manufacturer</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        /**
         * Sets the <code>model</code>.
         *
         * @param model Value to associate <code>model</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets the <code>versionFirmware</code>.
         *
         * @param versionFirmware Value to associate <code>versionFirmware</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionFirmware(String versionFirmware) {
            this.versionFirmware = versionFirmware;
            return this;
        }

        /**
         * Sets the <code>versionHardware</code>.
         *
         * @param versionHardware Value to associate <code>versionHardware</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setVersionHardware(String versionHardware) {
            this.versionHardware = versionHardware;
            return this;
        }

        /**
         * Sets the <code>deviceId</code>.
         *
         * @param deviceId Value to associate <code>deviceId</code> to.
         * @return The modified <code>Builder</code>.
         */
        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
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
            this.custom.put(key, value);
            return this;
        }

        /**
         * Passes the <code>Builder</code> to the <code>PlatformAppMetaData</code> constructor.
         *
         * @return The resulting <code>PlatformAppMetaData</code> object.
         */
        public PlatformAppMetaData build() {
            return new PlatformAppMetaData(this);
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
        if (toCompare instanceof PlatformAppMetaData) {
            return ((this.custom.equals(((PlatformAppMetaData) toCompare).custom)) &&
                    (this.title.equals(((PlatformAppMetaData) toCompare).title))) &&
                    (this.summary.equals(((PlatformAppMetaData) toCompare).summary)) &&
                    (this.description.equals(((PlatformAppMetaData) toCompare).description)) &&
                    (this.operationSystem.equals(((PlatformAppMetaData) toCompare).operationSystem)) &&
                    (this.manufacturer.equals(((PlatformAppMetaData) toCompare).manufacturer)) &&
                    (this.model.equals(((PlatformAppMetaData) toCompare).model)) &&
                    (this.versionFirmware.equals(((PlatformAppMetaData) toCompare).versionFirmware)) &&
                    (this.versionHardware.equals(((PlatformAppMetaData) toCompare).versionHardware)) &&
                    (this.deviceId.equals(((PlatformAppMetaData) toCompare).deviceId));
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
        result = 31 * result + title.hashCode();
        result = 31 * result + summary.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + operationSystem.hashCode();
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + versionFirmware.hashCode();
        result = 31 * result + versionHardware.hashCode();
        result = 31 * result + deviceId.hashCode();
        result = 31 * result + custom.hashCode();
        return result;
    }
}
