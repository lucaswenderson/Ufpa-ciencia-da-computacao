class Universidade {
  private String nome;
  private Departamento[] departamento;  

  public Universidade(String nome) {
    this.nome = nome;
    departamento = new Departamento[10];
  }

  public void adicionarDepartamento(Departamento depart) {
		for(int i = 0; i < departamento.length; i++ ) { 
			if(departamento[i] == null) { 
				departamento[i] = depart; 
        System.out.println("Departamento " + depart.getNome() + " adicionado");
				break;
			}
		}
	}

  public String getNome() {
    return nome;
  }
} 

class Departamento {
  private String nome;
  private Pessoa[] pessoa;

  public Departamento(String nome, int tamanho) {
    this.nome = nome;
    pessoa = new Pessoa[tamanho];
  }

  public void adicionarPessoa(Pessoa p1, Pessoa p2) {
		for(int i = 0; i < pessoa.length; i++ ) { 
			if(pessoa[i] == null) { 
				pessoa[i] = p1; 
				pessoa[i + 1] = p2; 
        System.out.println("Pessoa " + p1.getNome() + " adicionado em " + nome);
        System.out.println("Pessoa " + p2.getNome() + " adicionado em " + nome);
				break;
			}
		}
	}

  public String getNome() {
    return nome;
  }
}

class Pessoa {
  private String nome;

  public Pessoa(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }
} 

class Main {
  public static void main(String[] args) {

    Universidade uni = new Universidade("UFPA");

    Departamento dp1 = new Departamento("ICEN", 2);
    Departamento dp2 = new Departamento("ITEC", 2);
    Departamento dp3 = new Departamento("ICSH", 2);
    uni.adicionarDepartamento(dp1);
    uni.adicionarDepartamento(dp2);
    uni.adicionarDepartamento(dp3);

    Pessoa p1 = new Pessoa("Pessoa Aleatoria 1");
    Pessoa p2 = new Pessoa("Pessoa Aleatoria 2");
    dp1.adicionarPessoa(p1,p2);
    dp2.adicionarPessoa(p1,p2);
    dp3.adicionarPessoa(p1,p2);
    
  }
}