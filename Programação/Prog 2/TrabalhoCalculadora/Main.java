import java.util.Scanner;

class Main {

  //Metodo que limpa o terminal para melhorar a navegação e vizualização
  public static void clearscreen() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }

  //Metodo que verifica a validade do polinomio A para saber se é gerada um polinomio do segundo grau.
  public static double verificaPoliA(double a) {
    Scanner ler = new Scanner(System.in);
    if(a == 0) {
      while(a == 0) {
        System.out.println("\nEsse valor não gera um polinomio quadrado, insira um valor valido");
        System.out.print("Insira o valor do polinomio \"a\": ");
        a = ler.nextDouble();
      }
    }
    return a;
  }

  public static void main(String[] args) {
    int flag = -1, flag2;
    double poliA, poliB, poliC, num1, num2;
    double[] raiz = new double[2];

    Scanner ler = new Scanner(System.in);
    
    //Mantem o codigo em loop como solicitado no trabalho.
    while( flag != 0) {

      //Escolhe em qual das calculadoras ira trabalhar
      System.out.println("-------MENU PRINCIPAL-------\n");
      System.out.println("1 - Calculadora Simples ");
      System.out.println("2 - Calculadora Polinomial ");
      System.out.println("0 - Encerrar: \n");
      System.out.print("Insira a opção desejada: ");
      flag = ler.nextInt();
      clearscreen();

      switch(flag) {
        //Caso que encerra o programa                      
        case 0:
          flag = 0;
          break;
        //Calculadora Simples
        case 1:
          Calculadora calcBasic = new Calculadora();
          //Reseta a flag para caso o usuario entre na calculadora basica mais de uma vez após ir para o menu principal.
          flag2 = -1;
          //Mantem a calculadora em loop até o usuario desejar sair
          while(flag2 != 0) {
            //Escolhe uma operação
            System.out.println("-------Calculadora Basica-------\n");
            System.out.println("1 - Soma ");
            System.out.println("2 - Subtração ");
            System.out.println("3 - Multiplicação ");
            System.out.println("4 - Divisão ");
            System.out.println("5 - Exponenciação ");
            System.out.println("6 - Resto da Divisão ");
            System.out.println("0 - Voltar para o menu principal: \n");
            System.out.print("Insira a opção desejada: ");
            flag2 = ler.nextInt();
            switch(flag2) {
              //Usuario volta para o menu principal
              case 0:
                flag2 = 0;
                clearscreen();
                break;
              //soma
              case 1:
                clearscreen();
                System.out.print("\nInsira o valor do primeiro numero: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do segundo numero: ");
                num2 = ler.nextDouble();
                System.out.println("\nA soma de "+num1+" + "+num2+" = "+calcBasic.soma(num1,num2));
                break;
              //subtração
              case 2:  
                clearscreen();
                System.out.print("\nInsira o valor do primeiro numero: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do segundo numero: ");
                num2 = ler.nextDouble();
                System.out.println("\nA subtração de "+num1+" - "+num2+" = "+calcBasic.subtracao(num1,num2));
                break;
              //multiplicação
              case 3: 
                clearscreen(); 
                System.out.print("\nInsira o valor do primeiro numero: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do segundo numero: ");
                num2 = ler.nextDouble();
                System.out.println("\nA multiplicação de "+num1+" x "+num2+" = "+calcBasic.multiplicacao(num1,num2));
                break;
              //divisão
              case 4:  
                clearscreen();
                System.out.print("\nInsira o valor do dividendo: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do divisor: ");
                num2 = ler.nextDouble();
                System.out.println("\nA divisão de "+num1+" / "+num2+" = "+calcBasic.divisao(num1,num2));
                break;
              //exponenciação
              case 5: 
                clearscreen(); 
                System.out.print("\nInsira o valor do numero base: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do expoente: ");
                num2 = ler.nextDouble();
                System.out.println("\nA exponenciação de "+num1+" ^ "+num2+" = "+calcBasic.exponeciacao(num1,num2));
                break;
              //resto da divisão
              case 6: 
                clearscreen(); 
                System.out.print("\nInsira o valor do dividendo: ");
                num1 = ler.nextDouble();
                System.out.print("Insira o valor do divisor: ");
                num2 = ler.nextDouble();
                System.out.println("\nO resto da divisão de "+num1+" / "+num2+" = "+calcBasic.restoDivisao(num1,num2));
                break;
              default:
                clearscreen();
                System.out.println("Insira uma opção valida.");
                break;
            }
          }
          break;
        //Calculadora Polinomial
        case 2:
          //Reseta a flag para caso o usuario entre na calculadora polinomial mais de uma vez após ir para o menu principal.
          flag2 = -1;
          //Mantem a calculadora em loop até o usuario desejar sair
          while(flag2 != 0) {
            //Insere os valores dos polinomios "a", "b" e "c"
            System.out.println("-------Calculadora Polinomial-------\n");
            System.out.print("Insira o valor do polinomio \"a\": ");
            poliA = verificaPoliA(ler.nextDouble()); //verifica "a" até a inserção de um valor valido
            System.out.print("Insira o valor do polinomio \"b\": ");
            poliB = ler.nextDouble();
            System.out.print("Insira o valor do polinomio \"c\": ");
            poliC = ler.nextDouble();
            //Instancia com os valores previamente inseridos
            Calculadora calcPoli = new Calculadora(poliA, poliB, poliC);
            //Caso exista raiz, imprime o resultado
            if(calcPoli.getDelta() >= 0) {
              raiz = calcPoli.bhaskara();
              System.out.println("\nAs raizes são: ");
              System.out.println("R1 = "+raiz[0]);
              System.out.println("R2 = "+raiz[1]+"\n");
            }
            else {
              System.out.println("\nEsse polinomio não possui raizes no conjunto dos reais.\n");
            }
            //Pergunta se o usuario deseja continuar ou voltar para o menu principal
            System.out.println("0 - Voltar para o menu principal");
            System.out.println("1 - Realizar outra operação");
            System.out.print("Insira a opção desejada: ");
            flag2 = ler.nextInt();
            //Valida se o valor inserido esta dentro do escopo desejado
            if(flag2 != 0 && flag2 != 1) {
              while(flag2 != 0 && flag2 != 1) {
                System.out.print("Insira um valor valido: ");
                flag2 = ler.nextInt();
              }
            }
            clearscreen();
          }
          break;
        default:
          System.out.print("Insira uma opção valida.\n\n");
          break;
      }
    }
  }
}