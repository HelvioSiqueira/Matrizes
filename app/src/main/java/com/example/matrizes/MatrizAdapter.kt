package com.example.matrizes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.item_matriz.view.*

class MatrizAdapter(private val celulas: List<Matriz>): RecyclerView.Adapter<MatrizAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_matriz, parent, false)

        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun getItemCount(): Int = celulas.size

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val edtCelula: TextInputLayout? = itemView.EdtCelula
    }
}
