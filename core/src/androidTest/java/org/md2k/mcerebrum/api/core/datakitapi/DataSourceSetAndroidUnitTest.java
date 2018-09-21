package org.md2k.mcerebrum.api.core.datakitapi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataSourceSetAndroidUnitTest {
    private DataSourceReadWrite dataSourceReadWrite = CommonObjectConstructors.createDataSourceReadWrite();
    private DataSourceCreator dataSourceCreator = CommonObjectConstructors.createDataSourceCreator();
    private DataSourceSet dataSourceSet, dataSourceSet2;

    @Before
    public void createDataSourceSet() {
        DataSource[] dataSourceList = {dataSourceReadWrite, dataSourceCreator};
        dataSourceSet = new DataSourceSet(dataSourceList, TestingConstants.STATUS_INT_ARRAY[0]);

        dataSourceSet2 = new DataSourceSet(dataSourceList, TestingConstants.STATUS_INT_ARRAY[0]);
    }

    @Test
    public void dataSourceSetHashCodeTest() {
        assertEquals(dataSourceSet.hashCode(), dataSourceSet2.hashCode());
    }
}
