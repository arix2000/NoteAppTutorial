package com.note.app.tutorial.features.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.note.app.tutorial.core.database.NoteDao
import com.note.app.tutorial.core.database.entities.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val dao: NoteDao) : ViewModel() {
    private val _state: MutableState<NotesState> = mutableStateOf(NotesState())
    val state: State<NotesState> get() = _state

    fun invokeEvent(noteEvent: NoteEvent) {
        when (noteEvent) {
            is NoteEvent.DeleteNoteEvent -> deleteNote(noteEvent.note)
            NoteEvent.GetAllNotesEvent -> getAllNotes()
            is NoteEvent.GetNote -> getNote(noteEvent.noteId)
            is NoteEvent.UpsertNoteEvent -> upsertNote(noteEvent.note)
        }
    }

    private fun upsertNote(note: Note) {
        viewModelScope.launch {
            if (dao.getAllNotes().map { it.id }.contains(note.id))
                dao.update(note)
            else
                dao.insert(note)
            getAllNotes()
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.delete(note)
            getAllNotes()
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(notes = dao.getAllNotes())
        }
    }

    private fun getNote(noteId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(note = dao.getNote(noteId), isLoading = false)
        }
    }
}