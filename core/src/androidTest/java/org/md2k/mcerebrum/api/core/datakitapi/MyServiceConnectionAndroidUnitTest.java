package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MyServiceConnectionAndroidUnitTest {
    ConnectionCallback testConnectionCallback;
    MyServiceConnection testServiceConnection;
    DataSourceReadWrite testDataSourceReadWrite;
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;

    final String testDataSourceType = DATASOURCE.TYPE.ACCELEROMETER;
    final String testDataSourceId = DATASOURCE.ID.SMOKING;
    DataSourceMetaData testDataSourceMetaData;
    final String testPlatformType = PLATFORM.TYPE.AUTOSENSE_CHEST;
    final String testPlatformId = PLATFORM.ID.CHEST;
    final String testPlatformAppType = PLATFORM_APP.TYPE.AUTOSENSE_CHEST;
    final String testPlatformAppId = PLATFORM_APP.ID.CHEST;
    final String testApplicationType = APPLICATION.TYPE.SENSE;
    final String testDataType = "Test Data Type";
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
    ArrayList<DataDescriptor> testDataDescriptors = new ArrayList<>();

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

    public void createDataSourceReadWrite() {
        testDataSourceReadWrite = new DataSourceReadWrite();
    }

    @Before
    public void objectCreation() {
        createPlatformMetaData();
        createPlatformAppMetaData();
        createApplicationMetaData();
        createDataDescriptor();
        createDataSourceMetaData();
        testDataDescriptors.add(testDataDescriptor);

        testServiceConnection = new MyServiceConnection(testConnectionCallback);
    }

    @Test
    public void registerTest() {
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testServiceConnection.register(testDataSourceReadWrite));

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);
        assertEquals(MCerebrumStatus.INVALID_DATA_SOURCE, testServiceConnection.register(testDataSourceReadWrite));

        createDataSourceReadWrite();
        assertEquals(MCerebrumStatus.MISSING_DATA_SOURCE_TYPE, testServiceConnection.register(testDataSourceReadWrite));

        testDataSourceReadWrite.setDataSourceType(testDataSourceType);
        assertEquals(MCerebrumStatus.MISSING_DATA_TYPE, testServiceConnection.register(testDataSourceReadWrite));

        //testDataSourceReadWrite.setDataType(testDataType);
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
        assertEquals(MCerebrumStatus.SUCCESS, testServiceConnection.register(testDataSourceReadWrite));
    }
}
