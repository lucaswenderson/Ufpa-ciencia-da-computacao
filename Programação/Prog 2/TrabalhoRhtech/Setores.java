public enum Setores{
    REQ("Requisitos"),
    MOD("Modelagem"),
    COD("Codificação"),
    TEST("Teste"),
    RH("Recursos Humanos");

    //Requisitos, Modelagem,Codificação, Teste e Recursos Humanos

    private final String setores;

    private Setores(String setores){
        this.setores = setores;
    }

    public String getSetores() {
        return setores;
    }

}