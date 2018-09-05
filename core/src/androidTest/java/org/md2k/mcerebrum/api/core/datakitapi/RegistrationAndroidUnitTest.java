package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import org.md2k.mcerebrum.api.core.MCerebrumAPI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RegistrationAndroidUnitTest {
    static final double DELTA = TestingConstants.DELTA;
    final int[] statusIntArray = TestingConstants.STATUS_INT_ARRAY;

    private final String testKey = TestingConstants.TEST_KEY;

    DataSourceCreator testDataSourceCreator;
    DataSourceReadWrite testDataSourceReadWrite;
    Registration testReg;
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;

    @Before
    public void objectCreation() {
        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        MCerebrumAPI.init(testContext);

        // Create test data sources
        testDataSourceCreator = CommonObjectConstructors.createDataSourceCreator();
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();
    }

    @Test
    public void registrationCreatorTest() {
        for (int statusInt : statusIntArray) {
            testReg = new Registration(testDataSourceCreator, statusInt);
            assertEquals(statusInt, testReg.getStatus());
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
        assertEquals(testDataSourceCreator.getDataSourceMetaData().getData(testKey),
                testReg.getDataSource().getDataSourceMetaData().getData(testKey));

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
        assertEquals(testDataSourceCreator.getPlatformMetaData().getData(testKey),
                testReg.getDataSource().getPlatformMetaData().getData(testKey));

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
        assertEquals(testDataSourceCreator.getApplicationMetaData().getData(testKey),
                testReg.getDataSource().getApplicationMetaData().getData(testKey));

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
        for (int statusInt : statusIntArray) {
            testReg = new Registration(testDataSourceCreator, statusInt);
            assertEquals(statusInt, testReg.getStatus());
        }
        assertEquals(testDataSourceCreator, testReg.getDataSource());
    }

    @Test
    public void registrationReadWriteTest() {
        for (int statusInt : statusIntArray) {
             testReg = new Registration(testDataSourceReadWrite, statusInt);
             assertEquals(statusInt, testReg.getStatus());
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
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getData(testKey),
                testReg.getDataSource().getDataSourceMetaData().getData(testKey));

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
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getData(testKey),
                testReg.getDataSource().getPlatformMetaData().getData(testKey));

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
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getData(testKey),
                testReg.getDataSource().getApplicationMetaData().getData(testKey));

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
        for (int statusInt : statusIntArray) {
             testReg = new Registration(testDataSourceReadWrite, statusInt);
             assertEquals(statusInt, testReg.getStatus());
        }
        assertEquals(testDataSourceReadWrite, testReg.getDataSource());
    }
}
