package com.example.mvvm_anotaciones

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appanotificaciones.R
import com.example.mvvm_anotaciones.repository.NotasDB
import com.example.mvvm_anotaciones.ui.NotasAdapter
import com.example.mvvm_anotaciones.ui.NotasViewModel
import com.example.mvvm_anotaciones.ui.NotasViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_add_new_item.*

class activity_main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = NotasDB(this)
        val repository = NotasRepository(database)
        val factory = NotasViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(NotasViewModel::class.java)

        val adapter = NotasAdapter(listOf(),viewModel)

        rvTareasPendientes.layoutManager = LinearLayoutManager(this)
        rvNotes.adapter = adapter


        viewModel.getAllNotasItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })



        binding.btnAgregar.setOnClickListener {
            if(binding.tvDescripcionTarea.text.toString().isNotBlank()){
                val anota = Anotacion((anotacionAdapter.itemCount + 1).toLong(),
                    binding.tvDescripcionTarea.text.toString().trim())
                anota.id = database.insertTarea(anota)
                if(anota.id != constants.ID_ERROR)
                {
                    addAnotacion(anota)
                    binding.tvDescripcionTarea.text?.clear()
                    Snackbar.make(binding.root, "Se agrego una tarea", Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root, "Error al agregar tarea", Snackbar.LENGTH_SHORT).show()
                }
            }else
            {
                binding.tvDescripcionTarea.error = getString(R.string.strValidacionError)
            }
        }
        btnAgregar.setOnClickListener {
            val popup = Dialog(this)
            popup.setContentView(R.layout.popup_add_new_item)
            popup.show()

            popup.btnSave_popup.setOnClickListener {
                val nName = popup.etName_popup.text.toString()

                viewModel.upsert(NotasItem(nName,false))
                popup.dismiss()
            }


        }



    }
}}