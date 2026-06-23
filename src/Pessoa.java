public abstract class Pessoa {
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;

    public Pessoa(String nome, String cpf, int idade, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public int getIdade(){
        return idade;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public abstract String exibirResumo();
}
