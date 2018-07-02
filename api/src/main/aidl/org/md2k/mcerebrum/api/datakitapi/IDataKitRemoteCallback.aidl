package org.md2k.mcerebrum.api.datakitapi;

import org.md2k.mcerebrum.api.datakitapi.datatype.Data;

interface IDataKitRemoteCallback{
    void onReceived(in Data[] data);

}
