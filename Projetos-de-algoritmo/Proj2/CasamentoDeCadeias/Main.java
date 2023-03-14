import java.lang.Math;
import java.util.Scanner;

class Main {
	public static void main(String []args) {
    Scanner ler = new Scanner(System.in);
    int flag = 1;

    System.out.println("Exemplos: ");
    System.out.println("Alfabeto = AABAACAADAABAABA ");
    System.out.println("Texto =  ABA");
    
    busca("AABAACAADAABAABA".toCharArray(), "ABA".toCharArray());

    System.out.println("");

    System.out.print("Insira o alfabeto desejado(limite: 256): ");
    char alfabeto[] = ler.next().toCharArray();
    
    do {
      System.out.print("Insira a busca desejada: ");
      char texto[] = ler.next().toCharArray();
      
      System.out.println("");
      
      busca(alfabeto, texto);

      System.out.println("");

      System.out.print("Deseja realizar outra busca ? (1 - Sim / 0 - Não): ");
      flag = ler.nextInt();
    } while( flag == 1);
	}

	private static final int limite = 256;
	
	public static void busca( char alfabeto[], char texto[]) {
    int tamanhoAlfabeto = alfabeto.length;
    int tamanhoTexto = texto.length;
    int shift = 0;
    int tabelaPreproc[] = new int[limite];

    preprocessamento(texto, tamanhoTexto, tabelaPreproc);

    while(shift <= (tamanhoAlfabeto - tamanhoTexto)) {
      int j = tamanhoTexto - 1;

      while((j >= 0) && (texto[j] == alfabeto[shift + j])) {
        j--;
      }

      if (j < 0) {
        System.out.println("O casamento ocorre na posição = " + shift);

        if((shift + tamanhoTexto) < tamanhoAlfabeto) {
          shift += tamanhoTexto - tabelaPreproc[ alfabeto[shift + tamanhoTexto] ];
        }
        else {
          shift += 1;
        }
      }
      else {
        shift += Math.max(1, j - tabelaPreproc[ alfabeto[shift + j] ]);
      }
    }
	}

	public static void preprocessamento(char texto[], int tamanhoTexto, int tabelaPreproc[]) {
    for (int i = 0; i < limite; i++){
      tabelaPreproc[i] = -1;
    }

    for (int i = 0; i < tamanhoTexto; i++){
      tabelaPreproc[ (int)texto[i] ] = i;
    }
	}
}
