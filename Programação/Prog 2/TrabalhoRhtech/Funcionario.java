class Funcionario{
    private String nome; 
    private String sobrenome;
    private int idade;
    private String sexo; 
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;
    private Contrato contrato;

    //default
    public Funcionario(){

    }
    
    public Funcionario( 
        String nome, 
        String sobrenome, 
        int idade, 
        String sexo, 
        String cpf,
        String email,
        String telefone,
        Endereco endereco,
        Contrato contrato
        ) {
        this.nome= nome; 
        this.sobrenome= sobrenome;
        this.idade= idade;
        this.sexo= sexo; 
        this.cpf= cpf;
        this.email= email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.contrato = contrato;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        if (endereco == null) endereco = new Endereco();
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contrato getContrato() {
        if (contrato == null) contrato = new Contrato();
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    public String imprimir(){
        return  "Nome: "+nome+", Sobrenome: "+sobrenome+", Idade: "+idade
                +", Sexo: "+sexo+", Cpf: "+cpf+", Email: "+email+", Telefone: "+telefone
                +"\nBairro: "+endereco.getBairro()+", Cidade: "+endereco.getCidade()+", Estado: "+endereco.getEstado()
                +"\nSetor: "+contrato.getSetor()+", Id: "+contrato.getId()+", Salário: "+contrato.getSalario()
                +", Cargo: "+contrato.getCargo();
    }
 
}