package com.example.matrizes

data class Matriz(var celula: Int?) {

    fun vetorMatriz(array: Array<Any>, colunas: Int): Array<Array<Any>> {
        var matriz = arrayOf<Array<Any>>()
        var matrizLinhas = arrayOf<Any>()

        var cont = 0

        array.forEach {

            matrizLinhas += it
            cont += 1

            if (cont == colunas) {
                matriz += matrizLinhas

                matrizLinhas = arrayOf()

                cont = 0
            }
        }

        return matriz
    }
}