# mCerebrum API
[![Build Status](https://travis-ci.org/MD2Korg/mCerebrum-PhoneSensor.svg?branch=master)](https://travis-ci.org/MD2Korg/mCerebrum-PhoneSensor)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/a21b44949a144915b01fecf2b137d3f2)](https://www.codacy.com/app/twhnat/mCerebrum-PhoneSensor)

# Overview
This android library is the communication API for mCerebrum and is utilized by all applications that wish to communicate with mCerebrum.

# Getting Started
### Setting up the dependency
The first step is to include mCerebrumAPI into your project for example, as a gradle compile dependancy:
```groovy
implementation "org.md2k.mcerebrum.api:core:3.0.0"
```
### Initialize mCerebrumAPI
The second is to initialize mCerebrumAPI once in `Application.onCreate():`
```java
mCerebrumAPI.init(context); // access data 
mCerebrumAPI.init(context, myPlugin); //access data and provide control to mCerebrum
```
### Access Data
To access the data, first you need to connect with mCerebrum. Then, you can insert, query or subscribe any datastream. Functions supported by mCerebrum: `connect()`, `disconnect()`, `register()`, `unregister()`, `find()`, `insert()`, `query()`, `querySummary()`, `queryCount()`, `subscribe()`, `unsubscribe()`

#### Example
##### Insert Data to mCerebrum
Supported data types: `DataPointBoolean`, `DataPointByte`, `DataPointInt`, `DataPointLong`, `DataPointDouble`, `DataPointString`, `DataPointEnum`, `DataPointObject`. Each of these data types accept arrays.
Steps:
- Connect to mCerebrum
- Create a datasource using `DataSourceCreator` and register it to mCerebrum
- Insert data to mCerebrum with registered id

```java
public class MyClass{
    void myFunc(){
        // connect to mCerebrum
        mCerebrumAPI.connect(cc);
    }

    ConnectionCallback cc=new ConnectionCallback() {
        @Override
        public void onConnected() {
            // prepare data source
            DataSourceCreator c = DataSourceCreator.builder("ACCELEROMETER", DataType.DATAPOINT_DOUBLE)
            .setPlatformAsPhone()
            .build();
            // register dataSource
            Registration r = mCerebrumAPI.register(c);
            // prepare a datapoint
            long curTime = System.currentTimeMillis();
            double[] d = new double[]{0.0, 9.8, 0.0};
            DataPointDouble data = new DataPointDouble(curTime, d);
            // insert the datapoint
            mCerebrumAPI.insert(r, data);
            //disconnect from mCerebrum
            mCerebrumAPI.disconnect(cc);
        }

        @Override
        public void onError(MCerebrumException e) {

        }

        @Override
        public void onDisconnected() {

        }
    };
}
```
##### Subscribe data
##### Query Data
There are 2 ways to query data from mCerebrum. a) **`query by time`** b) **`query last N samples`**.

Steps:
- Connect to mCerebrum
- Create a datasource using `DataSourceRequest` and search using `find()` function
- Query data with specific datasource (returned by `find()` function)

###### Example
```java
    public void query() {
        // prepare datasource to check availability in mCerebrum (Here: phone accelerometer)
        DataSourceQuery q = DataSourceQuery.builder()
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
```
#### Supported function
```
int connect(ConnectionCallback c);
int disconnect(ConnectionCallback c);
Registration register(DataSourceCreator d);
int unregister(Registration r);
DataSourceSet find(DataSourceQuery d);
int insert(Registration r, Data[] d);
DataSet query(DataSource dataSource, int lastNPoint);
DataSet query(DataSource dataSource, long startTimestamp, long endTimestamp);
DataSet querySummary(DataSource dataSource, long startTimestamp, long endTimestamp);
DataSet queryCount(DataSource dataSource, long startTimestamp, long endTimestamp);
int subscribe(DataSource dataSource, DataCallback callback);
int unsubscribe(dataSource dataSource, DataCallback callback);
```

# Release History
- `0.1.0` Initial release

# Contributors
- Syed Monowar Hossain ([monowar](https://github.com/monowar)) <monowar.hossain@gmail.com>
- Timothy Hnat ([twhnat](https://github.com/twhnat)) <twhnat@memphis.edu>

# License
[BSD 2-Clause](LICENSE)
## More information
- [MD2K](https://md2k.org/)
- [Documentation and Training](http://docs.md2k.org)
- [MD2K GitHub Organization](https://github.com/MD2Korg/)

## Provide feedback or submit a bug report
[http://docs.md2k.org/feedback](http://docs.md2k.org/feedback)

# Support
[MD2K](https://md2k.org) is supported by the [National Institutes of Health](https://www.nih.gov/) [Big Data to Knowledge Initiative](https://datascience.nih.gov/bd2k) Grant **#1U54EB020404**

Team: 
[Cornell Tech](http://tech.cornell.edu/), 
[GA Tech](http://www.gatech.edu/), 
[U Memphis](http://www.memphis.edu/), 
[Northwestern](http://www.northwestern.edu/), 
[Ohio State](https://www.osu.edu/), 
[Open mHealth](http://www.openmhealth.org/), 
[Rice](http://www.rice.edu/), 
[UCLA](http://www.ucla.edu/), 
[UCSD](http://www.ucsd.edu/), 
[UCSF](http://www.ucsf.edu/), 
[U Mass](http://www.umass.edu/), 
[U Michigan](https://www.umich.edu/), 
[WVU](http://www.wvu.edu/)
