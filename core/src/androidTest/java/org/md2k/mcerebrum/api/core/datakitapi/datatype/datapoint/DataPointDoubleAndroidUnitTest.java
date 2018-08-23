package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

@SmallTest
public class DataPointDoubleAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final double testSample = 6.2831853071;
    private DataPointDouble mDataPointDouble;

    private final double[] testSampleArray = {3.14159265359, 1.61803398874989484, 2.71828, 6.2831853071};
    private DataPointDouble mDataPointDoubleArray;

    // Create the object.
    @Before
    public void createDataPointDouble() {
        mDataPointDouble = new DataPointDouble(testTimestamp, testSample);
        mDataPointDoubleArray = new DataPointDouble(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointDouble.getTimestamp());
        assertEquals(testSample, mDataPointDouble.getSample()[0], 0.1);
        assertEquals(testTimestamp, mDataPointDoubleArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointDoubleArray.getSample(), 0.1);
    }

    @Test
    public void dataPointDoubleCloneTest() {
        DataPointDouble dataPointClone = mDataPointDouble.clone();
        assertEquals(mDataPointDouble.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointDouble.getSample(), dataPointClone.getSample(), 0.1);
        assertNotSame(mDataPointDouble, dataPointClone);
    }

    @Test
    public void dataPointDoubleCloneComparableTest() {
        DataPointDouble dataPointClone = mDataPointDouble.clone();
        assertEquals(mDataPointDouble, dataPointClone);
        assertNotSame(mDataPointDouble, dataPointClone);
    }

    @Test
    public void dataPointDouble_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointDouble.writeToParcel(parcel, mDataPointDouble.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointDouble createdFromParcel = DataPointDouble.CREATOR.createFromParcel(parcel);
        DataPointDouble[] createdFromParcelArray = DataPointDouble.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointDouble.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointDouble.getSample(), createdFromParcel.getSample(), 0.1);
    }

    @Test
    public void dataPointDouble_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointDouble.writeToParcel(parcel, mDataPointDouble.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointDouble createdFromParcel = DataPointDouble.CREATOR.createFromParcel(parcel);
        DataPointDouble[] createdFromParcelArray = DataPointDouble.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointDouble, createdFromParcel);
    }

    @Test
    public void dataPointDoubleArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointDoubleArray.writeToParcel(parcel, mDataPointDoubleArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointDouble createdFromParcel = DataPointDouble.CREATOR.createFromParcel(parcel);
        DataPointDouble[] createdFromParcelArray = DataPointDouble.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointDoubleArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointDoubleArray.getSample(), createdFromParcel.getSample(), 0.1);
    }

    @Test
    public void dataPointDoubleArray_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointDoubleArray.writeToParcel(parcel, mDataPointDoubleArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointDouble createdFromParcel = DataPointDouble.CREATOR.createFromParcel(parcel);
        DataPointDouble[] createdFromParcelArray = DataPointDouble.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointDoubleArray, createdFromParcel);
    }
}