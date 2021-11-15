package com.example.hindi.dataSource.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hindi.models.Note

@Dao
interface NoteDAo {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from note " )

    fun getAllNotes():LiveData<MutableList<Note>>
}
