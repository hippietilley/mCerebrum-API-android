package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Build;
import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DataSourceReadWriteAndroidUnitTest {
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

    ArrayList testDataDescriptors = new ArrayList();
    PlatformMetaData testPlatformMetaData;
    PlatformAppMetaData testPlatformAppMetaData;
    ApplicationMetaData testAppMetaData;
    DataDescriptor testDataDescriptor;
    DataSourceMetaData testDataSourceMetaData;
    DataSourceReadWrite testDataSourceReadWrite;

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
        testDataDescriptors.add(testDataDescriptor);
    }

    @Test
    public void dataSourceTypeTest() {
        for (int i = 0; i < dataSourceTypeArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setDataSourceType(dataSourceTypeArray[i]);
            assertEquals(dataSourceTypeArray[i], testDataSourceReadWrite.getDataSourceType());
        }
    }

    @Test
    public void dataSourceIdTest() {
        for (int i = 0; i < dataSourceIdArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setDataSourceId(dataSourceIdArray[i]);
            assertEquals(dataSourceIdArray[i], testDataSourceReadWrite.getDataSourceId());
        }
    }

    @Test
    public void platformTypeTest() {
        for (int i = 0; i < platformTypeArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformType(platformTypeArray[i]);
            assertEquals(platformTypeArray[i], testDataSourceReadWrite.getPlatformType());
        }
    }

    @Test
    public void platformIdTest() {
        for (int i = 0; i < platformIdArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformId(platformIdArray[i]);
            assertEquals(platformIdArray[i], testDataSourceReadWrite.getPlatformId());
        }
    }

    @Test
    public void platformAppTypeTest() {
        for (int i = 0; i < platformAppTypeArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformAppType(platformAppTypeArray[i]);
            assertEquals(platformAppTypeArray[i], testDataSourceReadWrite.getPlatformAppType());
        }
    }

    @Test
    public void platformAppIdTest() {
        for (int i = 0; i < platformAppIdArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformAppId(platformAppIdArray[i]);
            assertEquals(platformAppIdArray[i], testDataSourceReadWrite.getPlatformAppId());
        }
    }

    @Test
    public void applicationTypeTest() {
        for (int i = 0; i < applicationTypeArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setApplicationType(applicationTypeArray[i]);
            assertEquals(applicationTypeArray[i], testDataSourceReadWrite.getApplicationType());
        }
    }

    @Test
    public void applicationIdTest() {
        for (int i = 0; i < applicationIdArray.length; i++) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setApplicationId(applicationIdArray[i]);
            assertEquals(applicationIdArray[i], testDataSourceReadWrite.getApplicationId());
        }
    }

    @Test
    public void platformMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setPlatformMetadata(testPlatformMetaData);
        assertEquals(testPlatformMetaData.getTitle(), testDataSourceReadWrite.getPlatformMetaData().getTitle());
        assertEquals(testPlatformMetaData.getSummary(), testDataSourceReadWrite.getPlatformMetaData().getSummary());
        assertEquals(testPlatformMetaData.getDescription(), testDataSourceReadWrite.getPlatformMetaData().getDescription());
        assertEquals(testPlatformMetaData.getOperationSystem(), testDataSourceReadWrite.getPlatformMetaData().getOperationSystem());
        assertEquals(testPlatformMetaData.getManufacturer(), testDataSourceReadWrite.getPlatformMetaData().getManufacturer());
        assertEquals(testPlatformMetaData.getModel(), testDataSourceReadWrite.getPlatformMetaData().getModel());
        assertEquals(testPlatformMetaData.getVersionFirmware(), testDataSourceReadWrite.getPlatformMetaData().getVersionFirmware());
        assertEquals(testPlatformMetaData.getVersionHardware(), testDataSourceReadWrite.getPlatformMetaData().getVersionHardware());
        assertEquals(testPlatformMetaData.getDeviceId(), testDataSourceReadWrite.getPlatformMetaData().getDeviceId());
        assertEquals(testPlatformMetaData.getValue(testKey), testDataSourceReadWrite.getPlatformMetaData().getValue(testKey));
    }

    @Test
    public void platformAppMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        assertEquals(testPlatformAppMetaData.getTitle(), testDataSourceReadWrite.getPlatformAppMetaData().getTitle());
        assertEquals(testPlatformAppMetaData.getSummary(), testDataSourceReadWrite.getPlatformAppMetaData().getSummary());
        assertEquals(testPlatformAppMetaData.getDescription(), testDataSourceReadWrite.getPlatformAppMetaData().getDescription());
        assertEquals(testPlatformAppMetaData.getOperationSystem(), testDataSourceReadWrite.getPlatformAppMetaData().getOperationSystem());
        assertEquals(testPlatformAppMetaData.getManufacturer(), testDataSourceReadWrite.getPlatformAppMetaData().getManufacturer());
        assertEquals(testPlatformAppMetaData.getModel(), testDataSourceReadWrite.getPlatformAppMetaData().getModel());
        assertEquals(testPlatformAppMetaData.getVersionFirmware(), testDataSourceReadWrite.getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testPlatformAppMetaData.getVersionHardware(), testDataSourceReadWrite.getPlatformAppMetaData().getVersionHardware());
        assertEquals(testPlatformAppMetaData.getDeviceId(), testDataSourceReadWrite.getPlatformAppMetaData().getDeviceId());
        assertEquals(testPlatformAppMetaData.getValue(testKey), testDataSourceReadWrite.getPlatformAppMetaData().getValue(testKey));
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        assertEquals(testAppMetaData.getTitle(), testDataSourceReadWrite.getApplicationMetaData().getTitle());
        assertEquals(testAppMetaData.getSummary(), testDataSourceReadWrite.getApplicationMetaData().getSummary());
        assertEquals(testAppMetaData.getDescription(), testDataSourceReadWrite.getApplicationMetaData().getDescription());
        assertEquals(testAppMetaData.getVersionName(), testDataSourceReadWrite.getApplicationMetaData().getVersionName());
        assertEquals(testAppMetaData.getVersionNumber(), testDataSourceReadWrite.getApplicationMetaData().getVersionNumber());
        assertEquals(testAppMetaData.getValue(testKey), testDataSourceReadWrite.getApplicationMetaData().getValue(testKey));
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        assertEquals(testDataDescriptor.getTitle(), testDataSourceReadWrite.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataDescriptor.getSummary(), testDataSourceReadWrite.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataDescriptor.getDescription(), testDataSourceReadWrite.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataDescriptor.getMinValue(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getMinValue(), 0.1);
        assertEquals(testDataDescriptor.getMaxValue(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getMaxValue(), 0.1);
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsString(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsInt(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataDescriptor.getUnit(), testDataSourceReadWrite.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataDescriptor.getValue(testKey), testDataSourceReadWrite.getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
        assertEquals(testDataSourceMetaData.getTitle(), testDataSourceReadWrite.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), testDataSourceReadWrite.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), testDataSourceReadWrite.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceMetaData.getValue(testKey), testDataSourceReadWrite.getDataSourceMetaData().getValue(testKey));
    }

    @Test
    public void dataSourceReadWrite_ParcelableWriteReadTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceType(dataSourceTypeArray[0]);
        testDataSourceReadWrite.setDataSourceId(dataSourceIdArray[0]);
        testDataSourceReadWrite.setPlatformType(platformTypeArray[0]);
        testDataSourceReadWrite.setPlatformId(platformIdArray[0]);
        testDataSourceReadWrite.setPlatformAppType(platformAppTypeArray[0]);
        testDataSourceReadWrite.setPlatformAppId(platformAppIdArray[0]);
        testDataSourceReadWrite.setApplicationType(applicationTypeArray[0]);
        testDataSourceReadWrite.setPlatformMetadata(testPlatformMetaData);
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);

        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceReadWrite.writeToParcel(parcel , testDataSourceReadWrite.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceReadWrite createdFromParcel = DataSourceReadWrite.CREATOR.createFromParcel(parcel);
        DataSourceReadWrite[] createdFromParcelArray = DataSourceReadWrite.CREATOR.newArray(1);

        // Verify the results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(testDataSourceReadWrite.getDataSourceId(), createdFromParcel.getDataSourceId());
        assertEquals(testDataSourceReadWrite.getPlatformType(), createdFromParcel.getPlatformType());
        assertEquals(testDataSourceReadWrite.getPlatformId(), createdFromParcel.getPlatformId());
        assertEquals(testDataSourceReadWrite.getPlatformAppType(), createdFromParcel.getPlatformAppType());
        assertEquals(testDataSourceReadWrite.getPlatformAppId(), createdFromParcel.getPlatformAppId());
        assertEquals(testDataSourceReadWrite.getApplicationType(), createdFromParcel.getApplicationType());

        // Test the DataSourceMetaData for equality
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getTitle(),
                createdFromParcel.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getSummary(),
                createdFromParcel.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getDescription(),
                createdFromParcel.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getValue(testKey),
                createdFromParcel.getDataSourceMetaData().getValue(testKey));

        // Test the PlatformMetaData for equality
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getTitle(),
                createdFromParcel.getPlatformMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getSummary(),
                createdFromParcel.getPlatformMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getDescription(),
                createdFromParcel.getPlatformMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getOperationSystem(),
                createdFromParcel.getPlatformMetaData().getOperationSystem());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getManufacturer(),
                createdFromParcel.getPlatformMetaData().getManufacturer());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getModel(),
                createdFromParcel.getPlatformMetaData().getModel());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getVersionFirmware(),
                createdFromParcel.getPlatformMetaData().getVersionFirmware());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getVersionHardware(),
                createdFromParcel.getPlatformMetaData().getVersionHardware());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getDeviceId(),
                createdFromParcel.getPlatformMetaData().getDeviceId());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getValue(testKey),
                createdFromParcel.getPlatformMetaData().getValue(testKey));

        // Test PlatformAppMetaData for equality
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getTitle(),
                createdFromParcel.getPlatformAppMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getSummary(),
                createdFromParcel.getPlatformAppMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getDescription(),
                createdFromParcel.getPlatformAppMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getOperationSystem(),
                createdFromParcel.getPlatformAppMetaData().getOperationSystem());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getManufacturer(),
                createdFromParcel.getPlatformAppMetaData().getManufacturer());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getModel(),
                createdFromParcel.getPlatformAppMetaData().getModel());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getVersionFirmware(),
                createdFromParcel.getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getVersionHardware(),
                createdFromParcel.getPlatformAppMetaData().getVersionHardware());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getDeviceId(),
                createdFromParcel.getPlatformAppMetaData().getDeviceId());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getValue(testKey),
                createdFromParcel.getPlatformAppMetaData().getValue(testKey));

        // Test ApplicationMetaData for equality
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getTitle(),
                createdFromParcel.getApplicationMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getSummary(),
                createdFromParcel.getApplicationMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getDescription(),
                createdFromParcel.getApplicationMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getVersionName(),
                createdFromParcel.getApplicationMetaData().getVersionName());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getVersionNumber(),
                createdFromParcel.getApplicationMetaData().getVersionNumber());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getValue(testKey),
                createdFromParcel.getApplicationMetaData().getValue(testKey));

        assertEquals(testDataSourceReadWrite.getDataRate(), createdFromParcel.getDataRate());

        // Test DataDescriptors for equality
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getTitle(),
                createdFromParcel.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getSummary(),
                createdFromParcel.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getDescription(),
                createdFromParcel.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMinValue(),
                createdFromParcel.getDataDescriptors().get(0).getMinValue(), 0.1);
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMaxValue(),
                createdFromParcel.getDataDescriptors().get(0).getMaxValue(), 0.1);
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsString(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsInt(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getUnit(),
                createdFromParcel.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getValue(testKey),
                createdFromParcel.getDataDescriptors().get(0).getValue(testKey));
    }
}
