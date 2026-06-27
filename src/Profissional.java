import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;

public abstract class Profissional extends Pessoa{
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private List<String> diasDisponiveis = new ArrayList<>();
    private List<HorarioDisponivel> horariosDisponiveis = new ArrayList<>();

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
        setDiasDisponiveis(dias);
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
        setDiasDisponiveis(dias);
    }

    // verifica se o profissional atende naquele dia
    public boolean atendeNoDia(String dia) {
        for (HorarioDisponivel horario : horariosDisponiveis) {
            if (horario.getDiaSemana().equals(dia)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarHorario(HorarioDisponivel horario) {
        if (horario == null) {
            return;
        }
        horariosDisponiveis.add(horario);
        if (!diasDisponiveis.contains(horario.getDiaSemana())) {
            diasDisponiveis.add(horario.getDiaSemana());
        }
    }

    public void adicionarHorario(String diaSemana, String turno) {
        adicionarHorario(new HorarioDisponivel(diaSemana, turno));
    }

    private void setDiasDisponiveis(List<String> dias) {
        this.diasDisponiveis = new ArrayList<>();
        this.horariosDisponiveis = new ArrayList<>();
        if (dias == null) {
            return;
        }
        for (String dia : dias) {
            adicionarHorario(new HorarioDisponivel(dia));
        }
    }

    protected String resumoAgenda() {
        if (horariosDisponiveis.isEmpty()) {
            return "Sem horarios cadastrados";
        }

        StringBuilder resumo = new StringBuilder();
        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            resumo.append(horariosDisponiveis.get(i).exibirResumo());
            if (i < horariosDisponiveis.size() - 1) {
                resumo.append(", ");
            }
        }
        return resumo.toString();
    }

    // valida as especialidades aceitas pela clinica
    public static boolean especialidadeValida(String esp) {
        return !normalizarEspecialidade(esp).equals("");
    }

    public static String normalizarEspecialidade(String esp) {
        if (esp == null) return "";

        String normalizada = Normalizer.normalize(esp.trim().toLowerCase(), Normalizer.Form.NFD);
        normalizada = normalizada.replaceAll("\\p{M}", "");
        normalizada = normalizada.replaceAll("\\s+", " ");

        if (normalizada.equals("clinica geral") || normalizada.equals("clinico geral")) return "clinica geral";
        if (normalizada.equals("fisioterapia") || normalizada.equals("fisio")) return "fisioterapia";
        if (normalizada.equals("psicologia") || normalizada.equals("psicologo")) return "psicologia";
        if (normalizada.equals("nutricao") || normalizada.equals("nutricionista")) return "nutricao";
        return "";
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
        return new ArrayList<>(diasDisponiveis); 
    }

    public List<HorarioDisponivel> getHorariosDisponiveis(){
        return new ArrayList<>(horariosDisponiveis);
    }

    public abstract void registrarEspecifico();
    public abstract String exibirResumo();
}
