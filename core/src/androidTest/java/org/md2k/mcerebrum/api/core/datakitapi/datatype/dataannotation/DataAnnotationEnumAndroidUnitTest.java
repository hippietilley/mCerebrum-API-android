package org.md2k.mcerebrum.api.core.datakitapi.datatype.dataannotation;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

@SmallTest
public class DataAnnotationEnumAndroidUnitTest {
    private long testStartTimestamp = 1268660060;
    private long testEndTimestamp = 1268660460;
    private byte testSample = 127;
    private DataAnnotationEnum mDataAnnotationEnum;

    @Before
    public void createDataAnnotationEnum() {
        mDataAnnotationEnum = new DataAnnotationEnum(testStartTimestamp, testEndTimestamp, testSample);
    }

    @Test
    public void fieldAccuracyTest() {
        assertEquals(testStartTimestamp, mDataAnnotationEnum.getTimestamp());
        assertEquals(testEndTimestamp, mDataAnnotationEnum.getEndTimestamp());
        assertEquals(testSample, mDataAnnotationEnum.getSample()[0]);
    }

    @Test
    public void cloneTest() {
        DataAnnotationEnum dataAnnotationEnumClone = mDataAnnotationEnum.clone();
        assertEquals(mDataAnnotationEnum.getTimestamp(), dataAnnotationEnumClone.getTimestamp());
        assertArrayEquals(mDataAnnotationEnum.getSample(), dataAnnotationEnumClone.getSample());
        assertNotSame(mDataAnnotationEnum, dataAnnotationEnumClone);
    }

    @Test
    public void cloneComparableTest() {
        DataAnnotationEnum dataAnnotationEnumClone = mDataAnnotationEnum.clone();
        assertEquals(mDataAnnotationEnum, dataAnnotationEnumClone);
        assertNotSame(mDataAnnotationEnum, dataAnnotationEnumClone);
    }

    @Test
    public void DataAnnotationEnum_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataAnnotationEnum.writeToParcel(parcel, mDataAnnotationEnum.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataAnnotationEnum createdFromParcel = DataAnnotationEnum.CREATOR.createFromParcel(parcel);
        DataAnnotationEnum[] createdFromParcelArray = DataAnnotationEnum.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataAnnotationEnum.getTimestamp(), createdFromParcel.getTimestamp());
        assertArrayEquals(mDataAnnotationEnum.getSample(), createdFromParcel.getSample());
    }

    @Test
    public void DataAnnotationEnum_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataAnnotationEnum.writeToParcel(parcel, mDataAnnotationEnum.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataAnnotationEnum createdFromParcel = DataAnnotationEnum.CREATOR.createFromParcel(parcel);
        DataAnnotationEnum[] createdFromParcelArray = DataAnnotationEnum.CREATOR.newArray(1);

        // Verify results.
        assertThat(createdFromParcelArray.length, is(not(0)));
        assertEquals(mDataAnnotationEnum, createdFromParcel);
    }
}
