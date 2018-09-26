package org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class DataPointObjectAndroidUnitTest {
    // Objects created with a single data point
    private DataPointObject testDPOByte = DataPointObjectMocker.createDPOByte();
    private DataPointObject testDPOShort = DataPointObjectMocker.createDPOShort();
    private DataPointObject testDPOInt = DataPointObjectMocker.createDPOInt();
    private DataPointObject testDPOLong = DataPointObjectMocker.createDPOLong();
    private DataPointObject testDPOChar = DataPointObjectMocker.createDPOChar();
    private DataPointObject testDPOFloat = DataPointObjectMocker.createDPOFloat();
    private DataPointObject testDPODouble = DataPointObjectMocker.createDPODouble();
    private DataPointObject testDPOBoolean = DataPointObjectMocker.createDPOBoolean();

    // Objects created with an array of data points.
    private DataPointObject testDPOByteArray = DataPointObjectMocker.createDPOByteArray();
    private DataPointObject testDPOShortArray = DataPointObjectMocker.createDPOShortArray();
    private DataPointObject testDPOIntArray = DataPointObjectMocker.createDPOIntArray();
    private DataPointObject testDPOLongArray = DataPointObjectMocker.createDPOLongArray();
    private DataPointObject testDPOCharArray = DataPointObjectMocker.createDPOCharArray();
    private DataPointObject testDPOFloatArray = DataPointObjectMocker.createDPOFloatArray();
    private DataPointObject testDPODoubleArray = DataPointObjectMocker.createDPODoubleArray();
    private DataPointObject testDPOBooleanArray = DataPointObjectMocker.createDPOBooleanArray();
    private DataPointObject testDPOAllTypeArray = DataPointObjectMocker.createDPOAllTypeArray();
    private DataPointObject testDPOStringArray = DataPointObjectMocker.createDPOStringArray();

    private DataPointObject[] allTestDPO = {testDPOByte, testDPOShort, testDPOInt, testDPOLong,
                                    testDPOChar, testDPOFloat, testDPODouble, testDPOBoolean};
    private DataPointObject[] allTestDPOArrays = {testDPOByteArray, testDPOShortArray, testDPOIntArray,
                                          testDPOLongArray, testDPOCharArray, testDPOFloatArray,
                                          testDPODoubleArray, testDPOBooleanArray, testDPOAllTypeArray,
                                          testDPOStringArray};
    @Test
    public void fieldAccuracyTest() {
        final long testTimestamp = TestingConstants.TEST_TIMESTAMP;

        for (DataPointObject dataPointObject : allTestDPO) {
            assertEquals(testTimestamp, dataPointObject.getTimestamp());
        }

        for (DataPointObject dataPointObject : allTestDPOArrays) {
            assertEquals(testTimestamp, dataPointObject.getTimestamp());
        }
    }

    @Test
    public void dataPointObjectCloneTest() {
        for (DataPointObject dataPointObject : allTestDPO) {
            DataPointObject dataPointObjectClone = dataPointObject.clone();
            assertThat(dataPointObjectClone, is(equalTo(dataPointObject)));
        }
    }

    @Test
    public void dataPointObjectArrayCloneTest() {
        for (DataPointObject dataPointObject : allTestDPOArrays) {
            DataPointObject dataPointObjectClone = dataPointObject.clone();
            assertThat(dataPointObjectClone, is(equalTo(dataPointObject)));
        }
    }

    @Test
    public void dataPointObjectParcelableWriteReadTest() {
        for (DataPointObject dataPointObject : allTestDPO) {
            // Write data to parcel.
            Parcel parcel = Parcel.obtain();
            dataPointObject.writeToParcel(parcel, dataPointObject.describeContents());


            // After writing, reset the parcel for reading
            parcel.setDataPosition(0);

            // Read the data.
            DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
            DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

            // Verify results.
            assertNotEquals(0, createdFromParcelArray.length);
            assertThat(createdFromParcel, is(equalTo(dataPointObject)));
        }
    }

    @Test
    public void dataPointObjectArrayParcelableWriteReadTest() {
        for (DataPointObject dataPointObject : allTestDPOArrays) {
            // Write data to parcel.
            Parcel parcel = Parcel.obtain();
            dataPointObject.writeToParcel(parcel, dataPointObject.describeContents());

            // After writing, reset the parcel for reading
            parcel.setDataPosition(0);

            // Read the data.
            DataPointObject createdFromParcel = DataPointObject.CREATOR.createFromParcel(parcel);
            DataPointObject[] createdFromParcelArray = DataPointObject.CREATOR.newArray(1);

            // Verify results.
            assertNotEquals(0, createdFromParcelArray.length);
            assertThat(createdFromParcel, is(equalTo(dataPointObject)));
        }
    }


    @Test
    public void dataPointObjectHashcodeTest() {
        for (DataPointObject dataPointObject : allTestDPOArrays) {
            DataPointObject dataPointObjectClone = dataPointObject.clone();
            assertEquals(dataPointObject.hashCode(), dataPointObjectClone.hashCode());
        }
    }
}