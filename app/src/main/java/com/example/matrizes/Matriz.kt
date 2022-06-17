package com.example.matrizes

import java.lang.Double.sum

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

    //Função que soma DUAS matrizes
    //Ela precisará já receber em formato de matrizes
    //Então ela deve primeiro passa pela fun vetorMatriz()
    fun somaMatrizes(
        matriz_1: Array<Array<Double>>,
        matriz_2: Array<Array<Double>>
    ): Array<Array<Double>> {
        var matriz_S = arrayOf<Array<Double>>()

        //O primeiro for percorre as linhas da matriz_1
        //(Poderia ser qualquer das duas matrizes somadas já que elas devem ser iguais em tamanho)
        for (x in matriz_1.indices) {
            //Array pra armazenar a linha
            var linha = arrayOf<Double>()

            //For que percorre as colunas e soma os valores
            for (y in matriz_1[0].indices) {
                linha += (matriz_1[x][y] + matriz_2[x][y])
            }
            //Adiciona a linha na matriz
            matriz_S += linha
        }
        //Retorna a matriz
        return matriz_S
    }

    //Função que multiplica matrizes
    //Ela precisará já receber em formato de matrizes
    //Então ela deve primeiro passa pela fun vetorMatriz()
    fun multMatrizes(
        matriz_1: Array<Array<Double>>,
        matriz_2: Array<Array<Double>>
    ): Array<Array<Double>> {
        var matriz_M = arrayOf<Array<Double>>()

        //Cria uma matriz de 0 tendo como base a quantidade de linhas de matriz_1
        //e a quantidade de colunas em matriz_2
        for (x in matriz_2[0].indices) {
            var linha = arrayOf<Double>()

            for (y in matriz_1.indices) {
                linha += 0.0
            }
            matriz_M += linha
        }

        //Cont_l irá salvar a prox linha da matriz_2 pela qual dever ser mult pela matriz_1
        //Irá mudar a cada volta do loop z sendo assim ela multiplicada na vertical
        var cont_l = 0

        //Conta_c irá salvar a prox coluna da matriz_2 pela qual dever ser mult pela matriz_1
        //Irá mudar a cada volta do loop y indicando assim a prox coluna a ser multiplicada na vertical
        var cont_c = 0

        //Irá armazenar a multiplicação do elemento de matriz_1 com matriz_2 a cada volta em z
        var mult = 0.0

        for (x in matriz_1.indices) {
            for (y in matriz_2[0].indices) {
                //Irá armazenar a soma das multiplicações
                var soma = 0.0

                for (z in matriz_1[0].indices) {
                    mult = matriz_1[x][z] * matriz_2[cont_l][cont_c]
                    soma += mult

                    cont_l++
                }

                //Adiciona a soma no seu lugar na matriz_M
                matriz_M[x][y] = soma

                cont_l = 0
                cont_c += 1
            }
            cont_c = 0
        }

        return matriz_M
    }

    fun determinante(matriz: Array<Array<Double>>): Double {

        var matriz_D = arrayOf<Array<Double>>()
        var linha_D = arrayOf<Double>()

        for (y in matriz) {
            linha_D += y
            linha_D += y[0]
            linha_D += y[1]

            matriz_D += linha_D
            linha_D = arrayOf()
        }

        for (x in matriz_D) {
            for (y in x) {
                print("${y} ")
            }
            println()
        }
//
        var indo = arrayOf<Double>()
        var voltando = arrayOf<Double>()

        var cont = 0
        for (c in 0..2) {
            var mult = 0.0

            for (x in matriz_D.indices) {
                if (x == 0) {
                    mult = matriz_D[x][cont]
                } else {
                    mult *= matriz_D[x][cont]
                }

                cont++
            }
            cont = c + 1

            indo += mult
        }

        cont = matriz_D[0].size - 1
        var contInicial = cont

        for (c in 0..2) {
            var mult = 0.0

            for (x in matriz_D.indices) {

                if (x == 0) {
                    mult = matriz_D[x][cont]
                } else {
                    mult *= matriz_D[x][cont]
                }

                cont--
            }

            cont = contInicial - 1
            contInicial--

            voltando += mult
        }
        val ind = sum(indo[0], indo[1] + indo[2])
        val vol = sum(voltando[0], voltando[1] + voltando[2])

        return ind - vol
    }
}

