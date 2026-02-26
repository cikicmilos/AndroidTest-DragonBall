package com.example.dragonballapp_cikicmilos

import android.app.Application
import com.example.dragonballapp_cikicmilos.di.databaseModule
import com.example.dragonballapp_cikicmilos.di.networkModule
import com.example.dragonballapp_cikicmilos.di.repositoryModule
import com.example.dragonballapp_cikicmilos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DragonBallApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DragonBallApp)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
