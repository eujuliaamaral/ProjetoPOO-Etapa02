import java.util.List;

public class Fisioterapeuta extends Profissional{
    private int totalSessoesPrevistas;

    public Fisioterapeuta(String nome, String cpf, int idade, String telefone, 
                          String registro, double valor, int totalSessoesPrevistas, List<String> dias){
        super(nome, cpf, idade, telefone, "fisioterapia", registro, valor, dias);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    public Fisioterapeuta(String nome, String registro, double valor) {
        super(nome, "", 0, "", "fisioterapia", registro, valor);
        this.totalSessoesPrevistas = 0;
    }

    public Fisioterapeuta(String nome) {
        super(nome, "fisioterapia");
        this.totalSessoesPrevistas = 0;
    }

    public int getTotalSessoesPrevistas(){
        return totalSessoesPrevistas;
    }

    public void setTotalSessoesPrevistas(int totalSessoesPrevistas){
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    @Override
    public String exibirResumo(){
        return "Fisioterapeuta: " + getNome() + " | Registro: " + getRegistroProfissional() + 
               " | Sessões Previstas: " + getTotalSessoesPrevistas();
    }
}
