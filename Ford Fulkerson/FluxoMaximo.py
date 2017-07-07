
class Grafo:
	def __init__(self, matriz):
		self._matriz = matriz
	def somaEntradas(self, matriz, linha):
		res = 0
		for col in range(0,len(matriz)):
			res += matriz[linha][col]
		return res

	def somaSaidas(self, matriz, coluna):
		res = 0
		for lin in range(0,len(matriz)):
			res += matriz[lin][coluna]
		return res

	def calcularPotencial(self):
		matriz = self.matriz
		potencial = []
		for x in range(0,len(matriz)):
			entradas = self.somaEntradas(matriz, x)
			saidas = self.somaSaidas(matriz, x)
			potencial.append(entradas if entradas < saidas else saidas)
			self.potencial = potencial
		return potencial

	def escolherVerticeRef(self):
		vertice = 0
		for x in range(0,len(self.potencial)):
			if((self.potencial[vertice] > self.potencial[x] and self.potencial[x] >0) or (self.potencial[vertice] == 0) ):
				vertice = x
		self.verticeRef = vertice
		return vertice

	def fluirPeloVertice(self):
		boDestino = false
		if(self.dijkstra(origem, self.verticeRef) and self.dijkstra(self.escolherVerticeRef, destino)):
			atualizarPesoVertice()


	def dijkstra(self, origem, destino):
		if (origem == destino):
			return true
		else:
			for x range(0,len(self.matriz)):
				if(self.matriz[origem][x] > 0 and self.dijkstra(self.matriz[origem][x],destino)):
					return true
			return false

	def _get_matriz(self):
		return self._matriz

	matriz = property(_get_matriz)


def main():
	M = [[0, 15, 20, 10, 0, 0, 0],
		[ 0, 0, 0, 0, 2, 3, 0 ],
		[0, 0, 0, 0, 4, 5, 15],
		[ 0, 0, 0, 0, 5, 4, 0],
		[0, 0, 0, 0, 0, 0, 15],
		[0, 0, 0, 0, 0, 0, 10],
		[0, 0, 0, 0, 0, 0, 0]]

	grafo = Grafo(M)

	potencial = grafo.calcularPotencial();
	print(potencial)

	verticeReferencia = grafo.escolherVerticeRef();
	print(verticeReferencia)

	fluirPeloVertice()
	

if __name__ == "__main__":
	main()