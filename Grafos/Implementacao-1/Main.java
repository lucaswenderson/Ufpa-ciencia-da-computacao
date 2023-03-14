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

// Implementação por matriz de adjacencia 
class GrafoMatr {
  private int tamanho;
  private boolean matrizAdj[][];

  // Criação do grafo
  GrafoMatr(int tamanho) {
    this.tamanho = tamanho;
    matrizAdj = new boolean[tamanho][tamanho];
  }

  // Adiciona aresta
  void addAresta(int i, int j) {
    matrizAdj[i][j] = true;
    matrizAdj[j][i] = true;
  }

  // Imprime o grafo
  void imprimir() {
    for (int i = 0; i < tamanho; i++) {
      System.out.print(i + ": ");
      for (boolean j : matrizAdj[i]) {
        System.out.print((j ? 1 : 0) + " ");
      }
      System.out.print("\n");
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

    GrafoMatr grafo2 = new GrafoMatr(6);

		grafo2.addAresta(0, 1);
		grafo2.addAresta(0, 4);
		grafo2.addAresta(1, 2);
		grafo2.addAresta(1, 3);
		grafo2.addAresta(2, 3);
		grafo2.addAresta(3, 4);
		grafo2.addAresta(4, 2);
		grafo2.addAresta(5, 1);

    System.out.println("\nImplementação por Matriz de Adjacencia");
    grafo2.imprimir();
	}
}



public class TestaExpressoes {
	public static void main(String[] args) {
		boolean nome = "Maria".matches("Maria");
		System.out.println("Retorno = "+nome);
	}
}
