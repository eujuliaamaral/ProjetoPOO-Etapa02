import java.util.List;

public class Psicologo extends Profissional{
    private String abordagem;

    public Psicologo(String nome, String cpf, int idade, String telefone, 
                     String registro, double valor, String abordagem, List<String> dias){
        super(nome, cpf, idade, telefone, "psicologia",registro, valor, dias);
        this.abordagem = abordagem;
    }

    public Psicologo(String nome, String registro, double valor) {
        super(nome, "", 0, "", "psicologia", registro, valor);
        this.abordagem = "Nao informado";
    }

    public Psicologo(String nome) {
        super(nome, "psicologia");
        this.abordagem = "Nao informado";
    }

    public String getAbordagem(){
        return abordagem;
    }

    public void setAbordagem(String abordagem){
        this.abordagem = abordagem;
    }

    @Override
    public String exibirResumo(){
        return "Psicologo: " + getNome() + " | Registro: " + getRegistroProfissional() + 
               " | Abordagem: " + getAbordagem();
    }
}
