package br.com.james.branchAndBound;

import java.util.ArrayList;
import java.util.List;

import br.com.james.simplex.FuncaoObjetivo;
import br.com.james.simplex.Restricao;
import br.com.james.simplex.Sinal;
import br.com.james.simplex.TabelaSimplex;

public class No {
	public static boolean EXIBIR_INFORMACOES_NO = false;
	public static boolean EXIBIR_NO = false;
	public static boolean EXIBIR_TABELA_INICAL = false;
	public static boolean EXIBIR_TABELA_RESULTADO = false;
	private TabelaSimplex tabela;
	private List<Restricao> restricoes;
	private FuncaoObjetivo fo;
	private int numVar;
	private int variavelSelecionada;

	public No(TabelaSimplex tabela) {
		this.restricoes = new ArrayList<Restricao>();
		this.tabela = tabela;
		arazenarTabela(tabela);
		this.numVar = tabela.numVariaveis();
		this.variavelSelecionada = this.numVar - 1;
	}

	public No(TabelaSimplex novaTabela, int variavelSelecionada) {
		this.restricoes = new ArrayList<Restricao>();
		this.tabela = novaTabela;
		arazenarTabela(novaTabela);
		this.numVar = novaTabela.numVariaveis();
		this.variavelSelecionada = variavelSelecionada;
		if (No.EXIBIR_TABELA_INICAL)
			System.out.println(tabela);

	}

	public void resolverNo() {
		this.tabela.resolver();
		if (No.EXIBIR_TABELA_RESULTADO)
			System.out.println(tabela);
	}

	public boolean ehSolucao() {
		return validarInteiro(tabela.getVariavel(1)) && validarInteiro(tabela.getVariavel(2));
	}

	public boolean ehInviavel() {
		return !ehSolucao();
	}

	private boolean validarInteiro(float valorVariavel) {
		int valor = (int) valorVariavel;
		boolean retorno = (valorVariavel / valor) == 1f;
		return retorno;
	}

	public int comparar(No noCompara) {
		if (this.getTermoInd() > noCompara.getTermoInd())
			return 1;
		if (this.getTermoInd() < noCompara.getTermoInd())
			return -1;
		return 0;
	}

	public float getTermoInd() {
		return this.tabela.getFO().getTermoInd();
	}

	public No esquerda() {
		TabelaSimplex novaTabela = copiarTabela();
		float novoTermo = 1 + (int) tabela.getVariavel(variavelSelecionada + 1);
		float[] novoVetor = new float[numVar];
		novoVetor[variavelSelecionada] = 1;
		int proximaVariavel = calcProxvariavel();
		Restricao novaRestricao = new Restricao(novoTermo, novoVetor, Sinal.MAIOR_IGUAL);
		novaTabela.addRestricao(novaRestricao);
		No novoNo = new No(novaTabela, proximaVariavel);
		mostrar("ESQUERDA", novoTermo);
		return novoNo;
	}

	private int calcProxvariavel() {
		return (variavelSelecionada + 1) % numVar;
	}

	public No direita() {
		TabelaSimplex novaTabela = copiarTabela();
		float novoTermo = (int) tabela.getVariavel(variavelSelecionada + 1);
		float[] novoVetor = new float[numVar];
		novoVetor[variavelSelecionada] = 1;
		int proximaVariavel = calcProxvariavel();
		Restricao novaRestricao = new Restricao(novoTermo, novoVetor, Sinal.MENOR_IGUAL);
		novaTabela.addRestricao(novaRestricao);
		No novoNo = new No(novaTabela, proximaVariavel);
		mostrar("DIREITA", novoTermo);
		return novoNo;
	}

	private void arazenarTabela(TabelaSimplex tabela) {
		FuncaoObjetivo fo = tabela.getFo2().clonar();
		for (Restricao restricao : tabela.getRestricoes()) {
			Restricao novaRestricao = restricao.clonar();
			this.restricoes.add(novaRestricao);
		}
		this.fo = new FuncaoObjetivo(fo.getVetor());
	}

	private TabelaSimplex copiarTabela() {
		TabelaSimplex novaTabela = new TabelaSimplex(this.fo.clonar());
		for (Restricao restricao : restricoes) {
			novaTabela.addRestricao(restricao.clonar());
		}
		return novaTabela;
	}

	private void mostrar(String string, float numero) {
		if (No.EXIBIR_INFORMACOES_NO) {
			System.out.println("No [Posicao = " + string + "  |  Novo Valor = " + numero + " |  Variavel selecionado = "
					+ variavelSelecionada + "]");
		}
	}

	@Override
	public String toString() {
		return "No [numVar=" + numVar + ", variavelSelecionada=" + variavelSelecionada + "]";
	}

}
