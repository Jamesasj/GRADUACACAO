package br.com.james.simplex;

public class FuncaoObjetivo extends Linha {

	public FuncaoObjetivo(float valorFO, float[] vetorFO) {
		super(vetorFO, valorFO);

	}

	public FuncaoObjetivo(int tamanho) {
		super(new float[tamanho]);
	}

	public FuncaoObjetivo(float[] vetor) {
		super(vetor);
	}

	public FuncaoObjetivo() {
		super(Float.MIN_VALUE);
	}

	public boolean escolherVarivel() {
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] > 0) {
				return true;
			}
		}
		return false;
	}

	public int getVariavel() {
		float maiorValor = Float.MIN_VALUE;
		int posicao = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] > maiorValor) {
				maiorValor = vetor[i];
				posicao = i;
			}
		}
		return posicao;
	}

	public void soma(Restricao restricao) {
		for (int i = 0; i < vetor.length; i++) {
			this.vetor[i] += restricao.vetor[i];
		}
		this.vetor[this.vetor.length - 1] -= 1;
		this.termoInd += restricao.getTermoInd();
	}

	public int qtdVariaveis() {
		return this.vetor.length;
	}

	public FuncaoObjetivo clonar() {
		float[] novoV = new float[getVetor().length];
		for (int i = 0; i < getVetor().length; i++) {
			novoV[i] = vetor[i];
		}
		return new FuncaoObjetivo(novoV);
	}

}
