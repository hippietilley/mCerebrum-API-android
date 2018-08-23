package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class DataDescriptorAndroidUnitTest {
    public static final double DELTA = 0.1;
    private final String testTitle = "Test Title";
    private final String testSummary = "Test Summary";
    private final String testDescription = "Test Description";
    private final double testMinValue = 3.14;
    private final double testMaxValue = 6.28;
    private final String[] testPossibleValuesAsString = {"3.14", "4", "5", "6", "6.28"};
    private final int[] testPossibleValuesAsInt = {3, 4, 5, 6};
    private final String testUnit = "Test Unit";
    private final String testKey = "Test Key";
    private final String testValue = "Test Value";
    private DataDescriptor testDataDescriptor;

    @Test
    public void DataDescriptorBuilderTest() {
        testDataDescriptor = new DataDescriptor.Builder().setValue(testKey, testValue).build();
        assertEquals(testValue, testDataDescriptor.getValue(testKey));

        testDataDescriptor = new DataDescriptor.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setMinValue(testMinValue)
                .setMaxValue(testMaxValue).setPossibleValues(testPossibleValuesAsString)
                .setPossibleValues(testPossibleValuesAsInt).setUnit(testUnit).build();
        assertEquals(testTitle, testDataDescriptor.getTitle());
        assertEquals(testSummary, testDataDescriptor.getSummary());
        assertEquals(testDescription, testDataDescriptor.getDescription());
        assertEquals(testMinValue, testDataDescriptor.getMinValue(), DELTA);
        assertEquals(testMaxValue, testDataDescriptor.getMaxValue(), DELTA);
        assertArrayEquals(testPossibleValuesAsString, testDataDescriptor.getPossibleValuesAsString());
        assertArrayEquals(testPossibleValuesAsInt, testDataDescriptor.getPossibleValuesAsInt());
        assertEquals(testUnit, testDataDescriptor.getUnit());
        assertEquals(null, testDataDescriptor.getValue(testKey));
    }

    @Test
    public void DataDescriptor_ParcelableWriteReadTest() {
        testDataDescriptor = new DataDescriptor.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setMinValue(testMinValue)
                .setMaxValue(testMaxValue).setPossibleValues(testPossibleValuesAsString)
                .setPossibleValues(testPossibleValuesAsInt).setUnit(testUnit).setValue(testKey, testValue)
                .build();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataDescriptor.writeToParcel(parcel, testDataDescriptor.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataDescriptor createdFromParcel = DataDescriptor.CREATOR.createFromParcel(parcel);
        DataDescriptor[] createdFromParcelArray = DataDescriptor.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testTitle, createdFromParcel.getTitle());
        assertEquals(testSummary, createdFromParcel.getSummary());
        assertEquals(testDescription, createdFromParcel.getDescription());
        assertEquals(testMinValue, createdFromParcel.getMinValue(), DELTA);
        assertEquals(testMaxValue, createdFromParcel.getMaxValue(), DELTA);
        assertArrayEquals(testPossibleValuesAsString, createdFromParcel.getPossibleValuesAsString());
        assertArrayEquals(testPossibleValuesAsInt, createdFromParcel.getPossibleValuesAsInt());
        assertEquals(testUnit, createdFromParcel.getUnit());
        assertEquals(testValue, createdFromParcel.getValue(testKey));
    }

    @Test
    public void DataDescriptor_ParcelableWriteReadComparableTest() {
        testDataDescriptor = new DataDescriptor.Builder().setTitle(testTitle)
                .setSummary(testSummary).setDescription(testDescription).setMinValue(testMinValue)
                .setMaxValue(testMaxValue).setPossibleValues(testPossibleValuesAsString)
                .setPossibleValues(testPossibleValuesAsInt).setUnit(testUnit).setValue(testKey, testValue)
                .build();

        // Write to parcel
        Parcel parcel = Parcel.obtain();
        testDataDescriptor.writeToParcel(parcel, testDataDescriptor.describeContents());

        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        DataDescriptor createdFromParcel = DataDescriptor.CREATOR.createFromParcel(parcel);
        DataDescriptor[] createdFromParcelArray = DataDescriptor.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testDataDescriptor, createdFromParcel);
    }
}
