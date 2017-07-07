package br.com.james.simplex;

public enum Sinal {
	MENOR_IGUAL(1), MAIOR_IGUAL(-1), IGUAL(0);

	private final int valor;

	public int getValor() {
		return valor;
	}

	private Sinal(int valor) {
		this.valor = valor;
	}

}
