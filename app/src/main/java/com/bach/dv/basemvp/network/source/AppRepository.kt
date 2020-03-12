package com.bach.dv.basemvp.network.source

import com.bach.dv.basemvp.network.source.local.IAppDataSource
import com.bach.dv.basemvp.network.source.remote.IAuthRemoteDataSource

class AppRepository : IAuthRemoteDataSource, IAppDataSource {
}