/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.estruturas;

import java.awt.event.ItemEvent;

/**
 *
 * @author james
 */
public class Empregado {

    private String nome;
    private String cpf;
    private String cargo;

    public Empregado(String nome, String cpf, String cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Empregado() {
        this.nome = null;
        this.cpf = null;
        this.cargo = null;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome + ";" + this.cpf + ";" + this.cargo;
    }

    public int compareTo(Empregado temp) {
        int i = this.nome.compareTo(temp.getNome());
        if (this.nome.compareTo(temp.getNome()) <= -1) {
            return -1;
        } else {
            if (this.nome.compareTo(temp.getNome()) == 0) {
                if ((temp.getCpf() != null)&&(this.cpf!=null)) {
                    if (this.cpf.compareTo(temp.getCpf()) == -1) {
                        return -1;
                    } else {
                        if (this.cpf.compareTo(temp.getCpf()) == 1) {
                            return 1;
                        }
                    }
                }
                return 0;
            }
        }

        return 1;
    }
}