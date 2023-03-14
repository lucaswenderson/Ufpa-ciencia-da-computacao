public class Agenda {	
	private Contato[] contatos;

  public Agenda(int tamanho) { 
		contatos = new Contato[tamanho]; 
	}
	
	public void adicionarContato(Contato contato) {
		for( int i = 0; i < contatos.length; i++ ) { 
			if (contatos[i] == null) { 
				contatos[i] = contato; 
        System.out.println("Contato " + contato.getNome() + " adicionado");
				break;
			}
		}
	}
	
	public void removerContato(String nome) {
		for (int i = 0; i < contatos.length; i++) {
      if(contatos[i] == null) {
        break;
      } 
			else if (contatos[i].getNome() == nome) { 
				contatos[i] = null;

				for (int k = i; k < contatos.length - 1; k++)
					contatos[k] = contatos[k+1];
				
				System.out.println("Contato " + nome + " removido"); 
				break; 
			}
		}
	}
	
	public Contato buscarContato(String nome) { 
		
    for (int i = 0; i < contatos.length; i++) {
      if(contatos[i] == null){
        break;
      } 
      else if (contatos[i].getNome() == nome) { 
        return contatos[i];
      }
    }
    return null;
	}	
}