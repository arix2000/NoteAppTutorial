package com.note.app.tutorial.core.di

import com.note.app.tutorial.features.notes.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteViewModel(get()) }
}