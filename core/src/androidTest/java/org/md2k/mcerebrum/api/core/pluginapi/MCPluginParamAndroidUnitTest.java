package org.md2k.mcerebrum.api.core.pluginapi;

import android.os.Parcel;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.pluginapi.MCPluginParam;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MCPluginParamAndroidUnitTest {
    public static String testTitle = "Test title";
    public static String testId = "Test Id";
    public static JSONObject testJSONObject = new JSONObject();
    public MCPluginParam testMCPluginParam;

    @Before
    public void createPluginParam() {
        testMCPluginParam = new MCPluginParam(testTitle, testId, testJSONObject);
    }

    @Test
    public void objectCreationTest() {
        assertEquals(testTitle, testMCPluginParam.getTitle());
        assertEquals(testId, testMCPluginParam.getId());
        assertEquals(testJSONObject, testMCPluginParam.getParams());
    }

    @Test
    public void MCPluginParam_ParcelableWriteRead() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testMCPluginParam.writeToParcel(parcel, testMCPluginParam.describeContents());


        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        MCPluginParam createdFromParcel = MCPluginParam.CREATOR.createFromParcel(parcel);
        MCPluginParam[] createdFromParcelArray = MCPluginParam.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testMCPluginParam.getTitle(), createdFromParcel.getTitle());
        assertEquals(testMCPluginParam.getId(), createdFromParcel.getId());
        assertEquals(testMCPluginParam.getParams().toString(), createdFromParcel.getParams().toString());
    }

    @Test
    public void MCPluginParam_ParcelableWriteReadComparable() {
        // Write data to parcel.
        Parcel parcel = Parcel.obtain();
        testMCPluginParam.writeToParcel(parcel, testMCPluginParam.describeContents());


        // After writing, reset the parcel for reading
        parcel.setDataPosition(0);

        // Read the data.
        MCPluginParam createdFromParcel = MCPluginParam.CREATOR.createFromParcel(parcel);
        MCPluginParam[] createdFromParcelArray = MCPluginParam.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testMCPluginParam, createdFromParcel);
    }
}

