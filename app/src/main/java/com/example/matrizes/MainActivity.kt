package com.example.matrizes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_matriz.*
import kotlinx.android.synthetic.main.item_matriz.view.*

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

        btnResolver.setOnClickListener{
            //txtResultado.text = celulas[3].celula?.toString()

            //Obtem-se o item da celula na posição numero 3
            txtResultado.text = rvMatriz.layoutManager?.findViewByPosition(3)?.EdtCelula?.editText?.text.toString()
        }


    }
    private fun initRecyclerView(colunas: Int){
        rvMatriz.adapter = adapter

        val layoutManager = GridLayoutManager(this, colunas)

        rvMatriz.layoutManager = layoutManager
    }


    private fun addCelula(){
        val celula = Matriz(null)

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