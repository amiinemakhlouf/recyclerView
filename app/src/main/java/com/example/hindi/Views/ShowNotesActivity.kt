package com.example.hindi.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hindi.adapters.NotesAdapter
import com.example.hindi.databinding.ActivityShowNotesBinding
import com.example.hindi.databinding.ItemsBinding
import com.example.hindi.models.Note
import com.example.hindi.utils.OnDeleteItem
import com.example.hindi.utils.OnNoteItemClickListener
import com.example.hindi.viewModels.ShowNotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ShowNotesActivity : AppCompatActivity(), OnNoteItemClickListener, OnDeleteItem,TextToSpeech.OnInitListener
     {
    private lateinit var binding: ActivityShowNotesBinding
    private lateinit var noteAdapter: NotesAdapter
    private val viewModel: ShowNotesViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteAdapter = NotesAdapter(this, viewModel)
        initRecyclerView()
        viewModel.textToSpeech= TextToSpeech(this,this)


        viewModel.mutableListOfNotes.observe(this, Observer {
            noteAdapter.dataSEt = it
            noteAdapter.notifyDataSetChanged()
            val itemsBinding = ItemsBinding.inflate(layoutInflater)
            noteAdapter.NotesViewHolder(itemsBinding)

        })
        binding.addItem.setOnClickListener {

            Utils.switchActivity(this, AddItemActivity())
        }


    }

    override fun noteOnItemClick(position: Int) {
        viewModel.textToSpeech.speak(noteAdapter.dataSEt[position].body,TextToSpeech.QUEUE_FLUSH,null,null)
    }
    override fun onDeleteItem(note: Note) {
        viewModel.deleteNotes(note)
    }

    private fun initRecyclerView() {

        binding.noteRV.apply {
            layoutManager = LinearLayoutManager(this@ShowNotesActivity)
            adapter = noteAdapter
        }
    }

         override fun onInit(status: Int) {

             if (status == TextToSpeech.SUCCESS) {
                 // set US English as language for tts
                 val result = viewModel.textToSpeech!!.setLanguage(Locale.US)

                 if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                     Log.e("TTS", "The Language specified is not supported!")
                 }

             } else {
                 Log.e("TTS", "Initilization Failed!")
             }
         }


         }

