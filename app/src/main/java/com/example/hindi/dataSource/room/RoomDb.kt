package com.example.hindi.dataSource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hindi.models.Note


@Database(entities = [Note::class],version = 1)
 abstract  class RoomDb: RoomDatabase() {
   abstract fun getNoteDao():NoteDAo
 companion object{
     val databaseName ="NoteDatabase"
 }

}