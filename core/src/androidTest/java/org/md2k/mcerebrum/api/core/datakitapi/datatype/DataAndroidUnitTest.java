package org.md2k.mcerebrum.api.core.datakitapi.datatype;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

@SmallTest
public class DataAndroidUnitTest {
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;
    private Data mData;

    // Create the object.
    @Before
    public void createData() {
        mData = new Data(testTimestamp);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mData.getTimestamp());
    }

    @Test
    public void dataCloneTest() {
        Data dataClone = mData.clone();
        assertEquals(mData.getTimestamp(), dataClone.getTimestamp());
        assertNotSame(mData, dataClone);
    }

    @Test
    public void dataCloneComparableTest() {
        Data dataClone = mData.clone();
        assertThat(dataClone, is(equalTo(mData)));
        assertNotSame(mData, dataClone);
    }

    @Test
    public void dataPoint_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mData.writeToParcel(parcel, mData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        Data createdFromParcel = Data.CREATOR.createFromParcel(parcel);
        Data[] createdFromParcelArray = Data.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(mData.getTimestamp(), createdFromParcel.getTimestamp());
    }

    @Test
    public void dataPoint_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mData.writeToParcel(parcel, mData.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        Data createdFromParcel = Data.CREATOR.createFromParcel(parcel);
        Data[] createdFromParcelArray = Data.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mData)));
    }

    @Test
    public void dataHashcodeTest() {
        Data dataClone = mData.clone();
        assertEquals(mData.hashCode(), dataClone.hashCode());
    }
}
