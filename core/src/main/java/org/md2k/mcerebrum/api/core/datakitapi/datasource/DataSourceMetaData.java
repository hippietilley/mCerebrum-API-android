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

import org.md2k.mcerebrum.api.core.datakitapi.DataSourceCreator;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Builder class for <code>DataSource</code> objects
 */
public class DataSourceMetaData implements Parcelable {
    /**
     * Title of the data source
     */
    private static final String TITLE = "TITLE";

    /**
     * Summary of the data source
     */
    private static final String SUMMARY = "SUMMARY";

    /**
     * Description of what the data source is.
     */
    private static final String DESCRIPTION = "DESCRIPTION";

    /**
     * Description of what the data source is.
     */
    private static final String DATA_RATE = "DATA_RATE";

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


    public String getMetaData(String key) {
        return metaData.get(key);
    }


    private DataSourceMetaData(Builder builder) {
        this.metaData = new HashMap<>();
        this.metaData.putAll(builder.metaData);
    }


    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DataSourceMetaData dataSourceMetaData) {
        if (dataSourceMetaData == null)
            return new Builder();
        else return new Builder(dataSourceMetaData.metaData);
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
            this.metaData.put(TITLE, title);
            return this;
        }

        public Builder setSummary(String summary) {
            this.metaData.put(SUMMARY, summary);
            return this;
        }

        public Builder setDescription(String description) {
            this.metaData.put(DESCRIPTION, description);
            return this;
        }

        public Builder setDataRateAsOnChange() {
            this.metaData.put(DATA_RATE, "ON_CHANGE");
            return this;
        }

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

        public Builder setMetaData(String key, String value) {
            this.metaData.put(key, value);
            return this;
        }

        public Builder setMetaData(HashMap<String, String> metaData) {
            for (HashMap.Entry<String, String> entry : metaData.entrySet())
                this.metaData.put(entry.getKey(), entry.getValue());
            return this;
        }

        public DataSourceMetaData build() {
            return new DataSourceMetaData(this);
        }
    }

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

    public static final Creator<DataSourceMetaData> CREATOR = new Creator<DataSourceMetaData>() {
        @Override
        public DataSourceMetaData createFromParcel(Parcel in) {
            return new DataSourceMetaData(in);
        }

        @Override
        public DataSourceMetaData[] newArray(int size) {
            return new DataSourceMetaData[size];
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
        if (toCompare instanceof DataSourceMetaData) {
            return ((this.getTitle().equals(((DataSourceMetaData) toCompare).getTitle())) &&
                    (this.getSummary().equals(((DataSourceMetaData) toCompare).getSummary())) &&
                    (this.getDescription().equals(((DataSourceMetaData) toCompare).getDescription())) &&
                    (this.getMetaData(DATA_RATE).equals(((DataSourceMetaData) toCompare).getMetaData(DATA_RATE))));
        } else
            return false;
    }
}