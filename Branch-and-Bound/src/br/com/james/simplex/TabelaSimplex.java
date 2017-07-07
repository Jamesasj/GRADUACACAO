package br.com.james.simplex;

import java.util.ArrayList;
import java.util.List;

public class TabelaSimplex {
	private List<Linha> linhas;
	private List<Restricao> restricaos;
	private FuncaoObjetivo fo;
	private FuncaoObjetivo q;
	private boolean ehDual;
	private int qtdVariaveis;
	private FuncaoObjetivo fo2;

	public TabelaSimplex(FuncaoObjetivo fo) {
		this.fo = fo;
		this.fo2 = fo.clonar();
		q = new FuncaoObjetivo(fo.qtdVariaveis());
		linhas = new ArrayList<>();
		this.restricaos = new ArrayList<>();
		linhas.add(q);
		linhas.add(fo);
		qtdVariaveis = 0;
	}

	public TabelaSimplex() {
		this.fo = new FuncaoObjetivo();
	}

	public void addRestricao(Restricao restricao) {
		restricao.setIndice(linhas.size() + fo.qtdVariaveis() - 1);
		this.restricaos.add(restricao.clonar());

		qtdVariaveis = restricao.criarVariaveis(++qtdVariaveis);

		for (Linha linha : linhas) {
			linha.recalcularVariaveis(restricao.getVetor().length);
		}

		if (restricao.getSinal() == Sinal.MAIOR_IGUAL || restricao.getSinal() == Sinal.IGUAL) {
			q.soma(restricao);
			this.ehDual = true;
		}

		this.linhas.add(restricao);

	}

	public void resolver() {
		if (this.ehDual) {
			resolver(q);
		}
		resolver(fo);
	}

	private void resolver(FuncaoObjetivo funcao) {
		while (funcao.escolherVarivel()) {
			int variavel = funcao.getVariavel();
			int pivo = selecionarPivo(linhas.indexOf(funcao), variavel);
			pivotar(variavel, pivo);
			this.linhas.get(pivo).setIndice(variavel + 1);
		}
	}

	private void pivotar(int posicao, int pivo) {
		Linha xPivo = this.linhas.get(pivo);
		xPivo.pivotar(posicao);
		for (int i = 0; i < linhas.size(); i++) {
			if (i != pivo) {
				linhas.get(i).subtrair(xPivo, posicao);
			}
		}
	}

	private int selecionarPivo(int indexFO, int variavel) {
		float menorValor = Float.MAX_VALUE;
		int pivo = 0;
		for (int i = indexFO + 1; i < linhas.size(); i++) {
			float areaCalculo = linhas.get(i).divideVariavelIndPelaVariavel(variavel);
			if (i != indexFO && areaCalculo < menorValor && areaCalculo > 0) {
				menorValor = areaCalculo;
				pivo = i;
			}
		}
		return pivo;
	}

	@Override
	public String toString() {
		return "TabelaSimplex [linhas=" + linhas + "]";
	}

	public static void main(String[] args) {
//		teste1();
		// teste2();
		// teste3();
		 teste4();
		;
	}

	/** Problemas de minimizar **/
	private static void teste2() {
		TabelaSimplex TBL = new TabelaSimplex(new FuncaoObjetivo(new float[] { 4, 5, 9, 11 }));
		TBL.addRestricao(new Restricao(15, new float[] { 1, 1, 1, 1 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(120, new float[] { 7, 5, 3, 2 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(100, new float[] { 3, 5, 10, 15 }, Sinal.MENOR_IGUAL));
		TBL.resolver();
		System.out.println(TBL);
	}

	// ** Maximizar **/
	private static void teste1() {
		TabelaSimplex TBL = new TabelaSimplex(new FuncaoObjetivo(new float[] { 3, 5 }));
		TBL.addRestricao(new Restricao(4, new float[] { 1, 0 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(6, new float[] { 0, 1 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(18, new float[] { 3, 2 }, Sinal.MAIOR_IGUAL));
		TBL.resolver();
		System.out.println(TBL);
	}

	// ** Maximizar **/
	private static void teste3() {
		TabelaSimplex TBL = new TabelaSimplex(new FuncaoObjetivo(new float[] { 5, 8 }));
		TBL.addRestricao(new Restricao(6, new float[] { 1, 1 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(45, new float[] { 5, 9 }, Sinal.MENOR_IGUAL));
		TBL.resolver();
		System.out.println(TBL);
	}

	// ** Maximizar **/
	private static void teste4() {
		TabelaSimplex TBL = new TabelaSimplex(new FuncaoObjetivo(new float[] { 5, 8 }));
		TBL.addRestricao(new Restricao(6, new float[] { 1, 1 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(45, new float[] { 5, 9 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(4, new float[] { 0, 1 }, Sinal.MAIOR_IGUAL));
		TBL.resolver();
		System.out.println(TBL);
	}

	// ** Maximizar **/
	private static void teste5() {
		TabelaSimplex TBL = new TabelaSimplex(new FuncaoObjetivo(new float[] { 5, 8 }));
		TBL.addRestricao(new Restricao(6, new float[] { 1, 1 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(45, new float[] { 5, 9 }, Sinal.MENOR_IGUAL));
		TBL.addRestricao(new Restricao(4, new float[] { 0, 1 }, Sinal.MAIOR_IGUAL));
		TBL.addRestricao(new Restricao(1, new float[] { 1, 0 }, Sinal.MENOR_IGUAL));
		TBL.resolver();
		System.out.println(TBL);
	}

	public FuncaoObjetivo getFO() {
		return this.fo;
	}

	public FuncaoObjetivo getQ() {
		return this.q;
	}

	public List<Restricao> getRestricoes() {
		return this.restricaos;
	}

	public float getVariavel(int variavel) {
		for (Linha linha : linhas) {
			if (linha.getIndex() == variavel) {
				return linha.termoInd;
			}
		}
		return Float.NaN;
	}

	public int numVariaveis() {
		return fo.getQtdVar();
	}

	public FuncaoObjetivo getFo2() {
		return fo2;
	}

}
