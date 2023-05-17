package com.example.mvvm_anotaciones.ui

import androidx.lifecycle.ViewModel
import com.astro.mvvm_notes.data.db.NotesItem
import com.astro.mvvm_notes.data.db.repository.NotesRepository
import com.example.mvvm_anotaciones.repository.NotasItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotasViewModel(private val repository: NotasRepository):ViewModel() {

    fun upsert(item: NotasItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: NotasItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllNotasItem() = repository.getAllNotesItem()
}


