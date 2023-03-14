// Implementação do algoritmo de ordenação topologica  
import java.util.*;

// Grafo direcionado ultilizando lista de adjacencia 
class GrafoAdj {
  private int tamanho;
  private ArrayList<ArrayList<Integer> > adjacente;

  // Criação do grafo
  GrafoAdj(int tam) {
    tamanho = tam;
    adjacente = new ArrayList<ArrayList<Integer> >(tam);
    for (int i = 0; i < tam; ++i)
      adjacente.add(new ArrayList<Integer>());
  }

  // Adcionar aresta 
  void addAresta(int verticeDe, int verticePara) { 
    adjacente.get(verticeDe).add(verticePara);
  }

  // Funcção recursiva auxiliar para a ordenação
  void auxiliar(int vertice, boolean visitados[], Stack<Integer> pilha) {
    // Marca o "nó" atual como visitado.
    visitados[vertice] = true;
    Integer i;


    Iterator<Integer> it = adjacente.get(vertice).iterator();
    while (it.hasNext()) {
      i = it.next();
      if (!visitados[i])
        auxiliar(i, visitados, pilha);
    }

    // Armazena o vertice atual na pilha que tem o resultado 
    pilha.push(vertice);
  }

  void ordenacaoTopologica() {
    Stack<Integer> pilha = new Stack<Integer>();

    // Marca todos os nós como não visitados
    boolean visitados[] = new boolean[tamanho];
    for (int i = 0; i < tamanho; i++){
      visitados[i] = false;
    }

    for (int i = 0; i < tamanho; i++){
      if (visitados[i] == false){
        auxiliar(i, visitados, pilha);
      }
    }

    // Imprime o conteudo da pilha
    while (pilha.empty() == false)
      System.out.print(pilha.pop() + " ");
  }

  void imprimir() {
		for (int i = 0; i < tamanho; i++) {
			System.out.println("Lista adjacente do vertice " + i);
			System.out.print("Cabeça");
			for (int j = 0; j < adjacente.get(i).size(); j++) {
				System.out.print(" -> "+adjacente.get(i).get(j));
			}
			System.out.println();
		}
	}
}

class Main {
  public static void main(String args[]) {

    GrafoAdj grafo1 = new GrafoAdj(6);
    grafo1.addAresta(0, 1);
    grafo1.addAresta(0, 4);
    grafo1.addAresta(1, 2);
    grafo1.addAresta(1, 3);
    grafo1.addAresta(2, 3);
    grafo1.addAresta(3, 4);
    grafo1.addAresta(4, 2);
    grafo1.addAresta(5, 1);

    System.out.println("\nGrafo:");
    grafo1.imprimir();
      System.out.println("\nOrdenação Topologica:");
    grafo1.ordenacaoTopologica();
  }
}
