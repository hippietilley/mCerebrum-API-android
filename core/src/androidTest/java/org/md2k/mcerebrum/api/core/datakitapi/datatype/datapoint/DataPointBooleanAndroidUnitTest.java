package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

@SmallTest
public class DataPointBooleanAndroidUnitTest {
    private final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

    private final boolean testSample = true;
    private DataPointBoolean mDataPointBoolean;

    private final boolean[] testSampleArray = {true, false, true};
    private DataPointBoolean mDataPointBooleanArray;

    // Create the object.
    @Before
    public void createDataPointBoolean() {
        mDataPointBoolean = new DataPointBoolean(testTimestamp, testSample);
        mDataPointBooleanArray = new DataPointBoolean(testTimestamp, testSampleArray);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointBoolean.getTimestamp());
        assertEquals(testSample, mDataPointBoolean.getSample()[0]);
        assertEquals(testTimestamp, mDataPointBooleanArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointBooleanArray.getSample());
    }

    @Test
    public void dataPointBooleanCloneTest() {
        DataPointBoolean dataPointClone = mDataPointBoolean.clone();
        assertThat(dataPointClone, is(equalTo(mDataPointBoolean)));
        assertNotSame(mDataPointBoolean, dataPointClone);
    }

    @Test
    public void dataPointBooleanParcelableWriteReadTest() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointBoolean.writeToParcel(parcel, mDataPointBoolean.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointBoolean createdFromParcel = DataPointBoolean.CREATOR.createFromParcel(parcel);
        DataPointBoolean[] createdFromParcelArray = DataPointBoolean.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mDataPointBoolean)));
    }

    @Test
    public void dataPointBooleanArrayParcelableWriteReadTest() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointBooleanArray.writeToParcel(parcel, mDataPointBooleanArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointBoolean createdFromParcel = DataPointBoolean.CREATOR.createFromParcel(parcel);
        DataPointBoolean[] createdFromParcelArray = DataPointBoolean.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mDataPointBooleanArray)));
    }

    @Test
    public void equalsNullObjectTest() {
        DataPointBoolean nullBoolean = null;
        DataPointBoolean nullBoolean1 = null;

        assertThat(nullBoolean, is(equalTo(nullBoolean1)));
        assertThat(nullBoolean, is(not(equalTo(mDataPointBoolean))));
    }

    @Test
    public void dataPointBooleanHashcodeTest() {
        DataPointBoolean dataClone = mDataPointBoolean.clone();
        assertEquals(mDataPointBoolean.hashCode(), dataClone.hashCode());

        DataPointBoolean dpbWithDifferentTimestamp = new DataPointBoolean(testTimestamp + 10, testSample);
        assertNotEquals(dpbWithDifferentTimestamp.hashCode(), dataClone.hashCode());

        DataPointBoolean dpbWithDifferentSample = new DataPointBoolean(testTimestamp, false);
        assertNotEquals(dpbWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}
