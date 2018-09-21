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
public class DataPointDoubleAndroidUnitTest {
    private static final double DELTA = TestingConstants.DELTA;
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

    private final double testSample = 6.2831853071;
    private DataPointDouble mDataPointDouble;

    private final double[] testSampleArray = {3.14159265359, 1.61803398874989484, 2.71828, 6.2831853071};
    private DataPointDouble mDataPointDoubleArray;

    @Before
    public void createDataPointDouble() {
        mDataPointDouble = new DataPointDouble(testTimestamp, testSample);
        mDataPointDoubleArray = new DataPointDouble(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointDouble.getTimestamp());
        assertEquals(testSample, mDataPointDouble.getSample()[0], DELTA);
        assertEquals(testTimestamp, mDataPointDoubleArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointDoubleArray.getSample(), DELTA);
    }

    @Test
    public void dataPointDoubleCloneTest() {
        DataPointDouble dataPointClone = mDataPointDouble.clone();
        assertThat(dataPointClone, is(equalTo(mDataPointDouble)));
        assertNotSame(mDataPointDouble, dataPointClone);
    }

    @Test
    public void dataPointDouble_ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointDouble)));
    }

    @Test
    public void dataPointDoubleArray_ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointDoubleArray)));
    }

    @Test
    public void dataPointDoubleHashcodeTest() {
        DataPointDouble dataClone = mDataPointDouble.clone();
        assertEquals(mDataPointDouble.hashCode(), dataClone.hashCode());

        DataPointDouble dpbWithDifferentTimestamp = new DataPointDouble(testTimestamp + 10, testSample);
        assertNotEquals(dpbWithDifferentTimestamp.hashCode(), dataClone.hashCode());

        DataPointDouble dpbWithDifferentSample = new DataPointDouble(testTimestamp, Math.sqrt(2));
        assertNotEquals(dpbWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}