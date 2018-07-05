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
public class DataPointEnumAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final byte testSample = 1;
    private DataPointEnum mDataPointEnum;

    private final byte[] testSampleArray = {-126, -1, 0, 1, 127};
    private DataPointEnum mDataPointEnumArray;

    // Create the object.
    @Before
    public void createDataPointEnum() {
        mDataPointEnum = new DataPointEnum(testTimestamp, testSample);
        mDataPointEnumArray = new DataPointEnum(testTimestamp, testSampleArray);
    }

    @Test
    public void dataPointEnumCloneTest() {
        DataPointEnum dataPointClone = mDataPointEnum.clone();
        assertEquals(mDataPointEnum.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointEnum.getSample(), dataPointClone.getSample());
        assertNotEquals(mDataPointEnum, dataPointClone);
    }

    @Test
    public void dataPointEnum_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointEnum.writeToParcel(parcel, mDataPointEnum.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointEnum createdFromParcel = DataPointEnum.CREATOR.createFromParcel(parcel);
        DataPointEnum[] createdFromParcelArray = DataPointEnum.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointEnum.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointEnum.getSample());
    }

    @Test
    public void dataPointEnumArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointEnumArray.writeToParcel(parcel, mDataPointEnumArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointEnum createdFromParcel = DataPointEnum.CREATOR.createFromParcel(parcel);
        DataPointEnum[] createdFromParcelArray = DataPointEnum.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointEnumArray.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointEnumArray.getSample());
    }
}