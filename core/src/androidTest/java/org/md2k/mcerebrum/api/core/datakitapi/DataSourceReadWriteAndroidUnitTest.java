package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataSourceReadWriteAndroidUnitTest {
    private final String[] dataSourceTypeArray = TestingConstants.DATA_SOURCE_TYPE_ARRAY;
    private final String[] dataSourceIdArray = TestingConstants.DATASOURCE_ID_ARRAY;
    private final String[] platformTypeArray = TestingConstants.PLATFORM_TYPE_ARRAY;
    private final String[] platformIdArray = TestingConstants.PLATFORM_ID_ARRAY;
    private final String[] platformAppTypeArray = TestingConstants.PLATFORM_APP_TYPE_ARRAY;
    private final String[] platformAppIdArray = TestingConstants.PLATFORM_APP_ID_ARRAY;
    private final String[] applicationTypeArray = TestingConstants.APPLICATION_TYPE_ARRAY;
    private final String[] applicationIdArray = TestingConstants.APPLICATION_ID_ARRAY;

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
        assertEquals(testPlatformMetaData, testDataSourceReadWrite.getPlatformMetaData());
    }

    @Test
    public void platformAppMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        assertEquals(testPlatformAppMetaData, testDataSourceReadWrite.getPlatformAppMetaData());
    }

    @Test
    public void applicationMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        assertEquals(testAppMetaData, testDataSourceReadWrite.getApplicationMetaData());
    }

    @Test
    public void dataDescriptorTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        for (DataDescriptor dataDescriptorToCheck : testDataSourceReadWrite.getDataDescriptors())
            assertEquals(testDataDescriptor, dataDescriptorToCheck);
    }

    @Test
    public void dataSourceMetaDataTest() {
        testDataSourceReadWrite = new DataSourceReadWrite();
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
        assertEquals(testDataSourceMetaData, testDataSourceReadWrite.getDataSourceMetaData());
    }

    @Test
    public void dataSourceReadWriteParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(testDataSourceReadWrite)));
    }

    @Test
    public void dataSourceReadWriteHashCodeTest() {
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();
        DataSourceReadWrite testDataSourceReadWrite2 = CommonObjectConstructors.createDataSourceReadWrite();
        assertEquals(testDataSourceReadWrite.hashCode(), testDataSourceReadWrite2.hashCode());
    }
}