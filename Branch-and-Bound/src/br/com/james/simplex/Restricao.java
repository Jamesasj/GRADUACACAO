package br.com.james.simplex;

public class Restricao extends Linha {

	private Sinal sinal;

	public Restricao(int indice, float termoInd, float[] vetorResticao, Sinal sinal) {
		super(vetorResticao, termoInd);
		this.sinal = sinal;
	}

	public Restricao(float termoInd, float[] vetorResticao, Sinal sinal) {
		super(vetorResticao, termoInd);
		this.sinal = sinal;
	}

	public boolean ehIndice(int i) {
		return i == this.getIndex();
	}

	public Sinal getSinal() {
		return this.sinal;
	}

	public Restricao clonar() {
		float[] novov = new float[getVetor().length];
		for (int i = 0; i < novov.length; i++) {
			novov[i] = getVetor()[i];
		}
		return new Restricao(getTermoInd(), novov, getSinal());

	}

	public int criarVariaveis(int quantidadeVAriaveis) {
		float[] novoVetor;
		if (sinal == Sinal.MENOR_IGUAL) {
			novoVetor = new float[getVetor().length + quantidadeVAriaveis];
			novoVetor[novoVetor.length - 1] = sinal.getValor();
		} else {
			quantidadeVAriaveis++;
			novoVetor = new float[getVetor().length + quantidadeVAriaveis];
			novoVetor[novoVetor.length - 2] = -1;
			novoVetor[novoVetor.length - 1] = 1;
		}
		for (int i = 0; i < getVetor().length; i++) {
			novoVetor[i] = getVetor()[i];
		}
		this.vetor = novoVetor;
		return quantidadeVAriaveis;

	}

}
