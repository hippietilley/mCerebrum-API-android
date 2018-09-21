package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

@SmallTest
public class DataPointIntAndroidUnitTest {
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

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
        assertThat(dataPointClone, is(equalTo(mDataPointInt)));
        assertNotSame(mDataPointInt, dataPointClone);
    }

    @Test
    public void dataPointInt_ParcelableWriteReadTest() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointInt.writeToParcel(parcel, mDataPointInt.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointInt createdFromParcel = DataPointInt.CREATOR.createFromParcel(parcel);
        DataPointInt[] createdFromParcelArray = DataPointInt.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mDataPointInt)));
    }

    @Test
    public void dataPointIntArray_ParcelableWriteReadTest() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointIntArray.writeToParcel(parcel, mDataPointIntArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointInt createdFromParcel = DataPointInt.CREATOR.createFromParcel(parcel);
        DataPointInt[] createdFromParcelArray = DataPointInt.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mDataPointIntArray)));
    }

    @Test
    public void dataPointIntHashcodeTest() {
        DataPointInt dataClone = mDataPointInt.clone();
        assertEquals(mDataPointInt.hashCode(), dataClone.hashCode());

        DataPointInt dpbWithDifferentTimestamp = new DataPointInt(testTimestamp + 10, testSample);
        assertNotEquals(dpbWithDifferentTimestamp.hashCode(), dataClone.hashCode());

        DataPointInt dpbWithDifferentSample = new DataPointInt(testTimestamp, 42);
        assertNotEquals(dpbWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}