package org.md2k.mcerebrum.api.core;

import org.md2k.mcerebrum.api.core.datakitapi.ApplicationMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.DataDescriptor;
import org.md2k.mcerebrum.api.core.datakitapi.DataSourceMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.PlatformAppMetaData;
import org.md2k.mcerebrum.api.core.datakitapi.PlatformMetaData;

public class CommonObjectConstructors {

    public static PlatformMetaData createPlatformMetaData() {
        PlatformMetaData.Builder testPlatformMetaDataBuilder;
        testPlatformMetaDataBuilder = PlatformMetaData.builder();
        return testPlatformMetaDataBuilder.setValue(TestingConstants.TEST_KEY, TestingConstants.TEST_VALUE)
                                          .setTitle(TestingConstants.TEST_TITLE)
                                          .setSummary(TestingConstants.TEST_SUMMARY)
                                          .setDescription(TestingConstants.TEST_DESCRIPTION)
                                          .setOperationSystem(TestingConstants.TEST_OPERATING_SYSTEM)
                                          .setManufacturer(TestingConstants.TEST_MANUFACTURER)
                                          .setModel(TestingConstants.TEST_MODEL)
                                          .setVersionFirmware(TestingConstants.TEST_VERSION_FIRMWARE)
                                          .setVersionHardware(TestingConstants.TEST_VERSION_HARDWARE)
                                          .setDeviceId(TestingConstants.TEST_DEVICE_ID).build();
    }

    public static PlatformAppMetaData createPlatformAppMetaData() {
        PlatformAppMetaData.Builder testPlatformAppMetaDataBuilder = PlatformAppMetaData.builder();
        return testPlatformAppMetaDataBuilder.setTitle(TestingConstants.TEST_TITLE)
                                             .setSummary(TestingConstants.TEST_SUMMARY)
                                             .setDescription(TestingConstants.TEST_DESCRIPTION)
                                             .setOperationSystem(TestingConstants.TEST_OPERATING_SYSTEM)
                                             .setManufacturer(TestingConstants.TEST_MANUFACTURER)
                                             .setModel(TestingConstants.TEST_MODEL)
                                             .setVersionFirmware(TestingConstants.TEST_VERSION_FIRMWARE)
                                             .setVersionHardware(TestingConstants.TEST_VERSION_HARDWARE)
                                             .setDeviceId(TestingConstants.TEST_DEVICE_ID)
                                             .setValue(TestingConstants.TEST_KEY, TestingConstants.TEST_VALUE)
                                             .build();
    }

    public static ApplicationMetaData createApplicationMetaData() {
        ApplicationMetaData.Builder testAppMetaDataBuilder = ApplicationMetaData.builder();
        return testAppMetaDataBuilder.setTitle(TestingConstants.TEST_TITLE)
                                     .setSummary(TestingConstants.TEST_SUMMARY)
                                     .setDescription(TestingConstants.TEST_DESCRIPTION)
                                     .setVersionName(TestingConstants.TEST_VERSION_NAME)
                                     .setVersionNumber(TestingConstants.TEST_VERSION_NUMBER)
                                     .setValue(TestingConstants.TEST_KEY, TestingConstants.TEST_VALUE)
                                     .build();
    }

    public static DataDescriptor createDataDescriptor() {
        DataDescriptor.Builder testDataDescriptorBuilder = DataDescriptor.builder();
        return testDataDescriptorBuilder.setTitle(TestingConstants.TEST_TITLE)
                                        .setSummary(TestingConstants.TEST_SUMMARY)
                                        .setDescription(TestingConstants.TEST_DESCRIPTION)
                                        .setMinValue(TestingConstants.TEST_MIN_VALUE)
                                        .setMaxValue(TestingConstants.TEST_MAX_VALUE)
                                        .setPossibleValues(TestingConstants.TEST_POSSIBLE_VALUES_AS_STRING)
                                        .setPossibleValues(TestingConstants.TEST_POSSIBLE_VALUES_AS_INT)
                                        .setUnit(TestingConstants.TEST_UNIT)
                                        .setValue(TestingConstants.TEST_KEY, TestingConstants.TEST_VALUE)
                                        .build();
    }

    public static DataSourceMetaData createDataSourceMetaData() {
        DataSourceMetaData.Builder testDataSourceMetaDataBuilder = DataSourceMetaData.builder();
        return testDataSourceMetaDataBuilder.setTitle(TestingConstants.TEST_TITLE)
                                            .setSummary(TestingConstants.TEST_SUMMARY)
                                            .setDescription(TestingConstants.TEST_DESCRIPTION)
                                            .setValue(TestingConstants.TEST_KEY, TestingConstants.TEST_VALUE)
                                            .build();
    }
}
