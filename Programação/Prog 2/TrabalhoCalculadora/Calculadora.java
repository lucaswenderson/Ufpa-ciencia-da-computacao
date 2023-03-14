import java.lang.Math;

public class Calculadora {

  private double polinomioA;
  private double polinomioB;
  private double polinomioC;
  private double delta;

  // Metodos get e set dos atributos
  public double getPolinomioA() {
		return polinomioA;
	}

	public void setPolinomioA(double num) {
		this.polinomioA = num;
	}

  public double getPolinomioB() {
		return polinomioB;
	}

	public void setPolinomioB(double num) {
		this.polinomioB = num;
	}
    
  public double getPolinomioC() {
		return polinomioC;
	}

	public void setPolinomioC(double num) {
		this.polinomioC = num;
	}

  public double getDelta() {
		return delta;
	}

	public void setDelta(double num) {
		this.delta = delta();
	}

  //Contrutor padrão para a calculadora simples
  public Calculadora() {}

  //Contrutor personalizado para a calculadora polinomial
  public Calculadora(double a, double b, double c) {
    this.polinomioA = a;
    this.polinomioB = b;
    this.polinomioC = c;
    this.delta = delta();
  }

  //Metodos das operações solicitadas na calculadora simples
  public double soma(double numero1, double numero2) {
    return numero1 + numero2;
  }

  public double subtracao(double numero1, double numero2) {
    return numero1 - numero2;
  }

  public double multiplicacao(double numero1, double numero2) {
    return numero1 * numero2;
  }

  public double divisao(double numero1, double numero2) {
    return numero1 / numero2;
  }
   
  public double exponeciacao(double numero1, double numero2) {
    return Math.pow(numero1, numero2);
  }

  public double restoDivisao(double numero1, double numero2) {
    return numero1 % numero2;
  }

  //Metodo que faz o calculo de delta dinamicamente baseados nos valores instanciados nos atributos
  public double delta() {
    return (Math.pow(getPolinomioB(), 2)) - (4*getPolinomioA()*getPolinomioC());
  }

  //Metodo que realiza o calculo das raizes baseado na equação de bhaskara
  public double[] bhaskara() {
    double[] raiz = new double[2];
    if(getDelta() >= 0) {
      raiz[0] = ((-1)*getPolinomioB() + Math.sqrt(getDelta()))/(2 * getPolinomioA());
      raiz[1] = ((-1)*getPolinomioB() - Math.sqrt(getDelta()))/(2 * getPolinomioA());
    }
    return raiz;
  }
}
