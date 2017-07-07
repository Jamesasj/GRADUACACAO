package trabalho.po;

import java.util.*;
import java.io.*;
import trabalho.po.estruturas.Dados;
import trabalho.po.estruturas.Empregado;

public class Ordencao {

    static final int QTD_VEZES = 4;
    static final int N_ARQUIVOS = 3;// de 1 � x; x>0 && x<13

    /*
     * ==Arquivos Teste==
     *
     * 1 -> carro1000alea.txt 2 -> carro1000inv.txt 3 -> carro1000ord.txt 4 ->
     * carro5000alea.txt 5 -> carro5000inv.txt 6 -> carro5000ord.txt 7 ->
     * carro10000alea.txt 8 -> carro10000inv.txt 9 -> carro10000ord.txt 10 ->
     * carro50000alea.txt 11-> carro50000inv.txt 12-> carro50000ord.txt
     */
    public static void main(String args[]) {
        StringBuffer relatorio = new StringBuffer();


        for (int i = 0; i < QTD_VEZES; i++) {
            relatorio.append((i + 1) + "� execuaoo do programa: \n\n");
            relatorio.append(pesquisaShellSort());
            relatorio.append(pesquisaQuickSort());
            relatorio.append(pesquisaHeapSort());
            relatorio.append("\n\n=============================================================================\n\n");
        }


        gravaArquivo(relatorio, "Relatorio Final.txt");


    }

    //=======================================================
    //=======================Metodos=========================
    //=======================================================
    static StringBuffer pesquisaShellSort() {
        Dados registros;
        StringBuffer relatorio = new StringBuffer();
        long inicio, fim;

        for (int i = 0; i < N_ARQUIVOS; i++) {

            //Ordenaaoo usando o Shellsort
            inicio = System.nanoTime();
            registros = leArquivo(i);
            registros.shellsort();
            fim = System.nanoTime();

            relatorio.append("ShellSort/ " + getNomeArquivo(i) + "\nTempo de leitura: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n");

            inicio = System.nanoTime();
            registros.shellsort();
            fim = System.nanoTime();

            relatorio.append("Tempo de ordenaaoo: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n\n");

            gravaArquivo(registros, "(ShellSort)" + getNomeArquivo(i));

        }

        return relatorio;
    }

    static StringBuffer pesquisaQuickSort() {
        Dados registros;
        StringBuffer relatorio = new StringBuffer();
        long inicio, fim;

        for (int i = 0; i < N_ARQUIVOS; i++) {

            inicio = System.nanoTime();
            registros = leArquivo(i);
            fim = System.nanoTime();

            relatorio.append("QuickSort/ " + getNomeArquivo(i) + "\nTempo de leitura: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n");

            inicio = System.nanoTime();
            registros.shellsort();
            fim = System.nanoTime();

            relatorio.append("Tempo de ordenaaoo: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n\n");

            gravaArquivo(registros, "(QuickSort)" + getNomeArquivo(i));
        }

        return relatorio;
    }

    static StringBuffer pesquisaHeapSort() {
        Dados registros;
        StringBuffer relatorio = new StringBuffer();
        long inicio, fim;

        for (int i = 0; i < N_ARQUIVOS; i++) {
            inicio = System.nanoTime();
            registros = leArquivo(i);
            fim = System.nanoTime();

            relatorio.append("HeapSort/ " + getNomeArquivo(i) + "\nTempo de leitura: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n");

            inicio = System.nanoTime();
            registros.shellsort();
            fim = System.nanoTime();

            relatorio.append("Tempo de ordenaaoo: ( Em milisegundos: "
                    + (fim - inicio) / 1000000 + " ) ( Em nanosegundos: " + (fim - inicio) + " )\n\n");

            gravaArquivo(registros, "(HeapSort)" + getNomeArquivo(i));
        }

        return relatorio;

    }

    static Dados leArquivo(int i) {
        int tam;
        Dados registros;
        Empregado elemento;
        String nome, cargo;
        String cpf;
        BufferedReader arquivo;
        StringTokenizer aux;
        tam = tamArquivo(i);
        registros = new Dados(tam);
        try {
            File fileDir = new File(getNomeArquivo(i));
            arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "ISO8859-1"));
            System.out.println(arquivo.toString());
            for (int j = 0; j < tam; j++) {
                aux = new StringTokenizer(arquivo.readLine(), ";");
                nome = aux.nextToken();
                cargo = aux.nextToken();
                cpf = aux.nextToken();
                elemento = new Empregado(nome, cpf, cargo);
                registros.insere(elemento);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return registros;

    }

    static void gravaArquivo(Object informaaoo, String nome) {
        BufferedWriter arquivo;
        try {
            arquivo = new BufferedWriter(new FileWriter(nome, false));
            arquivo.write(informaaoo.toString());
            arquivo.flush();
            arquivo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static int tamArquivo(int i) {
        if (i < 3) {
            return 500;
        } else {
            if (i > 2 && i < 6) {
                return 5000;
            } else {
                if (i > 5 && i < 9) {
                    return 10000;
                } else {
                    return 50000;
                }
            }
        }
    }

    static String getNomeArquivo(int i) {
        String arquivos[] = {"500alea", "500inv", "500ord",
            "5000alea", "5000inv", "5000ord",
            "10000alea", "10000inv", "10000ord",
            "50000alea", "50000inv", "50000ord"};
        return "pessoa" + arquivos[i] + ".txt";

    }
   
}
/*
 * , "1000ord", "5000alea", "5000inv", "5000ord", "10000alea", "10000inv",
 * "10000ord", "50000alea", "50000inv", "50000ord"
 */
