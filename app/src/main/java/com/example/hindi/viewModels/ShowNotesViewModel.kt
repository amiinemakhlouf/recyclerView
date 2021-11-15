package com.example.hindi.viewModels

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hindi.dataSource.room.NoteDAo
import com.example.hindi.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ShowNotesViewModel @Inject constructor(private val noteDao: NoteDAo) : ViewModel() {

    lateinit var textToSpeech: TextToSpeech
    var text:String=""
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

     fun saySomething(something: String, queueMode: Int = TextToSpeech.QUEUE_ADD) {
        val speechStatus = textToSpeech.speak(something, queueMode, null, "ID")
        if (speechStatus == TextToSpeech.ERROR) {
            Log.d("text to speech","Cant use the Text to speech")

        }
    }


}