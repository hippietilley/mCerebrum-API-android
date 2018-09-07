package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataSourceReadWriteAndroidUnitTest {
    static final double DELTA = TestingConstants.DELTA;

    private final String[] dataSourceTypeArray = TestingConstants.DATA_SOURCE_TYPE_ARRAY;
    private final String[] dataSourceIdArray = TestingConstants.DATASOURCE_ID_ARRAY;
    private final String[] platformTypeArray = TestingConstants.PLATFORM_TYPE_ARRAY;
    private final String[] platformIdArray = TestingConstants.PLATFORM_ID_ARRAY;
    private final String[] platformAppTypeArray = TestingConstants.PLATFORM_APP_TYPE_ARRAY;
    private final String[] platformAppIdArray = TestingConstants.PLATFORM_APP_ID_ARRAY;
    private final String[] applicationTypeArray = TestingConstants.APPLICATION_TYPE_ARRAY;
    private final String[] applicationIdArray = TestingConstants.APPLICATION_ID_ARRAY;

    private final String testKey = TestingConstants.TEST_KEY;

    private ArrayList testDataDescriptors = new ArrayList();
    private PlatformMetaData testPlatformMetaData;
    private PlatformAppMetaData testPlatformAppMetaData;
    private ApplicationMetaData testAppMetaData;
    private DataDescriptor testDataDescriptor;
    private DataSourceMetaData testDataSourceMetaData;
    private DataSourceReadWrite testDataSourceReadWrite;

    @Before
    public void objectCreation(){
        testPlatformMetaData = CommonObjectConstructors.createPlatformMetaData();
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();
        testAppMetaData = CommonObjectConstructors.createApplicationMetaData();
        testDataDescriptor = CommonObjectConstructors.createDataDescriptor();
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();
        testDataDescriptors.add(testDataDescriptor);
    }

    @Test
    public void dataSourceTypeTest() {
        for (String dataSourceType : dataSourceTypeArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setDataSourceType(dataSourceType);
            assertEquals(dataSourceType, testDataSourceReadWrite.getDataSourceType());
        }
    }

    @Test
    public void dataSourceIdTest() {
        for (String dataSourceId : dataSourceIdArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setDataSourceId(dataSourceId);
            assertEquals(dataSourceId, testDataSourceReadWrite.getDataSourceId());
        }
    }

    @Test
    public void platformTypeTest() {
        for (String platformType : platformTypeArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformType(platformType);
            assertEquals(platformType, testDataSourceReadWrite.getPlatformType());
        }
    }

    @Test
    public void platformIdTest() {
        for (String platformId : platformIdArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformId(platformId);
            assertEquals(platformId, testDataSourceReadWrite.getPlatformId());
        }
    }

    @Test
    public void platformAppTypeTest() {
        for (String platformAppType : platformAppTypeArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformAppType(platformAppType);
            assertEquals(platformAppType, testDataSourceReadWrite.getPlatformAppType());
        }
    }

    @Test
    public void platformAppIdTest() {
        for (String platformAppId : platformAppIdArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setPlatformAppId(platformAppId);
            assertEquals(platformAppId, testDataSourceReadWrite.getPlatformAppId());
        }
    }

    @Test
    public void applicationTypeTest() {
        for (String applicationType : applicationTypeArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setApplicationType(applicationType);
            assertEquals(applicationType, testDataSourceReadWrite.getApplicationType());
        }
    }

    @Test
    public void applicationIdTest() {
        for (String applicationId : applicationIdArray) {
            testDataSourceReadWrite = new DataSourceReadWrite();
            testDataSourceReadWrite.setApplicationId(applicationId);
            assertEquals(applicationId, testDataSourceReadWrite.getApplicationId());
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
        assertEquals(testPlatformMetaData.getMetaData(testKey), testDataSourceReadWrite.getPlatformMetaData().getMetaData(testKey));
    }

    @Test
    public void platformMetaDataComparableTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setPlatformMetadata(testPlatformMetaData);
        assertEquals(testPlatformMetaData, testDataSourceReadWrite.getPlatformMetaData());
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
    public void platformAppMetaDataComparableTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        assertEquals(testPlatformAppMetaData, testDataSourceReadWrite.getPlatformAppMetaData());
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
        assertEquals(testAppMetaData.getMetaData(testKey), testDataSourceReadWrite.getApplicationMetaData().getMetaData(testKey));
    }

    @Test
    public void applicationMetaDataComparableTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        assertEquals(testAppMetaData, testDataSourceReadWrite.getApplicationMetaData());
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        assertEquals(testDataDescriptor.getTitle(), testDataSourceReadWrite.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataDescriptor.getSummary(), testDataSourceReadWrite.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataDescriptor.getDescription(), testDataSourceReadWrite.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataDescriptor.getMinValue(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataDescriptor.getMaxValue(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsString(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataDescriptor.getPossibleValuesAsInt(),
                testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataDescriptor.getUnit(), testDataSourceReadWrite.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataDescriptor.getValue(testKey), testDataSourceReadWrite.getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void dataDescriptorComparableTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        for (DataDescriptor dataDescriptorToCheck : testDataSourceReadWrite.getDataDescriptors())
            assertEquals(testDataDescriptor, dataDescriptorToCheck);
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
        assertEquals(testDataSourceMetaData.getTitle(), testDataSourceReadWrite.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), testDataSourceReadWrite.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), testDataSourceReadWrite.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceMetaData.getMetaData(testKey), testDataSourceReadWrite.getDataSourceMetaData().getMetaData(testKey));
    }

    @Test
    public void dataSourceMetaDataComparableTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
        assertEquals(testDataSourceMetaData, testDataSourceReadWrite.getDataSourceMetaData());
    }

    @Test
    public void dataSourceReadWrite_ParcelableWriteReadTest() {
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();

        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceReadWrite.writeToParcel(parcel , testDataSourceReadWrite.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceReadWrite createdFromParcel = DataSourceReadWrite.CREATOR.createFromParcel(parcel);
        DataSourceReadWrite[] createdFromParcelArray = DataSourceReadWrite.CREATOR.newArray(1);

        // Verify the results.
        assertNotEquals(0, createdFromParcelArray.length);
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
        assertEquals(testDataSourceReadWrite.getDataSourceMetaData().getMetaData(testKey),
                createdFromParcel.getDataSourceMetaData().getMetaData(testKey));

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
        assertEquals(testDataSourceReadWrite.getPlatformMetaData().getMetaData(testKey),
                createdFromParcel.getPlatformMetaData().getMetaData(testKey));

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
        assertEquals(testDataSourceReadWrite.getApplicationMetaData().getMetaData(testKey),
                createdFromParcel.getApplicationMetaData().getMetaData(testKey));

        // Test DataDescriptors for equality
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getTitle(),
                createdFromParcel.getDataDescriptors().get(0).getTitle());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getSummary(),
                createdFromParcel.getDataDescriptors().get(0).getSummary());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getDescription(),
                createdFromParcel.getDataDescriptors().get(0).getDescription());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMinValue(),
                createdFromParcel.getDataDescriptors().get(0).getMinValue(), DELTA);
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getMaxValue(),
                createdFromParcel.getDataDescriptors().get(0).getMaxValue(), DELTA);
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsString(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsString());
        assertArrayEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getPossibleValuesAsInt(),
                createdFromParcel.getDataDescriptors().get(0).getPossibleValuesAsInt());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getUnit(),
                createdFromParcel.getDataDescriptors().get(0).getUnit());
        assertEquals(testDataSourceReadWrite.getDataDescriptors().get(0).getValue(testKey),
                createdFromParcel.getDataDescriptors().get(0).getValue(testKey));
    }

    @Test
    public void dataSourceReadWrite_ParcelableWriteReadComparableTest() {
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();

        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceReadWrite.writeToParcel(parcel, testDataSourceReadWrite.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceReadWrite createdFromParcel = DataSourceReadWrite.CREATOR.createFromParcel(parcel);
        DataSourceReadWrite[] createdFromParcelArray = DataSourceReadWrite.CREATOR.newArray(1);

        // Verify the results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataSourceReadWrite, createdFromParcel);
    }
}
