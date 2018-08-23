package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class ApplicationMetaDataAndroidUnitTest {
    private String testTitle = "testApp";
    private String testSummary = "testSummary";
    private String testDescription = "Description of the testApp";
    private String testVersionName = "Test version";
    private int testVersionNumber = 1;
    private String testKey = "Key";
    private String testValue = "Value";
    private ApplicationMetaData testAppMetaData;

    @Test
    public void ApplicationMetaDataBuilderTest() {
        testAppMetaData = new ApplicationMetaData.Builder().setValue(testKey, testValue).build();
        assertEquals(testValue, testAppMetaData.getValue(testKey));

        testAppMetaData = new ApplicationMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).build();
        assertEquals(testTitle, testAppMetaData.getTitle());
        assertEquals(testSummary, testAppMetaData.getSummary());
        assertEquals(testDescription, testAppMetaData.getDescription());
        assertEquals(testVersionName, testAppMetaData.getVersionName());
        assertEquals(testVersionNumber, testAppMetaData.getVersionNumber());
        assertNull(testAppMetaData.getValue(testKey));
    }

    @Test
    public void ApplicationMetaData_ParcelableWriteReadTest() {
        testAppMetaData = new ApplicationMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).setValue(testKey, testValue).build();

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
        assertEquals(testAppMetaData.getTitle(), createdFromParcel.getTitle());
        assertEquals(testAppMetaData.getSummary(), createdFromParcel.getSummary());
        assertEquals(testAppMetaData.getDescription(), createdFromParcel.getDescription());
        assertEquals(testAppMetaData.getVersionName(), createdFromParcel.getVersionName());
        assertEquals(testAppMetaData.getVersionNumber(), createdFromParcel.getVersionNumber());
        assertEquals(testAppMetaData.getValue(testKey), createdFromParcel.getValue(testKey));
    }

    @Test
    public void ApplicationMetaData_ParcelableWriteReadComparableTest() {
        testAppMetaData = new ApplicationMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setVersionName(testVersionName)
                .setVersionNumber(testVersionNumber).setValue(testKey, testValue).build();

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
        assertEquals(testAppMetaData, createdFromParcel);
    }
}
