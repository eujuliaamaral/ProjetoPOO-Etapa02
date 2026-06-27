import java.util.List;

public class ClinicoGeral extends Profissional{
    private String encaminhamento;

    public ClinicoGeral(String nome, String cpf, int idade, String telefone, 
                         String registro, double valor, String encaminhamento, List<String> dias){
        super(nome, cpf, idade, telefone, "clinica geral", registro, valor, dias);
        this.encaminhamento = encaminhamento;                 
    }

    public ClinicoGeral(String nome, String registro, double valor) {
        super(nome, "", 0, "", "clinica geral", registro, valor);
        this.encaminhamento = "Nao informado";
    }

    public ClinicoGeral(String nome) {
        super(nome, "clinica geral");
        this.encaminhamento = "Nao informado";
    }

    public String getEncaminhamento(){
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento){
        this.encaminhamento = encaminhamento;
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("Registro clinico geral: encaminhamento - " + getEncaminhamento());
    }

    @Override
    public String exibirResumo(){
        return "CLinico Geral: " + getNome() + " | Registro: " + getRegistroProfissional() + 
               " | Encaminhamento: " + getEncaminhamento();
    }
}
