package com.note.app.tutorial.features.notes

import com.note.app.tutorial.core.database.entities.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val note: Note? = null,
    val isLoading: Boolean = false
)
