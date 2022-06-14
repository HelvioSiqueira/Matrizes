package com.example.matrizes

data class Matriz(var celula: Int?) {

    //Função que transforma um vetor em matriz
    //Ela irá receber um array e a quantidade de colunas e irá retornar um array bidimensional
    fun vetorMatriz(array: Array<Any>, colunas: Int): Array<Array<Any>> {

        //Cria o array bidimensional
        var matriz = arrayOf<Array<Any>>()

        //Cria um array para armazenar as linhas
        var matrizLinhas = arrayOf<Any>()

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
    fun matrizInversa(array: Array<Any>, colunas: Int): Array<Array<Any>>{

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
}

