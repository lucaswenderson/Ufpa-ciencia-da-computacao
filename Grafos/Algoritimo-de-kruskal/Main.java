import java.util.*;

class Grafo {

	class Aresta implements Comparable<Aresta> {
		int origem, destino, peso;

		// Função usada para ordenar as nArestas com base no peso
		public int compareTo(Aresta compareEdge) {
			return this.peso - compareEdge.peso;
		}
	};

	class subconjunto	{
		int pai, rank;
	};

	int nVertices, nArestas; 
	Aresta aresta[]; // Todas as arestas

	// Cria o grafo com "v" Vertices e "a" Arestas
	Grafo(int v, int a)	{
		nVertices = v;
		nArestas = a;
		aresta = new Aresta[nArestas];
		for (int i = 0; i < a; ++i)
			aresta[i] = new Aresta();
	}

	// Função auxiliar para buscar o conjunto de um elemento "i"
	int busca(subconjunto subconjuntos[], int i) {
		// enconta a raiz e coloca ela como  pai de "i"
		if (subconjuntos[i].pai != i)
			subconjuntos[i].pai = busca(subconjuntos, subconjuntos[i].pai);
		return subconjuntos[i].pai;
	}

  // Função que une dois conjuntos "x" e "y" (com base no rank)
	void uniao(subconjunto subconjuntos[], int x, int y) {
		int xRaiz = busca(subconjuntos, x);
		int yRaiz = busca(subconjuntos, y);

		// vincula a arvore de menor rank sobre a raiz da arvore de maior rank
		if (subconjuntos[xRaiz].rank < subconjuntos[yRaiz].rank)
			subconjuntos[xRaiz].pai = yRaiz;
		else if (subconjuntos[xRaiz].rank > subconjuntos[yRaiz].rank)
			subconjuntos[yRaiz].pai = xRaiz;

    // se os ranks forma iguais, coloca ele como a raiz e incremente 1 no rank
		else {
			subconjuntos[yRaiz].pai = xRaiz;
			subconjuntos[xRaiz].rank++;
		}
	}

	void kruskal() {
		// Armazena a arvore geradora minima (AGM)
		Aresta AGM[] = new Aresta[nVertices];
		
    int a = 0;	
  	int i = 0;
		for (i = 0; i < nVertices; ++i) {
			AGM[i] = new Aresta();
    }

    // Ordena todas as arestas em ordem crescente com base no peso
		Arrays.sort(aresta);

		// Cria "n" subconjuntos
		subconjunto subconjuntos[] = new subconjunto[nVertices];
		for (i = 0; i < nVertices; ++i){
			subconjuntos[i] = new subconjunto();
    }
		// Inicializa "n" subconjuntos 
		for (int v = 0; v < nVertices; ++v)	{
			subconjuntos[v].pai = v;
			subconjuntos[v].rank = 0;
		}

		i = 0; // Flag para proxima aresta

		while (a < nVertices - 1)	{
			// Pega a menor aresta e aumenta a flag para proxima iteração
			Aresta proxAresta = aresta[i++];

			int x = busca(subconjuntos, proxAresta.origem);
			int y = busca(subconjuntos, proxAresta.destino);

      // Se incluir a aresta não gerar ciclo, inclui ela na AGM e incrementa a flag para proxima aresta
			if (x != y) {
				AGM[a++] = proxAresta;
				uniao(subconjuntos, x, y);
			}
		}
    imprimir(AGM, a);
  }
  void imprimir(Aresta AGM[], int a) {
		System.out.println("Arestas da arvore geradora minima: ");
		System.out.println("vInicio -- vFim == Peso");
		for (int i = 0; i < a; ++i) {
			System.out.println(AGM[i].origem + " -- " + AGM[i].destino + " == " + AGM[i].peso);
		}
	}
}

class Main { 
	public static void main(String[] args) {

		int nVertices = 4; 
		int nArestas = 5; 
		Grafo g1 = new Grafo(nVertices, nArestas);

    /* Grafo 1 - exemplo:
          10
      0--------1
      |  \     |
    6 |  5\    |15
      |    \   |
      2--------3
          4       
    */
    
    insercao(g1, 0, 0, 1, 10); //Aresta 0 - 1
    insercao(g1, 1, 0, 2, 6); //Aresta 0 - 2
    insercao(g1, 2, 0, 3, 5); //Aresta 0 - 3 
    insercao(g1, 3, 1, 3, 15); //Aresta 1 - 3 
    insercao(g1, 4, 2, 3, 4); //Aresta 2 - 3 
		g1.kruskal();

    nVertices = 6; 
		nArestas = 8; 
		Grafo g2 = new Grafo(nVertices, nArestas);

    /* Grafo 2 - exemplo:
      0               3
      |  \           / \  
      |    \4     3/     \3
    4 |      \   /         \
      |       |2------4-----|4  
      |      /   \         /
      |    /2     2\     /3
      |  /           \ /
      1               5
    */

    insercao(g2, 0, 0, 1, 4); //Aresta 0 - 1
    insercao(g2, 1, 0, 2, 4); //Aresta 0 - 2
    insercao(g2, 2, 1, 2, 2); //Aresta 1 - 2
    insercao(g2, 3, 2, 3, 3); //Aresta 2 - 3
    insercao(g2, 4, 2, 5, 2); //Aresta 2 - 5
    insercao(g2, 5, 2, 4, 4); //Aresta 2 - 4
    insercao(g2, 6, 3, 4, 3); //Aresta 3 - 4
    insercao(g2, 7, 5, 4, 3); //Aresta 5 - 4
    g2.kruskal();
	}

  public static void insercao(Grafo grafo, int vert, int orig, int dest, int peso){
    grafo.aresta[vert].origem = orig;
    grafo.aresta[vert].destino = dest;
    grafo.aresta[vert].peso = peso;
  }	
}

