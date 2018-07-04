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
public class DataPointObjectAndroidUnitTest {
    private final long testTimestamp = 1268660460;

    private final String testSample = "Hello world";
    private DataPointObject mDataPointObject;

    private final String[] testSampleArray = {"Test 1", "Test 2"};
    private DataPointObject mDataPointObjectArray;

    // Create the object.
    @Before
    public void createDataPointObject() {
        mDataPointObject = new DataPointObject(testTimestamp, testSample);
        mDataPointObjectArray = new DataPointObject(testTimestamp, testSampleArray);
    }

    @Test
    public void dataPointObjectCloneTest() {
        DataPointObject dataPointClone = mDataPointObject.clone();
        assertEquals(mDataPointObject.getTimestamp(), dataPointClone.getTimestamp());
        assertArrayEquals(mDataPointObject.getSample(), dataPointClone.getSample());
        assertNotEquals(mDataPointObject, dataPointClone);
    }

    @Test
    public void dataPointObject_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObject.writeToParcel(parcel, mDataPointObject.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointObject.getTimestamp());
        for (int i = 0; i < createdFromParcel.getSample().length; i++)
            assertEquals(createdFromParcel.getSample()[i], mDataPointObject.getSample()[i]);
    }

    @Test
    public void dataPointObjectArray_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataPointObjectArray.writeToParcel(parcel, mDataPointObjectArray.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
        DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mDataPointObjectArray.getTimestamp());
        assertArrayEquals(createdFromParcel.getSample(), mDataPointObjectArray.getSample());
    }
}