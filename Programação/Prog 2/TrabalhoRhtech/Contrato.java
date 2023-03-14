public class Contrato {
    private String setor;
    private int id;
    private float salario;
    private String cargo;

    public Contrato(){
        
    }

    public Contrato(String setor, int id, float salario, String cargo){
        this.setor = setor;
        this.id = id;
        this.salario = salario;
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String imprimirContrato(){
        return  "\nSetor: "+setor
                +"\nId: "+id
                +"\nSalário: "+salario
                +"\nCargo: "+cargo;
    }
}
