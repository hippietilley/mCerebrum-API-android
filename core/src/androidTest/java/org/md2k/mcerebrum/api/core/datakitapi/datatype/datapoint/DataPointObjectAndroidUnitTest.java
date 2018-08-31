package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

@SmallTest
public class DataPointObjectAndroidUnitTest {
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

    private final String testSample = "Hello world";
    private final String[] testSampleArray = {"Test 1", "Test 2"};
    private DataPointObject mDataPointObjectString;
    private DataPointObject mDataPointObject;
    private DataPointObject mDataPointObjectArray;
    private DataPointObject mDataPointObjectStringArray;

    private final Object testObjectSample = (Object)(testSample);
    private final Boolean testObjectSample1 = true;
    private final Double testObjectSample2 = 6.28;
    private final Integer testObjectSample3 = 21;

    private final Object[] testObjectSampleArray = {testObjectSample1, testObjectSample2, testObjectSample3};

    // Create the object.
    @Before
    public void createDataPointObject() {
        mDataPointObjectString = new DataPointObject(testTimestamp, testSample);
        mDataPointObjectStringArray = new DataPointObject(testTimestamp, testSampleArray);
        mDataPointObject = new DataPointObject(testTimestamp, testObjectSample);
        mDataPointObjectArray = new DataPointObject(testTimestamp, testObjectSampleArray);
    }


    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointObjectString.getTimestamp());
        assertEquals("Hello world", mDataPointObjectString.getSample()[0]);

        assertEquals(testTimestamp, mDataPointObjectStringArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointObjectStringArray.getSample());

        assertEquals(testTimestamp, mDataPointObject.getTimestamp());
        assertEquals(testObjectSample, mDataPointObject.getSample());

        assertEquals(testTimestamp, mDataPointObjectArray.getTimestamp());
        assertArrayEquals(testObjectSampleArray, mDataPointObjectArray.getSample());
    }

    @Test
    public void dataPointObjectCloneTest() {
        DataPointObject dataPointClone = mDataPointObject.clone();
        assertEquals(mDataPointObject.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointObject.getSample(), dataPointClone.getSample());
        assertNotSame(mDataPointObject, dataPointClone);
    }

    @Test
    public void dataPointObjectCloneComparableTest() {
        DataPointObject dataPointClone = mDataPointObjectString.clone();
        assertEquals(mDataPointObjectString, dataPointClone);
        assertNotSame(mDataPointObjectString, dataPointClone);
    }

    @Test
    public void dataPointObjectString_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectString.writeToParcel(parcel, mDataPointObjectString.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectString.getTimestamp(), createdFromParcel.getTimestamp());
        for (String sample : createdFromParcel.getSample()) {
            for (String expectedSample : mDataPointObject.getSample())
                assertEquals(expectedSample, sample);
        }
    }


    @Test
    public void dataPointObject_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObject.writeToParcel(parcel, mDataPointObject.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObject.getTimestamp(), createdFromParcel.getTimestamp());
        for (String sample : createdFromParcel.getSample()) {
            for (String expectedSample : mDataPointObject.getSample())
                assertEquals(expectedSample, sample);
        }
    }

    @Test
    public void dataPointObject_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObject.writeToParcel(parcel, mDataPointObject.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObject, createdFromParcel);
    }

    @Test
    public void dataPointObjectString_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectString.writeToParcel(parcel, mDataPointObjectString.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectString, createdFromParcel);
    }

    @Test
    public void dataPointObjectStringArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectStringArray.writeToParcel(parcel, mDataPointObjectStringArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectStringArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointObjectStringArray.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointObjectArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectArray.writeToParcel(parcel, mDataPointObjectArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointObjectArray.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointObjectStringArray_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectStringArray.writeToParcel(parcel, mDataPointObjectStringArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectStringArray, createdFromParcel);
    }

    @Test
    public void dataPointObjectArray_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectArray.writeToParcel(parcel, mDataPointObjectArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointObjectArray, createdFromParcel);
    }
}