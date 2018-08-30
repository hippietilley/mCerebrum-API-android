package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class DataSourceCreatorAndroidUnitTest {
    static final double DELTA = TestingConstants.DELTA;

    String[] dataSourceTypeArray = TestingConstants.DATA_SOURCE_TYPE_ARRAY;
    DataType[] dataTypeArray = TestingConstants.DATA_TYPE_ARRAY;
    String[] dataSourceIdArray = TestingConstants.DATASOURCE_ID_ARRAY;
    String[] platformTypeArray = TestingConstants.PLATFORM_TYPE_ARRAY;
    String[] platformIdArray = TestingConstants.PLATFORM_ID_ARRAY;
    String[] platformAppTypeArray = TestingConstants.PLATFORM_APP_TYPE_ARRAY;
    String[] platformAppIdArray = TestingConstants.PLATFORM_APP_ID_ARRAY;
    String[] applicationTypeArray = TestingConstants.APPLICATION_TYPE_ARRAY;
    String[] applicationIdArray = TestingConstants.APPLICATION_ID_ARRAY;
    TimeUnit[] timeUnitArray = TestingConstants.TIME_UNITS;

    DataSourceCreator testDataSourceCreator;
    DataSourceMetaData testDataSourceMetaData;
    PlatformMetaData testPlatformMetaData;
    PlatformAppMetaData testPlatformAppMetaData;
    ApplicationMetaData testAppMetaData;
    DataDescriptor testDataDescriptor;
<<<<<<< HEAD
=======
    final DataType[] dataTypeArray = {DataType.DATAPOINT_BOOLEAN, DataType.DATAPOINT_BYTE,
            DataType.DATAPOINT_INT, DataType.DATAPOINT_LONG, DataType.DATAPOINT_DOUBLE,
            DataType.DATAPOINT_STRING, DataType.DATAPOINT_ENUM, DataType.DATAPOINT_OBJECT,
            DataType.DATAANNOTATION_ENUM, DataType.UNKNOWN};

    // Variable for Platform and Application metadata objects
    private final String testTitle = "Android Phone";
    private final String testSummary = "Android Phone";
    private final String testDescription = "Test Description";
    private final String testOperationSystem = "Android " + Build.VERSION.RELEASE;
    private final String testManufacturer = Build.MANUFACTURER;
    private final String testModel = Build.MODEL;
    private final String testVersionFirmware = "Test Version Firmware";
    private final String testVersionHardware = "Test Version Hardware";
    private String testVersionName = "Test version";
    private int testVersionNumber = 1;
    private final double testMinValue = 3.14;
    private final double testMaxValue = 6.28;
    private final String[] testPossibleValuesAsString = {"3.14", "4", "5", "6", "6.28"};
    private final int[] testPossibleValuesAsInt = {3, 4, 5, 6};
    private final String testUnit = "Test Unit";
    private final String testDeviceId = "Test Device ID";
    private final String testKey = "Test Key";
    private final String testValue = "Test Value";
    private DataSourceCreator testDataSourceCreator;
    private final TimeUnit[] timeUnitArray = {TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES,
            TimeUnit.SECONDS, TimeUnit.MILLISECONDS};
    private final int testSampleNo = 10;
>>>>>>> upstream/master
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;

    @Before
    public void objectCreation(){
        testPlatformMetaData = CommonObjectConstructors.createPlatformMetaData();
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();
        testAppMetaData = CommonObjectConstructors.createApplicationMetaData();
        testDataDescriptor = CommonObjectConstructors.createDataDescriptor();
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        MCerebrumAPI.init(testContext);
    }

    @Test
    public void dataSourceTypeTest() {
        for (String dataSourceType : dataSourceTypeArray) {
            for (DataType dataType : dataTypeArray) {
                testDataSourceCreator = DataSourceCreator.builder(dataSourceType, dataType).build();
                assertEquals(dataType.name(), testDataSourceCreator.getDataType());
            }
            assertEquals(dataSourceType, testDataSourceCreator.getDataSourceType());
        }
    }

    @Test
    public void dataSourceIdTest() {
        for (String dataSourceId : dataSourceIdArray) {
             testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(dataSourceId).build();
             assertEquals(dataSourceId, testDataSourceCreator.getDataSourceId());
        }
    }

    @Test
    public void platformTypeTest() {
        for (String platformType : platformTypeArray) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformType(platformType).build();
            assertEquals(platformType, testDataSourceCreator.getPlatformType());
        }
    }

    @Test
    public void platformIdTest() {
        for (String platformId : platformIdArray) {
             testDataSourceCreator = new DataSourceCreator.Builder().setPlatformId(platformId).build();
             assertEquals(platformId, testDataSourceCreator.getPlatformId());
        }
    }

    @Test
    public void platformAppTypeTest() {
        for (String platformAppType : platformAppTypeArray) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppType(platformAppType).build();
            assertEquals(platformAppType, testDataSourceCreator.getPlatformAppType());
        }
    }

    @Test
    public void platformAppIdTest() {
        for (String platformAppId : platformAppIdArray) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppId(platformAppId).build();
            assertEquals(platformAppId, testDataSourceCreator.getPlatformAppId());
        }
    }

    @Test
    public void applicationTypeTest() {
        for (String applicationType : applicationTypeArray) {
            testDataSourceCreator = new DataSourceCreator.Builder().setApplicationType(applicationType).build();
            assertEquals(applicationType, testDataSourceCreator.getApplicationType());
        }
    }

    @Test
    public void applicationIdTest() {
        for (String applicationId : applicationIdArray) {
            testDataSourceCreator = new DataSourceCreator.Builder().setApplicationId(applicationId).build();
            assertEquals(applicationId, testDataSourceCreator.getApplicationId());
        }
    }

    @Test
    public void platformMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformMetadata(testPlatformMetaData).build();
        assertEquals(testPlatformMetaData.getTitle(), testDataSourceCreator.getPlatformMetaData().getTitle());
        assertEquals(testPlatformMetaData.getSummary(), testDataSourceCreator.getPlatformMetaData().getSummary());
        assertEquals(testPlatformMetaData.getDescription(), testDataSourceCreator.getPlatformMetaData().getDescription());
        assertEquals(testPlatformMetaData.getOperationSystem(), testDataSourceCreator.getPlatformMetaData().getOperationSystem());
        assertEquals(testPlatformMetaData.getManufacturer(), testDataSourceCreator.getPlatformMetaData().getManufacturer());
        assertEquals(testPlatformMetaData.getModel(), testDataSourceCreator.getPlatformMetaData().getModel());
        assertEquals(testPlatformMetaData.getVersionFirmware(), testDataSourceCreator.getPlatformMetaData().getVersionFirmware());
        assertEquals(testPlatformMetaData.getVersionHardware(), testDataSourceCreator.getPlatformMetaData().getVersionHardware());
        assertEquals(testPlatformMetaData.getDeviceId(), testDataSourceCreator.getPlatformMetaData().getDeviceId());
        assertEquals(testPlatformMetaData.getValue(TestingConstants.TEST_KEY),
                testDataSourceCreator.getPlatformMetaData().getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void platformMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformMetadata(testPlatformMetaData).build();
        assertEquals(testPlatformMetaData, testDataSourceCreator.getPlatformMetaData());
    }

    @Test
    public void platformAppMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppMetadata(testPlatformAppMetaData).build();
        assertEquals(testPlatformAppMetaData.getTitle(), testDataSourceCreator.getPlatformAppMetaData().getTitle());
        assertEquals(testPlatformAppMetaData.getSummary(), testDataSourceCreator.getPlatformAppMetaData().getSummary());
        assertEquals(testPlatformAppMetaData.getDescription(), testDataSourceCreator.getPlatformAppMetaData().getDescription());
        assertEquals(testPlatformAppMetaData.getOperationSystem(), testDataSourceCreator.getPlatformAppMetaData().getOperationSystem());
        assertEquals(testPlatformAppMetaData.getManufacturer(), testDataSourceCreator.getPlatformAppMetaData().getManufacturer());
        assertEquals(testPlatformAppMetaData.getModel(), testDataSourceCreator.getPlatformAppMetaData().getModel());
        assertEquals(testPlatformAppMetaData.getVersionFirmware(), testDataSourceCreator.getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testPlatformAppMetaData.getVersionHardware(), testDataSourceCreator.getPlatformAppMetaData().getVersionHardware());
        assertEquals(testPlatformAppMetaData.getDeviceId(), testDataSourceCreator.getPlatformAppMetaData().getDeviceId());
        assertEquals(testPlatformAppMetaData.getValue(TestingConstants.TEST_KEY), testDataSourceCreator.getPlatformAppMetaData().getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void platformAppMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppMetadata(testPlatformAppMetaData).build();
        assertEquals(testPlatformAppMetaData, testDataSourceCreator.getPlatformAppMetaData());
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        assertEquals(testAppMetaData.getTitle(), testDataSourceCreator.getApplicationMetaData().getTitle());
        assertEquals(testAppMetaData.getSummary(), testDataSourceCreator.getApplicationMetaData().getSummary());
        assertEquals(testAppMetaData.getDescription(), testDataSourceCreator.getApplicationMetaData().getDescription());
        assertEquals(testAppMetaData.getVersionName(), testDataSourceCreator.getApplicationMetaData().getVersionName());
        assertEquals(testAppMetaData.getVersionNumber(), testDataSourceCreator.getApplicationMetaData().getVersionNumber());
        assertEquals(testAppMetaData.getValue(TestingConstants.TEST_KEY), testDataSourceCreator.getApplicationMetaData().getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void applicationMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        assertEquals(testAppMetaData, testDataSourceCreator.getApplicationMetaData());
    }

    @Test
    public void dataRateTest() {
        double hoursInDay = 24.0;
        double minInHour = 60.0;
        double secInMin = 60.0;
        // Test days
        testDataSourceCreator = new DataSourceCreator.Builder()
                .setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[0]).build();
        assertEquals(Double.toString((TestingConstants.TEST_SAMPLE_NO) / (hoursInDay * minInHour * secInMin)),
                testDataSourceCreator.getDataRate());
        // Test hours
        testDataSourceCreator = new DataSourceCreator.Builder()
                .setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[1]).build();
        assertEquals(Double.toString((TestingConstants.TEST_SAMPLE_NO) / (minInHour * secInMin)), testDataSourceCreator.getDataRate());
        // Test minutes
        testDataSourceCreator = new DataSourceCreator.Builder()
                .setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[2]).build();
        assertEquals(Double.toString((TestingConstants.TEST_SAMPLE_NO) / (secInMin)), testDataSourceCreator.getDataRate());
        // Test seconds
        testDataSourceCreator = new DataSourceCreator.Builder()
                .setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[3]).build();
        assertEquals(Double.toString(TestingConstants.TEST_SAMPLE_NO), testDataSourceCreator.getDataRate());
        // Test default (TimeUnit.MILLISECONDS)
        testDataSourceCreator = new DataSourceCreator.Builder()
                .setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[4]).build();
        assertEquals(Double.toString(TestingConstants.TEST_SAMPLE_NO), testDataSourceCreator.getDataRate());
    }

    @Test
    public void platformAsPhoneTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAsPhone().build();
        assertEquals(PLATFORM.TYPE.PHONE, testDataSourceCreator.getPlatformType());
        assertNotNull(testDataSourceCreator.getPlatformId());
        assertEquals(TestingConstants.TEST_TITLE, testDataSourceCreator.getPlatformMetaData().getTitle());
        //assertEquals(TestingConstants.TEST_SUMMARY, testDataSourceCreator.getPlatformMetaData().getSummary()); This is also commented out in DataSourceCreator.setPlatformAsPhone()
        assertEquals(TestingConstants.TEST_OPERATING_SYSTEM, testDataSourceCreator.getPlatformMetaData().getOperationSystem());
        assertEquals(TestingConstants.TEST_MANUFACTURER, testDataSourceCreator.getPlatformMetaData().getManufacturer());
        assertEquals(TestingConstants.TEST_MODEL, testDataSourceCreator.getPlatformMetaData().getModel());
    }

    @Test
    public void platformAsPhoneComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAsPhone().build();
        assertEquals(PLATFORM.TYPE.PHONE, testDataSourceCreator.getPlatformType());
        assertNotNull(testDataSourceCreator.getPlatformId());
        assertEquals(testPlatformMetaData, testDataSourceCreator.getPlatformMetaData());
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataDescriptor(0, testDataDescriptor).build();
        assertEquals(testDataDescriptor.getTitle(), testDataSourceCreator.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataDescriptor.getSummary(), testDataSourceCreator.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataDescriptor.getDescription(), testDataSourceCreator.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataDescriptor.getMinValue(),
                testDataSourceCreator.getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataDescriptor.getMaxValue(),
                testDataSourceCreator.getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsString(),
                testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsInt(),
                testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataDescriptor.getUnit(), testDataSourceCreator.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataDescriptor.getValue(TestingConstants.TEST_KEY), testDataSourceCreator.getDataDescriptors().get(0).getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void dataDescriptorComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataDescriptor(0, testDataDescriptor).build();
        for (DataDescriptor dataDescriptorToCheck : testDataSourceCreator.getDataDescriptors())
            assertEquals(testDataDescriptor, dataDescriptorToCheck);
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertEquals(testDataSourceMetaData.getTitle(), testDataSourceCreator.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), testDataSourceCreator.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), testDataSourceCreator.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceMetaData.getValue(TestingConstants.TEST_KEY), testDataSourceCreator.getDataSourceMetaData().getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void dataSourceMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertEquals(testDataSourceMetaData, testDataSourceCreator.getDataSourceMetaData());
    }

    @Test
    public void dataSourceCreator_ParcelableWriteReadTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(dataSourceIdArray[0])
                .setPlatformType(platformTypeArray[0]).setPlatformId(platformIdArray[0])
                .setPlatformAppType(platformAppTypeArray[0]).setPlatformAppId(platformAppIdArray[0])
                .setApplicationType(applicationTypeArray[0]).setDataSourceMetadata(testDataSourceMetaData)
                .setPlatformMetadata(testPlatformMetaData).setPlatformAppMetadata(testPlatformAppMetaData)
                .setApplicationMetaData(testAppMetaData).setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[0])
                .setDataDescriptor(0, testDataDescriptor).build();

        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceCreator.writeToParcel(parcel , testDataSourceCreator.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceCreator createdFromParcel = DataSourceCreator.CREATOR.createFromParcel(parcel);
        DataSourceCreator[] createdFromParcelArray = DataSourceCreator.CREATOR.newArray(1);

        // Verify the results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataSourceCreator.getDataSourceId(), createdFromParcel.getDataSourceId());
        assertEquals(testDataSourceCreator.getPlatformType(), createdFromParcel.getPlatformType());
        assertEquals(testDataSourceCreator.getPlatformId(), createdFromParcel.getPlatformId());
        assertEquals(testDataSourceCreator.getPlatformAppType(), createdFromParcel.getPlatformAppType());
        assertEquals(testDataSourceCreator.getPlatformAppId(), createdFromParcel.getPlatformAppId());
        assertEquals(testDataSourceCreator.getApplicationType(), createdFromParcel.getApplicationType());

        // Test the DataSourceMetaData for equality
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getTitle(),
                createdFromParcel.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getSummary(),
                createdFromParcel.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getDescription(),
                createdFromParcel.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getValue(TestingConstants.TEST_KEY),
                createdFromParcel.getDataSourceMetaData().getValue(TestingConstants.TEST_KEY));

        // Test the PlatformMetaData for equality
        assertEquals(testDataSourceCreator.getPlatformMetaData().getTitle(),
                createdFromParcel.getPlatformMetaData().getTitle());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getSummary(),
                createdFromParcel.getPlatformMetaData().getSummary());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getDescription(),
                createdFromParcel.getPlatformMetaData().getDescription());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getOperationSystem(),
                createdFromParcel.getPlatformMetaData().getOperationSystem());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getManufacturer(),
                createdFromParcel.getPlatformMetaData().getManufacturer());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getModel(),
                createdFromParcel.getPlatformMetaData().getModel());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getVersionFirmware(),
                createdFromParcel.getPlatformMetaData().getVersionFirmware());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getVersionHardware(),
                createdFromParcel.getPlatformMetaData().getVersionHardware());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getDeviceId(),
                createdFromParcel.getPlatformMetaData().getDeviceId());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getValue(TestingConstants.TEST_KEY),
                createdFromParcel.getPlatformMetaData().getValue(TestingConstants.TEST_KEY));

        // Test PlatformAppMetaData for equality
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getTitle(),
                createdFromParcel.getPlatformAppMetaData().getTitle());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getSummary(),
                createdFromParcel.getPlatformAppMetaData().getSummary());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getDescription(),
                createdFromParcel.getPlatformAppMetaData().getDescription());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getOperationSystem(),
                createdFromParcel.getPlatformAppMetaData().getOperationSystem());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getManufacturer(),
                createdFromParcel.getPlatformAppMetaData().getManufacturer());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getModel(),
                createdFromParcel.getPlatformAppMetaData().getModel());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getVersionFirmware(),
                createdFromParcel.getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getVersionHardware(),
                createdFromParcel.getPlatformAppMetaData().getVersionHardware());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getDeviceId(),
                createdFromParcel.getPlatformAppMetaData().getDeviceId());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getValue(TestingConstants.TEST_KEY),
                createdFromParcel.getPlatformAppMetaData().getValue(TestingConstants.TEST_KEY));

        // Test ApplicationMetaData for equality
        assertEquals(testDataSourceCreator.getApplicationMetaData().getTitle(),
                createdFromParcel.getApplicationMetaData().getTitle());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getSummary(),
                createdFromParcel.getApplicationMetaData().getSummary());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getDescription(),
                createdFromParcel.getApplicationMetaData().getDescription());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getVersionName(),
                createdFromParcel.getApplicationMetaData().getVersionName());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getVersionNumber(),
                createdFromParcel.getApplicationMetaData().getVersionNumber());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getValue(TestingConstants.TEST_KEY),
                createdFromParcel.getApplicationMetaData().getValue(TestingConstants.TEST_KEY));

        assertEquals(testDataSourceCreator.getDataRate(), createdFromParcel.getDataRate());

        // Test DataDescriptors for equality
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getTitle(),
                createdFromParcel.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getSummary(),
                createdFromParcel.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getDescription(),
                createdFromParcel.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getMinValue(),
                createdFromParcel.getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getMaxValue(),
                createdFromParcel.getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsString(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsInt(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getUnit(),
                createdFromParcel.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getValue(TestingConstants.TEST_KEY),
                createdFromParcel.getDataDescriptors().get(0).getValue(TestingConstants.TEST_KEY));
    }

    @Test
    public void dataSourceCreator_ParcelableWriteReadComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(dataSourceIdArray[0])
                .setPlatformType(platformTypeArray[0]).setPlatformId(platformIdArray[0])
                .setPlatformAppType(platformAppTypeArray[0]).setPlatformAppId(platformAppIdArray[0])
                .setApplicationType(applicationTypeArray[0]).setDataSourceMetadata(testDataSourceMetaData)
                .setPlatformMetadata(testPlatformMetaData).setPlatformAppMetadata(testPlatformAppMetaData)
                .setApplicationMetaData(testAppMetaData).setDataRate(TestingConstants.TEST_SAMPLE_NO, timeUnitArray[0])
                .setDataDescriptor(0, testDataDescriptor).build();

        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceCreator.writeToParcel(parcel, testDataSourceCreator.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceCreator createdFromParcel = DataSourceCreator.CREATOR.createFromParcel(parcel);
        DataSourceCreator[] createdFromParcelArray = DataSourceCreator.CREATOR.newArray(1);

        // Verify the results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataSourceCreator, createdFromParcel);
    }
}
