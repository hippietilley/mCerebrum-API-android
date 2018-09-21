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
    private String[] dataSourceTypeArray = TestingConstants.DATA_SOURCE_TYPE_ARRAY;
    private DataType[] dataTypeArray = TestingConstants.DATA_TYPE_ARRAY;
    private String[] dataSourceIdArray = TestingConstants.DATASOURCE_ID_ARRAY;
    private String[] platformTypeArray = TestingConstants.PLATFORM_TYPE_ARRAY;
    private String[] platformIdArray = TestingConstants.PLATFORM_ID_ARRAY;
    private String[] platformAppTypeArray = TestingConstants.PLATFORM_APP_TYPE_ARRAY;
    private String[] platformAppIdArray = TestingConstants.PLATFORM_APP_ID_ARRAY;
    private String[] applicationTypeArray = TestingConstants.APPLICATION_TYPE_ARRAY;
    private String[] applicationIdArray = TestingConstants.APPLICATION_ID_ARRAY;

    private DataSourceCreator testDataSourceCreator;
    private DataSourceMetaData testDataSourceMetaData;
    private PlatformMetaData testPlatformMetaData;
    private PlatformAppMetaData testPlatformAppMetaData;
    private ApplicationMetaData testAppMetaData;
    private DataDescriptor testDataDescriptor;
    private Context testContext;

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
        assertThat(testDataSourceCreator.getPlatformMetaData(), is(equalTo(testPlatformMetaData)));
    }

    @Test
    public void platformAppMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setPlatformAppMetadata(testPlatformAppMetaData).build();
        assertThat(testDataSourceCreator.getPlatformAppMetaData(), is(equalTo(testPlatformAppMetaData)));
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setApplicationMetaData(testAppMetaData).build();
        // testAppMetaData.versionName turns null at this point TODO: make a test that checks for object mutability
        assertThat(testDataSourceCreator.getApplicationMetaData(), is(equalTo(testAppMetaData)));
    }

    @Test
    public void platformAsPhoneTest() {
        testDataSourceCreator = CommonObjectConstructors.createDataSourceCreatorWithPlatformAsPhone();
        assertThat(testDataSourceCreator, is(equalTo(CommonObjectConstructors.createDataSourceCreatorWithPlatformAsPhone())));
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataDescriptor(0, testDataDescriptor).build();
        for (DataDescriptor dataDescriptorToCheck : testDataSourceCreator.getDataDescriptors())
            assertThat(dataDescriptorToCheck, is(equalTo(testDataDescriptor)));
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceCreator = new DataSourceCreator.Builder().setDataSourceMetadata(testDataSourceMetaData).build();
        assertThat(testDataSourceCreator.getDataSourceMetaData(), is(equalTo(testDataSourceMetaData)));
    }

    @Test
    public void dataSourceCreator_ParcelableWriteReadTest() {
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

    @Test
    public void dataSourceCreatorHashCodeTest() {
        testDataSourceCreator = CommonObjectConstructors.createDataSourceCreator();
        DataSourceCreator testDataSourceCreator2 = CommonObjectConstructors.createDataSourceCreator();
        assertEquals(testDataSourceCreator.hashCode(), testDataSourceCreator2.hashCode());
    }
}
