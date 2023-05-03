package com.example.challengechap4.dbroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun insertNote(noteData: NoteData)

    @Query("SELECT * FROM NoteData ORDER BY id ASC ")
    fun getDataNote() : List<NoteData>

    @Delete
    fun deleteNote(note: NoteData )

    @Update
    fun updateNote(note: NoteData )

}