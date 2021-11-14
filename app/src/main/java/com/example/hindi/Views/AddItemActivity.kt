package com.example.hindi.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hindi.databinding.ActivityAddItemBinding
import com.example.hindi.models.Note
import com.example.hindi.viewModels.AddItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding
    private val viewModel: AddItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bt.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy:MM:dd h:mm a", Locale.getDefault())

            viewModel.addItem(Note(
                binding.title.text.toString(),
                binding.content.text.toString(),
                sdf.format(Date()),

                ))
            Utils.switchActivity(this,ShowNotesActivity())

        }
    }

}