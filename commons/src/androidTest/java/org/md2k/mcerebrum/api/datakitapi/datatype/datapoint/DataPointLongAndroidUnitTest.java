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
    public void dataPointLongCloneTest() {
        DataPointLong dataPointClone = mDataPointLong.clone();
        assertEquals(mDataPointLong.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointLong.getSample(), dataPointClone.getSample());
        assertNotEquals(mDataPointLong, dataPointClone);
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointLong.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointLong.getSample());
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointLongArray.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointLongArray.getSample());
    }
}