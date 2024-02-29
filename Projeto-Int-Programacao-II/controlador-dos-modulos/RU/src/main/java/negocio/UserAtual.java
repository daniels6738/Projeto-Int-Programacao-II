package negocio;


public class UserAtual {
    private static UserAtual instancia = null;
    private String cpf;
    private String nome;

    private UserAtual(){
        this.cpf = null;
    }

    public static synchronized UserAtual getInstance(){
        if(instancia == null) instancia = new UserAtual();
        return instancia;
    }


    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
