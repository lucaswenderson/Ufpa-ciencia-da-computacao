import java.util.*;

class GrafoAdj {
	private int tamanho; 
	private LinkedList<Integer> adjacente[];

  GrafoAdj(int tamanho) {
		this.tamanho = tamanho;
		adjacente = new LinkedList[tamanho];
		for (int i = 0; i < tamanho; ++i)
			adjacente[i] = new LinkedList();
	}

	void addAresta(int verticeDe, int verticePara) {
		adjacente[verticeDe].add(verticePara); 
	}

	void profundidade(int vertice, boolean visitado[])
	{
		visitado[vertice] = true;
		System.out.print(vertice + " ");

		Iterator<Integer> i = adjacente[vertice].listIterator();
		while (i.hasNext()) {
			int proximo = i.next();
			if (!visitado[proximo])
				profundidade(proximo, visitado);
		}
	}

	void buscaProfundidade(int vertice) {
		boolean visitado[] = new boolean[tamanho];
		profundidade(vertice, visitado);
	}

  void buscaLargura(int vertice) {
    boolean visitado[] = new boolean[tamanho];
    LinkedList<Integer> queue = new LinkedList<Integer>();

    visitado[vertice]=true;
    queue.add(vertice);

    while (queue.size() != 0) {
      vertice = queue.poll();
      System.out.print(vertice+" ");

      Iterator<Integer> i = adjacente[vertice].listIterator();
      while (i.hasNext()) {
        int proximo = i.next();
        if (!visitado[proximo]) {
          visitado[proximo] = true;
          queue.add(proximo);
        }
      }
    }
  }

  void imprimir() {
		for (int i = 0; i < tamanho; i++) {
			System.out.println("Lista adjacente do vertice " + i);
			System.out.print("Cabeça");
			for (int j = 0; j < adjacente[i].size(); j++) {
				System.out.print(" -> "+adjacente[i].get(j));
			}
			System.out.println();
		}
	}
}

class GrafoMatr {
	static final int tamanho = 9;

	int distanciaMinima(int distancia[], Boolean arr[]) {
		int min = Integer.MAX_VALUE;
    int indiceMin = -1;

		for (int vertice = 0; vertice < tamanho; vertice++) {
			if ((arr[vertice] == false) && (distancia[vertice] <= min)) {
				min = distancia[vertice];
				indiceMin = vertice;
			}
    }
		return indiceMin;
	}

	void imprimir(int distancia[]) {
		System.out.println("Vertice  Distancia da raiz");
		for (int i = 0; i < tamanho; i++)
			System.out.println(i + " \t\t " + distancia[i]);
	}

	void dijkstra(int grafo[][], int raiz) {
		int distancia[] = new int[tamanho];
		Boolean arr[] = new Boolean[tamanho];

		for (int i = 0; i < tamanho; i++) {
			distancia[i] = Integer.MAX_VALUE;
			arr[i] = false;
		}
		distancia[raiz] = 0;

		for (int count = 0; count < tamanho - 1; count++) {
			int disMin = distanciaMinima(distancia, arr);
			arr[disMin] = true;

			for (int v = 0; v < tamanho; v++) {
				if (!arr[v] && grafo[disMin][v] != 0 && distancia[disMin] != Integer.MAX_VALUE && distancia[disMin] + grafo[disMin][v] < distancia[v]) {
					distancia[v] = distancia[disMin] + grafo[disMin][v];
        }
      }
		}
		imprimir(distancia);
	}
}

class Main {
	public static void main(String[] args) {
    GrafoAdj grafo1 = new GrafoAdj(6);

		grafo1.addAresta(0, 1);
		grafo1.addAresta(0, 4);
		grafo1.addAresta(1, 2);
		grafo1.addAresta(1, 3);
		grafo1.addAresta(2, 3);
		grafo1.addAresta(3, 4);
		grafo1.addAresta(4, 2);
		grafo1.addAresta(5, 1);

    grafo1.imprimir();

		System.out.println("\nBusca por profundidade(inicio: vertice -> 4)");
		grafo1.buscaProfundidade(4);
    
    System.out.println("\nBusca por largura(inicio: vertice -> 1)");
    grafo1.buscaLargura(1);

    System.out.print("\n\nAlgoritmos de dijkstra (grafo 2)");
		int matriz[][] = new int[][] {
      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
      { 0, 0, 2, 0, 0, 0, 6, 7, 0 },
      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
      { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
      { 0, 8, 0, 9, 0, 10, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
      { 8, 11, 0, 0, 0, 0, 1, 0, 7 }
    };
		System.out.println("");
		GrafoMatr grafo2 = new GrafoMatr();
		grafo2.dijkstra(matriz, 0);
	}
}