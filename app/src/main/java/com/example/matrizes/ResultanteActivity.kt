package com.example.matrizes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_matriz_resultante.*

class ResultanteActivity : AppCompatActivity() {

    private var celulas = mutableListOf<Celula>()
    private val adapter = MatrizAdapter(celulas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matriz_resultante)

        val texto_lay = intent.getStringExtra("titulo")

        txtResultado.text = texto_lay

        gerarMatriz()
    }

    private fun initRecyclerView(colunas: Int) {
        rvMatrizResultante.adapter = adapter

        val layoutManager = GridLayoutManager(this, colunas)

        rvMatrizResultante.layoutManager = layoutManager
    }

    private fun addCelula(elemento: Double) {
        val celula = Celula(elemento)

        celulas.add(celula)
        adapter.notifyItemInserted(celulas.lastIndex)
    }

    private fun gerarMatriz() {

        val colunas = intent.getIntExtra("colunas", 1)
        val vetor = intent.getDoubleArrayExtra("array")

        initRecyclerView(colunas)

        var cont = 0

        for (x in 1..vetor!!.size) {
            addCelula(vetor[cont])
            cont++
        }

    }
}