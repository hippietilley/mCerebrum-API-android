package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class DataKitAPIAndroidUnitTest {
    // Variables for testing null cases
    DataKitAPI testDataKitAPIwithNullmCerebrumAPI;
    MCerebrumAPI testmCerebrumAPINullInstance;
    ConnectionCallback testConnectionCallbackNull;

    // Variables for testing nonnull cases
    DataKitAPI testDataKitAPI;
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;
    ConnectionCallback testConnectionCallback = new ConnectionCallback() {
        @Override
        public void onConnected() {

        }

        @Override
        public void onError(MCerebrumException e) {

        }

        @Override
        public void onDisconnected() {

        }
    };

    @Before
    public void gettingStarted() {
        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        testmCerebrumAPI.init(testContext);


        // Create DataKitAPI instance.
        testDataKitAPI = new DataKitAPI(testmCerebrumAPI);
    }

    @Ignore
    @Test
    public void nullmCerebrumAPITest() {
        testDataKitAPIwithNullmCerebrumAPI = new DataKitAPI(testmCerebrumAPINullInstance);
        assertNull(testDataKitAPIwithNullmCerebrumAPI);
    }

    @Test
    public void nullConnectionCallbackConnectTest() {
        assertEquals(MCerebrumStatus.INVALID_PARAMETER, testDataKitAPI.connect(testConnectionCallbackNull));
    }

    @Ignore
    @Test
    public void successfulConnectionTest() {
        assertEquals(MCerebrumStatus.SUCCESS, testDataKitAPI.connect(testConnectionCallback));
    }
}
