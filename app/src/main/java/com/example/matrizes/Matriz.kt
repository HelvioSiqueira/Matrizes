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

    //Transforma uma matriz em um vetor novamente
    fun matrizVetor(matriz: Array<Array<Double>>): Array<Double>{

        var vetor = arrayOf<Double>()

        for(linha in matriz){
            vetor += linha
        }

        return vetor
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

    //Função que calcula o determinante de uma matriz de ordem até 3
    //Ela precisará já receber em formato de matrizes
    //Então ela deve primeiro passa pela fun vetorMatriz()
    fun determinante(matriz: Array<Array<Double>>): Double {

        //Adiciona as duas colunas que seram necessarias na formula de Sarras
        val matriz_D = addColunas(matriz)

        //Vetores que irão armazenas as operações na diagonal indo e voltando na matriz
        var indo = arrayOf<Double>()
        var voltando = arrayOf<Double>()

        //Cont irá indicar a prox posição de coluna na diagonal(x também mudará a cada loop)
        var cont = 0

        //00 * * * *    * 01 * * *     * * 02 * *
        //* 11 * * *    * * 12 * *     * * * 13 *
        //* * 22 * *    * * * 23 *     * * * * 24

        //c irá rodar 3 vezes para obter o resultado nas 3 diagonais indo
        for (c in 0..2) {
            //Irá armazenar a multiplicação da diagonal
            var mult = 0.0

            //x será as linhas
            for (x in matriz_D.indices) {
                //Se for a primeira linha mult receberá direto o elemento de matriz_D[x][cont]
                //(Se não for assim o elemento será multiplicado por 0 e o resultado será sempre 0)
                if (x == 0) {
                    mult = matriz_D[x][cont]
                } else {
                    mult *= matriz_D[x][cont]
                }

                //Incrementa cont para indicar a prox coluna na linha de baixo e assim vai
                cont++
            }
            //Será somado +1 e o c atual(na primeira vez o valor atribuido a cont será 1 já c ainda é 0)
            //E indicará a coluna que cont irá começar a contar no prox loop de c
            cont = c + 1

            //Adiciona mult no array
            indo += mult
        }

        //cont agora receberá o indice do ultimo elemento em qualquer linha
        //Como agora as operações serão feitas na 3 diagonais voltando cont será decrementado
        cont = matriz_D[0].size - 1

        //contInicial irá salvar a posição inicial de cont
        //Ele servirá pra reiniciar o valor de cont(Já que ele será decrementado no loop x)
        //Quando ele for reiniciado(contInicial--) ele representará a prox coluna inicial no loop x
        var contInicial = cont

        //* * * * 04    * * * 03 *     * * 02 * *
        //* * * 13 *    * * 12 * *     * 11 * * *
        //* * 22 * *    * 21 * * *     20 * * * *

        //c irá rodar 3 vezes para obter o resultado nas 3 diagonais voltando
        for (c in 0..2) {
            //Irá armazenar a multiplicação da diagonal
            var mult = 0.0

            //x será as linhas
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
        val ind = indo[0] + indo[1] + indo[2]
        val vol = voltando[0] + voltando[1] + voltando[2]

        return ind - vol
    }

    //Função que adiciona as colunas necessarias(0 e 1) para formula de Sarras
    fun addColunas(matriz: Array<Array<Double>>): Array<Array<Double>> {

        var matriz_D = arrayOf<Array<Double>>()
        var linha = arrayOf<Double>()

        for (y in matriz) {

            //Add a linha da matriz
            linha += y

            //Add o primeiro elemento a linha já add
            linha += y[0]

            //Add o segundo elemento a linha já addp
            linha += y[1]

            //Adiciona a linha à matriz
            matriz_D += linha

            //Reinicia o array
            linha = arrayOf()
        }

        return matriz_D
    }
}

