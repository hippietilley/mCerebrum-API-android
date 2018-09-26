package org.md2k.mcerebrum.api.core.datakitapi.datasource;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.md2k.mcerebrum.api.core.datakitapi.CommonObjectConstructors;
import org.md2k.mcerebrum.api.core.datakitapi.TestingConstants;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PlatformMetaData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class PlatformMetaDataAndroidUnitTest {
    private final String testTitle = TestingConstants.TEST_TITLE;
    private final String testSummary = TestingConstants.TEST_SUMMARY;
    private final String testDescription = TestingConstants.TEST_DESCRIPTION;
    private final String testOperationSystem = TestingConstants.TEST_OPERATING_SYSTEM;
    private final String testManufacturer = TestingConstants.TEST_MANUFACTURER;
    private final String testModel = TestingConstants.TEST_MODEL;
    private final String testVersionFirmware = TestingConstants.TEST_VERSION_FIRMWARE;
    private final String testVersionHardware = TestingConstants.TEST_VERSION_HARDWARE;
    private final String testDeviceId = TestingConstants.TEST_DEVICE_ID;
    private final String testKey = TestingConstants.TEST_KEY;
    private final String testValue = TestingConstants.TEST_VALUE;
    private PlatformMetaData testPlatformMetaData;

    @Test
    public void platformMetaDataBuilderTest() {
        testPlatformMetaData = new PlatformMetaData.Builder().setMetaData(testKey, testValue).build();
        assertEquals(testValue, testPlatformMetaData.getMetaData(testKey));

        testPlatformMetaData = new PlatformMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).build();
        assertEquals(testTitle, testPlatformMetaData.getTitle());
        assertEquals(testSummary, testPlatformMetaData.getSummary());
        assertEquals(testDescription, testPlatformMetaData.getDescription());
        assertEquals(testOperationSystem, testPlatformMetaData.getOperationSystem());
        assertEquals(testManufacturer, testPlatformMetaData.getManufacturer());
        assertEquals(testModel, testPlatformMetaData.getModel());
        assertEquals(testVersionFirmware, testPlatformMetaData.getVersionFirmware());
        assertEquals(testVersionHardware, testPlatformMetaData.getVersionHardware());
        assertEquals(testDeviceId, testPlatformMetaData.getDeviceId());
        assertNull(testPlatformMetaData.getMetaData(testKey));
    }

    @Test
    public void platformMetaDataParcelableWriteReadTest() {
        testPlatformMetaData = CommonObjectConstructors.createPlatformMetaData();

        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testPlatformMetaData.writeToParcel(parcel, testPlatformMetaData.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        PlatformMetaData createdFromParcel = PlatformMetaData.CREATOR.createFromParcel(parcel);
        PlatformMetaData[] createdFromParcelArray = PlatformMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(testPlatformMetaData)));
    }

    @Test
    public void platformMetaDataHashCodeTest() {
        testPlatformMetaData = CommonObjectConstructors.createPlatformMetaData();
        PlatformMetaData testPlatformMetaData2 = CommonObjectConstructors.createPlatformMetaData();
        assertEquals(testPlatformMetaData.hashCode(), testPlatformMetaData2.hashCode());
    }
}