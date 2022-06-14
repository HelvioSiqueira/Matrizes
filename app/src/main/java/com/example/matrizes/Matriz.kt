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

    fun matrizInversa(array: Array<Any>, colunas: Int): Array<Array<Any>>{

        val matriz = vetorMatriz(array, colunas)


        var matriz_I = arrayOf<Array<Any>>()

        val tamanhoL = matriz.size - 1
        val tamanhoC = matriz[0].size - 1

        for(x in 0..tamanhoC){
            var linhas = arrayOf<Any>()

            for(y in 0..tamanhoL){
                linhas += matriz[y][x]
            }
            matriz_I += linhas
        }

        return matriz_I
    }
}

