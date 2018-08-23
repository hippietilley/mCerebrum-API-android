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
public class DataPointLongAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final long testSample = 1;
    private DataPointLong mDataPointLong;

    private final long[] testSampleArray = {-3874901, -1, 0, 1, 784309147};
    private DataPointLong mDataPointLongArray;

    // Create the object.
    @Before
    public void createDataPointLong() {
        mDataPointLong = new DataPointLong(testTimestamp, testSample);
        mDataPointLongArray = new DataPointLong(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointLong.getTimestamp());
        assertEquals(testSample, mDataPointLong.getSample()[0], 0.1);
        assertEquals(testTimestamp, mDataPointLongArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointLongArray.getSample());
    }

    @Test
    public void dataPointLongCloneTest() {
        DataPointLong dataPointClone = mDataPointLong.clone();
        assertEquals(mDataPointLong.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointLong.getSample(), dataPointClone.getSample());
        assertNotSame(mDataPointLong, dataPointClone);
    }

    @Test
    public void dataPointLongCloneComparableTest() {
        DataPointLong dataPointClone = mDataPointLong.clone();
        assertEquals(mDataPointLong, dataPointClone);
        assertNotSame(mDataPointLong, dataPointClone);
    }

    @Test
    public void dataPointLong_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointLong.writeToParcel(parcel, mDataPointLong.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointLong createdFromParcel = DataPointLong.CREATOR.createFromParcel(parcel);
        DataPointLong[] createdFromParcelArray = DataPointLong.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointLong.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointLong.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointLong_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointLong.writeToParcel(parcel, mDataPointLong.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointLong createdFromParcel = DataPointLong.CREATOR.createFromParcel(parcel);
        DataPointLong[] createdFromParcelArray = DataPointLong.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointLong, createdFromParcel);
    }

    @Test
    public void dataPointLongArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointLongArray.writeToParcel(parcel, mDataPointLongArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointLong createdFromParcel = DataPointLong.CREATOR.createFromParcel(parcel);
        DataPointLong[] createdFromParcelArray = DataPointLong.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointLongArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointLongArray.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointLongArray_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointLongArray.writeToParcel(parcel, mDataPointLongArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointLong createdFromParcel = DataPointLong.CREATOR.createFromParcel(parcel);
        DataPointLong[] createdFromParcelArray = DataPointLong.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mDataPointLongArray, createdFromParcel);
        }
    }