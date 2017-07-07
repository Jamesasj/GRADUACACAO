/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po;

import trabalho.po.estruturas.Arvore.ABB.ArvoreABB;
import trabalho.po.estruturas.Arvore.AVL.ArvoreAVL;
import trabalho.po.estruturas.Dados;
import trabalho.po.estruturas.hash.Hashing;
import trabalho.po.file.Arquivo;

/**
 *
 * @author james
 */
public class TrabalhoPO {

    /**
     * @param args the command line arguments
     */
    static int NUMARQUIVOS = 15;

    public static void main(String[] args) {
        String tempoExe = "";
        tempoExe += executarABBBalanceada();

        tempoExe += questao2();
        tempoExe += parte3();
        tempoExe += executarHashing();
        Arquivo.gravaArquivo(tempoExe, "Resultados.txt");
        System.out.print(tempoExe);
    }

    /**
     * 1) Carregar um vetor com os registros
     *
     * 2) Escolha um método de ordenação, dentre os pedidos, para ordenar os
     * registros por nome e gravando em um arquivo. Depois faça a ordenação
     * inversa e grave em outro arquivo. O nome do arquivo deve ser:
     * pessoa+quantElementos+tipo.txt (Ex. pessoa500ord.txt, pessoa1000inv.txt).
     */
    public static void ordenarNome() {
        for (int i = 0; i < NUMARQUIVOS; i += 3) {
            //Ordena
            Dados dados = Arquivo.leArquivo(i);
            dados.inverter(false);
            dados.quicksort();
            Arquivo.gravaArquivo(dados, i + 1);
            //Invete
            dados.inverter(true);
            dados.shellsort();
            Arquivo.gravaArquivo(dados, i + 2);
        }
    }

    /**
     * 3) Comece a contar o tempo.
     *
     * 4) Carregue o vetor com o arquivo de 500 elementos aleatórios.
     *
     * 5) Use o método ShellSort para ordenar este vetor, ordenando por nome e
     * por CPF e gravando o resultado em um arquivo.
     *
     * 6) Usar pesquisa binária para pesquisar 200 nomes. Estes nomes estarão em
     * um arquivo que será fornecido. Ao final, deve gerar um arquivo onde, para
     * cada nome encontrado, são gravados todos os outros dados e, se o nome não
     * for encontrado, deve-se gravar uma mensagem de NOME INEXISTENTE. Observe
     * que um nome pode aparecer mais de uma vez, então, todas as ocorrências
     * devem ser gravadas no arquivo.
     *
     * 7) Repita 4 vezes os processos de 4 a 6
     *
     * 8) Termine de contar o tempo, faça uma média e armazene este resultado.
     *
     * 9) Faça os itens de 3 a 8 para cada um dos tamanhos (500, 1000, 5000,
     * 10000 e 50000), para cada tipo de arquivo (aleatório, ordenado e
     * invertido) e para cada método de ordenação (ShellSort, QuickSort,
     * HeapSort, Quicksort com Inserção Direta). Ao todo, o programa rodará 60
     * vezes.
     */
    public static String questao2() {
        String tempoExe = "";
        tempoExe += executarQuick();
        tempoExe += executarShell();
        tempoExe += executarQuickComInsersao();
        tempoExe += executarHeap();
        return tempoExe;
    }

    public static String executarShell() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int j = 0; j < 4; j++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                dados.shellsort();
                Dados nomesPesquisa = Arquivo.leArquivo(15);

                for (int a = 0; a < nomesPesquisa.getNElem(); a++) {
                    if (a == 21) {
                        a = 21;
                    }
                    String aux = dados.buscaBinaria(nomesPesquisa.getEmpregado(a));
                    resultados += aux;
                }
                Arquivo.gravaArquivo(resultados, "(ShellSort)" + Arquivo.getNomeArquivo(i));
                long fim = System.nanoTime();
                arquivo = "(ShellSort)" + Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";
        }
        return tempoExe;

    }

    public static String executarQuickComInsersao() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int j = 0; j < 4; j++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                dados.quicksortInsercao();
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int a = 0; a < nomesPesquisa.getNElem(); a++) {
                    String aux = dados.buscaBinaria(nomesPesquisa.getEmpregado(a));
                    resultados += aux;
                }
                long fim = System.nanoTime();
                Arquivo.gravaArquivo(resultados, "(QuickSortComIsersão)" + Arquivo.getNomeArquivo(i));
                arquivo = "(QuickSortComIsersão)" + Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";

        }
        return tempoExe;
    }

    public static String executarHeap() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int j = 0; j < 4; j++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                dados.heapSort();
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int a = 0; a < nomesPesquisa.getNElem(); a++) {
                    String aux = dados.buscaBinaria(nomesPesquisa.getEmpregado(a));
                    resultados += aux;
                }
                long fim = System.nanoTime();
                Arquivo.gravaArquivo(resultados, "(HeapSort)" + Arquivo.getNomeArquivo(i));
                arquivo = "(HeapSort)" + Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";

        }
        return tempoExe;
    }

    public static String executarQuick() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int j = 0; j < 4; j++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                dados.quicksort();
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int a = 0; a < nomesPesquisa.getNElem(); a++) {
                    String aux = dados.buscaBinaria(nomesPesquisa.getEmpregado(a));
                    resultados += aux;
                }
                long fim = System.nanoTime();
                Arquivo.gravaArquivo(resultados, "(QuickSort)" + Arquivo.getNomeArquivo(i));
                arquivo = "(QuickSort)" + Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";
        }
        return tempoExe;
    }

    /**
     * 10) Comece a contar o tempo.
     *
     * 11) Carregue o arquivo de 500 elementos aleatórios, por nome em uma ABB
     * não balanceada
     *
     * 12) Faça a pesquisa, usando o 200 nomes fornecidos pela professora. Gere
     * um arquivo onde, para cada nome encontrado, são gravados todos os outros
     * dados e, se o nome não for encontrado, deve-se gravar uma mensagem de
     * NOME INEXISTENTE. Observe que um nome pode aparecer mais de uma vez,
     * então, todas as ocorrências devem ser gravadas no arquivo.
     *
     * 13) Repita 4 vezes os processos 11 e 12
     *
     * 14) Termine de contar o tempo, faça uma média e armazene este resultado.
     *
     * 15) Faça os itens de 10 a 14 para cada um dos tamanhos (500, 1000, 5000,
     * 10000 e 50000), para cada tipo de arquivo (aleatório, ordenado e
     * invertido) e para cada árvore (ABB, ABB Balanceada, AVL). Ao todo, o
     * programa rodará 45 vezes.
     *
     */
    public static String parte3() {
        String tempoExe = "";
        tempoExe += executarABB();
        tempoExe += executarABBBalanceada();
        tempoExe += executarAVL();
        return tempoExe;
    }

    public static String executarABB() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int h = 0; h < 4; h++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                ArvoreABB arvoreABB = new ArvoreABB();
                for (int j = 0; j < dados.getNElem(); j++) {
                    arvoreABB.insere(dados.getEmpregado(j));
                }
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int j = 0; j < nomesPesquisa.getNElem(); j++) {
                    String empregado = arvoreABB.pesquisa(nomesPesquisa.getEmpregado(j));
                    resultados += empregado;
                }
                long fim = System.nanoTime();
                arquivo = Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
                Arquivo.gravaArquivo(resultados, "(ABB)" + arquivo);
                arquivo = "(ABB)" + Arquivo.getNomeArquivo(i);
            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";
        }
        return tempoExe;
    }

    public static String executarABBBalanceada() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int h = 0; h < 4; h++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                ArvoreABB arvoreABB = new ArvoreABB();

                for (int j = 0; j < dados.getNElem(); j++) {
                    arvoreABB.insere(dados.getEmpregado(j));
                    if (j % 100 == 0) {
                        arvoreABB.balancear(arvoreABB.camCentral(j+1));
                    }
                }

                Dados nomesPesquisa = Arquivo.leArquivo(15);

                for (int j = 0; j < nomesPesquisa.getNElem(); j++) {
                    String empregado = arvoreABB.pesquisa(nomesPesquisa.getEmpregado(j));
                    resultados += empregado;
                }
                long fim = System.nanoTime();
                arquivo = Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
                Arquivo.gravaArquivo(resultados, "(ABB Balanceada)" + arquivo);
                arquivo = "(ABB Balanceada)" + Arquivo.getNomeArquivo(i);

            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";

        }
        return tempoExe;
    }

    public static String executarAVL() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int h = 0; h < 4; h++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                ArvoreAVL arvoreAVL = new ArvoreAVL();
                for (int j = 0; j < dados.getNElem(); j++) {
                    arvoreAVL.insere(dados.getEmpregado(j));
                }
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int j = 0; j < nomesPesquisa.getNElem(); j++) {
                    String empregado = arvoreAVL.pesquisa(nomesPesquisa.getEmpregado(j).getNome());
                    resultados += empregado;
                }
                long fim = System.nanoTime();
                arquivo = Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
                Arquivo.gravaArquivo(resultados, "(AVL )" + arquivo);
                arquivo = "(AVL )" + Arquivo.getNomeArquivo(i);

            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";
        }
        return tempoExe;
    }

    /**
     * 16) Comece a contar o tempo.
     *
     * 17) Carregue o arquivo de 500 elementos aleatórios, por nome em um
     * Hashing Encadeado
     *
     * 18) Faça a pesquisa, usando o 200 nomes fornecidos pela professora. Gere
     * um arquivo onde, para cada nome encontrado, são gravados todos os outros
     * dados e, se o nome não for encontrado, deve-se gravar uma mensagem de
     * NOME INEXISTENTE. Observe que um nome pode aparecer mais de uma vez,
     * então, todas as ocorrências devem ser gravadas no arquivo.
     *
     * 19) Repita 4 vezes os processos 17 e 18
     *
     * 20) Termine de contar o tempo, faça uma média e armazene este resultado.
     *
     * 21) Compare os tempos de todos os algoritmos em cada tamanho e tipo de
     * arquivo e gere conclusões.
     */
    public static String executarHashing() {
        String tempoExe = "";
        String arquivo = "";
        for (int i = 0; i < NUMARQUIVOS; i++) {
            double total = 0;
            for (int h = 0; h < 4; h++) {
                String resultados = "";
                long inicio = System.nanoTime();
                Dados dados = Arquivo.leArquivo(i);
                Hashing hashing = new Hashing(dados.getNElem());
                for (int j = 0; j < dados.getNElem(); j++) {
                    hashing.insere(dados.getEmpregado(j));
                }
                Dados nomesPesquisa = Arquivo.leArquivo(15);
                for (int j = 0; j < nomesPesquisa.getNElem(); j++) {
                    String empregado = hashing.pesquisa(nomesPesquisa.getEmpregado(j).getNome());
                    resultados += empregado;
                }
                long fim = System.nanoTime();
                arquivo = Arquivo.getNomeArquivo(i);
                total += (fim - inicio);
                Arquivo.gravaArquivo(resultados, "(HashingEncadeado)" + arquivo);
                arquivo = "(HashingEncadeado )" + Arquivo.getNomeArquivo(i);

            }
            total = total / 4;
            tempoExe += arquivo + " - Tempo Médio em segundos: " + (total * Math.pow(10, -9)) + " - Tempo Médio em nanosegundos: " + total + "\n";

        }
        return tempoExe;
    }
}
