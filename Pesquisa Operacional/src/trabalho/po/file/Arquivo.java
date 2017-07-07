/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.file;

import java.io.*;
import java.util.StringTokenizer;
import trabalho.po.estruturas.Dados;
import trabalho.po.estruturas.Empregado;

/**
 *
 * @author james
 */
public class Arquivo {

    public static Dados leArquivo(int i) {
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
            if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12||i==15) {
                arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "ISO8859-1"));
            } else {
                System.out.println("i = " + i);
                arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir)));
            }
            System.out.println("fileDir = " + fileDir);
            for (int j = 0; j < tam; j++) {
                if (i < 15) {
                    aux = new StringTokenizer(arquivo.readLine(), ";");
                    nome = aux.nextToken();
                    cpf = aux.nextToken();
                    cargo = aux.nextToken();
                    elemento = new Empregado(nome, cpf, cargo);
                } else {
                    nome = arquivo.readLine();
                    elemento = new Empregado(nome, null, null);
                }
                registros.insere(elemento);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return registros;
    }

    public static void gravaArquivo(Object informaaoo, int pos) { //String nome) {
        String nome = getNomeArquivo(pos);
        gravaArquivo(informaaoo, nome);
    }

    public static void gravaArquivo(Object informaaoo, String nome) {
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

    public static int tamArquivo(int i) {
        if (i < 3) {
            return 500;
        } else {
            if (i > 2 && i < 6) {
                return 1000;
            } else {
                if (i > 5 && i < 9) {
                    return 5000;
                } else {
                    if (i > 8 && i < 12) {
                        return 10000;
                    } else {
                        if (i == 15) {
                            return 200;
                        } else {
                            return 50000;
                        }
                    }
                }
            }
        }
    }

    public static String getNomeArquivo(int i) {
        String arquivos[] = {"500alea", "500ord", "500inv",
            "1000alea", "1000ord", "1000inv",
            "5000alea", "5000ord", "5000inv",
            "10000alea", "10000ord", "10000inv",
            "50000alea", "50000ord", "50000inv",
            "nomes"};
        return ((i < 15) ? "pessoa" + arquivos[i] : arquivos[i]) + ".txt";

    }
}
