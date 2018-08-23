package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class PlatformMetaDataAndroidUnitTest {
    private final String testTitle = "Test Title";
    private final String testSummary = "Test Summary";
    private final String testDescription = "Test Description";
    private final String testOperationSystem = "Test Operating System";
    private final String testManufacturer = "Test Manufacturer";
    private final String testModel = "Test Model";
    private final String testVersionFirmware = "Test Version Firmware";
    private final String testVersionHardware = "Test Version Hardware";
    private final String testDeviceId = "Test Device ID";
    private final String testKey = "Test Key";
    private final String testValue = "Test Value";
    private PlatformMetaData testPlatformMetaData;

    @Test
    public void PlatformMetaDataBuilderTest() {
        testPlatformMetaData = new PlatformMetaData.Builder().setValue(testKey, testValue).build();
        assertEquals(testValue, testPlatformMetaData.getValue(testKey));

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
        assertNull(testPlatformMetaData.getValue(testKey));
    }

    @Test
    public void PlatformMetaData_ParcelableWriteReadTest() {
        testPlatformMetaData = new PlatformMetaData.Builder().setValue(testKey, testValue).build();
        testPlatformMetaData.setTitle(testTitle);
        testPlatformMetaData.setSummary(testSummary);
        testPlatformMetaData.setDescription(testDescription);
        testPlatformMetaData.setOperationSystem(testOperationSystem);
        testPlatformMetaData.setManufacturer(testManufacturer);
        testPlatformMetaData.setModel(testModel);
        testPlatformMetaData.setVersionFirmware(testVersionFirmware);
        testPlatformMetaData.setVersionHardware(testVersionHardware);
        testPlatformMetaData.setDeviceId(testDeviceId);

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
    public void PlatformMetaData_ParcelableWriteReadComparableTest() {
        testPlatformMetaData = new PlatformMetaData.Builder().setValue(testKey, testValue).build();
        testPlatformMetaData.setTitle(testTitle);
        testPlatformMetaData.setSummary(testSummary);
        testPlatformMetaData.setDescription(testDescription);
        testPlatformMetaData.setOperationSystem(testOperationSystem);
        testPlatformMetaData.setManufacturer(testManufacturer);
        testPlatformMetaData.setModel(testModel);
        testPlatformMetaData.setVersionFirmware(testVersionFirmware);
        testPlatformMetaData.setVersionHardware(testVersionHardware);
        testPlatformMetaData.setDeviceId(testDeviceId);

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
        assertEquals(testPlatformMetaData, createdFromParcel);
    }
}