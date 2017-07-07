package trabalho.po.estruturas;

public class Dados {

    private boolean inverter;
    private Empregado[] vetDados;
    private int nElem;
    private int ordem;

    public void inverter(boolean estadoInversao) {
        if (this.inverter = estadoInversao) {
            this.ordem = -1;
        } else {
            this.ordem = 1;
        }
    }

    public Dados(int tam) {
        this.nElem = 0;
        this.vetDados = new Empregado[tam];
        this.inverter = false;
        this.ordem = 1;
    }

    public boolean insere(Empregado elemento) {
        if (this.nElem == this.vetDados.length) {
            return false;
        } else {
            this.vetDados[nElem] = elemento;
            this.nElem++;
            return true;
        }
    }

    @Override
    public String toString() {
        String aux = "";
        for (int i = 0; i < this.nElem; i++) {
            aux += this.vetDados[i].toString() + "\n";
        }
        return aux;
    }

    //===============================================================================================================
    //======================================Metodos_de_pesquisa======================================================
    //===============================================================================================================
    //===============================================QuickSort=======================================================
    public void quicksortInsercao() {
        ordena(0, this.nElem - 1);
    }

    private void ordena(int esq, int dir) {
        int i = esq, j = dir;
        Empregado temp, pivo;
        pivo = this.vetDados[(i + j) / 2];
        if (dir - esq > 24 ) {
            do {
                while (this.vetDados[i].compareTo(pivo) == -this.ordem) {
                    i++;
                }
                while (this.vetDados[j].compareTo(pivo) == this.ordem) {
                    j--;
                }
                if (i <= j) {
                    temp = this.vetDados[i];
                    this.vetDados[i] = this.vetDados[j];
                    this.vetDados[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (esq < j) {
                ordena(esq, j);
            }
            if (dir > i) {
                ordena(i, dir);
            }
        } else {
            this.insercaoDireta(esq + 1, dir + 1);
        }
    }
    
    
        //===============================================QuickSort=======================================================
    public void quicksort() {
        ordena2(0, this.nElem - 1);
    }

    private void ordena2(int esq, int dir) {
        int i = esq, j = dir;
        Empregado temp, pivo;
        pivo = this.vetDados[(i + j) / 2];
            do {
                while (this.vetDados[i].compareTo(pivo) == -this.ordem) {
                    i++;
                }
                while (this.vetDados[j].compareTo(pivo) == this.ordem) {
                    j--;
                }
                if (i <= j) {
                    temp = this.vetDados[i];
                    this.vetDados[i] = this.vetDados[j];
                    this.vetDados[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (esq < j) {
                ordena(esq, j);
            }
            if (dir > i) {
                ordena(i, dir);
            }
    }

    //===========================================Insercao_Direta=====================================================
    private void insercaoDireta(int esq, int dir) {
        int i, j;
        Empregado temp;
        for (i = esq; i < dir; i++) {
            temp = this.vetDados[i];
            j = i - 1;
            while ((j >= 0) && this.vetDados[j].compareTo(temp) == this.ordem) {
                this.vetDados[j + 1] = this.vetDados[j--];
            }
            this.vetDados[j + 1] = temp;
        }
    }

    //===============================================ShellSort=======================================================
    public void shellsort() {
        int i, j, h;
        Empregado temp;
        h = 1;
        do {
            h = 3 * h + 1;
        } while (h < this.nElem);

        do {
            h = h / 3;

            for (i = h; i < this.nElem; i++) {
                temp = this.vetDados[i];
                j = i;

                while (this.vetDados[j - h].compareTo(temp) == this.ordem) {
                    this.vetDados[j] = this.vetDados[j - h];
                    j -= h;
                    if (j < h) {
                        break;
                    }
                }
                this.vetDados[j] = temp;
            }
        } while (h != 1);
    }

    //===============================================HeapSort========================================================
    public void heapSort() {
        int dir = nElem - 1;
        int esq = (dir - 1) / 2;
        Empregado temp;
        while (esq >= 0) {
            refazHeap(esq--, this.nElem - 1);
        }
        while (dir > 0) {
            temp = this.vetDados[0];
            this.vetDados[0] = this.vetDados[dir];
            this.vetDados[dir--] = temp;
            refazHeap(0, dir);
        }
    }

    private void refazHeap(int esq, int dir) {
        int i = esq;
        int MaiorFolha = 2 * i + 1;
        Empregado raiz = this.vetDados[i];
        boolean heap = false;
        while ((MaiorFolha <= dir) && (!heap)) {
            if (MaiorFolha < dir) {
                if (this.vetDados[MaiorFolha].compareTo(this.vetDados[MaiorFolha + 1]) == -this.ordem) {
                    MaiorFolha++;
                }
            }
            if (this.vetDados[MaiorFolha].compareTo(raiz) == this.ordem) {
                this.vetDados[i] = this.vetDados[MaiorFolha];
                i = MaiorFolha;
                MaiorFolha = 2 * i + 1;
            } else {
                heap = true;
            }
        }
        this.vetDados[i] = raiz;
    }

    public int getNElem() {
        return nElem;
    }

    public Empregado getEmpregado(int pos) {
        return this.vetDados[pos];
    }

    //===============================================Busca Binária========================================================
    public String buscaBinaria(Empregado valor) {
        int esq = 0;
        int dir = this.getNElem();
        int valorMeio;
        String resultado = "";
        while (esq <= dir) {
            valorMeio = esq + ((dir - esq) / 2);
            if (this.vetDados[valorMeio].compareTo(valor) == -1) {
                esq = valorMeio + 1;
            } else if (this.vetDados[valorMeio].compareTo(valor) == 1) {
                dir = valorMeio - 1;
            } else {
                int prim = this.buscaPrimeiro(valorMeio, valor);
                while (this.vetDados[prim].compareTo(valor) == 0) {
                    resultado += this.vetDados[prim].getNome() + " - " + this.vetDados[prim].getCpf() + "\n";
                    prim++;
                }
                return resultado;
            }
        }
        return valor.getNome() + " - NOME INEXISTENTE\n";
    }

    private int buscaPrimeiro(int pos, Empregado valor) {
        while (this.vetDados[pos].compareTo(valor) == 0) {
            --pos;
        }
        return ++pos;
    }
    //===============================================================================================================
}
