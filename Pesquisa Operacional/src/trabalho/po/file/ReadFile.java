/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.po.file;

import java.io.*;
import java.text.ParseException;
import trabalho.po.estruturas.Empregado;
import trabalho.po.estruturas.No;
import trabalho.po.util.List;

/**
 *
 * @author james
 */
public class ReadFile {

    private BufferedReader arquivo;

    public ReadFile(String arquivo) throws FileNotFoundException, UnsupportedEncodingException {
        File fileDir = new File(arquivo);
        this.arquivo = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "ISO8859-1"));
    }

    public List ler() throws ParseException, IOException {
        List l = new List();
        String linha;

        while ((linha = arquivo.readLine()) != null) {
            Empregado e = new Empregado();
            String[] empregadoTexto = linha.split(";");
            e.setNome(empregadoTexto[0]);
            e.setCpf(empregadoTexto[1]);
            e.setCargo(empregadoTexto[2]);
            l.inserePrimeiro(e);
        }
        this.arquivo.close();
        return l;
    }
}