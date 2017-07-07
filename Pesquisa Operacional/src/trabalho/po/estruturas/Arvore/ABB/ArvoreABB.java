package trabalho.po.estruturas.Arvore.ABB;

import trabalho.po.estruturas.Dados;
import trabalho.po.estruturas.Empregado;
import trabalho.po.estruturas.NoArv;
import trabalho.po.util.List;

public class ArvoreABB {

    private NoArv raiz;

    public ArvoreABB() {
        this.raiz = null;
    }

    public boolean insere(Empregado elem) {
        this.raiz = this.insere(elem, this.raiz);
        return true;
    }

    private NoArv insere(Empregado elem, NoArv no) {
        NoArv novo;
        if (no == null) {
            novo = new NoArv(elem);
            return novo;
        } else if (elem.getNome().equals(no.getInfo().getNome())) {
            no.setInfo(elem);
            return no;
        } else if (elem.getNome().compareTo(no.getInfo().getNome()) < 0) {
            no.setAnt(this.insere(elem, no.getAnt()));
            return no;
        } else if (elem.getNome().compareTo(no.getInfo().getNome()) > 0) {
            no.setProx(this.insere(elem, no.getProx()));
            return no;
        } else {
            return no;
        }
    }

    public Dados camCentral(int tam) {
        Dados vetOrdenado=new Dados(tam);
        return this.fazCamCentral(this.raiz, vetOrdenado);
    }

    private Dados fazCamCentral(NoArv arv, Dados vetOrdenado) {
        if (arv != null) {
            vetOrdenado = this.fazCamCentral(arv.getAnt(), vetOrdenado);
            vetOrdenado = lerList(arv, vetOrdenado);
            vetOrdenado = this.fazCamCentral(arv.getProx(), vetOrdenado);
        }
        return vetOrdenado;
    }

    private Dados lerList(NoArv arv, Dados vetOrdenado) {
        List aux = arv.getList();
        int i = 0;
        Empregado e = aux.pesqSeq(i);
        while (e != null) {
            vetOrdenado.insere(e);
            i++;
            e = aux.pesqSeq(i);
        }
        return vetOrdenado;
    }

    public void balancear(Dados vetOrdenado) {
        ArvoreABB temp = new ArvoreABB();
        this.balancear(vetOrdenado, temp, 0, vetOrdenado.getNElem() - 1);
        this.raiz = temp.raiz;
    }

    private void balancear(Dados vetOrdenado, ArvoreABB temp, int inic, int fim) {
        int meio;
        if (fim >= inic) {
            meio = (inic + fim) / 2;
            if (vetOrdenado.getEmpregado(meio).getNome().equals("LIDIA ROMÃO")) {
                int i = 0;
            }
            temp.insere(vetOrdenado.getEmpregado(meio));
            this.balancear(vetOrdenado, temp, inic, meio - 1);
            this.balancear(vetOrdenado, temp, meio + 1, fim);
        }
    }

    public boolean pesquisa(String chave) {
        NoArv temp;
        temp = this.pesquisa(chave, this.raiz);
        if (temp != null) {
            return true;
        } else {
            return false;
        }
    }

    public String pesquisa(Empregado chave) {
        NoArv temp;
        temp = this.pesquisa(chave.getNome(), this.raiz);

        if (temp != null) {
            return temp.toString();
        } else {
            return chave.getNome() + " - NOME INEXISTENTE\n";
        }
    }

    private NoArv pesquisa(String chave, NoArv no) {
        NoArv temp;
        temp = no;
        if (temp != null) {
            if (chave.compareTo(temp.getInfo().getNome()) < 0) {
                temp = this.pesquisa(chave, temp.getAnt());
            } else {
                if (chave.compareTo(temp.getInfo().getNome()) > 0) {
                    temp = this.pesquisa(chave, temp.getProx());
                }
            }
        }
        return temp;
    }
}
