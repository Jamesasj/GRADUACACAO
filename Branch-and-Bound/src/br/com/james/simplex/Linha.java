package br.com.james.simplex;

import java.util.Arrays;

public abstract class Linha {
	private int index;
	protected float termoInd;
	protected float[] vetor;
	private int qtdVar;

	public int getQtdVar() {
		return qtdVar;
	}

	public Linha(float[] vetor) {
		this.vetor = vetor;
		termoInd = 0f;
		qtdVar = vetor.length;
	}

	public Linha(float[] vetorResticao, float termoInd2) {
		vetor = vetorResticao;
		termoInd = termoInd2;
		qtdVar = vetor.length;
	}

	public Linha(float minValue) {
		this.termoInd = minValue;
	}

	public void subtrair(Linha pivo, int posicao) {
		float multiplicador = vetor[posicao];
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] -= pivo.get(i) * multiplicador;
		}
		termoInd -= pivo.getTermoInd() * multiplicador;

	}

	public float getTermoInd() {
		return Math.abs(this.termoInd);
	}

	private float get(int pos) {
		return vetor[pos];
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public float divideVariavelIndPelaVariavel(int variavel) {
		return this.termoInd / vetor[variavel];
	}

	public void pivotar(int posicao) {
		float pivo = vetor[posicao];
		for (int i = 0; i < vetor.length; i++) {
			vetor[i] /= pivo;
		}
		termoInd /= pivo;
	}

	public void setIndice(int indice) {
		this.index = indice;
	}

	public float[] getVetor() {
		return vetor;
	}

	@Override
	public String toString() {
		return "\nLinha [index=" + index + ", termoInd=" + termoInd + ", vetor=" + Arrays.toString(vetor) + "]";
	}

	public void recalcularVariaveis(int quantidadeColunas) {
		float[] novoVetor = new float[quantidadeColunas];

		for (int i = 0; i < vetor.length; i++) {
			novoVetor[i] = this.vetor[i];
		}
		this.vetor = novoVetor;
	}

}
