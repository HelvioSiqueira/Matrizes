package com.example.matrizes

data class Matriz(var celula: Int?) {

    //Função que transforma um vetor em matriz
    //Ela irá receber um array e a quantidade de colunas e irá retornar um array bidimensional
    fun vetorMatriz(array: Array<Double>, colunas: Int): Array<Array<Double>> {

        //Cria o array bidimensional
        var matriz = arrayOf<Array<Double>>()

        //Cria um array para armazenar as linhas
        var matrizLinhas = arrayOf<Double>()

        //Contador que irá contar a quantidade de numeros armazenados na linha
        var cont = 0

        //.forEach percorre cada elemento no vetor, o it representa esse elemento
        array.forEach {

            //Começa adicionando o numero a linha e incrementando o contador
            matrizLinhas += it
            cont++

            //Quando o contador for igual a coluna(num de elementos na linha)
            //ele irá adicionar a linha na matriz e irão ser zerados a matrizLinhas e o contador
            if (cont == colunas) {
                matriz += matrizLinhas

                matrizLinhas = arrayOf()

                cont = 0
            }
        }

        //Retorna a matriz
        return matriz
    }

    //Função que inverte a matriz
    fun matrizInversa(array: Array<Double>, colunas: Int): Array<Array<Any>> {

        //Começa transformando um vetor em uma matriz
        val matriz = vetorMatriz(array, colunas)

        //Cria a variavel que irá armazenar a matriz inversa
        var matriz_I = arrayOf<Array<Any>>()

        //O primeiro for servirá para percorrer as colunas
        for (x in matriz[0].indices) {
            //Cria e zera o array que irá armazenar cada numero da linha
            var linhas = arrayOf<Any>()

            //Esse for irá percorrer cada elemento da linha
            for (y in matriz.indices) {

                //Adiciona o elemento atual com os indices invertidos no array de linha
                linhas += matriz[y][x]
            }
            //Adiciona a linha na matriz
            matriz_I += linhas
        }

        //retorna a matriz
        return matriz_I
    }

    fun somaMatrizes(
        matriz_1: Array<Array<Double>>,
        matriz_2: Array<Array<Double>>
    ): Array<Array<Double>> {
        var matriz_S = arrayOf<Array<Double>>()

        for (x in matriz_1.indices) {
            var linha = arrayOf<Double>()

            for (y in matriz_1[0].indices) {
                linha += (matriz_1[x][y] + matriz_2[x][y])
            }
            matriz_S += linha
        }
        return matriz_S
    }

    fun multMatrizes(
        matriz_1: Array<Array<Double>>,
        matriz_2: Array<Array<Double>>
    ): Array<Array<Double>> {
        var matriz_M = arrayOf<Array<Double>>()

        //Cria a matriz vazia tendo como base a quantidade de colunas de matriz_2
        //e a quantidade de linhas em matriz_1
        for (x in matriz_2[0].indices) {
            var linha = arrayOf<Double>()

            for (y in matriz_1.indices) {
                linha += 0.0
            }
            matriz_M += linha
        }

        var cont_l = 0
        var cont_c = 0
        var mult = 0.0

        for (x in matriz_1.indices) {
            for (y in matriz_2[0].indices) {
                var soma = 0.0

                for (z in matriz_1[0].indices) {
                    mult = matriz_1[x][z] * matriz_2[cont_l][cont_c]
                    soma += mult

                    cont_l++
                }
                matriz_M[x][y] = soma

                cont_l = 0
                cont_c += 1
            }
            cont_c = 0
        }

        return matriz_M
    }
}

