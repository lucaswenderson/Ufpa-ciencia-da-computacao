import java.util.Scanner;

class Endereco {
  private String cidade;
  private String bairro;
  private String numero;

}

class Sala {
  private int numMoveis;
}

class Quarto {
  private int numMoveis;
}

class Banheiro {
  private int qtd;
  private int numMoveis;
}

class Garagem {
  private int espacoVeicu;
}

class Cozinha {
  private int qtd;
  private int numMoveis;
}

class Casa {
  private Endereco endereco;
  private Sala[] salas;
  private Quarto[] quartos;
  private Banheiro[] banheiros;
  private Garagem garagem;
  private Cozinha cozinha;
}


class Main {

  public static void main(String[] args) {
    Casa casa = new Casa();
  }
}