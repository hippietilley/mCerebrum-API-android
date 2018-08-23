package org.md2k.mcerebrum.api.core.datakitapi;

import android.os.Parcel;
import android.support.test.filters.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@SmallTest
public class PlatformAppMetaDataAndroidUnitTest {
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
        testPlatformAppMetaData = new PlatformAppMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).setValue(testKey, testValue)
                .build();

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
        testPlatformAppMetaData = new PlatformAppMetaData.Builder().setTitle(testTitle).setSummary(testSummary)
                .setDescription(testDescription).setOperationSystem(testOperationSystem)
                .setManufacturer(testManufacturer).setModel(testModel).setVersionFirmware(testVersionFirmware)
                .setVersionHardware(testVersionHardware).setDeviceId(testDeviceId).setValue(testKey, testValue)
                .build();

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
        assertEquals(testPlatformAppMetaData, createdFromParcel);
    }
}
