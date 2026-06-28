import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClinicaServico {
    // ArrayList<...>: a ordem de insercao importa e precisamos de acesso por indice (get(i))
    private List<Paciente> pacientes;
    private List<Profissional> profissionais;
    private List<Consulta> consultas;
    private List<Atendimento> atendimentos;
    private List<Pagamento> pagamentos;
    private List<Double> multas;
    private List<Pessoa> pessoas;

    // HashSet<String>: so precisamos verificar existencia (contains/add) de CPF; ordem nao importa
    private Set<String> cpfsCadastrados;

    // HashMap<String, ...>: busca direta por chave (CPF / nome), mais eficiente que percorrer a lista
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

    public Paciente buscarPacienteObrigatorio(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = buscarPacientePorCpf(cpf);
        if (paciente == null) {
            throw new PacienteNaoEncontradoException("Paciente com CPF '" + cpf + "' nao encontrado.");
        }
        return paciente;
    }

    public Profissional buscarProfissionalObrigatorio(String nome) throws ProfissionalNaoEncontradoException {
        Profissional profissional = buscarProfissionalPorNome(nome);
        if (profissional == null) {
            throw new ProfissionalNaoEncontradoException("Profissional '" + nome + "' nao encontrado.");
        }
        return profissional;
    }

    // verifica se ja existe consulta agendada nesse horario com esse profissional
    private boolean existeConflito(String nomeProfissional, String data, String horario) {
        for (Consulta consulta : consultas) {
            if (consulta.getNomeProfissional().equals(nomeProfissional)
                    && consulta.getData().equals(data)
                    && consulta.getHorario().equals(horario)
                    && consulta.getStatus().equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    public Consulta agendarConsulta(String cpf, String nomeProfissional, String data,
                                    String horario, String tipo, String diaSemana)
            throws PacienteNaoEncontradoException, PacienteInativoException,
                   ProfissionalNaoEncontradoException, HorarioIndisponivelException,
                   OperacaoInvalidaException {

        Paciente paciente = buscarPacienteObrigatorio(cpf);
        if (!paciente.getAtivo()) {
            throw new PacienteInativoException("Paciente " + paciente.getNome() + " esta inativo e nao pode agendar.");
        }

        Profissional profissional = buscarProfissionalObrigatorio(nomeProfissional);
        if (profissional.getValorConsulta() <= 0) {
            throw new OperacaoInvalidaException("Profissional sem valor de consulta definido. Nao pode agendar.");
        }
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException("Profissional nao atende em " + diaSemana + ".");
        }
        if (existeConflito(nomeProfissional, data, horario)) {
            throw new HorarioIndisponivelException("Horario " + horario + " ja esta ocupado em " + data + ".");
        }

        Consulta consulta = new Consulta(cpf, nomeProfissional, data, horario, tipo);
        consultas.add(consulta);
        return consulta;
    }

    public Consulta buscarConsulta(String cpf, String data, String horario)
            throws ConsultaNaoEncontradaException {
        for (Consulta consulta : consultas) {
            if (consulta.getCpfPaciente().equals(cpf)
                    && consulta.getData().equals(data)
                    && consulta.getHorario().equals(horario)) {
                return consulta;
            }
        }
        throw new ConsultaNaoEncontradaException(
                "Consulta nao encontrada para CPF " + cpf + " em " + data + " " + horario + ".");
    }

    public void cancelarConsulta(Consulta consulta) throws OperacaoInvalidaException {
        if (consulta.getStatus().equals("realizada")) {
            throw new OperacaoInvalidaException("Consulta ja realizada. Nao pode ser cancelada.");
        }
        if (consulta.getStatus().equals("cancelada")) {
            throw new OperacaoInvalidaException("Consulta ja esta cancelada.");
        }
        consulta.cancelar();
    }

    public Atendimento registrarAtendimento(int indiceConsulta, Atendimento atendimento)
            throws ConsultaNaoEncontradaException, OperacaoInvalidaException {
        if (indiceConsulta < 0 || indiceConsulta >= consultas.size()) {
            throw new ConsultaNaoEncontradaException("Indice de consulta invalido: " + indiceConsulta);
        }
        Consulta consulta = consultas.get(indiceConsulta);
        if (!consulta.getStatus().equals("agendada")) {
            throw new OperacaoInvalidaException("So e possivel registrar atendimento em consulta agendada.");
        }
        Profissional profissional = buscarProfissionalPorNome(consulta.getNomeProfissional());
        if (profissional != null) {
            profissional.registrarEspecifico(atendimento);
        }
        atendimentos.add(atendimento);
        consulta.realizar();
        return atendimento;
    }

    public Pagamento criarPagamento(int indiceConsulta, double valor, String tipoPagamento, int parcelas)
            throws PagamentoInvalidoException, ConvenioNaoCobreException {

        if (indiceConsulta < 0 || indiceConsulta >= consultas.size()) {
            throw new PagamentoInvalidoException("Consulta invalida para pagamento: " + indiceConsulta);
        }
        if (valor < 0) {
            throw new PagamentoInvalidoException("Valor de pagamento nao pode ser negativo.");
        }

        if (tipoPagamento.equals("dinheiro") || tipoPagamento.equals("pix")) {
            return new PagamentoDinheiro(indiceConsulta, valor);
        }

        if (tipoPagamento.equals("cartao")) {
            if (parcelas < 1 || parcelas > 6) {
                throw new PagamentoInvalidoException("Parcelas devem estar entre 1 e 6. Informado: " + parcelas);
            }
            return new PagamentoCartao(indiceConsulta, valor, parcelas);
        }

        if (tipoPagamento.equals("convenio")) {
            Consulta consulta = consultas.get(indiceConsulta);
            Paciente paciente = buscarPacientePorCpf(consulta.getCpfPaciente());
            Convenio convenio = (paciente != null) ? paciente.getConvenioObjeto() : null;
            if (convenio == null) {
                throw new PagamentoInvalidoException("Paciente sem convenio cadastrado para pagamento por convenio.");
            }
            Profissional profissional = buscarProfissionalPorNome(consulta.getNomeProfissional());
            String especialidade = (profissional != null) ? profissional.getEspecialidade() : "";
            if (!convenio.cobreEspecialidade(especialidade)) {
                throw new ConvenioNaoCobreException(
                        "Convenio " + convenio.getNome() + " nao cobre a especialidade '" + especialidade + "'.");
            }
            return new PagamentoConvenio(indiceConsulta, valor, convenio);
        }

        throw new PagamentoInvalidoException("Tipo de pagamento nao reconhecido: " + tipoPagamento);
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
