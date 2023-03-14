import java.util.*;

// Implementação por lista de adjacencia 
class GrafoAdj {
	private int tamanho; 
	private LinkedList<Integer> adjacente[];

  // Criação do grafo
  GrafoAdj(int tamanho) {
		this.tamanho = tamanho;
		adjacente = new LinkedList[tamanho];
		for (int i = 0; i < tamanho; ++i)
			adjacente[i] = new LinkedList();
	}

  // Adcionar aresta 
	void addAresta(int verticeDe, int verticePara) {
		adjacente[verticeDe].add(verticePara); 
	}

  //Função auxiliar do DFS
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
  
  //Implementação do DFS
	void buscaProfundidade(int vertice) {
		boolean visitado[] = new boolean[tamanho];
		profundidade(vertice, visitado);
	}

  //Imprime o grafo
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

    System.out.println("\nImplementação por Lista de Adjacencia");
    grafo1.imprimir();

    System.out.println("\nBusca por profundidade(inicio: vertice -> 4)");
		grafo1.buscaProfundidade(4);

    System.out.println("\nBusca por profundidade(inicio: vertice -> 0)");
		grafo1.buscaProfundidade(0);

    System.out.println("\nBusca por profundidade(inicio: vertice -> 5)");
		grafo1.buscaProfundidade(5);
	}
}