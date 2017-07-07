package trabalho.po.estruturas.Arvore.AVL;

import trabalho.po.estruturas.Empregado;
import trabalho.po.estruturas.NoArv;

public class ArvoreAVL {

    private NoArv raiz;
    private static boolean H;

    public ArvoreAVL() {
        this.raiz = null;
        ArvoreAVL.H = true;
    }

    public void insere(Empregado elem) {
        this.raiz = this.insere(elem, this.raiz);
    }

    private NoArv insere(Empregado elem, NoArv no) {
        if (no == null) {
            NoArv novo = new NoArv(elem);
            ArvoreAVL.H = true;
            return novo;
        } else if (elem.getNome().compareTo(no.getInfo().getNome()) == 0) {
            no.setInfo(elem);
            ArvoreAVL.H = false;
            return no;
        } else if (elem.getNome().compareTo(no.getInfo().getNome()) < 0) {
            no.setAnt(this.insere(elem, no.getAnt()));
            no = this.balancearDir(no);
        } else if (elem.getNome().compareTo(no.getInfo().getNome()) > 0) {
            no.setProx(this.insere(elem, no.getProx()));
            no = this.balancearEsq(no);
        }
        return no;
    }

    private NoArv balancearDir(NoArv no) {
        if (ArvoreAVL.H) {
            switch (no.getFatorBalanceamento()) {
                case 1:
                    no.setFatorBalanceamento((byte) 0);
                    ArvoreAVL.H = false;
                    break;
                case 0:
                    no.setFatorBalanceamento((byte) -1);
                    break;
                case -1:
                    no = this.rotacaoDireita(no);
            }
        }
        return no;
    }

    private NoArv balancearEsq(NoArv no) {
        if (ArvoreAVL.H) {
            switch (no.getFatorBalanceamento()) {
                case -1:
                    no.setFatorBalanceamento((byte) 0);
                    ArvoreAVL.H = false;
                    break;
                case 0:
                    no.setFatorBalanceamento((byte) 1);
                    break;
                case 1:
                    no = this.rotacaoEsquerda(no);
            }
        }
        return no;
    }

    private NoArv rotacaoDireita(NoArv no) {
        NoArv temp1, temp2;
        temp1 = no.getAnt();
        if (temp1.getFatorBalanceamento() == -1) {
            no.setAnt(temp1.getProx());
            temp1.setProx(no);
            no.setFatorBalanceamento((byte) 0);
            no = temp1;
        } else {
            temp2 = temp1.getProx();
            temp1.setProx(temp2.getAnt());
            temp2.setAnt(temp1);
            no.setAnt(temp2.getProx());
            temp2.setProx(no);
            if (temp2.getFatorBalanceamento() == -1) {
                no.setFatorBalanceamento((byte) 1);
            } else {
                no.setFatorBalanceamento((byte) 0);
            }
            if (temp2.getFatorBalanceamento() == 1) {
                temp1.setFatorBalanceamento((byte) -1);
            } else {
                temp1.setFatorBalanceamento((byte) 0);
            }
            no = temp2;
        }
        no.setFatorBalanceamento((byte) 0);
        ArvoreAVL.H = false;
        return no;
    }

    private NoArv rotacaoEsquerda(NoArv no) {
        NoArv temp1, temp2;
        temp1 = no.getProx();
        if (temp1.getFatorBalanceamento() == 1) {
            no.setProx(temp1.getAnt());
            temp1.setAnt(no);
            no.setFatorBalanceamento((byte) 0);
            no = temp1;
        } else {
            temp2 = temp1.getAnt();
            temp1.setAnt(temp2.getProx());
            temp2.setProx(temp1);
            no.setProx(temp2.getAnt());
            temp2.setAnt(no);
            if (temp2.getFatorBalanceamento() == 1) {
                no.setFatorBalanceamento((byte) -1);
            } else {
                no.setFatorBalanceamento((byte) 0);
            }
            if (temp2.getFatorBalanceamento() == -1) {
                temp1.setFatorBalanceamento((byte) 1);
            } else {
                temp1.setFatorBalanceamento((byte) 0);
            }
            no = temp2;
        }
        no.setFatorBalanceamento((byte) 0);
        ArvoreAVL.H = false;
        return no;
    }

    public String pesquisa(String chave) {
        NoArv temp;
        temp = this.pesquisa(chave, this.raiz);
        if (temp != null) {
            return this.pesquisa(chave, this.raiz).toString();
        } else {
            return chave + " NOME INEXISTENTE\n";
        }
    }

    private NoArv pesquisa(String chave, NoArv no) {
        NoArv temp;
        temp = no;
        if (temp != null) {
            if (chave.compareTo(temp.getInfo().getNome()) < 0) {
                temp = this.pesquisa(chave, temp.getAnt());
            } else if (chave.compareTo(temp.getInfo().getNome()) > 0) {
                temp = this.pesquisa(chave, temp.getProx());
            }
        }
        return temp;
    }
}