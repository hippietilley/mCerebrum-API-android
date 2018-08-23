package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class DataSourceRequestAndroidUnitTest {
    private final String testDataSourceType = "Test Data Source Type";
    private final String testDataSourceId = "Test Data Source Id";
    private final String testPlatformType = "Test Platform Type";
    private final String testPlatformId = "Test Platform Id";
    private final String testPlatformAppType = "Test Platform App Type";
    private final String testPlatformAppId = "Test Platform App Id";
    private final String testApplicationType = "Test Application Type";
    private final String testApplicationId = "Test Application Id";
    private DataSourceRequest testDataSourceRequest;

    @Before
    public void CreateDataSourceRequest() {
        testDataSourceRequest = new DataSourceRequest.Builder().setDataSourceType(testDataSourceType)
                .setDataSourceId(testDataSourceId).setPlatformType(testPlatformType)
                .setPlatformId(testPlatformId).setPlatformAppType(testPlatformAppType)
                .setPlatformAppId(testPlatformAppId).setApplicationType(testApplicationType)
                .setApplicationId(testApplicationId).build();
    }

    @Test
    public void DataSourceRequestBuilderTest() {
        assertEquals(testDataSourceType, testDataSourceRequest.getDataSourceType());
        assertEquals(testDataSourceId, testDataSourceRequest.getDataSourceId());
        assertEquals(testPlatformType, testDataSourceRequest.getPlatformType());
        assertEquals(testPlatformId, testDataSourceRequest.getPlatformId());
        assertEquals(testPlatformAppType, testDataSourceRequest.getPlatformAppType());
        assertEquals(testPlatformAppId, testDataSourceRequest.getPlatformAppId());
        assertEquals(testApplicationType, testDataSourceRequest.getApplicationType());
        assertEquals(testApplicationId, testDataSourceRequest.getApplicationId());
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
        assertEquals(testDataSourceType, createdFromParcel.getDataSourceType());
        assertEquals(testDataSourceId, createdFromParcel.getDataSourceId());
        assertEquals(testPlatformType, createdFromParcel.getPlatformType());
        assertEquals(testPlatformId, createdFromParcel.getPlatformId());
        assertEquals(testPlatformAppType, createdFromParcel.getPlatformAppType());
        assertEquals(testPlatformAppId, createdFromParcel.getPlatformAppId());
        assertEquals(testApplicationType, createdFromParcel.getApplicationType());
        assertEquals(testApplicationId, createdFromParcel.getApplicationId());
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
}
