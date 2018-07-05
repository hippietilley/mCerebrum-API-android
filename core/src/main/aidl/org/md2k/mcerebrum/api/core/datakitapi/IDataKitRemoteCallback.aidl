package org.md2k.mcerebrum.api.core.datakitapi;

import org.md2k.mcerebrum.api.core.datakitapi.datatype.Data;

interface IDataKitRemoteCallback{
    void onReceived(in Data[] data);

}
