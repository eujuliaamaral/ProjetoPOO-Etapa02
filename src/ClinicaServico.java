import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClinicaServico {
    private List<Paciente> pacientes;
    private List<Profissional> profissionais;
    private List<Consulta> consultas;
    private List<Atendimento> atendimentos;
    private List<Pagamento> pagamentos;
    private List<Double> multas;
    private List<Pessoa> pessoas;

    private Set<String> cpfsCadastrados;

    private Map<String, Paciente> pacientesPorCpf;
    private Map<String, Profissional> profissionaisPorNome;

    public ClinicaServico() {
        this.pacientes = new ArrayList<>();
        this.profissionais = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
        this.pagamentos = new ArrayList<>();
        this.multas = new ArrayList<>();
        this.pessoas = new ArrayList<>();
        this.cpfsCadastrados = new HashSet<>();
        this.pacientesPorCpf = new HashMap<>();
        this.profissionaisPorNome = new HashMap<>();
    }

    public boolean cadastrarPaciente(Paciente paciente) {
        if (paciente == null) {
            return false;
        }

        boolean cpfNovo = cpfsCadastrados.add(paciente.getCpf());
        if (!cpfNovo) {
            return false;
        }

        pacientes.add(paciente);
        pacientesPorCpf.put(paciente.getCpf(), paciente);
        pessoas.add(paciente);
        return true;
    }

    public void cadastrarProfissional(Profissional profissional) {
        if (profissional == null) {
            return;
        }
        profissionais.add(profissional);
        profissionaisPorNome.put(profissional.getNome(), profissional);
        pessoas.add(profissional);
    }

    public Paciente buscarPacientePorCpf(String cpf) {
        if (!pacientesPorCpf.containsKey(cpf)) {
            return null;
        }
        return pacientesPorCpf.get(cpf);
    }

    public Profissional buscarProfissionalPorNome(String nome) {
        if (!profissionaisPorNome.containsKey(nome)) {
            return null;
        }
        return profissionaisPorNome.get(nome);
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }

    public void adicionarPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    public void adicionarMulta(double valor) {
        multas.add(valor);
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public List<Double> getMultas() {
        return multas;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
