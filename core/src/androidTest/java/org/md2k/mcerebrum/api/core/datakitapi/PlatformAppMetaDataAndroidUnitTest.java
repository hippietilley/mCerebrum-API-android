package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class PlatformAppMetaDataAndroidUnitTest {
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
    private PlatformAppMetaData testPlatformAppMetaData;

    @Test
    public void PlatformAppMetaDataBuilderTest() {
        testPlatformAppMetaData = new PlatformAppMetaData.Builder().setValue(testKey, testValue).build();
        assertEquals(testValue, testPlatformAppMetaData.getValue(testKey));

        testPlatformAppMetaData = new PlatformAppMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).build();
        assertEquals(testTitle, testPlatformAppMetaData.getTitle());
        assertEquals(testSummary, testPlatformAppMetaData.getSummary());
        assertEquals(testDescription, testPlatformAppMetaData.getDescription());
        assertEquals(testOperationSystem, testPlatformAppMetaData.getOperationSystem());
        assertEquals(testManufacturer, testPlatformAppMetaData.getManufacturer());
        assertEquals(testModel, testPlatformAppMetaData.getModel());
        assertEquals(testVersionFirmware, testPlatformAppMetaData.getVersionFirmware());
        assertEquals(testVersionHardware, testPlatformAppMetaData.getVersionHardware());
        assertEquals(testDeviceId, testPlatformAppMetaData.getDeviceId());
        assertNull(testPlatformAppMetaData.getValue(testKey));
    }

    @Test
    public void PlatformAppMetaData_ParcelableWriteReadTest() {
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();

        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testPlatformAppMetaData.writeToParcel(parcel, testPlatformAppMetaData.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        PlatformAppMetaData createdFromParcel = PlatformAppMetaData.CREATOR.createFromParcel(parcel);
        PlatformAppMetaData[] createdFromParcelArray = PlatformAppMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertEquals(testTitle, createdFromParcel.getTitle());
        assertEquals(testSummary, createdFromParcel.getSummary());
        assertEquals(testDescription, createdFromParcel.getDescription());
        assertEquals(testOperationSystem, createdFromParcel.getOperationSystem());
        assertEquals(testManufacturer, createdFromParcel.getManufacturer());
        assertEquals(testModel, createdFromParcel.getModel());
        assertEquals(testVersionFirmware, createdFromParcel.getVersionFirmware());
        assertEquals(testVersionHardware, createdFromParcel.getVersionHardware());
        assertEquals(testDeviceId, createdFromParcel.getDeviceId());
        assertEquals(testValue, createdFromParcel.getValue(testKey));
    }

    @Test
    public void PlatformAppMetaData_ParcelableWriteReadComparableTest() {
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();

        // Write to parcel.
        Parcel parcel = Parcel.obtain();
        testPlatformAppMetaData.writeToParcel(parcel, testPlatformAppMetaData.describeContents());

        // After writing, reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        PlatformAppMetaData createdFromParcel = PlatformAppMetaData.CREATOR.createFromParcel(parcel);
        PlatformAppMetaData[] createdFromParcelArray = PlatformAppMetaData.CREATOR.newArray(1);

        // Verify results.
        assertNotEquals(0, createdFromParcelArray.length);
        assertThat(createdFromParcel, is(equalTo(testPlatformAppMetaData)));
    }

    @Test
    public void platformAppMetaDataHashCodeTest() {
        testPlatformAppMetaData = CommonObjectConstructors.createPlatformAppMetaData();
        PlatformAppMetaData testPlatformAppMetaData2 = CommonObjectConstructors.createPlatformAppMetaData();
        assertEquals(testPlatformAppMetaData.hashCode(), testPlatformAppMetaData2.hashCode());
    }
}
