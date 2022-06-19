package com.example.matrizes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_matriz.*
import kotlinx.android.synthetic.main.item_matriz.view.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var celulas = mutableListOf<Celula>()
    private var adapter = MatrizAdapter(celulas)
    private var COLUNAS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOk.setOnLongClickListener {
            //Checa se os inputText não são vazios antes de gerar a matriz
            if ((edtLinhas.text!!.isNotEmpty()) && (edtColunas.text!!.isNotEmpty())) {
                gerarMatriz(true)

            } else {
                Toast.makeText(this, "Coloque as linhas e colunas!", Toast.LENGTH_LONG).show()
            }

            return@setOnLongClickListener true
        }

        btnOk.setOnClickListener {
            //Checa se os inputText não são vazios antes de gerar a matriz
            if ((edtLinhas.text!!.isNotEmpty()) && (edtColunas.text!!.isNotEmpty())) {
                gerarMatriz(false)

            } else {
                Toast.makeText(this, "Coloque as linhas e colunas!", Toast.LENGTH_LONG).show()
            }
        }

        //Torna visivel os botões ocultados de cada operação de matriz
        btnResolver.setOnClickListener {
            if (oculto.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(oculto, AutoTransition())
                oculto.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(oculto, AutoTransition())
                oculto.visibility = View.GONE
            }
        }

        btnDeterminante.setOnClickListener{

            var vetor = arrayOf<Double>()

            //Pega os valores das celulas
            for(x in celulas.indices){

                //Quando a matriz é gerada com numeros aleatorios ela já vai adicionada com os valores
                //Mas quando é adicionado um por um não é assim, pois os elementos em cada celula em valor nulo
                //Aqui eu pego cada elemento na matriz por meio da posição da view no mutableList
                val numero_na_celula = rvMatriz.layoutManager?.findViewByPosition(x)?.EdtCelula?.editText?.text.toString()

                vetor += numero_na_celula.toDouble()
            }
            val matriz = Matriz(arrayOf(vetor), arrayOf(COLUNAS))

            txtResultado.text = matriz.determinante().toString()
        }


        //Função do botão de pegar o valor do InputText
        /*
        btnResolver.setOnClickListener(){


            //txtResultado.text = celulas[3].celula?.toString()

            //Obtem-se o item da celula na posição numero 3
            txtResultado.text = rvMatriz.layoutManager?.findViewByPosition(3)?.EdtCelula?.editText?.text.toString()
        }*/


    }

    //Função que inicia o adapter com o RecycleView
    //De acordo como o layout for definido
    private fun initRecyclerView(colunas: Int) {
        rvMatriz.adapter = adapter

        val layoutManager = GridLayoutManager(this, colunas)

        rvMatriz.layoutManager = layoutManager
    }


    //Adiciona a celula nula ao mutableList e notifica
    //o adapter que foi inserida um celula
    private fun addCelula() {
        val celula = Celula(null)

        celulas.add(celula)
        adapter.notifyItemInserted(celulas.lastIndex)
    }

    //Adiciona um valor aleatorio para a celula
    private fun addCelulaAleatoria() {
        val numero = Random.nextInt(20)

        val celula = Celula(numero)

        celulas.add(celula)
        adapter.notifyItemInserted(celulas.lastIndex)
    }

    //Gera a matriz conforme os valores inseridos nos textInput de linha e colunas
    private fun gerarMatriz(aleatorio: Boolean) {

        //Caso a mutableList não esteja vazia(Na segunda vez que a matriz for gerada)
        //linha toda as celulas nele
        if (celulas.isNotEmpty()) {
            celulas.clear()
        }

        //Obtem os valores nos textInput(vem como string)
        //.trim() remove os espaços antes e depois do numero
        val edtLinha = edtLinhas.text.toString().trim()
        val edtColuna = edtColunas.text.toString().trim()

        //Transforma pra int
        val colunas = edtColuna.toInt()
        val linhas = edtLinha.toInt()

        COLUNAS = colunas

        //Limpa os inputText
        edtColunas.text?.clear()
        edtLinhas.text?.clear()

        //Seleciona onde o cursor irá parar
        edtLinhas.requestFocus()


        initRecyclerView(colunas)

        //Se aleatorio for true gera a matriz já preenchida com valores aleatorios
        if (aleatorio) {
            for (x in 1..colunas * linhas) {
                addCelulaAleatoria()
            }
        } else {
            for (x in 1..colunas * linhas) {
                addCelula()
            }
        }
    }
}