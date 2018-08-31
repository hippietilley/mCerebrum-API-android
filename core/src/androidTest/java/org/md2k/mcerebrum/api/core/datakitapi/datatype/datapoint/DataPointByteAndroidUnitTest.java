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
public class DataPointByteAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final byte testSample = 1;
    private DataPointByte mDataPointByte;

    private final byte[] testSampleArray = {-126, -1, 0, 1, 127};
    private DataPointByte mDataPointByteArray;

    // Create the object.
    @Before
    public void createDataPointByte() {
        mDataPointByte = new DataPointByte(testTimestamp, testSample);
        mDataPointByteArray = new DataPointByte(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointByte.getTimestamp());
        assertEquals(testSample, mDataPointByte.getSample()[0]);
        assertEquals(testTimestamp, mDataPointByteArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointByteArray.getSample());
    }

    @Test
    public void dataPointByteCloneTest() {
        DataPointByte dataPointClone = mDataPointByte.clone();
        assertEquals(mDataPointByte.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointByte.getSample(), dataPointClone.getSample());
        assertNotSame(mDataPointByte, dataPointClone);
    }

    @Test
    public void dataPointByteCloneComparableTest() {
        DataPointByte dataPointClone = mDataPointByte.clone();
        assertEquals(mDataPointByte, dataPointClone);
        assertNotSame(mDataPointByte, dataPointClone);
    }

    @Test
    public void dataPointByte_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointByte.writeToParcel(parcel, mDataPointByte.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointByte createdFromParcel = DataPointByte.CREATOR.createFromParcel(parcel);
        DataPointByte[] createdFromParcelArray = DataPointByte.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointByte.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointByte.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointByte__ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointByte.writeToParcel(parcel, mDataPointByte.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointByte createdFromParcel = DataPointByte.CREATOR.createFromParcel(parcel);
        DataPointByte[] createdFromParcelArray = DataPointByte.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointByte, createdFromParcel);
    }

    @Test
    public void dataPointByteArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointByteArray.writeToParcel(parcel, mDataPointByteArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointByte createdFromParcel = DataPointByte.CREATOR.createFromParcel(parcel);
        DataPointByte[] createdFromParcelArray = DataPointByte.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointByteArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointByteArray.getSample(), createdFromParcel.getSample());
    }


    @Test
    public void dataPointByteArray_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointByteArray.writeToParcel(parcel, mDataPointByteArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointByte createdFromParcel = DataPointByte.CREATOR.createFromParcel(parcel);
        DataPointByte[] createdFromParcelArray = DataPointByte.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointByteArray, createdFromParcel);
    }
}
