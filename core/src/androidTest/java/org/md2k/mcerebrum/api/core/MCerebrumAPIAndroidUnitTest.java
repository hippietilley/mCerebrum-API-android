package org.md2k.mcerebrum.api.core;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import static org.junit.Assert.assertEquals;

public class MCerebrumAPIAndroidUnitTest {

    MCerebrumAPI testmCerebrumAPI;
    Context testContext;
    ConnectionCallback testConnectionCallback;

    @Before
    public void gettingStarted() {

    }

    @Ignore
    @Test
    public void dataKitAPIInitializationTest() {
        // testmCerebrumAPI.init() should create an instance of MCerebrumAPI that calls for an instance of DataKitAPI to be created.
    }

    @Ignore
    @Test
    public void connectionTest() {
        assertEquals(MCerebrumStatus.INVALID_PARAMETER, testmCerebrumAPI.connect(testConnectionCallback));

        testConnectionCallback = new ConnectionCallback() {
            @Override
            public void onConnected() {}

            @Override
            public void onError(MCerebrumException e) {}

            @Override
            public void onDisconnected() {}
        };

        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testmCerebrumAPI.connect(testConnectionCallback));

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);

        assertEquals(MCerebrumStatus.SUCCESS, testmCerebrumAPI.connect(testConnectionCallback));
    }
}
