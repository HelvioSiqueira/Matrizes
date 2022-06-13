package com.example.matrizes

data class Matriz(var celula: Int?) {

    private fun mostrarMatriz() {
        fun main() {

            //Cria um array de duas dimensões
            var matriz = arrayOf<Array<Int>>()

            for (x in 0..3) {

                //Cria o array unidimensional de linha
                var matriz2 = arrayOf<Int>()

                for (y in 0..3) {

                    //Adiciona y a matriz2
                    matriz2 += y
                }

                //Adiciona matriz2 a matriz
                matriz += matriz2
            }

            //Imprime a matriz
            for (x in matriz) {
                for (y in x) {
                    print("$y ")
                }
                println()
            }
        }

    }
}