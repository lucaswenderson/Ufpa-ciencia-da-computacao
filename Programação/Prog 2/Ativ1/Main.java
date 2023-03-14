//import java.util.Scanner;
//Scanner ler = new Scanner(System.in);
//System.out.print("Digite um número inteiro: ");
//num1 = ler.nextInt();

class Main {
	public static void main(String[] args) {

		Agenda contatos = new Agenda(10);
    Contato lucas = new Contato("Lucas", "Lucas@gmail.com"); 
		Contato joao = new Contato("João", "joao@hotmail.com");		
		Contato marcos = new Contato("Marcos", "marcos@hotmail.com");		
		Contato caio = new Contato("Caio", "caio@hotmail.com");		

		contatos.adicionarContato(lucas); 
		contatos.adicionarContato(joao); 
		contatos.adicionarContato(marcos); 
		contatos.adicionarContato(caio); 
		
    Contato aux = contatos.buscarContato("Caio");
    if (aux != null){
      System.out.println("Contato " + aux.getNome() + " encontrado.");
    }
    else{
      System.out.println("Contato não encontrado");
    }

		contatos.removerContato("Caio"); 

    aux = contatos.buscarContato("Caio");
    if (aux != null){
      System.out.println(aux.getNome());
    }
    else{
      System.out.println("Contato não encontrado");
    }	
	}
}