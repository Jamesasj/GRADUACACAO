package trabalho.po.estruturas;

import trabalho.po.util.List;

public class NoArv {

    private List info;
    private NoArv prox, ant;
    private byte fatorBalanceamento;

    public NoArv(Empregado info) {
        this.info=new List();
        this.info.inserePrimeiro(info);
    }

    public NoArv(Empregado info, byte fatorBalanceamento) {
        this.info.inserePrimeiro(info);
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public NoArv getAnt() {
        return ant;
    }

    public void setAnt(NoArv ant) {
        this.ant = ant;
    }

    public byte getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public void setFatorBalanceamento(byte fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public Empregado getInfo(Empregado info) {
        return this.info.pesqSeq(info.getNome());
    }

    public void setInfo(Empregado info) {
        this.info.insereUltimo(info);
    }

    public NoArv getProx() {
        return prox;
    }

    public void setProx(NoArv prox) {
        this.prox = prox;
    }
    public List getList(){
        return this.info;
    }
    public Empregado getInfo() {
        return this.info.getPrim().getInfo();
    }
    
    @Override
    public String toString() {
        return this.info.toString() ;
    }
    
}
