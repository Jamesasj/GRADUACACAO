package extras;

public class FluxoMaximo {
	static int[][] M = { { 0, 15, 20, 10, 0, 0, 0 }, { 0, 0, 0, 0, 2, 3, 0 }, { 0, 0, 0, 0, 4, 5, 15 },
			{ 0, 0, 0, 0, 5, 4, 0 }, { 0, 0, 0, 0, 0, 0, 15 }, { 0, 0, 0, 0, 0, 0, 10 }, { 0, 0, 0, 0, 0, 0, 0 } };

	static int qtdCa = 0;
	static int K;

	public static void main(String[] args) {

		fluxoM();

	}

	private static void fluxoM() {
		int[][] caminhos = new int[M.length][M.length];
		int origem = 0;
		int destino = 6;
		do {
			int pesos[] = avaliaEntradasESaidas(origem, destino);
			imprimirVetor(pesos);
			
			int pos = selecionarMenorPeso(pesos);
			
			fluirCaminho(pos, caminhos, origem, destino, pesos[pos]);
			
		} while (existeCaminho(origem, destino, avaliaEntradasESaidas(origem, destino)));

		System.out.print("\n\n Matriz");
		imprimirMatriz(M);

		System.out.print("\n\n Caminhos");
		imprimirMatriz(caminhos);
	}

	private static boolean existeCaminho(int origem, int destino, int[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
			if (i != origem && i != destino && vetor[i] > 0) {
				return true;
			}
		}
		return false;
	}

	static int k;

	private static boolean fluirCaminho(int pos, int[][] caminhos, int origem, int destino, int peso) {
		do {
			k = 0;
			if (!dijkstra(origem, pos, caminhos[qtdCa], origem) || !dijkstra(pos, destino, caminhos[qtdCa], k)) {
				peso -= peso;
				removeArestas(pos);
			} else {
				int menorPeso = retornaMenor(caminhos[qtdCa], origem, destino);
				removerUsados(caminhos[qtdCa], menorPeso, destino);
				peso -= menorPeso;
				qtdCa++;
			}
		} while (peso != 0);
		return true;

	}

	private static void removeArestas(int pos) {
		for (int i = 0; i < M.length; i++) {
			M[pos][i] = 0;
		}
	}

	private static void imprimirMatriz(int[][] m2) {
		for (int i = 0; i < m2.length; i++) {
			imprimirVetor(m2[i]);
		}

	}

	private static void removerUsados(int[] is, int menorPeso, int destino) {
		for (int i = 0; i < is.length - 1; i++) {
			if (is[i] != destino) {
				int x = is[i];
				int y = is[i + 1];
				M[x][y] -= menorPeso;
			} else {
				return;
			}
		}
	}

	private static int retornaMenor(int[] is, int origem, int destino) {
		int menor = Integer.MAX_VALUE;
		for (int i = 0; i < is.length - 1; i++) {
			if (is[i] != destino) {
				int x = is[i];
				int y = is[i + 1];
				if (is[i + 1] > 0)
					menor = menor > M[x][y] ? M[x][y] : menor;
			} else {
				return menor;
			}
		}
		return menor;
	}

	private static boolean dijkstra(int ori, int des, int[] caminho, int j) {
		if (ori != des) {
			for (int i = 0; i < M.length; i++) {
				if (M[ori][i] > 0) {
					if (dijkstra(i, des, caminho, j + 1)) {
						caminho[j] = ori;
						return true;
					}
				}
			}
			return false;
		} else {
			caminho[j] = ori;
			k = j;
			return true;
		}

	}

	private static int selecionarMenorPeso(int[] pesos) {
		int pos = 0;
		for (int i = 0; i < pesos.length; i++) {
			if (pesos[i] > 0) {
				pos = (pesos[pos] < pesos[i]) ? pos : i;
			}
		}
		return pos;
	}

	private static int[] avaliaEntradasESaidas(int origem, int destino) {
		int[] v = new int[M.length];

		v[origem] = somaLinhas(origem);
		v[destino] = somaColunas(destino);

		for (int i = 0; i < M.length; i++) {
			if (i != origem && i != destino) {
				int sEntradas = somaColunas(i);
				int sSaidas = somaLinhas(i);
				v[i] = sEntradas < sSaidas ? sEntradas : sSaidas;
			}
		}
		return v;
	}

	private static int somaLinhas(int i) {
		int aux = 0;
		for (int j = 0; j < M.length; j++) {
			aux += M[i][j];
		}
		return aux;
	}

	private static int somaColunas(int i) {
		int aux = 0;
		for (int j = 0; j < M.length; j++) {
			aux += M[j][i];
		}
		return aux;
	}

	private static void imprimirVetor(int[] vetor) {
		System.out.println("");
		for (int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i] + " ");
		}
	}
}
