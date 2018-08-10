package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@SmallTest
public class DataPointIntAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final int testSample = 1;
    private DataPointInt mDataPointInt;

    private final int[] testSampleArray = {-3874901, -1, 0, 1, 784309147};
    private DataPointInt mDataPointIntArray;

    // Create the object.
    @Before
    public void createDataPointInt() {
        mDataPointInt = new DataPointInt(testTimestamp, testSample);
        mDataPointIntArray = new DataPointInt(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointInt.getTimestamp());
        assertEquals(testSample, mDataPointInt.getSample()[0]);
        assertEquals(testTimestamp, mDataPointIntArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointIntArray.getSample());
    }

    @Test
    public void dataPointIntCloneTest() {
        DataPointInt dataPointClone = mDataPointInt.clone();
        assertEquals(mDataPointInt.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointInt.getSample(), dataPointClone.getSample());
        assertNotEquals(mDataPointInt, dataPointClone);
    }

    @Test
    public void dataPointInt_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointInt.writeToParcel(parcel, mDataPointInt.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointInt createdFromParcel = DataPointInt.CREATOR.createFromParcel(parcel);
        DataPointInt[] createdFromParcelArray = DataPointInt.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataPointInt.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointInt.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void dataPointIntArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointIntArray.writeToParcel(parcel, mDataPointIntArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointInt createdFromParcel = DataPointInt.CREATOR.createFromParcel(parcel);
        DataPointInt[] createdFromParcelArray = DataPointInt.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataPointIntArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointIntArray.getSample(), createdFromParcel.getSample());
    }
}