import java.util.*;

class Main {
	
	static void addBorda(ArrayList<ArrayList<Integer> > grafo,int x, int y) {
		grafo.get(x).add(y);
		grafo.get(y).add(x);
	}

	static void imprimir(ArrayList<ArrayList<Integer>> grafo) {
		for (int i = 0; i < grafo.size(); i++) {
			System.out.println("Lista adjacente do vertice " + i);
			System.out.print("Cabeça");
			for (int j = 0; j < grafo.get(i).size(); j++) {
				System.out.print(" -> "+grafo.get(i).get(j));
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		int V = 6;
		ArrayList<ArrayList<Integer> > grafo = new ArrayList<ArrayList<Integer> > (V);
		
		for (int i = 0; i < V; i++)
			grafo.add(new ArrayList<Integer>());

		addBorda(grafo, 0, 1);
		addBorda(grafo, 0, 4);
		addBorda(grafo, 1, 2);
		addBorda(grafo, 1, 3);
		addBorda(grafo, 2, 3);
		addBorda(grafo, 3, 4);
		addBorda(grafo, 4, 2);
		addBorda(grafo, 5, 1);
		
		imprimir(grafo);
	}
}
