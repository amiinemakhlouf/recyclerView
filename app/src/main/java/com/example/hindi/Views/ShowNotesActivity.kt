package com.example.hindi.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

@AndroidEntryPoint
class ShowNotesActivity : AppCompatActivity(), OnNoteItemClickListener, OnDeleteItem {
    private lateinit var binding: ActivityShowNotesBinding
    private lateinit var noteAdapter: NotesAdapter
    private val viewModel: ShowNotesViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val noteList = mutableListOf<Note>()

        noteAdapter = NotesAdapter(this, viewModel)
        initRecyclerView(this, viewModel)


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

        Toast.makeText(this, "${noteAdapter.dataSEt[position].title}", Toast.LENGTH_LONG).show()
    }

    override fun onDeleteItem(note: Note) {
        viewModel.deleteNotes(note)
    }

    private fun initRecyclerView(listener: ShowNotesActivity, viewModel: ShowNotesViewModel) {

        binding.noteRV.apply {
            layoutManager = LinearLayoutManager(this@ShowNotesActivity)
            adapter = noteAdapter
        }
    }


}