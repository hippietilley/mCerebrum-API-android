package org.md2k.mcerebrum.api.core.datakitapi.datatype;

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
public class DataAndroidUnitTest {
    private final long testTimestamp = 1268660460;
    private Data mData;

    // Create the object.
    @Before
    public void createData() {
        mData = new Data(testTimestamp);
    }

    @Test
    public void dataCloneTest() {
        Data dataClone = mData.clone();
        assertEquals(mData.getTimestamp(), dataClone.getTimestamp());
        assertNotEquals(mData, dataClone);
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
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(createdFromParcel.getTimestamp(), mData.getTimestamp());
    }
}
