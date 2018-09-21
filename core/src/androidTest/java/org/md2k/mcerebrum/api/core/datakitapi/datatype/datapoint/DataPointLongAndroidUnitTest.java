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
public class DataPointLongAndroidUnitTest {
    private static final double DELTA = TestingConstants.DELTA;
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

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
        assertEquals(testSample, mDataPointLong.getSample()[0], DELTA);
        assertEquals(testTimestamp, mDataPointLongArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointLongArray.getSample());
    }

    @Test
    public void dataPointLongCloneTest() {
        DataPointLong dataPointClone = mDataPointLong.clone();
        assertThat(dataPointClone, is(equalTo(mDataPointLong)));
        assertNotSame(mDataPointLong, dataPointClone);
    }

    @Test
    public void dataPointLong_ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointLong)));
    }

    @Test
    public void dataPointLongArray_ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointLongArray)));
    }


    @Test
    public void dataPointLongHashcodeTest() {
        DataPointLong dataClone = mDataPointLong.clone();
        assertEquals(mDataPointLong.hashCode(), dataClone.hashCode());

        DataPointLong dpbWithDifferentTimestamp = new DataPointLong(testTimestamp + 10, testSample);
        assertNotEquals(dpbWithDifferentTimestamp.hashCode(), dataClone.hashCode());

        DataPointLong dpbWithDifferentSample = new DataPointLong(testTimestamp, (long)475894890);
        assertNotEquals(dpbWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}