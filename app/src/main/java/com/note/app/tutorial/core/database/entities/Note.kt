package com.note.app.tutorial.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.note.app.tutorial.core.database.NoteTagConverter

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    @TypeConverters(NoteTagConverter::class)
    val tag: NoteTag
)
