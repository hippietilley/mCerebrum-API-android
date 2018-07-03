# mCerebrum API
[![Build Status](https://travis-ci.org/MD2Korg/mCerebrum-PhoneSensor.svg?branch=master)](https://travis-ci.org/MD2Korg/mCerebrum-PhoneSensor)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/a21b44949a144915b01fecf2b137d3f2)](https://www.codacy.com/app/twhnat/mCerebrum-PhoneSensor)

# Overview
This android library is the communication API for mCerebrum and is utilized by all applications that wish to communicate with mCerebrum.

# Getting Started
### Setting up the dependency
The first step is to include mCerebrumAPI into your project for example, as a gradle compile dependancy:
```
implementation "org.md2k.mcerebrum.api:core:3.0.0"
```
###Initialize mCerebrumAPI
The second is to initialize mCerebrumAPI once in `Application.onCreate():`
```
mCerebrumAPI.init(context); // access data 
mCerebrumAPI.init(context, myPlugin); //access data and provide control to mCerebrum
```
###Access Data
To access the data, first you need to connect with mCerebrum. Then, you can insert, query or subscribe any datastream. Functions supported by mCerebrum: `connect()`, `disconnect()`, `register()`, `unregister()`, `find()`, `insert()`, `query()`, `querySummary()`, `queryCount()`, `subscribe()`, `unsubscribe()`

#### Example
##### Insert Data to mCerebrum
```java
        mCerebrumAPI.connect(cc);
//...
    ConnectionCallback cc=new ConnectionCallback() {
        @Override
        public void onConnected() {
            DataSourceCreator c = DataSourceCreator.builder("ACCELEROMETER", DataType.DATAPOINT_DOUBLE)
            .setPlatformAsPhone()
            .build();
            Registration r = mCerebrumAPI.register(c);
            long curTime = System.currentTimeMillis();
            double[] d = new double[]{0.0, 9.8, 0.0};
            DataPointDouble data = new DataPointDouble(curTime, d);
            mCerebrumAPI.insert(r, data);
            //...
            mCerebrumAPI.disconnect(cc);
        }

        @Override
        public void onError(MCerebrumException e) {

        }

        @Override
        public void onDisconnected() {

        }
    };
```
##### Subscribe data
##### Query Data
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
