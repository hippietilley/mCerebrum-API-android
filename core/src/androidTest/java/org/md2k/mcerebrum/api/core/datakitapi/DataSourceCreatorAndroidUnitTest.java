package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DataSourceCreatorAndroidUnitTest {
    final String[] dataSourceTypeArray = {DATASOURCE.TYPE.ACCELEROMETER, DATASOURCE.TYPE.GYROSCOPE,
            DATASOURCE.TYPE.COMPASS, DATASOURCE.TYPE.AMBIENT_LIGHT, DATASOURCE.TYPE.PRESSURE,
            DATASOURCE.TYPE.PROXIMITY, DATASOURCE.TYPE.LOCATION, DATASOURCE.TYPE.GEOFENCE,
            DATASOURCE.TYPE.DISTANCE, DATASOURCE.TYPE.HEART_RATE, DATASOURCE.TYPE.SPEED,
            DATASOURCE.TYPE.STEP_COUNT, DATASOURCE.TYPE.PACE, DATASOURCE.TYPE.MOTION_TYPE,
            DATASOURCE.TYPE.ULTRA_VIOLET_RADIATION, DATASOURCE.TYPE.BAND_CONTACT, DATASOURCE.TYPE.CALORY_BURN,
            DATASOURCE.TYPE.ECG, DATASOURCE.TYPE.RESPIRATION, DATASOURCE.TYPE.RESPIRATION_BASELINE,
            DATASOURCE.TYPE.RESPIRATION_RAW, DATASOURCE.TYPE.ALTIMETER, DATASOURCE.TYPE.AIR_PRESSURE,
            DATASOURCE.TYPE.RR_INTERVAL, DATASOURCE.TYPE.AMBIENT_TEMPERATURE, DATASOURCE.TYPE.SKIN_TEMPERATURE,
            DATASOURCE.TYPE.BATTERY, DATASOURCE.TYPE.CPU, DATASOURCE.TYPE.MEMORY, DATASOURCE.TYPE.AUTOSENSE,
            DATASOURCE.TYPE.ACCELEROMETER_X, DATASOURCE.TYPE.ACCELEROMETER_Y, DATASOURCE.TYPE.ACCELEROMETER_Z,
            DATASOURCE.TYPE.GYROSCOPE_X, DATASOURCE.TYPE.GYROSCOPE_Y, DATASOURCE.TYPE.GYROSCOPE_Z,
            DATASOURCE.TYPE.EMA, DATASOURCE.TYPE.STATUS, DATASOURCE.TYPE.LOG, DATASOURCE.TYPE.TIMEZONE,
            DATASOURCE.TYPE.PRIVACY, DATASOURCE.TYPE.STUDY_INFO, DATASOURCE.TYPE.USER_INFO,
            DATASOURCE.TYPE.SLEEP, DATASOURCE.TYPE.WAKEUP, DATASOURCE.TYPE.DAY_START, DATASOURCE.TYPE.DAY_END,
            DATASOURCE.TYPE.STRESS_PROBABILITY, DATASOURCE.TYPE.STRESS_LABEL, DATASOURCE.TYPE.STUDY_START,
            DATASOURCE.TYPE.STUDY_END, DATASOURCE.TYPE.STRESS_ACTIVITY, DATASOURCE.TYPE.CSTRESS_FEATURE_VECTOR,
            DATASOURCE.TYPE.ORG_MD2K_CSTRESS_DATA_RIP_QUALITY, DATASOURCE.TYPE.ORG_MD2K_CSTRESS_DATA_ECG_QUALITY,
            DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_CLASSIFICATION,
            DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_ARRAY_CLASSIFICATION_FULL_EPISODE,
            DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_START, DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_PEAK,
            DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_END, DATASOURCE.TYPE.CSTRESS_FEATURE_VECTOR_RIP,
            DATASOURCE.TYPE.STRESS_RIP_PROBABILITY, DATASOURCE.TYPE.STRESS_RIP_LABEL, DATASOURCE.TYPE.ACTIVITY,
            DATASOURCE.TYPE.PUFF_PROBABILITY, DATASOURCE.TYPE.PUFF_LABEL, DATASOURCE.TYPE.PUFFMARKER_FEATURE_VECTOR,
            DATASOURCE.TYPE.PUFFMARKER_SMOKING_EPISODE, DATASOURCE.TYPE.NOTIFICATION_REQUEST,
            DATASOURCE.TYPE.NOTIFICATION_ACKNOWLEDGE, DATASOURCE.TYPE.NOTIFICATION_RESPONSE,
            DATASOURCE.TYPE.DATA_QUALITY, DATASOURCE.TYPE.DATA_VARIANCE, DATASOURCE.TYPE.TYPE_OF_DAY,
            DATASOURCE.TYPE.EVENT, DATASOURCE.TYPE.INCENTIVE, DATASOURCE.TYPE.BLOOD_PRESSURE,
            DATASOURCE.TYPE.WEIGHT, DATASOURCE.TYPE.ORALB_PRESSURE, DATASOURCE.TYPE.ORALB_CONNECTION,
            DATASOURCE.TYPE.ORALB_SECTOR, DATASOURCE.TYPE.ORALB_BRUSHING_MODE,
            DATASOURCE.TYPE.ORALB_BRUSHING_STATE, DATASOURCE.TYPE.ORALB_BUSHING_TIME,
            DATASOURCE.TYPE.ACTIVITY_TYPE, DATASOURCE.TYPE.LED, DATASOURCE.TYPE.SEQUENCE_NUMBER,
            DATASOURCE.TYPE.SMOKING, DATASOURCE.TYPE.RAW, DATASOURCE.TYPE.POST_QUIT,
            DATASOURCE.TYPE.PRE_QUIT, DATASOURCE.TYPE.BEACON, DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_MINUTE,
            DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_HOUR, DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_DAY,
            DATASOURCE.TYPE.GALVANIC_SKIN_RESPONSE, DATASOURCE.TYPE.TOUCH_SCREEN,
            DATASOURCE.TYPE.WORK_ANNOTATION, DATASOURCE.TYPE.CU_IS_SCREEN_ON,
            DATASOURCE.TYPE.CU_AUDIO_FEATURE, DATASOURCE.TYPE.CU_AUDIO_ENERGY,
            DATASOURCE.TYPE.CU_AUDIO_INFERENCE, DATASOURCE.TYPE.CU_APPUSAGE, DATASOURCE.TYPE.CU_SMS_NUMBER,
            DATASOURCE.TYPE.CU_SMS_TYPE, DATASOURCE.TYPE.CU_SMS_LENGTH, DATASOURCE.TYPE.CU_CALL_NUMBER,
            DATASOURCE.TYPE.CU_SMS_TYPE, DATASOURCE.TYPE.CU_CALL_DURATION, DATASOURCE.TYPE.LABEL,
            DATASOURCE.TYPE.MAGNETOMETER, DATASOURCE.TYPE.QUATERNION, DATASOURCE.TYPE.MAGNETOMETER_SENSITIVITY,
            DATASOURCE.TYPE.ORALB_BRUSHING_TIME, DATASOURCE.TYPE.ORALB_BRUSH_ACCELEROMETER};
    final String[] dataSourceIdArray = {DATASOURCE.ID.SMOKING, DATASOURCE.ID.EATING, DATASOURCE.ID.SELF_REPORT};
    DataSourceMetaData testDataSourceMetaData;
    final String[] platformTypeArray = {PLATFORM.TYPE.AUTOSENSE_CHEST, PLATFORM.TYPE.AUTOSENSE_BLE,
            PLATFORM.TYPE.AUTOSENSE_WRIST, PLATFORM.TYPE.MICROSOFT_BAND, PLATFORM.TYPE.PHONE,
            PLATFORM.TYPE.OMRON_BLOOD_PRESSURE, PLATFORM.TYPE.OMRON_WEIGHT_SCALE, PLATFORM.TYPE.EASYSENSE,
            PLATFORM.TYPE.MOTION_SENSE, PLATFORM.TYPE.MOTION_SENSE_HRV, PLATFORM.TYPE.MOTION_SENSE_HRV_PLUS,
            PLATFORM.TYPE.ORALB_BRUSH, PLATFORM.TYPE.BEACON};
    final String[] platformIdArray = {PLATFORM.ID.CHEST, PLATFORM.ID.LEFT_WRIST, PLATFORM.ID.RIGHT_WRIST};
    final String[] platformAppTypeArray = {PLATFORM_APP.TYPE.AUTOSENSE_CHEST, PLATFORM_APP.TYPE.AUTOSENSE_BLE,
            PLATFORM_APP.TYPE.AUTOSENSE_WRIST, PLATFORM_APP.TYPE.MICROSOFT_BAND, PLATFORM_APP.TYPE.PHONE,
            PLATFORM_APP.TYPE.OMRON_BLOOD_PRESSURE, PLATFORM_APP.TYPE.OMRON_WEIGHT_SCALE,
            PLATFORM_APP.TYPE.EASYSENSE, PLATFORM_APP.TYPE.MOTION_SENSE, PLATFORM_APP.TYPE.MOTION_SENSE_HRV,
            PLATFORM_APP.TYPE.MOTION_SENSE_HRV_PLUS, PLATFORM_APP.TYPE.ORALB_BRUSH, PLATFORM_APP.TYPE.BEACON};
    final String[] platformAppIdArray = {PLATFORM_APP.ID.CHEST, PLATFORM_APP.ID.LEFT_WRIST, PLATFORM_APP.ID.RIGHT_WRIST};
    final String[] applicationTypeArray = {APPLICATION.TYPE.SENSE, APPLICATION.TYPE.ANALYZE, APPLICATION.TYPE.ACT};
    final String[] applicationIdArray = {};
    PlatformMetaData testPlatformMetaData;
    PlatformAppMetaData testPlatformAppMetaData;
    ApplicationMetaData testAppMetaData;
    DataDescriptor testDataDescriptor;
    final String testDataType = "Test Data Type";
    final String testDataRate = "Test Data Rate";
    final String testAppId = "Test App Id";
    final int testDsId = 1;
    final long creationTime = 1268669453;
    final long modifiedTime = 1268660460;
    final int[] statusIntArray = {MCerebrumStatus.UNKNOWN_ERROR, MCerebrumStatus.SUCCESS,
            MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED,
            MCerebrumStatus.CONNECTION_ERROR, MCerebrumStatus.INVALID_PARAMETER,
            MCerebrumStatus.INVALID_DATA_SOURCE, MCerebrumStatus.MISSING_DATA_SOURCE_TYPE,
            MCerebrumStatus.MISSING_DATA_TYPE, MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED,
            MCerebrumStatus.INVALID_DATA, MCerebrumStatus.INCONSISTENT_DATA_TYPE,
            MCerebrumStatus.INVALID_TIMESTAMP, MCerebrumStatus.DATA_SIZE_TOO_LARGE};
    final String[] statusStringArray = {MCerebrumStatus.getStatusCodeString(MCerebrumStatus.UNKNOWN_ERROR),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.SUCCESS),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.CONNECTION_ERROR),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.INVALID_PARAMETER),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.INVALID_DATA_SOURCE),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.MISSING_DATA_SOURCE_TYPE),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.MISSING_DATA_TYPE),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.INVALID_DATA),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.INCONSISTENT_DATA_TYPE),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.INVALID_TIMESTAMP),
            MCerebrumStatus.getStatusCodeString(MCerebrumStatus.DATA_SIZE_TOO_LARGE)};
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
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;

    public void createPlatformMetaData() {
        // Create testPlatformMetaData
        testPlatformMetaData = new PlatformMetaData.Builder().setValue(testKey, testValue).build();
        testPlatformMetaData.setTitle(testTitle);
        testPlatformMetaData.setSummary(testSummary);
        testPlatformMetaData.setDescription(testDescription);
        testPlatformMetaData.setOperationSystem(testOperationSystem);
        testPlatformMetaData.setManufacturer(testManufacturer);
        testPlatformMetaData.setModel(testModel);
        testPlatformMetaData.setVersionFirmware(testVersionFirmware);
        testPlatformMetaData.setVersionHardware(testVersionHardware);
        testPlatformMetaData.setDeviceId(testDeviceId);
    }

    public void createPlatformAppMetaData() {
        // Create testPlatformAppMetaData
        testPlatformAppMetaData = new PlatformAppMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).setValue(testKey, testValue)
                .build();
    }

    public void createApplicationMetaData() {
        // Create testApplicationMetaData
        testAppMetaData = new ApplicationMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).setValue(testKey, testValue).build();
    }

    public void createDataDescriptor() {
        // Create testDataDescriptor
        testDataDescriptor = new DataDescriptor.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setMinValue(testMinValue)
                .setMaxValue(testMaxValue).setPossibleValues(testPossibleValuesAsString)
                .setPossibleValues(testPossibleValuesAsInt).setUnit(testUnit).setValue(testKey, testValue)
                .build();
    }

    public void createDataSourceMetaData() {
        // Create testDataSourceMetaData
        testDataSourceMetaData = new DataSourceMetaData.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setValue(testKey, testValue).build();
    }
    @Before
    public void objectCreation(){
        createPlatformMetaData();
        createPlatformAppMetaData();
        createApplicationMetaData();
        createDataDescriptor();
        createDataSourceMetaData();

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);
    }

    @Test
    public void dataSourceTypeTest() {
        for (int i = 0; i < dataSourceTypeArray.length; i++) {
            for (int j = 0; j < dataTypeArray.length; j++) {
                testDataSourceCreator = DataSourceCreator.builder(dataSourceTypeArray[i], dataTypeArray[j]).build();
                assertEquals(dataTypeArray[j].name(), testDataSourceCreator.getDataType());
            }
            assertEquals(dataSourceTypeArray[i], testDataSourceCreator.getDataSourceType());
        }
    }

    @Test
    public void dataSourceIdTest() {
        for (int i = 0; i < dataSourceIdArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(dataSourceIdArray[i]).build();
            assertEquals(dataSourceIdArray[i], testDataSourceCreator.getDataSourceId());
        }
    }

    @Test
    public void platformTypeTest() {
        for (int i = 0; i < platformTypeArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformType(platformTypeArray[i]).build();
            assertEquals(platformTypeArray[i], testDataSourceCreator.getPlatformType());
        }
    }

    @Test
    public void platformIdTest() {
        for (int i = 0; i < platformIdArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformId(platformIdArray[i]).build();
            assertEquals(platformIdArray[i], testDataSourceCreator.getPlatformId());
        }
    }

    @Test
    public void platformAppTypeTest() {
        for (int i = 0; i < platformAppTypeArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppType(platformAppTypeArray[i]).build();
            assertEquals(platformAppTypeArray[i], testDataSourceCreator.getPlatformAppType());
        }
    }

    @Test
    public void platformAppIdTest() {
        for (int i = 0; i < platformAppIdArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppId(platformAppIdArray[i]).build();
            assertEquals(platformAppIdArray[i], testDataSourceCreator.getPlatformAppId());
        }
    }

    @Test
    public void applicationTypeTest() {
        for (int i = 0; i < applicationTypeArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setApplicationType(applicationTypeArray[i]).build();
            assertEquals(applicationTypeArray[i], testDataSourceCreator.getApplicationType());
        }
    }

    @Test
    public void applicationIdTest() {
        for (int i = 0; i < applicationIdArray.length; i++) {
            testDataSourceCreator = new DataSourceCreator.Builder().setApplicationId(applicationIdArray[i]).build();
            assertEquals(applicationIdArray[i], testDataSourceCreator.getApplicationId());
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
        assertEquals(testPlatformMetaData.getValue(testKey), testDataSourceCreator.getPlatformMetaData().getValue(testKey));
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
        assertEquals(testPlatformAppMetaData.getValue(testKey), testDataSourceCreator.getPlatformAppMetaData().getValue(testKey));
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        assertEquals(testAppMetaData.getTitle(), testDataSourceCreator.getApplicationMetaData().getTitle());
        assertEquals(testAppMetaData.getSummary(), testDataSourceCreator.getApplicationMetaData().getSummary());
        assertEquals(testAppMetaData.getDescription(), testDataSourceCreator.getApplicationMetaData().getDescription());
        assertEquals(testAppMetaData.getVersionName(), testDataSourceCreator.getApplicationMetaData().getVersionName());
        assertEquals(testAppMetaData.getVersionNumber(), testDataSourceCreator.getApplicationMetaData().getVersionNumber());
        assertEquals(testAppMetaData.getValue(testKey), testDataSourceCreator.getApplicationMetaData().getValue(testKey));
    }

    @Test
    public void dataRateTest() {
        double hoursInDay = 24.0;
        double minInHour = 60.0;
        double secInMin = 60.0;
        // Test days
        testDataSourceCreator = new DataSourceCreator.Builder().setDataRate(testSampleNo, timeUnitArray[0]).build();
        assertEquals(Double.toString((testSampleNo) / (hoursInDay * minInHour * secInMin)),
                testDataSourceCreator.getDataRate());
        // Test hours
        testDataSourceCreator = new DataSourceCreator.Builder().setDataRate(testSampleNo, timeUnitArray[1]).build();
        assertEquals(Double.toString((testSampleNo) / (minInHour * secInMin)), testDataSourceCreator.getDataRate());
        // Test minutes
        testDataSourceCreator = new DataSourceCreator.Builder().setDataRate(testSampleNo, timeUnitArray[2]).build();
        assertEquals(Double.toString((testSampleNo) / (secInMin)), testDataSourceCreator.getDataRate());
        // Test seconds
        testDataSourceCreator = new DataSourceCreator.Builder().setDataRate(testSampleNo, timeUnitArray[3]).build();
        assertEquals(Double.toString(testSampleNo), testDataSourceCreator.getDataRate());
        // Test default (TimeUnit.MILLISECONDS)
        testDataSourceCreator = new DataSourceCreator.Builder().setDataRate(testSampleNo, timeUnitArray[4]).build();
        assertEquals(Double.toString(testSampleNo), testDataSourceCreator.getDataRate());
    }

    @Test
    public void platformAsPhoneTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAsPhone().build();
        assertEquals(PLATFORM.TYPE.PHONE, testDataSourceCreator.getPlatformType());
        assertNotNull(testDataSourceCreator.getPlatformId());
        assertEquals(testTitle, testDataSourceCreator.getPlatformMetaData().getTitle());
        //assertEquals(, testDataSourceCreator.getPlatformMetaData().getSummary());
        assertEquals(testOperationSystem, testDataSourceCreator.getPlatformMetaData().getOperationSystem());
        assertEquals(testManufacturer, testDataSourceCreator.getPlatformMetaData().getManufacturer());
        assertEquals(testModel, testDataSourceCreator.getPlatformMetaData().getModel());
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataDescriptor(0, testDataDescriptor).build();
        assertEquals(testDataDescriptor.getTitle(), testDataSourceCreator.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataDescriptor.getSummary(), testDataSourceCreator.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataDescriptor.getDescription(), testDataSourceCreator.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataDescriptor.getMinValue(),
                testDataSourceCreator.getDataDescriptors().get(0).getMinValue(), 0.1);
        assertEquals(testDataDescriptor.getMaxValue(),
                testDataSourceCreator.getDataDescriptors().get(0).getMaxValue(), 0.1);
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsString(),
                testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsInt(),
                testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataDescriptor.getUnit(), testDataSourceCreator.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataDescriptor.getValue(testKey), testDataSourceCreator.getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertEquals(testDataSourceMetaData.getTitle(), testDataSourceCreator.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), testDataSourceCreator.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), testDataSourceCreator.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceMetaData.getValue(testKey), testDataSourceCreator.getDataSourceMetaData().getValue(testKey));
    }

    @Test
    public void dataSourceCreator_ParcelableWriteReadTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(dataSourceIdArray[0])
                .setPlatformType(platformTypeArray[0]).setPlatformId(platformIdArray[0])
                .setPlatformAppType(platformAppTypeArray[0]).setPlatformAppId(platformAppIdArray[0])
                .setApplicationType(applicationTypeArray[0]).setDataSourceMetadata(testDataSourceMetaData)
                .setPlatformMetadata(testPlatformMetaData).setPlatformAppMetadata(testPlatformAppMetaData)
                .setApplicationMetaData(testAppMetaData).setDataRate(testSampleNo, timeUnitArray[0])
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(testDataSourceCreator.getDataSourceId(), createdFromParcel.getDataSourceId());
        assertEquals(testDataSourceCreator.getPlatformType(), createdFromParcel.getPlatformType());
        assertEquals(testDataSourceCreator.getPlatformId(), createdFromParcel.getPlatformId());
        assertEquals(testDataSourceCreator.getPlatformAppType(), createdFromParcel.getPlatformAppType());
        assertEquals(testDataSourceCreator.getPlatformAppId(), createdFromParcel.getPlatformAppId());
        assertEquals(testDataSourceCreator.getApplicationId(), createdFromParcel.getApplicationId());
        assertEquals(testDataSourceCreator.getApplicationType(), createdFromParcel.getApplicationId());
        assertEquals(testDataSourceCreator.getDataSourceMetaData(), createdFromParcel.getDataSourceMetaData());
        assertEquals(testDataSourceCreator.getPlatformMetaData(), createdFromParcel.getPlatformMetaData());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData(), createdFromParcel.getPlatformAppMetaData());
        assertEquals(testDataSourceCreator.getApplicationMetaData(), createdFromParcel.getApplicationMetaData());
        assertEquals(testDataSourceCreator.getDataRate(), createdFromParcel.getDataRate());
        assertEquals(testDataSourceCreator.getDataDescriptors(), createdFromParcel.getDataDescriptors());
    }
}
