import java.util.ArrayList;
import java.util.List;

public abstract class Profissional extends Pessoa{
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<String> diasDisponiveis = new ArrayList<>();

    // so nome e especialidade
    public Profissional(String nome, String especialidade) {
        super(nome, "", 0, "");
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
    }

    public Profissional(String nome, String cpf, int idade, String telefone, String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf, idade, telefone); 
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
    }

    // construtor completo com dias
    public Profissional(String nome, String cpf, int idade, String telefone, String especialidade, String registroProfissional,
                        double valorConsulta, List<String> dias) {
        super(nome, cpf, idade, telefone); 
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<>(dias);
    }

    public void atualizar(int idade, String telefone, String registro, double valor) {
        setIdade(idade);
        setTelefone(telefone);
        this.registroProfissional = registro;
        this.valorConsulta = valor;
    }

    public void atualizar(int idade, String telefone, String registro, double valor, List<String> dias) {
        setIdade(idade);
        setTelefone(telefone);
        this.registroProfissional = registro;
        this.valorConsulta = valor;
        this.diasDisponiveis = new ArrayList<>(dias);
    }

    // verifica se o profissional atende naquele dia
    public boolean atendeNoDia(String dia) {
        return diasDisponiveis.contains(dia);
    }

    // valida as especialidades aceitas pela clinica
    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    public String getEspecialidade(){
        return especialidade; 
    }
    public String getRegistroProfissional(){ 
        return registroProfissional; 
    }
    public double getValorConsulta(){ 
        return valorConsulta; 
    }
    public List<String> getDiasDisponiveis(){ 
        return diasDisponiveis; 
    }

    public abstract String exibirResumo();
}
