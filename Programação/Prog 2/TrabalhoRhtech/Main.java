import java.util.*;

public class Main {
    public static void main(String args[]){
        String nome;
        String cpf;

        Scanner ler = new Scanner(System.in);

        Ficha ficha = new Ficha();
        int menu;

        //Laço que permitirá a criação do menu
        do{ 
            exibirMenu();
            menu = ler.nextInt();
            
            switch (menu){
                case 1:
                    addFuncionario();
                    break;
                case 2:
                    System.out.println("\nPara consultar pelo nome [1] ou cpf [2]:\n");
                    menu = ler.nextInt();
                    if (menu == 1){                
                        System.out.println("Digite o nome: ");
                        nome = ler.next();
                        ficha.consultarNome(nome);
                        
                    }
                    if (menu == 2){
                        System.out.println("Digite o cpf: ");
                        cpf = ler.next();
                        ficha.consultarCpf(cpf);
                    }
                    if (menu > 2){
                        System.out.println("\nERRO: opção inválida!\n");
                    }
                    break;
                case 3:
                    ficha.atualizar();
                    break;
                case 4:
                    //Demitir um funcionário
                    System.out.println("Digite o cpf:");
                    cpf = ler.next();
                    if (! (Ficha.getFuncionarios().isEmpty())){
                        if (ficha.remover(cpf)){
                            System.out.println("Funcionário Demitido");
                        }else{
                            System.out.println("Funcionário não encontrado");
                        }

                    }else{
                        System.out.println("ERRO: não existem funcionários contratados!");
                    }
                    
                    break;
                case 5:
                    System.out.println("\nLista dos funcionários:");
                    System.out.println(Ficha.listar());
                    break;
                case 6:
                    Ficha.categorias();
                    break;
                case 7: 
                    Ficha.salarios();
                    break;
                case 8:
                    Ficha.funcioarioSetor();
                    break;

                case 9:
                    Endereco end = new Endereco("Guamá","Belém", "Pará"); 
                    Empresa empresa = new Empresa("Elite .LTDA", end, "(91) 3201-7390","34.621.748/0001-23","elite@eltite.comn.br","UFPa");
                    Ficha.infoEmpresa(empresa);
                    break;

                case 10:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção de menu invalida");
            }

        } while (menu != 10); 

    }

    static void exibirMenu(){
        System.out.println("\nBem vindo(a) ao RHTec, escolha uma das opções para utilizar o programa:\n");
        System.out.println("[1] Admitir um novo funcionário.");
        System.out.println("[2] Consultar um funcionário por nome ou cpf.");
        System.out.println("[3] Atualizar os dados contratuais de um funcionário.");
        System.out.println("[4] Demitir funcionário contratado.");
        System.out.println("[5] Visualizar a lista de funcionários.");
        System.out.println("[6] Visualizar os funcionários por: setor, sexo ou estado.");
        System.out.println("[7] Identificar o funcionário com o maior ou menor salário.");
        System.out.println("[8] Identificar o setor da empresa com o maior (e menor) número de funcionários.");
        System.out.println("[9] Informações da empresa."); 
        System.out.println("[10] para encerrar!");
    }

    // Método de add funcionários

    public static void addFuncionario(){
        String nome;
        String sobrenome;
        int idade;
        String sexo;
        String cpf;
        String email;
        String telefone;
        String bairro;
        String cidade;
        String estado;
        String setor;
        int id = 0;
        float salario;
        String cargo;
        Scanner lerString = new Scanner(System.in);
        Scanner ler = new Scanner(System.in);
        Scanner lerString2 = new Scanner(System.in);

        // Define informações pessoais
        System.out.println("Nome: ");
        nome = lerString2.next();

        System.out.println("Sobrenome: ");
        sobrenome = lerString2.next();

        System.out.println("Idade: ");
        idade = ler.nextInt();

        // Define Sexo
        sexo = defineSexo();

        System.out.println("Cpf:");
        cpf = lerString2.next();

        System.out.println("E-mail:");
        email = lerString2.next(); 

        System.out.println("Telefone:");
        telefone = ler.next();          

        // Definir informações do endereço
        System.out.println("Bairro:");
        bairro = lerString2.next();

        System.out.println("Cidade:");
        cidade = lerString2.next();

        System.out.println("Estado:");
        estado = lerString2.next();

        id = gerarId(id);

        // Define o Setor
        setor = Ficha.defineSetor();
        // Define um cargo
        cargo = Ficha.defineCargo(setor);

        System.out.println("Salário:");
        salario = lerString.nextInt();

        Endereco enderedo = new Endereco(bairro, cidade, estado);

        Contrato contrato = new Contrato(setor, id, salario, cargo);

        Funcionario funcionario = new Funcionario(nome, sobrenome, idade, sexo, cpf, email, telefone, enderedo, contrato);
        Ficha.admitir(funcionario);
        
    }
    
    // Método que gera ID
    public static int gerarId(int id){
        id++;
        return id;
    }

    // Método que define Sexo
    public static String defineSexo(){
        String sexo;
        System.out.println("Defina o sexo: [1] Feminino [2] Masculino");
        Scanner ler = new Scanner(System.in);
        int op = ler.nextInt();

        if (op == 1){
            sexo = "feminino";
            return sexo;
        }
        if (op == 2){
            sexo = "masculino";
            return sexo;
        }

        if (op > 2){
            return "Opção inválida!";
        }

        return null;
    }

    
}
