package org.md2k.mcerebrum.api.core;

import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import org.md2k.mcerebrum.api.core.datakitapi.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceCreator;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceRequest;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceSet;
import org.md2k.mcerebrum.api.core.datakitapi.PlatformAppMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.PlatformMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.Registration;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MCerebrumAPIAndroidUnitTest {

    MCerebrumAPI testmCerebrumAPI;
    MCerebrumAPI testmCerebrumAPINotInit;
    Context testContext;
    ConnectionCallback testConnectionCallback;
    ConnectionCallback testConnectionCallbackNull;
    DataSourceCreator testDataSourceCreator;
    DataSourceCreator.Builder testDataSourceCreatorBuilder;
    PlatformMetaData testPlatformMetaData;
    PlatformMetaData.Builder testPlatformMetaDataBuilder;
    PlatformAppMetaData testPlatformAppMetaData;
    PlatformAppMetaData.Builder testPlatformAppMetaDataBuilder;
    ApplicationMetaData testAppMetaData;
    ApplicationMetaData.Builder testAppMetaDataBuilder;
    DataDescriptor testDataDescriptor;
    DataDescriptor.Builder testDataDescriptorBuilder;
    DataSourceMetaData testDataSourceMetaData;
    DataSourceMetaData.Builder testDataSourceMetaDataBuilder;
    Registration testRegistration;
    DataSourceRequest testDataSourceRequest;
    DataSourceRequest.Builder testDataSourceRequestBuilder;
    DataSourceSet testDataSourceSet;
    Data testData;
    Data[] testDataArray;

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
    private final String testDataSourceType = DATASOURCE.TYPE.ACCELEROMETER;
    private final DataType testDataType = DataType.DATAPOINT_BOOLEAN;
    private final String testDataSourceId = DATASOURCE.ID.SMOKING;
    private final String testPlatformType = PLATFORM.TYPE.AUTOSENSE_CHEST;
    private final String testPlatformId = PLATFORM.ID.CHEST;
    private final String testPlatformAppType = PLATFORM_APP.TYPE.AUTOSENSE_CHEST;
    private final String testPlatformAppId = PLATFORM_APP.ID.CHEST;
    private final String testApplicationType = APPLICATION.TYPE.SENSE;
    private final TimeUnit testTimeUnit = TimeUnit.DAYS;
    private final int testSampleNo = 10;
    private final long testTimestamp = 1268660460;

    public void createPlatformMetaData() {
        testPlatformMetaDataBuilder = PlatformMetaData.builder();
        testPlatformMetaDataBuilder.setValue(testKey, testValue);
        testPlatformMetaDataBuilder.setTitle(testTitle);
        testPlatformMetaDataBuilder.setSummary(testSummary);
        testPlatformMetaDataBuilder.setDescription(testDescription);
        testPlatformMetaDataBuilder.setOperationSystem(testOperationSystem);
        testPlatformMetaDataBuilder.setManufacturer(testManufacturer);
        testPlatformMetaDataBuilder.setModel(testModel);
        testPlatformMetaDataBuilder.setVersionFirmware(testVersionFirmware);
        testPlatformMetaDataBuilder.setVersionHardware(testVersionHardware);
        testPlatformMetaDataBuilder.setDeviceId(testDeviceId);
        testPlatformMetaData = testPlatformMetaDataBuilder.build();
    }

    public void createPlatformAppMetaData() {
        testPlatformAppMetaDataBuilder = PlatformAppMetaData.builder();
        testPlatformAppMetaData = testPlatformAppMetaDataBuilder.setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).setValue(testKey, testValue)
                .build();
    }

    public void createApplicationMetaData() {
        testAppMetaDataBuilder = ApplicationMetaData.builder();
        testAppMetaData = testAppMetaDataBuilder.setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).setValue(testKey, testValue).build();
    }

    public void createDataDescriptor() {
        testDataDescriptorBuilder = DataDescriptor.builder();
        testDataDescriptor = testDataDescriptorBuilder.setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setMinValue(testMinValue)
                .setMaxValue(testMaxValue).setPossibleValues(testPossibleValuesAsString)
                .setPossibleValues(testPossibleValuesAsInt).setUnit(testUnit).setValue(testKey, testValue)
                .build();
    }

    public void createDataSourceMetaData() {
        testDataSourceMetaDataBuilder = DataSourceMetaData.builder();
        testDataSourceMetaData = testDataSourceMetaDataBuilder.setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setValue(testKey, testValue).build();
    }

    public void createRegistration() {
        testRegistration = testmCerebrumAPI.register(testDataSourceCreator);
    }

    public void createDataSourceRequest() {
        testDataSourceRequestBuilder = DataSourceRequest.builder();
        testDataSourceRequest = testDataSourceRequestBuilder.setDataSourceType(testDataSourceType)
                .setDataSourceId(testDataSourceId).setPlatformType(testPlatformType)
                .setPlatformId(testPlatformId).setPlatformAppId(testPlatformAppId)
                .setPlatformAppType(testPlatformAppType).setApplicationType(testApplicationType).build();
    }

    @Before
    public void gettingStarted() {
        testConnectionCallback = new ConnectionCallback() {
            @Override
            public void onConnected() {}

            @Override
            public void onError(MCerebrumException e) {}

            @Override
            public void onDisconnected() {}
        };

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);

        // Create DataSourceCreator
        createPlatformMetaData();
        createPlatformAppMetaData();
        createApplicationMetaData();
        createDataDescriptor();
        createDataSourceMetaData();
        testDataSourceCreatorBuilder = DataSourceCreator.builder(testDataSourceType, testDataType);
        testDataSourceCreator = testDataSourceCreatorBuilder.setDataSourceId(testDataSourceId)
                .setPlatformType(testPlatformType).setPlatformId(testPlatformId)
                .setPlatformAppType(testPlatformAppType).setPlatformAppId(testPlatformAppId)
                .setApplicationType(testApplicationType).setDataSourceMetadata(testDataSourceMetaData)
                .setPlatformMetadata(testPlatformMetaData).setPlatformAppMetadata(testPlatformAppMetaData)
                .setApplicationMetaData(testAppMetaData).setDataRate(testSampleNo, testTimeUnit)
                .setDataDescriptor(0, testDataDescriptor).build();

        testData = new Data(testTimestamp);
        testDataArray = new Data[1];
        testDataArray[0] = testData;
    }

    @Ignore
    @Test
    public void connectionTest() {
        assertEquals(MCerebrumStatus.INVALID_PARAMETER, testmCerebrumAPI.connect(testConnectionCallbackNull));
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testmCerebrumAPINotInit.connect(testConnectionCallback));
        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.connect(testConnectionCallback));
    }

    // Test after this point will always fail until connectionTest() is passing.

    @Ignore
    @Test
    public void registerTest() {
        assertNull(testmCerebrumAPINotInit.register(testDataSourceCreator));
        assertEquals(testDataSourceCreator, testmCerebrumAPI.register(testDataSourceCreator).getDataSource()); // This will not work without a comparative method in DataSourceCreator
        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.register(testDataSourceCreator).getStatus());
    }

    @Ignore
    @Test
    public void insertTest() {
        createRegistration();

        // Test for an array of data
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED,
                testmCerebrumAPINotInit.insert(testRegistration, testDataArray));
        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.insert(testRegistration, testDataArray));

        // Test for single data object
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED,
                testmCerebrumAPINotInit.insert(testRegistration, testData));
        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.insert(testRegistration, testData));
    }

    @Ignore
    @Test
    public void findTest() {
        createDataSourceRequest();
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testmCerebrumAPINotInit.find(testDataSourceRequest));
        assertEquals(testDataSourceSet, MCerebrumAPI.find(testDataSourceRequest));
    }

    // This test requires registerTest() to be passing.
    @Ignore
    @Test
    public void unregisterTest() {
        createRegistration();
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED,
                testmCerebrumAPINotInit.unregister(testRegistration));
    }

    @Ignore
    @Test
    public void disconnectTest() {
        assertEquals(MCerebrumStatus.INVALID_PARAMETER, testmCerebrumAPI.disconnect(testConnectionCallbackNull));
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testmCerebrumAPINotInit.disconnect(testConnectionCallback));
        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.disconnect(testConnectionCallback));
    }
}
