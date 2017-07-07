package trabalho.po.util;

import trabalho.po.estruturas.Empregado;
import trabalho.po.estruturas.No;

public class List {

    private No prim;
    private No ult;
    private int quantNos;

    public List() {
        this.prim = null;
        this.ult = null;
        this.quantNos = 0;
    }

    public int getQuantNos() {
        return this.quantNos;
    }

    public No getPrim() {
        return this.prim;
    }

    public No getUlt() {
        return this.ult;
    }

    public boolean eVazia() {
        return (this.prim == null);
    }

    public void inserePrimeiro(Empregado elem) {
        No novoNo = new No(elem);
        if (this.eVazia()) {
            this.ult = novoNo;
        }
        novoNo.setProx(this.prim);
        this.prim = novoNo;
        this.quantNos++;
    }

    public void insereUltimo(Empregado elem) {
        No novoNo = new No(elem);
        if (this.eVazia()) {
            this.prim = novoNo;
        } else {
            this.ult.setProx(novoNo);
        }
        this.ult = novoNo;
        this.quantNos++;
    }

    public Empregado pesqSeq(int pos) {
        No atual = this.prim;
        int i = 0;
        while (atual != null) {
            if (i == pos) {
                return atual.getInfo();
            } else {
                atual = atual.getProx();
            }
            i++;
        }
        return null;
    }

    public Empregado pesqSeq(String chave) {
        No atual = this.prim;
        while ((atual != null) && (!atual.getInfo().getNome().equals(chave))) {
            atual = atual.getProx();
        }
        if (atual == null) {
            return null;
        } else {
            return atual.getInfo();
        }
    }

    public String pesqSeqString(String chave) {
        No atual = this.prim;
        String aux = "";
        while (atual != null) {
            if (atual.getInfo().getNome().equals(chave)) {
                aux += atual.getInfo().getNome() + " - " + atual.getInfo().getCpf() + "\n";
            }
            atual = atual.getProx();
        }
        return aux;
    }

    @Override
    public String toString() {
        String msg = "";
        No atual = this.prim;
        while (atual != null) {
            msg += atual.getInfo().getNome() + " - " + atual.getInfo().getCpf() + "\n";
            atual = atual.getProx();
        }
        return msg;
    }
}
