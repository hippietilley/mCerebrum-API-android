package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SmallTest
public class DataSourceMetaDataAndroidUnitTest {
    private final String testTitle = "Test Title";
    private final String testSummary = "Test Summary";
    private final String testDescription = "Test Description";
    private final String testKey = "key";
    private final String testValue = "value";
    private DataSourceMetaData testDataSourceMetaData;

    @Test
    public void DataSourceMetaDataBuilderTest() {
        testDataSourceMetaData = new DataSourceMetaData.Builder().setValue(testKey, testValue).build();
        assertEquals(testValue, testDataSourceMetaData.getValue(testKey));

        testDataSourceMetaData = new DataSourceMetaData.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).build();
        assertEquals(testTitle, testDataSourceMetaData.getTitle());
        assertEquals(testSummary, testDataSourceMetaData.getSummary());
        assertEquals(testDescription, testDataSourceMetaData.getDescription());
        assertEquals(null, testDataSourceMetaData.getValue(testKey));
    }

    @Test
    public void DataSourceMetaData_ParcelableWriteReadTest() {
        testDataSourceMetaData = new DataSourceMetaData.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setValue(testKey, testValue).build();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataSourceMetaData.writeToParcel(parcel, testDataSourceMetaData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceMetaData createdFromParcel = DataSourceMetaData.CREATOR.createFromParcel(parcel);
        DataSourceMetaData[] createdFromParcelArray = DataSourceMetaData.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(testDataSourceMetaData.getTitle(), createdFromParcel.getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), createdFromParcel.getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), createdFromParcel.getDescription());
        assertEquals(testDataSourceMetaData.getValue(testKey), createdFromParcel.getValue(testKey));
    }

    @Test
    public void DataSourceMetaData_ParcelableWriteReadComparableTest() {
        testDataSourceMetaData = new DataSourceMetaData.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setValue(testKey, testValue).build();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataSourceMetaData.writeToParcel(parcel, testDataSourceMetaData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceMetaData createdFromParcel = DataSourceMetaData.CREATOR.createFromParcel(parcel);
        DataSourceMetaData[] createdFromParcelArray = DataSourceMetaData.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(testDataSourceMetaData, createdFromParcel);
    }
}
