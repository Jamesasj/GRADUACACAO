package trabalho.po.estruturas;


public class No {

    private Empregado info;
    private No prox, ant;
    private byte fatorBalanceamento;

    public No(Empregado info) {
        this.info = info;
    }

    public No(Empregado info, byte fatorBalanceamento) {
        this.info = info;
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public byte getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(byte fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public Empregado getInfo() {
        return this.info;
    }

    public void setInfo(Empregado info) {
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
