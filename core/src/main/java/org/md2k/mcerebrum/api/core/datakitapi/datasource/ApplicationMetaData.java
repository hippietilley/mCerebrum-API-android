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
 * Builder class for <code>DataSource</code> objects
 */
public class ApplicationMetaData implements Parcelable{
    private static final String TITLE = "TITLE";
    private static final String SUMMARY = "SUMMARY";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String VERSION_NAME = "VERSION_NAME";
    private static final String VERSION_NUMBER = "VERSION_NUMBER";

    private HashMap<String, String> metaData;

    public String getTitle() {
        return metaData.get(TITLE);
    }

    public String getSummary() {
        return metaData.get(SUMMARY);
    }

    public String getDescription() {
        return metaData.get(DESCRIPTION);
    }

    public String getVersionName() {
        return metaData.get(VERSION_NAME);
    }

    public int getVersionNumber() {
        if(metaData.containsKey(VERSION_NUMBER))
            return Integer.valueOf(metaData.get(VERSION_NUMBER));
        else return -1;
    }

    public String getMetaData(String key){
        return metaData.get(key);
    }
    public String[] getKeys(){
        String[] res=new String[metaData.size()];
        int i=-1;

        // Display the TreeMap which is naturally sorted
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            res[++i]=entry.getKey();
        }
        Arrays.sort(res);
        return res;
    }

    private ApplicationMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ApplicationMetaData applicationMetaData) {
        if(applicationMetaData ==null)
            return new Builder();
        else return new Builder(applicationMetaData.metaData);
    }


    public static class Builder {
        private HashMap<String, String> metaData;

        public Builder() {
            metaData = new HashMap<>();
        }
        Builder(HashMap<String, String> metaData) {
            this.metaData = new HashMap<>();
            this.metaData.putAll(metaData);
        }

        public Builder setTitle(String title) {
            metaData.put(TITLE,title);
            return this;
        }

        public Builder setSummary(String summary) {
            metaData.put(SUMMARY,summary);
            return this;
        }

        public Builder setDescription(String description) {
            metaData.put(DESCRIPTION,description);
            return this;
        }
        public Builder setVersionName(String versionName) {
            metaData.put(VERSION_NAME, versionName);
            return this;
        }
        public Builder setVersionNumber(int versionNumber) {
            metaData.put(VERSION_NUMBER, Integer.toString(versionNumber));
            return this;
        }

        public Builder setMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }
        public Builder setMetaData(HashMap<String, String> metaData){
            for (HashMap.Entry<String, String> entry : metaData.entrySet())
                this.metaData.put(entry.getKey(), entry.getValue());
            return this;
        }

        public ApplicationMetaData build() {
            return new ApplicationMetaData(this);
        }

    }
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
    @Override
    public int describeContents() {
        return 0;
    }

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

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof  DataSourceMetaData) {
            for (String thisKey : this.custom.keySet()) {
                if (!this.custom.get(thisKey).equalsIgnoreCase(((DataSourceMetaData) toCompare).custom.get(thisKey)))
                    return false;
            }
            for (String toCompareKey : ((DataSourceMetaData) toCompare).custom.keySet()) {
                if (!((DataSourceMetaData) toCompare).custom.get(toCompareKey).equalsIgnoreCase(this.custom.get(toCompareKey)))
                    return false;
            }
            if (!(this.title.equals(((DataSourceMetaData) toCompare).title)))
                return false;
            if (!(this.summary.equals(((DataSourceMetaData) toCompare).summary)))
                return false;
            if (!(this.description.equals(((DataSourceMetaData) toCompare).description)))
                return false;
            else
                return true;
        } else
            return false;
    }
}
