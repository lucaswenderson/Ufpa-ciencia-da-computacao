import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    // throws IOException exceção do erro de E/S
    public static void main(String args[]) throws IOException {
        ArrayList<String> tac = new ArrayList<String>();
        long tamanho;

        File f = new File(args[0]);
        tamanho = f.length();
        long fileSizeInKB = tamanho / 1024;
        if(fileSizeInKB <= 512){
       		FileReader arquivo = new FileReader(args[0]);// lendo os dados do arquivo
       		BufferedReader br = new BufferedReader(arquivo);// lendo o texto com base nos caracteres e aramazenando num
                                                        // buffer
        	while (br.ready()) {// ready verifica se o fluxo está pronto pra ser lido
            		String linha = br.readLine();// lê uma linha de texto e salva na variável
            		tac.add(linha);// adiciona a string na lista de strings
        	}
        	Collections.reverse(tac);// inverte a ordem dos elementos da lista
        	for (String arq : tac) {
            		System.out.println(arq);
        	}
        	br.close();
    	}
    } 
}
        
