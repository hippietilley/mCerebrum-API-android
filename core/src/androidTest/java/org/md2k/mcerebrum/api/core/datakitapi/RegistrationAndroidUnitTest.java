package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import org.md2k.mcerebrum.api.core.MCerebrumAPI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RegistrationAndroidUnitTest {
    private final int[] statusIntArray = TestingConstants.STATUS_INT_ARRAY;

    private DataSourceCreator testDataSourceCreator;
    private DataSourceReadWrite testDataSourceReadWrite;
    private Registration testReg;
    private Context testContext;

    @Before
    public void objectCreation() {
        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        MCerebrumAPI.init(testContext);

        // Create test data sources
        testDataSourceCreator = CommonObjectConstructors.createDataSourceCreator();
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();
    }

    @Test
    public void registrationCreatorTest() {
        for (int statusInt : statusIntArray) {
            testReg = new Registration(testDataSourceCreator, statusInt);
            assertEquals(statusInt, testReg.getStatus());
        }
        assertEquals(testDataSourceCreator, testReg.getDataSource());
    }

    @Test
    public void registrationReadWriteTest() {
        for (int statusInt : statusIntArray) {
             testReg = new Registration(testDataSourceReadWrite, statusInt);
             assertEquals(statusInt, testReg.getStatus());
        }
        assertEquals(testDataSourceReadWrite, testReg.getDataSource());
    }
}
