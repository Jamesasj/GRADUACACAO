package br.com.james.branchAndBound;

import java.util.ArrayList;
import java.util.List;

import br.com.james.simplex.FuncaoObjetivo;
import br.com.james.simplex.Restricao;
import br.com.james.simplex.Sinal;
import br.com.james.simplex.TabelaSimplex;

public class BranchAndBound {

	private No raiz;
	private No melhorNo;
	private List<No> arvore;

	public BranchAndBound(TabelaSimplex tabela) {
		this.raiz = new No(tabela);
		this.arvore = new ArrayList<No>();
		arvore.add(this.raiz);
	}

	public void executar() {
		this.raiz.resolverNo();
		if (!this.raiz.ehSolucao()) {
			dividirNo(this.raiz);
		}
	}

	private void executar(No raiz) {
		if (raiz.comparar(this.raiz) == 1) {
			return;
		}
		if (raiz.ehSolucao()) {
			melhorNo = melhorNo == null || raiz.comparar(melhorNo) == 1 ? raiz : melhorNo;
			return;
		}

		dividirNo(raiz);
	}

	private void dividirNo(No raiz) {
		No direita = raiz.direita();
		direita.resolverNo();
		this.arvore.add(direita);

		if (direita.ehSolucao()) {
			melhorNo = melhorNo == null || direita.comparar(melhorNo) == 1 ? raiz : melhorNo;
		}

		No esquerda = raiz.esquerda();
		esquerda.resolverNo();
		this.arvore.add(esquerda);

		if (esquerda.ehSolucao()) {
			melhorNo = melhorNo == null || esquerda.comparar(melhorNo) == 1 ? raiz : melhorNo;
		}

		System.out.println("\n ");
		if (esquerda.comparar(direita) == 1 && !esquerda.ehSolucao()) {
			executar(esquerda);
			executar(direita);
		} else {
			executar(direita);
			executar(esquerda);
		}
	}

	// MAXIMIZAR
	public static void main(String[] args) {
		 No.EXIBIR_TABELA_RESULTADO = true;
		// No.EXIBIR_TABELA_INICAL = true;
		// // No.EXIBIR_NO = true;
		No.EXIBIR_INFORMACOES_NO = true;
		TabelaSimplex tabela = new TabelaSimplex(new FuncaoObjetivo(new float[] { 5, 8 }));
		tabela.addRestricao(new Restricao(6, new float[] { 1, 1 }, Sinal.MENOR_IGUAL));
		tabela.addRestricao(new Restricao(45, new float[] { 5, 9 }, Sinal.MENOR_IGUAL));
		BranchAndBound BB = new BranchAndBound(tabela);
		BB.executar();
	}

}
