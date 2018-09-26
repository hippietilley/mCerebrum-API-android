package org.md2k.mcerebrum.api.core.datakitapi.datatype;

import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.dataannotation.DataAnnotationEnum;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointBoolean;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointByte;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointEnum;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointInt;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointLong;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointString;

public class DataPointMocker {
    /*
        This class creates DataPoint objects of various types for the DataSet tests.
     */

    private static long testStartTimestamp = 1268660060;
    private static long testEndTimestamp = 1268660460;
    private static final long TEST_TIMESTAMP = TestingConstants.TEST_TIMESTAMP;
    private static final byte TEST_BYTE = 23;
    private static final int TEST_INT = 235890824;
    private static final long TEST_LONG = 890589984;
    private static final double TEST_DOUBLE = 758940257;
    private static final boolean  TEST_BOOLEAN = true;

    private static final byte[] TEST_BYTE_ARRAY = {-128, 0, 127};
    private static final int[] TEST_INT_ARRAY = {-2^31, 0, (2^31)-1};
    private static final long[] TEST_LONG_ARRAY = {-2^63, 0 , (2^63)-1};
    private static final double[] TEST_DOUBLE_ARRAY = {-1.7976931348623157 * Math.exp(308),
            -4.9 * Math.exp(-324), 4.9 * Math.exp(-324),
            1.7976931348623157 * Math.exp(308)};
    private static final boolean[] TEST_BOOLEAN_ARRAY = {true, false};
    private static final String[] TEST_STRING_ARRAY = {"I may remark that the curious transformations many formulae can undergo,",
            "the unsuspected and to a beginner apparently impossible identity of forms ",
            "exceedingly dissimilar at first sight, is I think one of the chief ",
            "difficulties in the early part of mathematical studies.",
            "~ Ada Lovelace"};

    public static DataPointBoolean createDataPointBoolean() {
        return new DataPointBoolean(TEST_TIMESTAMP, TEST_BOOLEAN);
    }

    public static DataPointByte createDataPointByte() {
        return new DataPointByte(TEST_TIMESTAMP, TEST_BYTE);
    }

    public static DataPointDouble createDataPointDouble() {
        return new DataPointDouble(TEST_TIMESTAMP, TEST_DOUBLE);
    }

    // This uses TEST_BYTE because the constructor for DataPointEnum takes a byte or byte array.
    public static DataPointEnum createDataPointEnum() {
        return new DataPointEnum(TEST_TIMESTAMP, TEST_BYTE);
    }

    public static DataPointInt createDataPointInt() {
        return new DataPointInt(TEST_TIMESTAMP, TEST_INT);
    }

    public static DataPointLong createDataPointLong() {
        return new DataPointLong(TEST_TIMESTAMP, TEST_LONG);
    }

    public static DataPointString createDataPointString() {
        return new DataPointString(TEST_TIMESTAMP, TEST_STRING_ARRAY[0]);
    }

    // This uses TEST_BYTE because the constructor for DataAnnoEnum takes a byte or byte array.
    public static DataAnnotationEnum createDataAnnoEnum() {
        return new DataAnnotationEnum(testStartTimestamp, testEndTimestamp, TEST_BYTE);
    }

    public static DataPointBoolean createDataPointBooleanArray() {
        return new DataPointBoolean(TEST_TIMESTAMP, TEST_BOOLEAN_ARRAY);
    }

    public static DataPointByte createDataPointByteArray() {
        return new DataPointByte(TEST_TIMESTAMP, TEST_BYTE_ARRAY);
    }

    public static DataPointDouble createDataPointDoubleArray() {
        return new DataPointDouble(TEST_TIMESTAMP, TEST_DOUBLE_ARRAY);
    }

    // This uses TEST_BYTE because the constructor for DataPointEnum takes a byte or byte array.
    public static DataPointEnum createDataPointEnumArray() {
        return new DataPointEnum(TEST_TIMESTAMP, TEST_BYTE_ARRAY);
    }

    public static DataPointInt createDataPointIntArray() {
        return new DataPointInt(TEST_TIMESTAMP, TEST_INT_ARRAY);
    }

    public static DataPointLong createDataPointLongArray() {
        return new DataPointLong(TEST_TIMESTAMP, TEST_LONG_ARRAY);
    }

    public static DataPointString createDataPointStringArray() {
        return new DataPointString(TEST_TIMESTAMP, TEST_STRING_ARRAY);
    }
}
