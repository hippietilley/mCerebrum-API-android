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
 * Builder class for <code>DataSource</code> objects
 */
public class ApplicationMetaData implements Parcelable {
    private String title;
    private String summary;
    private String description;
    private String versionName;
    private int versionNumber;
    private HashMap<String, String> custom = new HashMap<>();

    private ApplicationMetaData() {
    }

    protected ApplicationMetaData(Parcel in) {
        title = in.readString();
        summary = in.readString();
        description = in.readString();
        versionName = in.readString();
        versionNumber = in.readInt();
        int size = in.readInt();
        if (size == -1) custom = null;
        else {
            custom = new HashMap<>();
            for (int j = 0; j < size; j++) {
                custom.put(in.readString(), in.readString());
            }
        }
    }

    public static final Creator<ApplicationMetaData> CREATOR = new Creator<ApplicationMetaData>() {
        @Override
        public ApplicationMetaData createFromParcel(Parcel in) {
            return new ApplicationMetaData(in);
        }

        @Override
        public ApplicationMetaData[] newArray(int size) {
            return new ApplicationMetaData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public String getValue(String key) {
        if (custom == null) return null;
        return custom.get(key);
    }

    private ApplicationMetaData(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        description = builder.description;
        versionName = builder.versionName;
        versionNumber = builder.versionNumber;
        custom = builder.custom;
    }

    protected void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    protected void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
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
        parcel.writeString(title);
        parcel.writeString(summary);
        parcel.writeString(description);
        parcel.writeString(versionName);
        parcel.writeInt(versionNumber);
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

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private String versionName;
        private int versionNumber;
        private HashMap<String, String> custom = new HashMap<>();

        Builder() {
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setVersionName(String versionName) {
            this.versionName = versionName;
            return this;
        }

        public Builder setVersionNumber(int versionNumber) {
            this.versionNumber = versionNumber;
            return this;
        }

        public Builder setValue(String key, String value) {
            this.custom.put(key, value);
            return this;
        }

        public ApplicationMetaData build() {
            return new ApplicationMetaData(this);
        }
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof ApplicationMetaData) {
            for (String thisKey : this.custom.keySet()) {
                if (!this.custom.get(thisKey).equalsIgnoreCase(((ApplicationMetaData) toCompare).custom.get(thisKey)))
                    return false;
            }
            for (String toCompareKey : ((ApplicationMetaData) toCompare).custom.keySet()) {
                if (!((ApplicationMetaData) toCompare).custom.get(toCompareKey).equalsIgnoreCase(this.custom.get(toCompareKey)))
                    return false;
            }
            
            return (this.title.equals(((ApplicationMetaData) toCompare).title)) &&
                   (this.summary.equals(((ApplicationMetaData) toCompare).summary)) &&
                   (this.description.equals(((ApplicationMetaData) toCompare).description)) &&
                   (this.versionName.equals(((ApplicationMetaData) toCompare).versionName)) &&
                   (this.versionNumber == ((ApplicationMetaData) toCompare).versionNumber);
        } else
            return false;
    }
}
