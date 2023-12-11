package com.note.app.tutorial.features.notes

import com.note.app.tutorial.core.database.entities.Note


sealed class NoteEvent {
    class UpsertNoteEvent(val note: Note): NoteEvent()

    object GetAllNotesEvent : NoteEvent()

    class DeleteNoteEvent(val note: Note): NoteEvent()

    class GetNote(val noteId: Int): NoteEvent()


}
