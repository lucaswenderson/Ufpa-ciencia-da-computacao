public class Empresa {
    private String nome, email, dono;
    protected Endereco endereco;
    private String telefone, cnpj;

    public Empresa(){

    }

    public Empresa(String nome, Endereco endereco, String telefone, String cnpj, String email, String dono) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.email = email;
        this.dono = dono;
    }

    // get e set nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // get e set endereco
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // get e set telefone
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // get e set cnpj
    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }
    // get e set email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // get e set email
    public String getDono() {
        return dono;
    }

    public void setdono(String dono) {
        this.dono = dono;
    }
}
