package com.bach.dv.basemvp.network.source.local.datasource

import com.bach.dv.basemvp.network.base.remote.BaseRemote
import com.bach.dv.basemvp.network.source.local.IAppDataSource

class AppDataSource :BaseRemote() , IAppDataSource {
    @Volatile
    private var instance: AppDataSource? = null
}