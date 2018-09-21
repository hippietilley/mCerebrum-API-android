package org.md2k.mcerebrum.api.core.datakitapi.datatype.dataannotation;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

@SmallTest
public class DataAnnotationEnumAndroidUnitTest {
    private long testStartTimestamp = 12686600;
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
        assertThat(dataAnnotationEnumClone, is(equalTo(mDataAnnotationEnum)));
        assertNotSame(mDataAnnotationEnum, dataAnnotationEnumClone);
    }

    @Test
    public void dataAnnotationEnumParcelableWriteReadTest() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        mDataAnnotationEnum.writeToParcel(parcel, mDataAnnotationEnum.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataAnnotationEnum createdFromParcel = DataAnnotationEnum.CREATOR.createFromParcel(parcel);
        DataAnnotationEnum[] createdFromParcelArray = DataAnnotationEnum.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(mDataAnnotationEnum)));
    }

    @Test
    public void dataAnnotationEnumHashcodeTest() {
        DataAnnotationEnum dataClone = mDataAnnotationEnum.clone();
        assertEquals(mDataAnnotationEnum.hashCode(), dataClone.hashCode());

        DataAnnotationEnum daeWithDifferentStartTimestamp = new
                DataAnnotationEnum(testStartTimestamp + 10, testEndTimestamp, testSample);
        assertNotEquals(daeWithDifferentStartTimestamp.hashCode(), dataClone.hashCode());

        DataAnnotationEnum daeWithDifferentEndTimestamp = new
                DataAnnotationEnum(testStartTimestamp, testEndTimestamp + 10, testSample);
        assertNotEquals(daeWithDifferentEndTimestamp.hashCode(), dataClone.hashCode());

        DataAnnotationEnum daeWithDifferentSample = new
                DataAnnotationEnum(testStartTimestamp, testEndTimestamp, (byte)101);
        assertNotEquals(daeWithDifferentSample.hashCode(), dataClone.hashCode());
    }
}
