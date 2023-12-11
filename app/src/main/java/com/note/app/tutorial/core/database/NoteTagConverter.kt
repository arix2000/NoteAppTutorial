package com.note.app.tutorial.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.note.app.tutorial.core.database.entities.NoteTag

class NoteTagConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(json: String): NoteTag {
        return gson.fromJson(json, NoteTag::class.java)
    }
    @TypeConverter
    fun toString(noteTag: NoteTag): String {
        return gson.toJson(noteTag)
    }

}