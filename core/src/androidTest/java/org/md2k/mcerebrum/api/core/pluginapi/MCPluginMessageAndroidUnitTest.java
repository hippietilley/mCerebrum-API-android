package org.md2k.mcerebrum.api.core.pluginapi;

import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MCPluginMessageAndroidUnitTest {
    public static String testTitle = "Test title";
    public static String testId = "Test Id";
    public static JsonObject testJSONObject = new JsonObject();
    public MCPluginMessage testMCPluginMessage;

    @Before
    public void createPluginParam() {
        testMCPluginMessage = new MCPluginMessage(testTitle, testId, testJSONObject);
    }

    @Test
    public void objectCreationTest() {
        assertEquals(testTitle, testMCPluginMessage.getTitle());
        assertEquals(testId, testMCPluginMessage.getId());
        assertEquals(testJSONObject, testMCPluginMessage.getParams());
    }
}
