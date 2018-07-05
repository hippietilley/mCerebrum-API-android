package org.md2k.mcerebrum.api.core.datakitapi;

import org.md2k.mcerebrum.api.core.datakitapi.DataSourceReadWrite;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.DataSet;
import org.md2k.mcerebrum.api.core.datakitapi.IDataKitRemoteCallback;

interface IDataKitRemoteService{
    int register(inout DataSourceReadWrite dataSourceReadWrite);
    int unregister(int dsId);
	int find(in DataSourceReadWrite dataSourceReadWrite, out DataSourceReadWrite[] dataSourceReadWrites);
	int insert(in Map data);
	int queryByNumber(int dsId, int lastNSample, out DataSet dataSet);
	int queryByTime(int dsId, long startTimestamp, long endTimestamp, out DataSet dataSet);
	int querySummary(int dsId, long startTimestamp, long endTimestamp, out DataSet dataSet);
	int queryCount(int dsId, long startTimestamp, long endTimestamp, out DataSet dataSet);
	int subscribe(int dsId, IDataKitRemoteCallback callback);
	int unsubscribe(int dsId, IDataKitRemoteCallback callback);

}
