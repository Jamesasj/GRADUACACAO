public class Exercicio103 {
	private static int n = 7;
	public static int tam = (int) Math.pow(2, n);
	public static int[][] _M1 = new int[tam][tam];
	public static int count = 1;
	public static int meio = 0;

	public static void main(String[] args) {
		int x = tam / 2;
		int y = tam / 2;
		meio = tam / 4;
		preencher(x - 1, y - 1, 0);
		quadrante(x - meio, y - meio, 0, meio);
		quadrante(x + meio, y - meio, 1, meio);
		quadrante(x - meio, y + meio, 2, meio);
		quadrante(x + meio, y + meio, 3, meio);

		for (int i = 0; i < _M1.length; i += 2) {
			for (int j = 0; j < _M1.length; j += 2) {
				if (_M1[i][j] != 0) {
					preencher(i, j, 3);
				} else if (_M1[i + 1][j] != 0) {
					preencher(i, j, 2);
				} else if (_M1[i][j + 1] != 0) {
					preencher(i, j, 1);
				} else if (_M1[i + 1][j + 1] != 0) {
					preencher(i, j, 0);
				} else {
					preencher(i, j, 3);
				}
			}
		}
		imprimir();
	}

	private static void quadrante(int x, int y, int pree, int h) {
		if (h != 2) {
			h = h / 2;
			quadrante(x - h, y - h, pree == 3 ? 3 : 0, h);
			quadrante(x + h, y - h, pree == 2 ? 2 : 1, h);
			quadrante(x - h, y + h, pree == 1 ? 1 : 2, h);
			quadrante(x + h, y + h, pree == 0 ? 0 : 3, h);
		}
		preencher(x - 1, y - 1, pree);
	}

	private static void preencher(int x, int y, int pree) {
		switch (pree) {
		case 0:
			_M1[x][y] = count;
			_M1[x + 1][y] = count;
			_M1[x][y + 1] = count;
			break;
		case 1:
			_M1[x][y] = count;
			_M1[x + 1][y] = count;
			_M1[x + 1][y + 1] = count;
			break;
		case 2:
			_M1[x][y] = count;
			_M1[x][y + 1] = count;
			_M1[x + 1][y + 1] = count;
			break;
		default:
			_M1[x + 1][y + 1] = count;
			_M1[x + 1][y] = count;
			_M1[x][y + 1] = count;
			break;
		}
		count++;
	}

	private static void imprimir() {
		String path = "C:\\Users\\Solange\\Desktop\\teste.csv";
		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(path));
			for (int[] v : _M1) {
				String linha = "";
				for (int i : v) {
					linha = linha + i + "\t";
				}
				buffWrite.append(linha + "\n");
			}
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
