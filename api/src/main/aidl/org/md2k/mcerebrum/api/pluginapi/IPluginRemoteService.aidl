package org.md2k.mcerebrum.api.pluginapi;
import org.md2k.mcerebrum.api.pluginapi.MCPluginParam;


interface IPluginRemoteService{
    int getConfigurationState();
    MCPluginParam[] getUserInterfaces();
    boolean openUserInterface(in MCPluginParam mcPluginParam);
    boolean configure(in MCPluginParam mcPluginParam);
    MCPluginParam[] getConfigurationOptions();

    boolean startBackgroundProcess();
    boolean stopBackgroundProcess();
    boolean isBackgroundProcessRunning();

    boolean clear();
    boolean reset();

    boolean getUserPermissions();
    boolean hasUserPermissions();
    String[] getDeclaredFunctions();

}
