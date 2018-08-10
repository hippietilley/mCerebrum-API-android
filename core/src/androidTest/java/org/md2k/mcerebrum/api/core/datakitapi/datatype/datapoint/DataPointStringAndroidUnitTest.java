package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

@SmallTest
public class DataPointStringAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final String testSample = "Hello world";
    private DataPointString mDataPointString;

    private final String testEmptyString = "";
    private DataPointString mDataPointEmptyString;

    private final String[] testSampleArray = {"Test 1", "Test 2", ""};
    private DataPointString mDataPointStringArray;

    // Create the object.
    @Before
    public void createDataPointString() {
        mDataPointString = new DataPointString(testTimestamp, testSample);
        mDataPointStringArray = new DataPointString(testTimestamp, testSampleArray);

        mDataPointEmptyString = new DataPointString(testTimestamp, testEmptyString);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testTimestamp, mDataPointString.getTimestamp());
        assertEquals(testSample, mDataPointString.getSample()[0]);

        assertEquals(testTimestamp, mDataPointEmptyString.getTimestamp());
        assertThat(mDataPointEmptyString.getSample()[0], isEmptyString());

        assertEquals(testTimestamp, mDataPointStringArray.getTimestamp());
        assertArrayEquals(testSampleArray, mDataPointStringArray.getSample());
    }

    @Test
    public void dataPointStringCloneTest() {
        DataPointString dataPointClone = mDataPointString.clone();
        assertEquals(mDataPointString.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointString.getSample(), dataPointClone.getSample());
        assertNotSame(mDataPointString, dataPointClone);
    }

    @Test
    public void dataPointString_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointString.writeToParcel(parcel, mDataPointString.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointString createdFromParcel = DataPointString.CREATOR.createFromParcel(parcel);
        DataPointString[] createdFromParcelArray = DataPointString.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataPointString.getTimestamp(), createdFromParcel.getTimestamp());
        for (int i = 0; i < createdFromParcel.getSample().length; i++)
            assertEquals(mDataPointString.getSample()[i], createdFromParcel.getSample()[i]);
    }

    @Test
    public void dataPointStringArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointStringArray.writeToParcel(parcel, mDataPointStringArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointString createdFromParcel = DataPointString.CREATOR.createFromParcel(parcel);
        DataPointString[] createdFromParcelArray = DataPointString.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataPointStringArray.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataPointStringArray.getSample(), createdFromParcel.getSample());
    }
}