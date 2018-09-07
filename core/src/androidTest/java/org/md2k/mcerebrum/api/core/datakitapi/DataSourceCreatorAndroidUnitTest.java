package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.os.Parcel;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
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
        assertEquals(testPlatformMetaData.getMetaData(TestingConstants.TEST_KEY),
                testDataSourceCreator.getPlatformMetaData().getMetaData(TestingConstants.TEST_KEY));
    }

    @Test
    public void platformMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformMetadata(testPlatformMetaData).build();
        assertThat(testDataSourceCreator.getPlatformMetaData(), is(equalTo(testPlatformMetaData)));
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
        assertThat(testDataSourceCreator.getPlatformAppMetaData(), is(equalTo(testPlatformAppMetaData)));
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        assertEquals(testAppMetaData.getTitle(), testDataSourceCreator.getApplicationMetaData().getTitle());
        assertEquals(testAppMetaData.getSummary(), testDataSourceCreator.getApplicationMetaData().getSummary());
        assertEquals(testAppMetaData.getDescription(), testDataSourceCreator.getApplicationMetaData().getDescription());
        assertEquals(testAppMetaData.getVersionName(), testDataSourceCreator.getApplicationMetaData().getVersionName());
        assertEquals(testAppMetaData.getVersionNumber(), testDataSourceCreator.getApplicationMetaData().getVersionNumber());
        assertEquals(testAppMetaData.getMetaData(TestingConstants.TEST_KEY), testDataSourceCreator.getApplicationMetaData().getMetaData(TestingConstants.TEST_KEY));
    }

    @Test
    public void applicationMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        // testAppMetaData.versionName turns null at this point TODO: make a test that checks for object mutability
        assertThat(testDataSourceCreator.getApplicationMetaData(), is(equalTo(testAppMetaData)));
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
            assertThat(dataDescriptorToCheck, is(equalTo(testDataDescriptor)));
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertEquals(testDataSourceMetaData.getTitle(), testDataSourceCreator.getDataSourceMetaData().getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), testDataSourceCreator.getDataSourceMetaData().getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), testDataSourceCreator.getDataSourceMetaData().getDescription());
        assertEquals(testDataSourceMetaData.getMetaData(TestingConstants.TEST_KEY), testDataSourceCreator.getDataSourceMetaData().getMetaData(TestingConstants.TEST_KEY));
    }

    @Test
    public void dataSourceMetaDataComparableTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertThat(testDataSourceCreator.getDataSourceMetaData(), is(equalTo(testDataSourceMetaData)));
    }

    @Test
    public void dataSourceCreator_ParcelableWriteReadComparableTest() {
        testDataSourceCreator = CommonObjectConstructors.createDataSourceCreator();

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
        assertThat(createdFromParcel, is(equalTo(testDataSourceCreator)));
    }
}
