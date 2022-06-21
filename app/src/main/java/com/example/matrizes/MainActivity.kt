package com.example.matrizes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_matriz_resultante.*
import kotlinx.android.synthetic.main.item_matriz.view.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var celulas = mutableListOf<Celula>()
    private var adapter = MatrizAdapter(celulas)
    private var COLUNAS = 0
    private var lista_colunas = arrayOf<Int>()
    private var lista_vetores = arrayOf<Array<Double>>()

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

            if(celulas.isNotEmpty()){
                var vetor = arrayOf<Double>()

                //Pega os valores das celulas
                for(x in celulas.indices){

                    //Quando a matriz é gerada com numeros aleatorios ela já vai adicionada com os valores
                    //Mas quando é adicionado um por um não é assim, pois os elementos em cada celula em valor nulo
                    //Aqui eu pego cada elemento na matriz por meio da posição da view no mutableList
                    val numero_na_celula = rvMatriz.layoutManager?.findViewByPosition(x)?.EdtCelula?.editText?.text.toString()

                    vetor += numero_na_celula.toDouble()

                }

                lista_vetores += vetor

                val matriz = Matriz(lista_vetores, lista_colunas)

                var determinante = DoubleArray(0)

                val intent = Intent(this, ResultanteActivity::class.java)

                determinante += matriz.determinante()

                val texto = resources.getString(R.string.resultado_determinante)

                intent.putExtra("array", determinante)
                intent.putExtra("colunas", arrayOf(1))
                intent.putExtra("titulo", texto)

                startActivity(intent)

            } else {
                Toast.makeText(this, "Adicione a matriz", Toast.LENGTH_LONG).show()
            }

            lista_vetores = arrayOf<Array<Double>>()
            lista_colunas = arrayOf<Int>()
        }

        btnSoma.setOnClickListener{

            if(lista_vetores.size > 1){
                val matriz = Matriz(lista_vetores, lista_colunas)

                try {
                    val matriz_S = matriz.somaMatrizes()
                    val matriz_S_vetor = matriz.matrizVetor(matriz_S)
                    val matriz_S_colunas = matriz.getColunas(matriz_S)

                    Toast.makeText(this, "Somado", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, ResultanteActivity::class.java)

                    val texto = resources.getString(R.string.resultado_soma)

                    intent.putExtra("colunas", matriz_S_colunas)
                    intent.putExtra("array", matriz_S_vetor)
                    intent.putExtra("titulo", texto)

                    startActivity(intent)
                }

                catch (e: Exception){
                    Toast.makeText(this, "As matrizes são diferentes!", Toast.LENGTH_LONG).show()
                }

                finally {
                    lista_vetores = arrayOf<Array<Double>>()
                    lista_colunas = arrayOf<Int>()
                    quantMatrizes.visibility = View.INVISIBLE
                    btnSoma.visibility = View.GONE
                    btnMultiplicar.visibility = View.GONE
                }
            } else {
                Toast.makeText(this, "Adicione ao menos duas matrizes", Toast.LENGTH_LONG).show()
            }
        }

        btnMultiplicar.setOnClickListener{
            if(lista_vetores.size > 1){
                val matriz = Matriz(lista_vetores, lista_colunas)

                try {
                    var matriz_M = matriz.multMatrizes()
                    var matriz_M_vetor = matriz.matrizVetor(matriz_M)
                    var matriz_M_colunas = matriz.getColunas(matriz_M)

                    Toast.makeText(this, "Multiplicado", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, ResultanteActivity::class.java)

                    val texto = resources.getString(R.string.resultado_mult)

                    intent.putExtra("colunas", matriz_M_colunas)
                    intent.putExtra("array", matriz_M_vetor)
                    intent.putExtra("titulo", texto)

                    startActivity(intent)

                }

                catch (e: Exception){
                    Toast.makeText(this, "L da matriz 1 deve ser igual a C da matriz 25", Toast.LENGTH_LONG).show()
                }

                finally {
                    lista_vetores = arrayOf<Array<Double>>()
                    lista_colunas = arrayOf<Int>()
                    quantMatrizes.visibility = View.INVISIBLE
                    btnMultiplicar.visibility = View.GONE
                    btnSoma.visibility = View.GONE
                }
            } else {
                Toast.makeText(this, "Adicione ao menos duas matrizes", Toast.LENGTH_LONG).show()
            }
        }

        btnMaisUma.setOnClickListener{

            if(celulas.isNotEmpty()){
                var vetor = arrayOf<Double>()

                for(x in celulas.indices){
                    val numero_na_celula = rvMatriz.layoutManager!!.findViewByPosition(x)!!.EdtCelula!!.editText!!.text.toString()

                    vetor += numero_na_celula.toDouble()
                }

                lista_vetores += vetor
            } else {
                Toast.makeText(this, "Adicione duas matrizes", Toast.LENGTH_LONG).show()
            }

            celulas.clear()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Matriz adicionada!", Toast.LENGTH_LONG).show()

            quantMatrizes.text = resources.getQuantityString(R.plurals.quant_text, lista_vetores.count(), lista_vetores.count())
            quantMatrizes.visibility = View.VISIBLE

            if(lista_vetores.size >= 2){
                btnSoma.visibility = View.VISIBLE
                btnMultiplicar.visibility = View.VISIBLE
            }

        }

        btnTransversa.setOnClickListener{
            if(lista_vetores.size > 0){
                val matriz = Matriz(lista_vetores, lista_colunas)

                val matriz_I = matriz.matrizInversa()
                val matriz_I_vetor = matriz.matrizVetor(matriz_I)
                val matriz_I_colunas = matriz.getColunas(matriz_I)

                val texto = resources.getString(R.string.resultado_transversa)

                Log.i("HSV", texto)

                intent = Intent(this, ResultanteActivity::class.java)
                intent.putExtra("array", matriz_I_vetor)
                intent.putExtra("colunas", matriz_I_colunas)
                intent.putExtra("titulo", texto)

                startActivity(intent)

                lista_vetores = arrayOf<Array<Double>>()
                lista_colunas = arrayOf<Int>()
                quantMatrizes.visibility = View.INVISIBLE

            } else {
                Toast.makeText(this, "Adicione a matriz", Toast.LENGTH_LONG).show()
            }
        }
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
        val numero = Random.nextInt(20).toDouble()

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

        lista_colunas += colunas

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