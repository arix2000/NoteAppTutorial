package com.note.app.tutorial.core

import android.app.Application
import com.note.app.tutorial.core.di.appModule
import com.note.app.tutorial.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NotesApplication)
            modules(appModule, viewModelModule)
        }
    }
}