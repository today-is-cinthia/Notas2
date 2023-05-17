package com.example.mvvm_anotaciones.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astro.mvvm_notes.data.db.repository.NotesRepository

class NotasViewModelFactory (
                            private val repository: NotasRepository
                            ) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotasViewModel(repository) as T
    }
}
