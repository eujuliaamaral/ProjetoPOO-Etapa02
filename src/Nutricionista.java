import java.util.List;

public class Nutricionista extends Profissional{
    private String planoAlimentar;

    public Nutricionista(String nome, String cpf, int idade, String telefone, 
                         String registro, double valor, String planoAlimentar, List<String> dias){
        super(nome, cpf, idade, telefone, "nutricao", registro, valor, dias);
        this.planoAlimentar = planoAlimentar;                 
    }

    public Nutricionista(String nome, String registro, double valor) {
        super(nome, "", 0, "", "nutricao", registro, valor);
        this.planoAlimentar = "Nao informado";
    }

    public Nutricionista(String nome) {
        super(nome, "nutricao");
        this.planoAlimentar = "Nao informado";
    }


    public String getPlanoAlimentar(){
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar){
        this.planoAlimentar = planoAlimentar;
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("Registro de nutricao: plano alimentar - " + getPlanoAlimentar());
    }

    @Override
    public String exibirResumo(){
        return "Nutricionista: " + getNome() + " | Registro: " + getRegistroProfissional() + 
               " | Plano Alimentar: " + getPlanoAlimentar();
    }
}
