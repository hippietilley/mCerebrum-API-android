package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.CommonObjectConstructors;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class DataDescriptorAndroidUnitTest {
    private static final double DELTA = TestingConstants.DELTA;
    private final String testTitle = TestingConstants.TEST_TITLE;
    private final String testSummary = TestingConstants.TEST_SUMMARY;
    private final String testDescription = TestingConstants.TEST_DESCRIPTION;
    private final double testMinValue = TestingConstants.TEST_MIN_VALUE;
    private final double testMaxValue = TestingConstants.TEST_MAX_VALUE;
    private final String[] testPossibleValuesAsString = TestingConstants.TEST_POSSIBLE_VALUES_AS_STRING;
    private final int[] testPossibleValuesAsInt = TestingConstants.TEST_POSSIBLE_VALUES_AS_INT;
    private final String testUnit = TestingConstants.TEST_UNIT;
    private final String testKey = TestingConstants.TEST_KEY;
    private final String testValue = TestingConstants.TEST_VALUE;
    private DataDescriptor testDataDescriptor;

    @Test
    public void dataDescriptorBuilderTest() {
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
        assertNull(testDataDescriptor.getValue(testKey));
    }

    @Test
    public void dataDescriptorParcelableWriteReadTest() {
        testDataDescriptor = CommonObjectConstructors.createDataDescriptor();

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
        assertThat(createdFromParcel, is(equalTo(testDataDescriptor)));
    }

    @Test
    public void setTestDataDescriptorHashCodeTest() {
        testDataDescriptor = CommonObjectConstructors.createDataDescriptor();
        DataDescriptor testDataDescriptor2 = CommonObjectConstructors.createDataDescriptor();
        assertEquals(testDataDescriptor.hashCode(), testDataDescriptor2.hashCode());
    }
}
