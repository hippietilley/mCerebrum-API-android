package org.md2k.mcerebrum.api.core.datakitapi;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.md2k.mcerebrum.api.core.MCerebrumAPI;
import org.md2k.mcerebrum.api.core.datakitapi.callback.ConnectionCallback;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.exception.MCerebrumException;
import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MyServiceConnectionAndroidUnitTest {
    ConnectionCallback testConnectionCallback;
    MyServiceConnection testServiceConnection;
    DataSourceReadWrite testDataSourceReadWrite;
    MCerebrumAPI testmCerebrumAPI;
    Context testContext;

    final String testDataSourceType = TestingConstants.DATA_SOURCE_TYPE_ARRAY[0];
    final String testDataSourceId = TestingConstants.DATASOURCE_ID_ARRAY[0];
    DataSourceMetaData testDataSourceMetaData;
    final String testPlatformType = TestingConstants.PLATFORM_TYPE_ARRAY[0];
    final String testPlatformId = TestingConstants.PLATFORM_ID_ARRAY[0];
    final String testPlatformAppType = TestingConstants.PLATFORM_APP_TYPE_ARRAY[0];
    final String testPlatformAppId = TestingConstants.PLATFORM_APP_ID_ARRAY[0];
    final String testApplicationType = TestingConstants.APPLICATION_TYPE_ARRAY[0];
    final String testDataType = TestingConstants.DATA_TYPE_ARRAY[0].toString();
    PlatformMetaData testPlatformMetaData;
    PlatformAppMetaData testPlatformAppMetaData;
    ApplicationMetaData testAppMetaData;
    DataDescriptor testDataDescriptor;

    ArrayList<DataDescriptor> testDataDescriptors = new ArrayList<>();

    public void createDataSourceReadWrite() {
        testDataSourceReadWrite = new DataSourceReadWrite();
    }

    public void createDataSourceReadWriteWithPopulatedFields() {
        testDataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();
    }

    public void resetTestServiceConnection() {
        testServiceConnection = new MyServiceConnection(testConnectionCallback);
    }

    @Before
    public void objectCreation() {
        testPlatformMetaData = CommonObjectConstructors.createPlatformMetaData();
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();
        testAppMetaData = CommonObjectConstructors.createApplicationMetaData();
        testDataDescriptor = CommonObjectConstructors.createDataDescriptor();
        testDataSourceMetaData = CommonObjectConstructors.createDataSourceMetaData();
        testDataDescriptors.add(testDataDescriptor);
        testConnectionCallback = new ConnectionCallback() {
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
        testServiceConnection = new MyServiceConnection(testConnectionCallback);
    }

    @Ignore // Waiting for datakitapi to be finished.
    @Test
    public void registerTest() {
        assertEquals(MCerebrumStatus.MCEREBRUM_API_NOT_INITIALIZED, testServiceConnection.register(testDataSourceReadWrite));

        // Initialize mCerebrumAPI
        testContext = InstrumentationRegistry.getContext();
        MCerebrumAPI.init(testContext);

        assertEquals(MCerebrumStatus.INVALID_DATA_SOURCE, testServiceConnection.register(testDataSourceReadWrite));

        createDataSourceReadWrite();
        assertEquals(MCerebrumStatus.MISSING_DATA_SOURCE_TYPE, testServiceConnection.register(testDataSourceReadWrite));

        testDataSourceReadWrite.setDataSourceType(testDataSourceType);
        assertEquals(MCerebrumStatus.MISSING_DATA_TYPE, testServiceConnection.register(testDataSourceReadWrite));

        testDataSourceReadWrite.setDataSourceId(testDataSourceId);
        testDataSourceReadWrite.setPlatformType(testPlatformType);
        testDataSourceReadWrite.setPlatformId(testPlatformId);
        testDataSourceReadWrite.setPlatformAppType(testPlatformAppType);
        testDataSourceReadWrite.setPlatformAppId(testPlatformAppId);
        testDataSourceReadWrite.setApplicationType(testApplicationType);
        testDataSourceReadWrite.setPlatformMetadata(testPlatformMetaData);
        testDataSourceReadWrite.setPlatformAppMetadata(testPlatformAppMetaData);
        testDataSourceReadWrite.setApplicationMetadata(testAppMetaData);
        testDataSourceReadWrite.setDataDescriptors(testDataDescriptors);
        testDataSourceReadWrite.setDataSourceMetadata(testDataSourceMetaData);
        testDataSourceReadWrite.setDataType(testDataType);
        assertEquals(MCerebrumStatus.CONNECTION_ERROR, testServiceConnection.register(testDataSourceReadWrite));

        // TODO: figure out why setting the DataType makes the object null
        /*
            .setDataType() doesn't make testServiceConnection null. mService in the MyServiceConnection
            is null until after a connection is made, but there isn't a method to connect.
         */
        // TODO: check exception is thrown properly
    }
}
