import java.util.Scanner;

class Heap {

  public void criaHeap(int array[], int tamanho) {

		int indiceInicial = (tamanho/2) - 1;

		for (int i = indiceInicial; i >= 0; i-- ) {
			peneiraHeapUB(array, tamanho, i);
		}
	}

	private void peneiraHeapUB(int array[], int tamanho, int indice) {

		int maior = indice; 
		int esquerda = 2 * indice + 1;
		int direita = 2 * indice + 2;

		if ((esquerda < tamanho) && (array[esquerda] > array[maior]))
			maior = esquerda;

		if ((direita < tamanho) && (array[direita] > array[maior]))
			maior = direita;

		if (maior != indice) {
			int aux = array[indice];
			array[indice] = array[maior];
			array[maior] = aux;

			peneiraHeapUB(array, tamanho, maior);
		}
	}

  private void peneiraHeapBU(int array[], int tamanho, int indice) {
    
    int pai = (indice - 1) / 2;
 
    if (array[pai] > 0) {
      if (array[indice] > array[pai]) {
        int aux = array[indice];
			  array[indice] = array[pai];
			  array[pai] = aux;
        peneiraHeapBU(array, tamanho, pai);
      }
    }
  }

  public int insereHeap(int array[], int tamanho, int valor) {

    tamanho = tamanho + 1;
 
    array[tamanho - 1] = valor;
    peneiraHeapBU(array, tamanho, tamanho - 1);

    return tamanho;
  } 

  public int deleteHeapRaiz(int array[], int tamanho) {
    
    int ultimoElemento = array[tamanho - 1];

    array[0] = ultimoElemento;

    tamanho = tamanho - 1;

    peneiraHeapUB(array, tamanho, 0);

    return tamanho;
  }

  public void imprimeHeap(int array[], int tamanho) {
		System.out.println("Array que representa a estrutura Heap:");

		for (int i = 0; i < tamanho; ++i)
			System.out.print(array[i] + " ");

		System.out.println();
	}
}

class Main {

	public static void main(String args[]) {

    Scanner ler = new Scanner(System.in);
    Heap hp = new Heap();
    int flag = 1, valor;
    int tamanho;
		
    System.out.println("------Atividade de Arvore Heap------");

    System.out.print("\nInsira o tamanho do array original: ");
    tamanho = ler.nextInt();
    int array[] = new int[tamanho];

    for(int i = 0; i < tamanho; i++) {
      System.out.print("Insira o valor da posição ["+i+"]: ");
      array[i] = ler.nextInt();
    }
    hp.criaHeap(array, tamanho);
    hp.imprimeHeap(array, tamanho);

    while( flag != 0) {

      System.out.print("\nMenu - Insira a opção desejada:");
      System.out.print("\n1 - Inserir: ");
      System.out.print("\n2 - Remover: ");
      System.out.print("\n3 - Imprimir: ");
      System.out.print("\n0 - Encerrar: \n");
      System.out.print("Insira a opção desejada: ");
      flag = ler.nextInt();

      switch(flag) {
        
        case 0:
          flag = 0;
          break;
        case 1:
          if(tamanho == array.length) {
            System.out.print("Primeiro remova um valor para ficar dentro do escopo do array original\n");
            break;
          }
          System.out.print("Insira o valor que deseja adicionar: ");
          valor = ler.nextInt();
          tamanho = hp.insereHeap(array, tamanho, valor);
          hp.imprimeHeap(array, tamanho);
          break;
        case 2:
          tamanho = hp.deleteHeapRaiz(array, tamanho);
		      hp.imprimeHeap(array, tamanho);
          break;
        case 3:
		      hp.imprimeHeap(array, tamanho);
          break;
      }
    }
  }
}