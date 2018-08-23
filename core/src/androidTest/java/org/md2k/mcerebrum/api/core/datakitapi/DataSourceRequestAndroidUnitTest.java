package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class DataSourceRequestAndroidUnitTest {
    private DataSourceRequest testDataSourceRequest;

    @Before
    public void CreateDataSourceRequest() {
        testDataSourceRequest = CommonObjectConstructors.createDataSourceRequest();
    }

    @Test
    public void DataSourceRequestBuilderTest() {
        assertEquals(TestingConstants.DATA_SOURCE_TYPE_ARRAY[0], testDataSourceRequest.getDataSourceType());
        assertEquals(TestingConstants.DATASOURCE_ID_ARRAY[0], testDataSourceRequest.getDataSourceId());
        assertEquals(TestingConstants.PLATFORM_TYPE_ARRAY[0], testDataSourceRequest.getPlatformType());
        assertEquals(TestingConstants.PLATFORM_ID_ARRAY[0], testDataSourceRequest.getPlatformId());
        assertEquals(TestingConstants.PLATFORM_APP_TYPE_ARRAY[0], testDataSourceRequest.getPlatformAppType());
        assertEquals(TestingConstants.PLATFORM_APP_ID_ARRAY[0], testDataSourceRequest.getPlatformAppId());
        assertEquals(TestingConstants.APPLICATION_TYPE_ARRAY[0], testDataSourceRequest.getApplicationType());
    }

    @Test
    public void DataSourceRequest_ParcelableWriteReadTest() {
        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceRequest.writeToParcel(parcel, testDataSourceRequest.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceRequest createdFromParcel = DataSourceRequest.CREATOR.createFromParcel(parcel);
        DataSourceRequest[] createdFromParcelArray = DataSourceRequest.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(TestingConstants.DATA_SOURCE_TYPE_ARRAY[0], createdFromParcel.getDataSourceType());
        assertEquals(TestingConstants.DATASOURCE_ID_ARRAY[0], createdFromParcel.getDataSourceId());
        assertEquals(TestingConstants.PLATFORM_TYPE_ARRAY[0], createdFromParcel.getPlatformType());
        assertEquals(TestingConstants.PLATFORM_ID_ARRAY[0], createdFromParcel.getPlatformId());
        assertEquals(TestingConstants.PLATFORM_APP_TYPE_ARRAY[0], createdFromParcel.getPlatformAppType());
        assertEquals(TestingConstants.PLATFORM_APP_ID_ARRAY[0], createdFromParcel.getPlatformAppId());
        assertEquals(TestingConstants.APPLICATION_TYPE_ARRAY[0], createdFromParcel.getApplicationType());
        assertEquals(TestingConstants.APPLICATION_ID_ARRAY[0], createdFromParcel.getApplicationId());
    }

    @Test
    public void DataSourceRequest_ParcelableWriteReadComparableTest() {
        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceRequest.writeToParcel(parcel, testDataSourceRequest.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceRequest createdFromParcel = DataSourceRequest.CREATOR.createFromParcel(parcel);
        DataSourceRequest[] createdFromParcelArray = DataSourceRequest.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataSourceRequest, createdFromParcel);
    }

    @Test
    public void DataSourceRequest_ParcelableWriteReadComparableTest() {
        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testDataSourceRequest.writeToParcel(parcel, testDataSourceRequest.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceRequest createdFromParcel = DataSourceRequest.CREATOR.createFromParcel(parcel);
        DataSourceRequest[] createdFromParcelArray = DataSourceRequest.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(testDataSourceRequest, createdFromParcel);
    }
}
