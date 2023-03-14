import java.util.ArrayList;
import java.util.Scanner;

public class Ficha {
    private static ArrayList<Funcionario> funcionarios =  new ArrayList<>();

    //Método GET
    public static ArrayList<Funcionario> getFuncionarios(){
        return funcionarios;
    }

    // Admitir funcionário (Add funcionario a lista)
    static public void admitir(Funcionario f) {
        funcionarios.add(f);
        System.out.println("Funcionário admitido com SUCESSO!");
    }

    // Lista de todos os dados da lista dos funcionários
    static public String listar(){
        String saida = "";
        int i = 1;
        for (Funcionario f : funcionarios){
            saida += "\n----"+"Funcionário "+(i++)+"----\n";
            saida += f.imprimir()+"\n";
        }
        return saida;
    }

    // Consertar
    // Demitir/Excluir funcionário pelo Cpf
    static public boolean remover(String cpf){
        for (Funcionario f : funcionarios){
            if (f.getCpf().equalsIgnoreCase(cpf)){
                funcionarios.remove(f);
                System.out.println("Funcionário foi demitido");  
                return true;
            }
        }
        return false;
    }

    // Método que busca um funcionario pelo nome
    public Funcionario consultarNome(String nome){
        for(Funcionario f : funcionarios){
            if(f.getNome().equalsIgnoreCase(nome)){
                System.out.println("Nome: "+f.getNome());
                System.out.println("Sobrenome"+f.getSobrenome());
                System.out.println("Cpf: "+f.getCpf());
                break;
            }else{
                System.out.println("Contato não encontrado!");
                break;
            }
        }
        return null;
    }

    // Método que busca um funcionario pelo cpf
    public Funcionario consultarCpf(String cpf){
        for(Funcionario f : funcionarios){
            if(f.getCpf().equalsIgnoreCase(cpf)){
                System.out.println("Nome: "+f.getNome());
                System.out.println("Sobrenome"+f.getSobrenome());
                System.out.println("Cpf: "+f.getCpf());
                break;
            }else{
                System.out.println("Contato não encontrado!");
                break;
            }
        }
        return null;
    }

    // Método que define o setor
    public static String defineSetor(){
        String setor;
        
        Scanner ler1 = new Scanner(System.in);

        System.out.println("\nDefina o setor que o funcionário irá:");
        System.out.println("[1] Requisitos");
        System.out.println("[2] Modelagem");
        System.out.println("[3] Codificação");
        System.out.println("[4] TesteTI");
        System.out.println("[5] Recursos Humanos");
        
        switch (ler1.nextInt()){
            case 1:
                Setores req = Setores.REQ;
                setor = req.getSetores();
                return setor;

            case 2:
                Setores mod = Setores.MOD;
                setor = mod.getSetores();
                return setor;

            case 3:
                Setores cod = Setores.COD;
                setor = cod.getSetores();
                return setor;
                
            case 4:
                Setores test = Setores.TEST;
                setor = test.getSetores();
                return setor;

            case 5:
                Setores rh = Setores.RH;
                setor = rh.getSetores();
            return setor;

            default:
                System.out.println("Opção invalida");
        }
        return null;
    }

    // saber qual o retorno do defineSetor()
    // transformar o retorno em uma string
    public static String setor(){
        String setor="";
        setor = defineSetor();
        return setor;
    }

    public static String defineCargo(String setor){
        String cargo="";
        Scanner ler2 = new Scanner(System.in);
        int cont = 0, i=0;

        System.out.println("Defina o cargo entre: [1] Chefe [2] Colaborador");
        int op = ler2.nextInt();
        //setor = setor();
        
        if (op == 1) {
            for (Funcionario f : funcionarios) {
                if ( setor == f.getContrato().getSetor()) {
                    if (f.getContrato().getCargo() == "Chefe") {
                        System.out.println("ERRO! Há um chefe nesse setor!");
                        cont = 1;
                    }
                }    
            }
            if (cont == 0){
                return cargo="Chefe";
            }
        } else if (op == 2) {
            return cargo = "Colaborador";
        }
        return null;
    }

    public void atualizar(){
        Scanner ler = new Scanner(System.in);
        int id, op, cont = -1;

        System.out.print("Digite o ID do funcionário: ");
        id = ler.nextInt();

        for (Funcionario f : funcionarios){
            cont += 1;
            if (f.getContrato().getId() == id){
                Setores[] setores = Setores.values();
                // Imprime todos os dados do funcionario que está tendo os dados alterados
                System.out.println(f.imprimir());

                System.out.printf("Nome: ");
                String nome = ler.next();
                f.setNome(nome);

                System.out.printf("Sobrenome: ");
                String sobrenome = ler.next();
                f.setSobrenome(sobrenome);

                System.out.printf("Idade: ");
                int idade = ler.nextInt();
                f.setIdade(idade);

                System.out.println("Sexo:\n" + "1-Feminino " + " 2-Masculino ");
                int opcaoSexo = ler.nextInt();
                String sexo;
                if (opcaoSexo == 1) {
                    sexo = "feminino";
                } else if (opcaoSexo == 2) {
                    sexo = "masculino";
                } else {
                    sexo = "Indefinido";
                }
                f.setSexo(sexo);

                System.out.printf("Cpf: ");
                String cpf = ler.next();
                f.setCpf(cpf);

                System.out.printf("Email: ");
                String email = ler.next();
                f.setEmail(email);
                
                System.out.printf("Telefone: ");
                String telefone = ler.next();
                f.setTelefone(telefone);

                System.out.printf("Bairro: ");
                String bairro = ler.next();
                f.getEndereco().setBairro(bairro);

                System.out.printf("Cidade: ");
                String cidade = ler.next();
                f.getEndereco().setCidade(cidade);

                System.out.println("Estado: ");
                String estado = ler.next();
                f.getEndereco().setEstado(estado);

                // Setor 
                System.out.println("\nEscolha o setor: ");
                System.out.println("[1] Requisitos");
                System.out.println("[2] Modelagem");
                System.out.println("[3] Codificação");
                System.out.println("[4] TesteTI");
                System.out.println("[5] Recursos Humanos");
                int index = ler.nextInt();
                String setor = setores[index - 1].getSetores();
                
                // Cargo
                System.out.println("\nEscolha o cargo [1] Chefe [2] Colaborador: ");
                op = ler.nextInt();

                int i = -1;
                if (op == 1) {
                    for (Funcionario cargo : funcionarios) {
                        i++;
                        if (cargo.getContrato().getSetor() == setor) {
                            if (cargo.getContrato().getCargo() == "Chefe"){
                                System.out.println("ERRO! Há um chefe nesse setor!");
                                cont = 1;
                            }
                        }
                    }
                }
                if (cont == 0) {
                    f.getContrato().setCargo("Chefe");
                
                } else if (op == 2) {
                    f.getContrato().setCargo("Colaborador");
                }
                
                // Altera salário
                System.out.printf("Salário:");
                float salario = ler.nextFloat();
                f.getContrato().setSalario(salario);

                // Define o setor
                f.getContrato().setSetor(setores[index - 1].getSetores());

                // Imprime os dados já alterados
                System.out.println(f.imprimir());
            }
        }
    }

    public static void salarios() {  
        Scanner ler = new Scanner(System.in);
        System.out.println("Verificar qual é o salario - [1] Maior Salário [2] Menor Salário:");
        int opcaoSalario = ler.nextInt();
        String nome=null, sobrenome=null;
        String setor=null, cargo=null;

        if (opcaoSalario == 1) {
            int i = 0;
            float sal=0;

            for (Funcionario f : funcionarios){
                if (i == 0 ){
                    i++;
                    sal = f.getContrato().getSalario();
                    nome = f.getNome();
                    sobrenome = f.getSobrenome();
                    setor = f.getContrato().getSetor();
                    cargo = f.getContrato().getCargo(); 
                }else{
                    if (f.getContrato().getSalario() > sal){
                        sal = f.getContrato().getSalario();
                        nome = f.getNome();
                        sobrenome = f.getSobrenome();
                        setor = f.getContrato().getSetor();
                        cargo = f.getContrato().getCargo(); 
                    }
                }
            }
            System.out.println("Funcionário com maior salário:"+"Nome: "+nome+" Sobrenome: "+sobrenome);
            System.out.println("Funcionário com maior salário:"+"Setor: "+setor+ " Cargo: "+cargo);
        }

        if (opcaoSalario == 2) {
            int i = 0;
            float sal=0;

            for (Funcionario f : funcionarios){
                if (i == 0 ){
                    i++;
                    sal = f.getContrato().getSalario();
                    nome = f.getNome();
                    sobrenome = f.getSobrenome();
                    setor = f.getContrato().getSetor();
                    cargo = f.getContrato().getCargo(); 
                }else{
                    if (f.getContrato().getSalario() < sal){
                        sal = f.getContrato().getSalario();
                        nome = f.getNome();
                        sobrenome = f.getSobrenome();
                        setor = f.getContrato().getSetor();
                        cargo = f.getContrato().getCargo(); 
                    }
                }
            }
            System.out.println("Funcionário com maior salário: "+nome+" Sobrenome: "+sobrenome);
            System.out.println("Funcionário com maior salário:"+"Setor: "+setor+ " Cargo: "+cargo);
        }

    }

    // Método que verifica o seter com maior e menor num de funcionario
    public static void funcioarioSetor(){
        ArrayList<String> setores = new ArrayList<String>();
        Scanner ler = new Scanner(System.in);
        int op, i, cont=0;

        System.out.println("Visualizar setor [1] maior [2] menor número de funcionario");
        op = ler.nextInt();
        String setor=null, maior="", menor="";

        // Add todos os setores presentes na ArrayList em uma array
        for (Funcionario f : funcionarios){
            setor = f.getContrato().getSetor();
            if (f.getContrato().getSetor() == setor){
                setores.add(setor);
            }
        }

        // ArrayList<String> listAux = new ArrayList<String>(); 

        for (i = 0; i < setores.size(); i++){
            int quantidade = 0;
            for (int j = 0; j < setores.size(); j++){
                if (setores.get(i) == setores.get(j)){
                    quantidade++;
                }
            }

            if (quantidade > cont){
                maior = setores.get(i);
                cont = quantidade;
            }

            if (quantidade < cont){
                menor = setores.get(i);
                cont = quantidade;
            } 
        }
        if (op == 1){    
            System.out.println("O setor "+maior+" é o maior com "+cont+" funcionario(s)");
        }
        
        if (op == 2){
            System.out.println("O setor "+menor+" é o  com menor "+cont+" funcionario(s)");
        }
    }

    public static void categorias(){
        Scanner ler = new Scanner(System.in);
        int i, op;
        String categoria;
        Setores[] setores = Setores.values();

        System.out.println("Escolher Categoria: \n" + "[1] Setor\n" + "[2] Sexo\n" + "[3] Estado");
        op = ler.nextInt();
        
        switch(op){
            // Caso em que a procura é pelo Setor
            case 1:
                System.out.println("\nEscolha o setor: ");
                System.out.println("[1] Requisitos");
                System.out.println("[2] Modelagem");
                System.out.println("[3] Codificação");
                System.out.println("[4] TesteTI");
                System.out.println("[5] Recursos Humanos");
                
                i = ler.nextInt();
                categoria = setores[i - 1].getSetores();

                for (Funcionario f : funcionarios){
                    if (f.getContrato().getSetor() == categoria){
                        System.out.println("Nome: "+f.getNome()+" "+ f.getSobrenome());
                    }
                }
                break;
            // Caso em que a procura é pelo Sexo
            case 2:
                System.out.println("Escolha entre [1] masculino [2] feminino: ");
                op = ler.nextInt();
                if (op==1){
                    categoria = "feminino";
                } else {
                    categoria = "masculino";
                }
                for (Funcionario f : funcionarios){
                    if (f.getSexo() == categoria){
                        System.out.println("Nome: "+f.getNome()+" "+ f.getSobrenome());
                    }
                }
                break;
            // Caso em que a procura é pelo Estado
            case 3:
                System.out.println("Digite a sigla do Estado:");
                categoria = ler.next();
                for (Funcionario f : funcionarios) {
                    String estado = f.getEndereco().getEstado();

                    int diffEstado = estado.indexOf(categoria);

                    if (diffEstado == 0) {
                        System.out.println("Nome: "+f.getNome()+" "+ f.getSobrenome());
                    }
                }
                break;
        }
    }

    // Exibir informações da empresa
    public static void infoEmpresa(Empresa empresa){
        System.out.println("Nome: "+empresa.getNome());
        System.out.println("Endereço:\n"+"Bairro: "+empresa.endereco.getBairro()
        +"\nCidade: "+empresa.endereco.getCidade()+"\nEstado: "+empresa.endereco.getEstado());
        System.out.println("Telefone: "+empresa.getTelefone());
        System.out.println("Email: "+empresa.getEmail());
        System.out.println("CNPJ: "+empresa.getCNPJ());
        System.out.println("Dono: "+empresa.getDono());
    }



}
