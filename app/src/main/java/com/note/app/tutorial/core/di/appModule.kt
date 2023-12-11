package com.note.app.tutorial.core.di

import com.note.app.tutorial.core.database.AppDatabase
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get()) }

    factory { get<AppDatabase>().noteDao() }
}