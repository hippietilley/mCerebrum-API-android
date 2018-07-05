package org.md2k.mcerebrum.api.datakitapi.datatype.datapoint;

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
    public void dataPointByteCloneTest() {
        DataPointByte dataPointClone = mDataPointByte.clone();
        assertEquals(mDataPointByte.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointByte.getSample(), dataPointClone.getSample());
        assertNotEquals(mDataPointByte, dataPointClone);
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointByte.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointByte.getSample());
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointByteArray.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointByteArray.getSample());
    }
}
