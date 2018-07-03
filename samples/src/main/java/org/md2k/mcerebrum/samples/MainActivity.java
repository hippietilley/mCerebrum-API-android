package org.md2k.mcerebrum.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.md2k.mcerebrum.api.datakitapi.DataSourceCreator;
import org.md2k.mcerebrum.api.datakitapi.DataSourceRequest;
import org.md2k.mcerebrum.api.datakitapi.DataSourceSet;
import org.md2k.mcerebrum.api.datakitapi.Registration;
import org.md2k.mcerebrum.api.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.mCerebrumAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insert(){
        // prepare data source
        DataSourceCreator c = DataSourceCreator.builder("ACCELEROMETER", DataType.DATAPOINT_DOUBLE)
                .setPlatformAsPhone()
                .build();
        // register dataSource
        Registration r = mCerebrumAPI.register(c);
        // prepare a data point
        long curTime = System.currentTimeMillis();
        double[] d = new double[]{0.0, 9.8, 0.0};
        DataPointDouble data = new DataPointDouble(curTime, d);
        // insert the data point
        mCerebrumAPI.insert(r, data);
    }

    public void query() {
        // prepare datasource to check availability in mCerebrum (Here: phone accelerometer)
        DataSourceRequest q = DataSourceRequest.builder()
                .setDataSourceType(DATASOURCE.TYPE.ACCELEROMETER)
                .setPlatformType(PLATFORM.TYPE.PHONE)
                .build();
        // search datasource in mCerebrum
        DataSourceSet dataSourceSet = mCerebrumAPI.find(q);
        // get phone accelerometer data for last 5 seconds
        long curTime = System.currentTimeMillis();
        DataSet dataSet = mCerebrumAPI.query(dataSourceSet.getDataSources()[0], curTime - 5000, curTime);
        /* to get last 5 phone accelerometer data, use following:

            DataSet dataSet = mCerebrumAPI.query(dataSourceSet.getDataSources()[0], 5);

        */
        // measure the size of the received data
        int sampleSize = dataSet.getData().length;
        // iterate data
        for (int i = 0; i < sampleSize; i++) {
            DataPointDouble dataPointDouble = (DataPointDouble) dataSet.getData()[i];
            long timeStamp = dataPointDouble.getTimestamp();
            double[] samples = dataPointDouble.getSample();
            Log.d("mcerebrum", "Time=" + timeStamp + " x=" + samples[0] + " y=" + samples[1] + " z=" + samples[2]);
        }
    }

    public void connect() {
        mCerebrumAPI.connect(cc);
    }

    ConnectionCallback cc = new ConnectionCallback() {
        @Override
        public void onConnected() {
            query();
        }

        @Override
        public void onError(MCerebrumException e) {

        }

        @Override
        public void onDisconnected() {

        }
    };

}
