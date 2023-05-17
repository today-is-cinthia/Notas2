package com.example.mvvm_anotaciones.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appanotificaciones.R
import com.example.mvvm_anotaciones.repository.NotasItem

class NotasAdapter(var items: List<NotasItem>,
                   val viewModel: NotasViewModel):
    RecyclerView.Adapter<NotasAdapter.NotasViewHolder>(){

    inner class NotasViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_notas,parent,false)
        return NotasViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int){

        val currentNotesItem = items[position]

        holder.itemView.apply {
            tvTarea.text = currentNotesItem.name
            checkBox_row.isChecked = currentNotesItem.isDone

            checkBox_row.setOnClickListener {

//                currentNotesItem.isDone = !checkBox_row.isChecked
                if(checkBox_row.isChecked){
                    currentNotesItem.isDone = true
                }else{
                    currentNotesItem.isDone = false
                }
                viewModel.upsert(currentNotesItem)

            }

            ivDel.setOnClickListener {
                viewModel.delete(currentNotesItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}


