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
    private DataPointObject mDataPointObject;

    private final Object testObjectSample = (Object)(testSample);

    private final Object[] testObjectSampleArray = {testSampleArray[0], testSampleArray[1]};

    private DataPointObject mDataPointObjectArray;

    // Create the object.
    @Before
    public void createDataPointObject() {
        mDataPointObject = new DataPointObject(testTimestamp, testSample);
        mDataPointObjectArray = new DataPointObject(testTimestamp, testSampleArray);
    }


    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointObject.getTimestamp());
        assertEquals("Hello world", mDataPointObject.getSample()[0]);
        assertEquals(testTimestamp, mDataPointObjectArray.getTimestamp());
        for (int i = 0; i < testSampleArray.length; i++) {
            assertEquals(testSampleArray[i], mDataPointObjectArray.getSample()[i]);
        }
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
        DataPointObject dataPointClone = mDataPointObject.clone();
        assertEquals(mDataPointObject, dataPointClone);
        assertNotSame(mDataPointObject, dataPointClone);
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
        for (int i = 0; i < createdFromParcel.getSample().length; i++)
            assertEquals(mDataPointObject.getSample()[i], createdFromParcel.getSample()[i]);
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