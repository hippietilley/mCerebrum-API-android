package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.CommonObjectConstructors;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;

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
    public void dataSourceMetaDataBuilderTest() {
        testDataSourceMetaData = new DataSourceMetaData.Builder().setMetaData(testKey, testValue).build();
        assertEquals(testValue, testDataSourceMetaData.getMetaData(testKey));

        testDataSourceMetaData = new DataSourceMetaData.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).build();
        assertEquals(testTitle, testDataSourceMetaData.getTitle());
        assertEquals(testSummary, testDataSourceMetaData.getSummary());
        assertEquals(testDescription, testDataSourceMetaData.getDescription());
        assertNull(testDataSourceMetaData.getMetaData(testKey));
    }

    @Test
    public void dataSourceMetaDataParcelableWriteReadComparableTest() {
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

    @Test
    public void dataSourceMetaDataHashCodeTest() {
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();
        DataSourceMetaData testDataSourceMetaData2 = CommonObjectConstructors.createDataSourceMetaData();
        assertEquals(testDataSourceMetaData.hashCode(), testDataSourceMetaData2.hashCode());
    }
}
