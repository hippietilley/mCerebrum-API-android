package org.md2k.mcerebrum.api.core.pluginapi;

import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MCPluginMessageAndroidUnitTest {
    private static String testTitle = "Test title";
    private static String testId = "Test Id";
    private static JsonObject testJSONObject = new JsonObject();
    private MCPluginMessage testMCPluginMessage;

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
