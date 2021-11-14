package com.example.hindi.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindi.dataSource.room.NoteDAo
import com.example.hindi.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowNotesViewModel @Inject constructor(private val noteDao: NoteDAo) : ViewModel() {


    fun insertNotes(note: Note) {
        viewModelScope.launch {
            noteDao.insertNote(note)
        }
    }

    fun deleteNotes(note: Note) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }

    val mutableListOfNotes =
        noteDao.getAllNotes()

}