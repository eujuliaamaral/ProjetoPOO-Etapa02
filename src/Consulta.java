public class Consulta implements Agendavel, Exportavel {
    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    // sem tipo - assume inicial
    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this(cpfPaciente, nomeProfissional, data, horario, "inicial");
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this(cpfPaciente, nomeProfissional, data, horario, tipo, "agendada");
    }

    // esse aqui a gente usa na remarcacao pra poder setar o status direto
    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo, String status) {
        setCpfPaciente(cpfPaciente);
        setNomeProfissional(nomeProfissional);
        setData(data);
        setHorario(horario);
        setTipo(tipo);
        setStatus(status);
    }

    @Override
    public void agendar() {
        this.status = "agendada";
    }

    @Override
    public void cancelar() {
        this.status = "cancelada";
    }

    // cancelar com motivo - retorna a msg formatada
    public String cancelar(String motivo) {
        cancelar();
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar() {
        this.status = "remarcada";
    }

    public void realizar() {
        this.status = "realizada";
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    @Override
    public String exportarDados() {
        return exibirResumo();
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        if (cpfPaciente == null) {
            this.cpfPaciente = "";
            return;
        }
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        if (nomeProfissional == null) {
            this.nomeProfissional = "";
            return;
        }
        this.nomeProfissional = nomeProfissional;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data == null) {
            this.data = "";
            return;
        }
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        if (horario == null) {
            this.horario = "";
            return;
        }
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            this.tipo = "inicial";
            return;
        }
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            this.status = "agendada";
            return;
        }
        this.status = status;
    }
}
