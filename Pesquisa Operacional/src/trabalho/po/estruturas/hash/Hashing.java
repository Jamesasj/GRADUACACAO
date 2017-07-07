package trabalho.po.estruturas.hash;

import trabalho.po.estruturas.Empregado;
import trabalho.po.util.List;

public class Hashing {

    private List[] tabela;

    public Hashing(int n) {
        this.tabela = new List[this.geraPrimo((int) ((n / 2) * 1.1))];
        for (int i = 0; i < this.tabela.length; i++) {
            this.tabela[i] = new List();
        }
    }

    public void insere(Empregado elem) {
        this.tabela[this.hashingCode(elem.getNome())].insereUltimo(elem);
    }

    private int hashingCode(String chave) {
        int i;
        long soma = 0;
        for (i = 0; i < chave.length(); i++) {
            soma += chave.charAt(i) * Math.pow(-7, i);
        }
        soma = (long) Math.sqrt(Math.pow(soma, 2)) % this.tabela.length;
        return (int) soma;
    }

    private int geraPrimo(int num) {
        boolean primo = false;
        int i, aux = num;
        while (!primo) {
            primo = true;
            for (i = 2; (i <= Math.sqrt(aux)) && primo; i++) {
                if (aux % i == 0) {
                    primo = false;
                }
            }
            if (!primo) {
                aux++;
            }
        }
        return aux;
    }

    public String pesquisa(String chave) {
        String aux = this.tabela[this.hashingCode(chave)].pesqSeqString(chave);
        if (aux.equals("")) {
            return chave + " - NOME INEXISTENTE\n";
        } else {
            return aux;
        }
    }
}
