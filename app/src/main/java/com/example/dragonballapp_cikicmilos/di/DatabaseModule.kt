package com.example.dragonballapp_cikicmilos.di

import androidx.room.Room
import com.example.dragonballapp_cikicmilos.data.local.AppDatabase
import com.example.dragonballapp_cikicmilos.data.local.LocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "dragonball_db"
        ).build()
    }

    single { get<AppDatabase>().characterDao() }

    single { get<AppDatabase>().transformationDao() }

    single { LocalDataSource(get(), get()) }
}
