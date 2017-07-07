/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.file;

import java.io.*;
import java.util.List;
import trabalho.po.estruturas.Empregado;

/**
 *
 * @author james
 */
public class WriteFile {

    final Writer arquivo;

    public WriteFile(String arquivo) throws IOException {
        OutputStream outputStream= new FileOutputStream(arquivo);
        this.arquivo = new OutputStreamWriter(outputStream,"UTF-8");
    }

    public void gravar(List<Empregado> l) throws IOException {
        for (Empregado e : l) {
            this.arquivo.write(e.toString()+'\n');
        }
        this.arquivo.close();
    }
}
