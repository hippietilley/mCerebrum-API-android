package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RegistrationAndroidUnitTest {
    public static final double DELTA = 0.1;
    final String testDataSourceType = DATASOURCE.TYPE.ACCELEROMETER;
    final String testDataSourceId = DATASOURCE.ID.SMOKING;
    DataSourceMetaData testDataSourceMetaData;
    final String testPlatformType = PLATFORM.TYPE.AUTOSENSE_CHEST;
    final String testPlatformId = PLATFORM.ID.CHEST;
    final String testPlatformAppType = PLATFORM_APP.TYPE.AUTOSENSE_CHEST;
    final String testPlatformAppId = PLATFORM_APP.ID.CHEST;
    final String testApplicationType = APPLICATION.TYPE.SENSE;
    final int[] statusIntArray = {MCerebrumStatus.UNKNOWN_ERROR, MCerebrumStatus.SUCCESS,
            MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, MCerebrumStatus.MCEREBRUM_APP_NOT_INSTALLED,
            MCerebrumStatus.CONNECTION_ERROR, MCerebrumStatus.INVALID_PARAMETER,
            MCerebrumStatus.INVALID_DATA_SOURCE, MCerebrumStatus.MISSING_DATA_SOURCE_TYPE,
            MCerebrumStatus.MISSING_DATA_TYPE, MCerebrumStatus.DATA_SOURCE_NOT_REGISTERED,
            MCerebrumStatus.INVALID_DATA, MCerebrumStatus.INCONSISTENT_DATA_TYPE,
            MCerebrumStatus.INVALID_TIMESTAMP, MCerebrumStatus.DATA_SIZE_TOO_LARGE};
    PlatformMetaData testPlatformMetaData;
    PlatformAppMetaData testPlatformAppMetaData;
    ApplicationMetaData testAppMetaData;
    DataDescriptor testDataDescriptor;

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
    DataSourceCreator testDataSourceCreator;
    DataSourceReadWrite testDataSourceReadWrite;
    Registration testReg;
    private final TimeUnit testTimeUnit = TimeUnit.DAYS;
    private final int testSampleNo = 10;
    ArrayList<DataDescriptor> testDataDescriptors = new ArrayList<>();
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
    public void objectCreation() {
        createPlatformMetaData();
        createPlatformAppMetaData();
        createApplicationMetaData();
        createDataDescriptor();
        createDataSourceMetaData();
        testDataDescriptors.add(testDataDescriptor);

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);

        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceId(testDataSourceId)
                .setPlatformType(testPlatformType).setPlatformId(testPlatformId)
                .setPlatformAppType(testPlatformAppType).setPlatformAppId(testPlatformAppId)
                .setApplicationType(testApplicationType).setDataSourceMetadata(testDataSourceMetaData)
                .setPlatformMetadata(testPlatformMetaData).setPlatformAppMetadata(testPlatformAppMetaData)
                .setApplicationMetaData(testAppMetaData).setDataRate(testSampleNo, testTimeUnit)
                .setDataDescriptor(0, testDataDescriptor).build();

        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceType(testDataSourceType);
        testDataSourceReadWrite.setDataSourceId(testDataSourceId);
        testDataSourceReadWrite.setPlatformType(testPlatformType);
        testDataSourceReadWrite.setPlatformId(testPlatformId);
        testDataSourceReadWrite.setPlatformAppType(testPlatformAppType);
        testDataSourceReadWrite.setPlatformAppId(testPlatformAppId);
        testDataSourceReadWrite.setApplicationType(testApplicationType);
        testDataSourceReadWrite.setPlatformMetadata(testPlatformMetaData);
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
    }

    @Test
    public void registrationCreatorTest() {
        for (int i = 0; i < statusIntArray.length; i++) {
            testReg = new Registration(testDataSourceCreator, statusIntArray[i]);
            assertEquals(statusIntArray[i], testReg.getStatus());
        }
        assertEquals(testDataSourceCreator.getDataSourceId(), testReg.getDataSource().getDataSourceId());
        assertEquals(testDataSourceCreator.getPlatformType(), testReg.getDataSource().getPlatformType());
        assertEquals(testDataSourceCreator.getPlatformId(), testReg.getDataSource().getPlatformId());
        assertEquals(testDataSourceCreator.getPlatformAppType(), testReg.getDataSource().getPlatformAppType());
        assertEquals(testDataSourceCreator.getPlatformAppId(), testReg.getDataSource().getPlatformAppId());
        assertEquals(testDataSourceCreator.getApplicationType(), testReg.getDataSource().getApplicationType());

        // Test the DataSourceMetaData for equality
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getTitle(),
                testReg.getDataSource().getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getSummary(),
                testReg.getDataSource().getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getDescription(),
                testReg.getDataSource().getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getValue(testKey),
                testReg.getDataSource().getDataSourceMetaData().getValue(testKey));

        // Test the PlatformMetaData for equality
        assertEquals(testDataSourceCreator.getPlatformMetaData().getTitle(),
                testReg.getDataSource().getPlatformMetaData().getTitle());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getSummary(),
                testReg.getDataSource().getPlatformMetaData().getSummary());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getDescription(),
                testReg.getDataSource().getPlatformMetaData().getDescription());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getOperationSystem(),
                testReg.getDataSource().getPlatformMetaData().getOperationSystem());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getManufacturer(),
                testReg.getDataSource().getPlatformMetaData().getManufacturer());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getModel(),
                testReg.getDataSource().getPlatformMetaData().getModel());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getVersionFirmware(),
                testReg.getDataSource().getPlatformMetaData().getVersionFirmware());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getVersionHardware(),
                testReg.getDataSource().getPlatformMetaData().getVersionHardware());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getDeviceId(),
                testReg.getDataSource().getPlatformMetaData().getDeviceId());
        assertEquals(testDataSourceCreator.getPlatformMetaData().getValue(testKey),
                testReg.getDataSource().getPlatformMetaData().getValue(testKey));

        // Test PlatformAppMetaData for equality
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getTitle(),
                testReg.getDataSource().getPlatformAppMetaData().getTitle());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getSummary(),
                testReg.getDataSource().getPlatformAppMetaData().getSummary());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getDescription(),
                testReg.getDataSource().getPlatformAppMetaData().getDescription());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getOperationSystem(),
                testReg.getDataSource().getPlatformAppMetaData().getOperationSystem());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getManufacturer(),
                testReg.getDataSource().getPlatformAppMetaData().getManufacturer());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getModel(),
                testReg.getDataSource().getPlatformAppMetaData().getModel());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getVersionFirmware(),
                testReg.getDataSource().getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getVersionHardware(),
                testReg.getDataSource().getPlatformAppMetaData().getVersionHardware());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getDeviceId(),
                testReg.getDataSource().getPlatformAppMetaData().getDeviceId());
        assertEquals(testDataSourceCreator.getPlatformAppMetaData().getValue(testKey),
                testReg.getDataSource().getPlatformAppMetaData().getValue(testKey));

        // Test ApplicationMetaData for equality
        assertEquals(testDataSourceCreator.getApplicationMetaData().getTitle(),
                testReg.getDataSource().getApplicationMetaData().getTitle());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getSummary(),
                testReg.getDataSource().getApplicationMetaData().getSummary());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getDescription(),
                testReg.getDataSource().getApplicationMetaData().getDescription());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getVersionName(),
                testReg.getDataSource().getApplicationMetaData().getVersionName());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getVersionNumber(),
                testReg.getDataSource().getApplicationMetaData().getVersionNumber());
        assertEquals(testDataSourceCreator.getApplicationMetaData().getValue(testKey),
                testReg.getDataSource().getApplicationMetaData().getValue(testKey));

        assertEquals(testDataSourceCreator.getDataRate(), testReg.getDataSource().getDataRate());

        // Test DataDescriptors for equality
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getTitle(),
                testReg.getDataSource().getDataDescriptors().get(0).getTitle());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getSummary(),
                testReg.getDataSource().getDataDescriptors().get(0).getSummary());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getDescription(),
                testReg.getDataSource().getDataDescriptors().get(0).getDescription());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getMinValue(),
                testReg.getDataSource().getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getMaxValue(),
                testReg.getDataSource().getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsString(),
                testReg.getDataSource().getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataSourceCreator.getDataDescriptors().get(0).getPossibleValuesAsInt(),
                testReg.getDataSource().getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getUnit(),
                testReg.getDataSource().getDataDescriptors().get(0).getUnit());
        assertEquals(testDataSourceCreator.getDataDescriptors().get(0).getValue(testKey),
                testReg.getDataSource().getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void registrationCreatorComparableTest() {
        for (int i = 0; i < statusIntArray.length; i++) {
            testReg = new Registration(testDataSourceCreator, statusIntArray[i]);
            assertEquals(statusIntArray[i], testReg.getStatus());
        }
        assertEquals(testDataSourceCreator, testReg.getDataSource());
    }

    @Test
    public void registrationReadWriteTest() {
        for (int i = 0; i < statusIntArray.length; i++) {
            testReg = new Registration(testDataSourceReadWrite, statusIntArray[i]);
            assertEquals(statusIntArray[i], testReg.getStatus());
        }
        assertEquals(testDataSourceReadWrite.getDataSourceId(), testReg.getDataSource().getDataSourceId());
        assertEquals(testDataSourceReadWrite.getPlatformType(), testReg.getDataSource().getPlatformType());
        assertEquals(testDataSourceReadWrite.getPlatformId(), testReg.getDataSource().getPlatformId());
        assertEquals(testDataSourceReadWrite.getPlatformAppType(), testReg.getDataSource().getPlatformAppType());
        assertEquals(testDataSourceReadWrite.getPlatformAppId(), testReg.getDataSource().getPlatformAppId());
        assertEquals(testDataSourceReadWrite.getApplicationType(), testReg.getDataSource().getApplicationType());

        // Test the DataSourceMetaData for equality
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getTitle(),
                testReg.getDataSource().getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getSummary(),
                testReg.getDataSource().getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getDescription(),
                testReg.getDataSource().getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getValue(testKey),
                testReg.getDataSource().getDataSourceMetaData().getValue(testKey));

        // Test the PlatformMetaData for equality
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getTitle(),
                testReg.getDataSource().getPlatformMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getSummary(),
                testReg.getDataSource().getPlatformMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getDescription(),
                testReg.getDataSource().getPlatformMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getOperationSystem(),
                testReg.getDataSource().getPlatformMetaData().getOperationSystem());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getManufacturer(),
                testReg.getDataSource().getPlatformMetaData().getManufacturer());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getModel(),
                testReg.getDataSource().getPlatformMetaData().getModel());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getVersionFirmware(),
                testReg.getDataSource().getPlatformMetaData().getVersionFirmware());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getVersionHardware(),
                testReg.getDataSource().getPlatformMetaData().getVersionHardware());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getDeviceId(),
                testReg.getDataSource().getPlatformMetaData().getDeviceId());
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getValue(testKey),
                testReg.getDataSource().getPlatformMetaData().getValue(testKey));

        // Test PlatformAppMetaData for equality
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getTitle(),
                testReg.getDataSource().getPlatformAppMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getSummary(),
                testReg.getDataSource().getPlatformAppMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getDescription(),
                testReg.getDataSource().getPlatformAppMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getOperationSystem(),
                testReg.getDataSource().getPlatformAppMetaData().getOperationSystem());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getManufacturer(),
                testReg.getDataSource().getPlatformAppMetaData().getManufacturer());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getModel(),
                testReg.getDataSource().getPlatformAppMetaData().getModel());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getVersionFirmware(),
                testReg.getDataSource().getPlatformAppMetaData().getVersionFirmware());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getVersionHardware(),
                testReg.getDataSource().getPlatformAppMetaData().getVersionHardware());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getDeviceId(),
                testReg.getDataSource().getPlatformAppMetaData().getDeviceId());
        assertEquals(testDataSourceReadWrite.getPlatformAppMetaData().getValue(testKey),
                testReg.getDataSource().getPlatformAppMetaData().getValue(testKey));

        // Test ApplicationMetaData for equality
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getTitle(),
                testReg.getDataSource().getApplicationMetaData().getTitle());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getSummary(),
                testReg.getDataSource().getApplicationMetaData().getSummary());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getDescription(),
                testReg.getDataSource().getApplicationMetaData().getDescription());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getVersionName(),
                testReg.getDataSource().getApplicationMetaData().getVersionName());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getVersionNumber(),
                testReg.getDataSource().getApplicationMetaData().getVersionNumber());
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getValue(testKey),
                testReg.getDataSource().getApplicationMetaData().getValue(testKey));

        assertEquals(testDataSourceReadWrite.getDataRate(), testReg.getDataSource().getDataRate());

        // Test DataDescriptors for equality
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getTitle(),
                testReg.getDataSource().getDataDescriptors().get(0).getTitle());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getSummary(),
                testReg.getDataSource().getDataDescriptors().get(0).getSummary());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getDescription(),
                testReg.getDataSource().getDataDescriptors().get(0).getDescription());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMinValue(),
                testReg.getDataSource().getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMaxValue(),
                testReg.getDataSource().getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsString(),
                testReg.getDataSource().getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsInt(),
                testReg.getDataSource().getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getUnit(),
                testReg.getDataSource().getDataDescriptors().get(0).getUnit());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getValue(testKey),
                testReg.getDataSource().getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void registrationReadWriteComparableTest() {
        for (int i = 0; i < statusIntArray.length; i++) {
            testReg = new Registration(testDataSourceReadWrite, statusIntArray[i]);
            assertEquals(statusIntArray[i], testReg.getStatus());
        }
        assertEquals(testDataSourceReadWrite, testReg.getDataSource());
    }
}
