package com.example.hindi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hindi.Views.ShowNotesActivity
import com.example.hindi.databinding.ItemsBinding
import com.example.hindi.models.Note
import com.example.hindi.viewModels.ShowNotesViewModel

class NotesAdapter(
    val listener: ShowNotesActivity,
    val viewModel: ShowNotesViewModel,
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
     var dataSEt = mutableListOf<Note>()

    inner class NotesViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root){
        init {
             binding.delete.setOnClickListener {
                listener.onDeleteItem(dataSEt[adapterPosition])
            }
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION)
                    listener.noteOnItemClick(adapterPosition)
            }
        }

        fun bind(data: Note) {
            binding.title.text = data.title
            binding.time.text = data.timeStamp

        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {


        return NotesViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.apply {
            holder.bind(dataSEt[position])

        }
    }


    override fun getItemCount(): Int {
        return dataSEt.size
    }


}
