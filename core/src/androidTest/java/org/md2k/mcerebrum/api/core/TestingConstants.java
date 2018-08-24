package org.md2k.mcerebrum.api.core;

import android.os.Build;

import org.md2k.mcerebrum.api.core.datakitapi.datasource.APPLICATION;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.DATASOURCE;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM;
import org.md2k.mcerebrum.api.core.datakitapi.datasource.PLATFORM_APP;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataType;

import java.util.concurrent.TimeUnit;

public class TestingConstants {
    // Variable for Platform and Application metadata objects
    public static final String TEST_TITLE = "Android Phone";
    public static final String TEST_SUMMARY = "Android Phone";
    public static final String TEST_DESCRIPTION = "Test Description";
    public static final String TEST_OPERATING_SYSTEM = "Android " + Build.VERSION.RELEASE;
    public static final String TEST_MANUFACTURER = Build.MANUFACTURER;
    public static final String TEST_MODEL = Build.MODEL;
    public static final String TEST_VERSION_FIRMWARE = "Test Version Firmware";
    public static final String TEST_VERSION_HARDWARE = "Test Version Hardware";
    public static final String TEST_VERSION_NAME = "Test version";
    public static final int TEST_VERSION_NUMBER = 1;
    public static final double TEST_MIN_VALUE = 3.14;
    public static final double TEST_MAX_VALUE = 6.28;
    public static final String[] TEST_POSSIBLE_VALUES_AS_STRING = {"3.14", "4", "5", "6", "6.28"};
    public static final int[] TEST_POSSIBLE_VALUES_AS_INT = {3, 4, 5, 6};
    public static final String TEST_UNIT = "Test Unit";
    public static final String TEST_DEVICE_ID = "Test Device ID";
    public static final String TEST_KEY = "Test Key";
    public static final String TEST_VALUE = "Test Value";

    public static final int TEST_SAMPLE_NO = 10;


    public static final String[] DATASOURCE_ID_ARRAY = {DATASOURCE.ID.SMOKING,
                                                      DATASOURCE.ID.EATING,
                                                      DATASOURCE.ID.SELF_REPORT};

    public static final String[] PLATFORM_TYPE_ARRAY = {PLATFORM.TYPE.AUTOSENSE_CHEST,
                                                      PLATFORM.TYPE.AUTOSENSE_BLE,
                                                      PLATFORM.TYPE.AUTOSENSE_WRIST,
                                                      PLATFORM.TYPE.MICROSOFT_BAND,
                                                      PLATFORM.TYPE.PHONE,
                                                      PLATFORM.TYPE.OMRON_BLOOD_PRESSURE,
                                                      PLATFORM.TYPE.OMRON_WEIGHT_SCALE,
                                                      PLATFORM.TYPE.EASYSENSE,
                                                      PLATFORM.TYPE.MOTION_SENSE,
                                                      PLATFORM.TYPE.MOTION_SENSE_HRV,
                                                      PLATFORM.TYPE.MOTION_SENSE_HRV_PLUS,
                                                      PLATFORM.TYPE.ORALB_BRUSH,
                                                      PLATFORM.TYPE.BEACON};

    public static final String[] PLATFORM_ID_ARRAY = {PLATFORM.ID.CHEST, PLATFORM.ID.LEFT_WRIST, PLATFORM.ID.RIGHT_WRIST};

    public static final String[] PLATFORM_APP_ID_ARRAY = {PLATFORM_APP.ID.CHEST, PLATFORM_APP.ID.LEFT_WRIST, PLATFORM_APP.ID.RIGHT_WRIST};

    public static final String[] APPLICATION_TYPE_ARRAY = {APPLICATION.TYPE.SENSE, APPLICATION.TYPE.ANALYZE, APPLICATION.TYPE.ACT};

    public static final String[] APPLICATION_ID_ARRAY = {};

    public static final TimeUnit[] TIME_UNITS = {TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS, TimeUnit.MILLISECONDS};

    public static final DataType[] DATA_TYPE_ARRAY = {DataType.DATAPOINT_BOOLEAN,
                                                    DataType.DATAPOINT_BYTE,
                                                    DataType.DATAPOINT_INT,
                                                    DataType.DATAPOINT_LONG,
                                                    DataType.DATAPOINT_DOUBLE,
                                                    DataType.DATAPOINT_STRING,
                                                    DataType.DATAPOINT_ENUM,
                                                    DataType.DATAPOINT_OBJECT,
                                                    DataType.DATAANNOTATION_ENUM,
                                                    DataType.UNKNOWN};

    public static final String[] PLATFORM_APP_TYPE_ARRAY = {PLATFORM_APP.TYPE.AUTOSENSE_CHEST,
                                                         PLATFORM_APP.TYPE.AUTOSENSE_BLE,
                                                         PLATFORM_APP.TYPE.AUTOSENSE_WRIST,
                                                         PLATFORM_APP.TYPE.MICROSOFT_BAND,
                                                         PLATFORM_APP.TYPE.PHONE,
                                                         PLATFORM_APP.TYPE.OMRON_BLOOD_PRESSURE,
                                                         PLATFORM_APP.TYPE.OMRON_WEIGHT_SCALE,
                                                         PLATFORM_APP.TYPE.EASYSENSE,
                                                         PLATFORM_APP.TYPE.MOTION_SENSE,
                                                         PLATFORM_APP.TYPE.MOTION_SENSE_HRV,
                                                         PLATFORM_APP.TYPE.MOTION_SENSE_HRV_PLUS,
                                                         PLATFORM_APP.TYPE.ORALB_BRUSH,
                                                         PLATFORM_APP.TYPE.BEACON};

    public static final String[] DATA_SOURCE_TYPE_ARRAY = {DATASOURCE.TYPE.ACCELEROMETER,
                                          DATASOURCE.TYPE.GYROSCOPE,
                                          DATASOURCE.TYPE.COMPASS,
                                          DATASOURCE.TYPE.AMBIENT_LIGHT,
                                          DATASOURCE.TYPE.PRESSURE,
                                          DATASOURCE.TYPE.PROXIMITY,
                                          DATASOURCE.TYPE.LOCATION,
                                          DATASOURCE.TYPE.GEOFENCE,
                                          DATASOURCE.TYPE.DISTANCE,

                                          DATASOURCE.TYPE.HEART_RATE,
                                          DATASOURCE.TYPE.SPEED,
                                          DATASOURCE.TYPE.STEP_COUNT,
                                          DATASOURCE.TYPE.PACE,
                                          DATASOURCE.TYPE.MOTION_TYPE,
                                          DATASOURCE.TYPE.ULTRA_VIOLET_RADIATION,
                                          DATASOURCE.TYPE.BAND_CONTACT,
                                          DATASOURCE.TYPE.CALORY_BURN,
                                          DATASOURCE.TYPE.ECG,
                                          DATASOURCE.TYPE.RESPIRATION,
                                          DATASOURCE.TYPE.RESPIRATION_BASELINE,
                                          DATASOURCE.TYPE.RESPIRATION_RAW,
                                          DATASOURCE.TYPE.ALTIMETER,
                                          DATASOURCE.TYPE.AIR_PRESSURE,
                                          DATASOURCE.TYPE.RR_INTERVAL,
                                          DATASOURCE.TYPE.AMBIENT_TEMPERATURE,
                                          DATASOURCE.TYPE.SKIN_TEMPERATURE,
                                          DATASOURCE.TYPE.BATTERY,
                                          DATASOURCE.TYPE.CPU,
                                          DATASOURCE.TYPE.MEMORY,
                                          DATASOURCE.TYPE.AUTOSENSE,
                                    // Accelerometer axes
                                          DATASOURCE.TYPE.ACCELEROMETER_X,
                                          DATASOURCE.TYPE.ACCELEROMETER_Y,
                                          DATASOURCE.TYPE.ACCELEROMETER_Z,
                                    // Gyroscope axes
                                          DATASOURCE.TYPE.GYROSCOPE_X,
                                          DATASOURCE.TYPE.GYROSCOPE_Y,
                                          DATASOURCE.TYPE.GYROSCOPE_Z,

                                          DATASOURCE.TYPE.EMA,
                                          DATASOURCE.TYPE.STATUS,
                                          DATASOURCE.TYPE.LOG,
                                          DATASOURCE.TYPE.TIMEZONE,

                                          DATASOURCE.TYPE.PRIVACY,
                                          DATASOURCE.TYPE.STUDY_INFO,
                                          DATASOURCE.TYPE.USER_INFO,

                                          DATASOURCE.TYPE.SLEEP,
                                          DATASOURCE.TYPE.WAKEUP,
                                          DATASOURCE.TYPE.DAY_START,
                                          DATASOURCE.TYPE.DAY_END,

                                          DATASOURCE.TYPE.STRESS_PROBABILITY,
                                          DATASOURCE.TYPE.STRESS_LABEL,
                                          DATASOURCE.TYPE.STUDY_START,

                                          DATASOURCE.TYPE.STUDY_END,
                                          DATASOURCE.TYPE.STRESS_ACTIVITY,
                                          DATASOURCE.TYPE.CSTRESS_FEATURE_VECTOR,

                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_DATA_RIP_QUALITY,
                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_DATA_ECG_QUALITY,

                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_CLASSIFICATION,

                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_ARRAY_CLASSIFICATION_FULL_EPISODE,

                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_START,
                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_PEAK,

                                          DATASOURCE.TYPE.ORG_MD2K_CSTRESS_STRESS_EPISODE_END,
                                          DATASOURCE.TYPE.CSTRESS_FEATURE_VECTOR_RIP,

                                          DATASOURCE.TYPE.STRESS_RIP_PROBABILITY,
                                          DATASOURCE.TYPE.STRESS_RIP_LABEL,
                                          DATASOURCE.TYPE.ACTIVITY,

                                          DATASOURCE.TYPE.PUFF_PROBABILITY,
                                          DATASOURCE.TYPE.PUFF_LABEL,
                                          DATASOURCE.TYPE.PUFFMARKER_FEATURE_VECTOR,

                                          DATASOURCE.TYPE.PUFFMARKER_SMOKING_EPISODE,
                                          DATASOURCE.TYPE.NOTIFICATION_REQUEST,

                                          DATASOURCE.TYPE.NOTIFICATION_ACKNOWLEDGE,
                                          DATASOURCE.TYPE.NOTIFICATION_RESPONSE,

                                          DATASOURCE.TYPE.DATA_QUALITY,
                                          DATASOURCE.TYPE.DATA_VARIANCE,
                                          DATASOURCE.TYPE.TYPE_OF_DAY,

                                          DATASOURCE.TYPE.EVENT,
                                          DATASOURCE.TYPE.INCENTIVE,
                                          DATASOURCE.TYPE.BLOOD_PRESSURE,

                                          DATASOURCE.TYPE.WEIGHT,
                                    // Oral B
                                          DATASOURCE.TYPE.ORALB_PRESSURE,
                                          DATASOURCE.TYPE.ORALB_CONNECTION,
                                          DATASOURCE.TYPE.ORALB_SECTOR,
                                          DATASOURCE.TYPE.ORALB_BRUSHING_MODE,
                                          DATASOURCE.TYPE.ORALB_BRUSHING_STATE,
                                          DATASOURCE.TYPE.ORALB_BUSHING_TIME,

                                          DATASOURCE.TYPE.ACTIVITY_TYPE,
                                          DATASOURCE.TYPE.LED,
                                          DATASOURCE.TYPE.SEQUENCE_NUMBER,

                                          DATASOURCE.TYPE.SMOKING,
                                          DATASOURCE.TYPE.RAW,
                                          DATASOURCE.TYPE.POST_QUIT,

                                          DATASOURCE.TYPE.PRE_QUIT,
                                          DATASOURCE.TYPE.BEACON,
                                    // Data Quality
                                          DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_MINUTE,
                                          DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_HOUR,
                                          DATASOURCE.TYPE.DATA_QUALITY_SUMMARY_DAY,

                                          DATASOURCE.TYPE.GALVANIC_SKIN_RESPONSE,
                                          DATASOURCE.TYPE.TOUCH_SCREEN,
                                          DATASOURCE.TYPE.WORK_ANNOTATION,
                                    // For data integration from Cornell
                                          DATASOURCE.TYPE.CU_IS_SCREEN_ON,
                                          DATASOURCE.TYPE.CU_AUDIO_FEATURE,
                                          DATASOURCE.TYPE.CU_AUDIO_ENERGY,
                                          DATASOURCE.TYPE.CU_AUDIO_INFERENCE,
                                          DATASOURCE.TYPE.CU_APPUSAGE,
                                          DATASOURCE.TYPE.CU_SMS_NUMBER,
                                          DATASOURCE.TYPE.CU_SMS_TYPE,
                                          DATASOURCE.TYPE.CU_SMS_LENGTH,
                                          DATASOURCE.TYPE.CU_CALL_NUMBER,
                                          DATASOURCE.TYPE.CU_SMS_TYPE,
                                          DATASOURCE.TYPE.CU_CALL_DURATION,
                                          DATASOURCE.TYPE.LABEL,
                                    // Magnetometer
                                          DATASOURCE.TYPE.MAGNETOMETER,
                                          DATASOURCE.TYPE.QUATERNION,
                                          DATASOURCE.TYPE.MAGNETOMETER_SENSITIVITY,
                                    // Oral B Toothbrush
                                          DATASOURCE.TYPE.ORALB_BRUSHING_TIME,
                                          DATASOURCE.TYPE.ORALB_BRUSH_ACCELEROMETER};
}
