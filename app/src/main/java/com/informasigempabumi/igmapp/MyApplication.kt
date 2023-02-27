package com.informasigempabumi.igmapp

import android.app.Application
import com.informasigempabumi.igmapp.core.di.netrworkModule
import com.informasigempabumi.igmapp.core.di.repositoryModule
import com.informasigempabumi.igmapp.ui.di.useCaseModule
import com.informasigempabumi.igmapp.ui.di.viewModelModule
import com.mapbox.mapboxsdk.Mapbox
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Mapbox.getInstance(applicationContext, getString(R.string.key_mapbox))
        startKoin {
            androidLogger(level = Level.NONE)
            modules(
                listOf(
                    netrworkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}