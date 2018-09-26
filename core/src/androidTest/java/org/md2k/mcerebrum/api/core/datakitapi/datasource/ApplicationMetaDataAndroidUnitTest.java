package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.CommonObjectConstructors;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class ApplicationMetaDataAndroidUnitTest {
    private String testTitle = TestingConstants.TEST_TITLE;
    private String testSummary = TestingConstants.TEST_SUMMARY;
    private String testDescription = TestingConstants.TEST_DESCRIPTION;
    private String testVersionName = TestingConstants.TEST_VERSION_NAME;
    private int testVersionNumber = TestingConstants.TEST_VERSION_NUMBER;
    private String testKey = TestingConstants.TEST_KEY;
    private String testValue = TestingConstants.TEST_VALUE;
    private ApplicationMetaData testAppMetaData;

    @Test
    public void applicationMetaDataBuilderTest() {
        testAppMetaData = new ApplicationMetaData.Builder().setMetaData(testKey, testValue).build();
        assertEquals(testValue, testAppMetaData.getMetaData(testKey));

        testAppMetaData = new ApplicationMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).build();
        assertEquals(testTitle, testAppMetaData.getTitle());
        assertEquals(testSummary, testAppMetaData.getSummary());
        assertEquals(testDescription, testAppMetaData.getDescription());
        assertEquals(testVersionName, testAppMetaData.getVersionName());
        assertEquals(testVersionNumber, testAppMetaData.getVersionNumber());
        assertNull(testAppMetaData.getMetaData(testKey));
    }

    @Test
    public void applicationMetaDataParcelableWriteReadTest() {
        testAppMetaData = CommonObjectConstructors.createApplicationMetaData();

        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testAppMetaData.writeToParcel(parcel, testAppMetaData.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        ApplicationMetaData createdFromParcel = ApplicationMetaData.CREATOR.createFromParcel(parcel);
        ApplicationMetaData[] createdFromParcelArray = ApplicationMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(createdFromParcel.getKeys().length,testAppMetaData.getKeys().length);
        assertThat(createdFromParcel, is(equalTo(testAppMetaData)));
    }

    @Test
    public void applicationMetaDataHashCodeTest() {
        testAppMetaData = CommonObjectConstructors.createApplicationMetaData();
        ApplicationMetaData testAppMetaData2 = CommonObjectConstructors.createApplicationMetaData();
        assertEquals(testAppMetaData.hashCode(), testAppMetaData2.hashCode());
    }
}
