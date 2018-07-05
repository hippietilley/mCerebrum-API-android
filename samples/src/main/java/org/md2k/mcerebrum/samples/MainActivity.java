package org.md2k.mcerebrum.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.md2k.mcerebrum.api.core.datakitapi.DataSourceCreator;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceRequest;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceSet;
import org.md2k.mcerebrum.api.core.datakitapi.Registration;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;

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
        Registration r = MCerebrumAPI.register(c);
        // prepare a data point
        long curTime = System.currentTimeMillis();
        double[] d = new double[]{0.0, 9.8, 0.0};
        DataPointDouble data = new DataPointDouble(curTime, d);
        // insert the data point
        MCerebrumAPI.insert(r, data);
    }

    public void query() {
        // prepare datasource to check availability in mCerebrum (Here: phone accelerometer)
        DataSourceRequest q = DataSourceRequest.builder()
                .setDataSourceType(DATASOURCE.TYPE.ACCELEROMETER)
                .setPlatformType(PLATFORM.TYPE.PHONE)
                .build();
        // search datasource in mCerebrum
        DataSourceSet dataSourceSet = MCerebrumAPI.find(q);
        // get phone accelerometer data for last 5 seconds
        long curTime = System.currentTimeMillis();
        DataSet dataSet = MCerebrumAPI.query(dataSourceSet.getDataSources()[0], curTime - 5000, curTime);
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
        MCerebrumAPI.connect(cc);
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
