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
public class DataPointByteAndroidUnitTest {
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

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
        assertThat(dataPointClone, is(equalTo(mDataPointByte)));
        assertNotSame(mDataPointByte, dataPointClone);
    }

    @Test
    public void dataPointByte__ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointByte)));
    }

    @Test
    public void dataPointByteArray_ParcelableWriteReadTest() {
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
        assertThat(createdFromParcel, is(equalTo(mDataPointByteArray)));
    }

    @Test
    public void dataPointByteHashcodeTest() {
        DataPointByte dataClone = mDataPointByte.clone();
        assertEquals(mDataPointByte.hashCode(), dataClone.hashCode());

        DataPointByte dpbWithDifferentTimestamp = new DataPointByte(testTimestamp + 10, testSample);
        assertNotEquals(dpbWithDifferentTimestamp.hashCode(), dataClone.hashCode());

        DataPointByte dpbWithDifferentSample = new DataPointByte(testTimestamp, (byte)101);
        assertNotEquals(dpbWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}
