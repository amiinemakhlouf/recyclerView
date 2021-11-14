package com.example.hindi.utils

import com.example.hindi.models.Note
import com.example.hindi.viewModels.ShowNotesViewModel

interface OnDeleteItem {

    fun onDeleteItem( note: Note)
}