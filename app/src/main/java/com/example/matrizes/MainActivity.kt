package com.example.matrizes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var celulas = mutableListOf<Matriz>()
    private var adapter = MatrizAdapter(celulas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnClickListener{
            if(edtLinhas.text!!.isNotEmpty()  && edtColunas.text!!.isNotEmpty()){
                gerarMatriz()
            } else {
                Toast.makeText(this, "Coloque as linhas e colunas!", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun initRecyclerView(colunas: Int){
        rvMatriz.adapter = adapter

        val layoutManager = GridLayoutManager(this, colunas)

        rvMatriz.layoutManager = layoutManager
    }


    private fun addCelula(){
        val celula = Matriz(0)

        celulas.add(celula)
        adapter.notifyItemInserted(celulas.lastIndex)
    }

    private fun gerarMatriz(){

            if(celulas.isNotEmpty()){
                celulas.clear()
            }

            val edtLinha = edtLinhas.text.toString()
            val edtColuna = edtColunas.text.toString()

            val colunas = edtColuna.toInt()
            val linhas = edtLinha.toInt()

            edtColunas.text?.clear()
            edtLinhas.text?.clear()

            edtLinhas.requestFocus()

            initRecyclerView(colunas)

            for(x in 1..colunas * linhas){
                addCelula()
            }
    }
}