package com.example.android.capstone;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

/**
 * Created by DELL on 1/11/2017.
 */

@SimpleSQLConfig(
        name = "PicProvider",
        authority = "com.example.android.capstone",
        database = "canvasDownload.db",
        version = 1)
public class PicProviderConfig implements ProviderConfig {

    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
