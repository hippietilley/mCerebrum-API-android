package org.md2k.mcerebrum.api.core.pluginapi;

import android.os.Parcel;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MCPluginParamAndroidUnitTest {
    private static String testTitle = TestingConstants.TEST_TITLE;
    private static String testId = TestingConstants.TEST_DEVICE_ID;
    private static JSONObject testJSONObject = new JSONObject();
    private MCPluginParam testMCPluginParam, testMCPluginParam2;

    @Before
    public void createPluginParam() {
        testMCPluginParam = new MCPluginParam(testTitle, testId, testJSONObject);
        testMCPluginParam2 = new MCPluginParam(testTitle, testId, testJSONObject);
    }

    @Test
    public void objectCreationTest() {
        assertEquals(testTitle, testMCPluginParam.getTitle());
        assertEquals(testId, testMCPluginParam.getId());
        assertEquals(testJSONObject, testMCPluginParam.getParams());
    }

    @Test
    public void mCPluginParam_ParcelableWriteReadComparable() {
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
        assertThat(createdFromParcel, is(equalTo(testMCPluginParam)));
    }

    @Test
    public void mCPluginParamHashCodeTest() {
        assertEquals(testMCPluginParam.hashCode(), testMCPluginParam2.hashCode());
    }
}

