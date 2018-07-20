# mCerebrum-API-android

mCerebrum is a configurable smartphone software platform for mobile and wearable sensors. It provides support for reliable data collection from mobile and wearable sensors, and offers real-time processing of these data.


This Android library is the communication API for mCerebrum and is utilized by all applications that wish to communicate with mCerebrum.

You can find more information about MD2K software on our [software website](https://md2k.org/software) or the MD2K organization on our [MD2K website](https://md2k.org/).

## Examples

### Setting up the dependency
The first step is to include mCerebrumAPI into your project for example, as a gradle compile dependancy:

```groovy
implementation "org.md2k.mcerebrum.api:core:<latest_version>"
```
(Please replace `<latest_version>` with this: [ ![Download](https://api.bintray.com/packages/md2korg/mCerebrum/core/images/download.svg) ](https://bintray.com/md2korg/mCerebrum/core/_latestVersion)
)

### Initialize mCerebrumAPI
The second is to initialize mCerebrumAPI once in `Application.onCreate():`
```java
mCerebrumAPI.init(context); // access data
mCerebrumAPI.init(context, myPlugin); //access data and provide functionality to control by mCerebrum
```

### Using mCerebrumAPI

To access the data, first you need to connect with mCerebrum. Then, you can insert, query or subscribe any datastream. Functions supported by mCerebrum: `connect()`, `disconnect()`, `register()`, `unregister()`, `find()`, `insert()`, `query()`, `querySummary()`, `queryCount()`, `subscribe()`, `unsubscribe()`

#### Connect to mCerebrum

#### Insert Data to mCerebrum

Supported data types: `DataPointBoolean`, `DataPointByte`, `DataPointInt`, `DataPointLong`, `DataPointDouble`, `DataPointString`, `DataPointEnum`, `DataPointObject`. Each of these data types accept arrays.

Steps:
- Connect to mCerebrum
- Create a datasource using `DataSourceCreator` and register it to mCerebrum using`register()` function
- Insert data to mCerebrum using registered id returned by `register()` function

```java
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
```
#### Subscribe data


#### Query Data

There are 2 ways to query data from mCerebrum. a) **`query by time`** b) **`query last N samples`**.

Steps:
- Connect to mCerebrum
- Create a datasource using `DataSourceRequest` and search using `find()` function
- Query data with specific datasource (returned by `find()` function)

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

#### Supported Functionality

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

## Contributing
Please read our [Contributing Guidelines]() for details on the process for submitting pull requests to us.

We use the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

Our [Code of Conduct](https://md2k.org/software/CodeofConduct) is the [Contributor Covenant](https://www.contributor-covenant.org/).

Bug reports can be submitted through [JIRA](https://md2korg.atlassian.net/secure/Dashboard.jspa).

Our discussion forum can be found [here](https://discuss.md2k.org/).

## Versioning

We use [Semantic Versioning](https://semver.org/) for versioning the software which is based on the following guidelines.

MAJOR.MINOR.PATCH (example: 3.0.12)

  1. MAJOR version when incompatible API changes are made,
  2. MINOR version when functionality is added in a backwards-compatible manner, and
  3. PATCH version when backwards-compatible bug fixes are introduced.

For the versions available, see [this repository's tags](https://github.com/MD2Korg/mCerebrum-API-android/tags).

## Contributors

Link to the [list of contributors](https://github.com/MD2Korg/mCerebrum-API-android/graphs/contributors) who participated in this project.

## License

This project is licensed under the BSD 2-Clause - see the [license](https://md2k.org/software-under-the-hood/software-uth-license) file for details.

## Acknowledgments

* [National Institutes of Health](https://www.nih.gov/) - [Big Data to Knowledge Initiative](https://datascience.nih.gov/bd2k)
  * Grants: R01MD010362, 1UG1DA04030901, 1U54EB020404, 1R01CA190329, 1R01DE02524, R00MD010468, 3UH2DA041713, 10555SC
* [National Science Foundation](https://www.nsf.gov/)
  * Grants: 1640813, 1722646
* [Intelligence Advanced Research Projects Activity](https://www.iarpa.gov/)
  * Contract: 2017-17042800006
