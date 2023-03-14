import java.util.Scanner;

class ContaCorrente {
  protected float saldo;
  
  public float getSaldo() { return saldo; }
  public void setSaldo(float saldo) { this.saldo = saldo; }

  public ContaCorrente() {
    this.setSaldo(0);
  }

  public ContaCorrente(float saldo) {
    this.setSaldo(saldo);
  }

  public void depositar(float valor) {
    setSaldo(getSaldo() + valor);    
  }

  public boolean sacar(float valor) {
    if(valor <= getSaldo()){
      setSaldo(getSaldo() - valor);    
      return true;
    }
    return false;
  }

  public String toString() {
    String output = "Saldo: "+this.saldo+"R$"+"\n";
    return output;
  }

  public boolean transferir(ContaCorrente conta, float valor) {
    boolean verificacao = sacar(valor);
    if(verificacao) {
      conta.depositar(valor);
      return true;
    }
    return false;
  }
}

class Cliente {
  private String nome;
  private ContaCorrente contaC;
  private ContaEspecial contaE;
  
  public String getLimite() { return nome; }
  public void setNome(String nome) { this.nome = nome; }

  public ContaCorrente getContaC() { return contaC; }
  public void setContaC(float saldo) {
    contaC = new ContaCorrente(saldo);
  }

  public ContaEspecial getContaE() { return contaE; }
  public void setContaE(float saldo, float limite) { 
    contaE = new ContaEspecial(saldo, limite);
  }

  public Cliente() {
    this.setNome("Sem nome");
  }
  
  public Cliente(String nome) {
    this.setNome(nome);
  }  

  public String toString() {
    String output = "Nome: "+nome+"\n";
    return output;
  }
}


class ContaEspecial extends ContaCorrente { 
  private float limite;

  public float getLimite() { return limite; }
  public void setLimite(float limite) { this.limite = limite; }

  public ContaEspecial(float saldo, float limite) {
    super(saldo);
    this.setLimite(limite);
  }

  @Override
  public boolean sacar(float valor) {
    if((saldo - valor) >= limite ) {
      super.setSaldo(super.getSaldo() - valor);
    }
    return false;
  }

  public String toString() {
    String output = "Saldo: "+saldo+"R$"+"\n"+
                    "Limite: "+this.limite+"R$"+"\n";
    return output;
  }
}

public class Main {
  public static void main(String[] args) {
    Cliente c1 = new Cliente("Lucas");
    Cliente c2 = new Cliente("Mario");
    Cliente c3 = new Cliente("João");
    c1.setContaC(1000);
    System.out.println(c1.toString() + c1.getContaC().toString());
    c2.setContaC(1000);
    System.out.println(c2.toString() + c2.getContaC().toString());
    c3.setContaE(1000, -1000);
    System.out.println(c3.toString() + c3.getContaE().toString());
    c1.getContaC().depositar(200);
    System.out.println(c1.toString() + c1.getContaC().toString());
    c2.getContaC().sacar(200);
    System.out.println(c2.toString() + c2.getContaC().toString());
    c1.getContaC().transferir(c2.getContaC(), 300);
    System.out.println(c1.toString() + c1.getContaC().toString());
    System.out.println(c2.toString() + c2.getContaC().toString());
    c3.getContaE().sacar(1500);
    System.out.println(c3.toString() + c3.getContaE().toString());
  }   
}