package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class DataSourceMetaDataAndroidUnitTest {
    private final String testTitle = TestingConstants.TEST_TITLE;
    private final String testSummary = TestingConstants.TEST_SUMMARY;
    private final String testDescription = TestingConstants.TEST_DESCRIPTION;
    private final String testKey = TestingConstants.TEST_KEY;
    private final String testValue = TestingConstants.TEST_VALUE;
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
        assertNull(testDataSourceMetaData.getValue(testKey));
    }

    @Test
    public void DataSourceMetaData_ParcelableWriteReadTest() {
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataSourceMetaData.writeToParcel(parcel, testDataSourceMetaData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceMetaData createdFromParcel = DataSourceMetaData.CREATOR.createFromParcel(parcel);
        DataSourceMetaData[] createdFromParcelArray = DataSourceMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataSourceMetaData.getTitle(), createdFromParcel.getTitle());
        assertEquals(testDataSourceMetaData.getSummary(), createdFromParcel.getSummary());
        assertEquals(testDataSourceMetaData.getDescription(), createdFromParcel.getDescription());
        assertEquals(testDataSourceMetaData.getValue(testKey), createdFromParcel.getValue(testKey));
    }

    @Test
    public void DataSourceMetaData_ParcelableWriteReadComparableTest() {
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataSourceMetaData.writeToParcel(parcel, testDataSourceMetaData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataSourceMetaData createdFromParcel = DataSourceMetaData.CREATOR.createFromParcel(parcel);
        DataSourceMetaData[] createdFromParcelArray = DataSourceMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(testDataSourceMetaData)));
    }
}
