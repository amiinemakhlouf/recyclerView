package com.example.hindi.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindi.dataSource.room.NoteDAo
import com.example.hindi.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
@HiltViewModel
class AddItemViewModel @Inject constructor(val dao:NoteDAo):ViewModel() {

    fun addItem(note:Note)= viewModelScope.launch {
        dao.insertNote(note)

    }

}