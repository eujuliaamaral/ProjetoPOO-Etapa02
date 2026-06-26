import java.util.List;

public class Atendimento implements Exportavel {
    // COMPOSIÇÃO: Prontuário só existe dentro de Atendimento — se Atendimento for removido, Prontuário também é.
    public int indiceConsulta;
    private Prontuario prontuario;

    // registro basico - so observacoes
    public Atendimento(int indiceConsulta, String observacoes, String data) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, "", data);
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico, String data) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, diagnostico, data);
    }

    // registro completo com procedimentos ja definidos
    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       List<String> procedimentos, String data) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, diagnostico, data);
        this.prontuario.adicionarProcedimentos(procedimentos);
    }

    // adiciona um por vez
    public void adicionarProcedimento(String procedimento) {
        this.prontuario.adicionarProcedimento(procedimento);
    }

    // adiciona varios de uma vez
    public void adicionarProcedimento(List<String> procedimentos) {
        this.prontuario.adicionarProcedimentos(procedimentos);
    }

    public String exibirResumo() {
        StringBuilder resumo = new StringBuilder("Data: " + prontuario.getDataRegistro());
        resumo.append("\nObservacoes: ").append(prontuario.getObservacoes());

        if (prontuario.getDiagnostico() != null && !prontuario.getDiagnostico().isEmpty()) {
            resumo.append("\nDiagnostico: ").append(prontuario.getDiagnostico());
        }

        if (!prontuario.getProcedimentos().isEmpty()) {
            resumo.append("\nProcedimentos: ");
            List<String> procs = prontuario.getProcedimentos();
            for (int i = 0; i < procs.size(); i++) {
                resumo.append(procs.get(i));
                if (i < procs.size() - 1) {
                    resumo.append(", ");
                }
            }
        }
        return resumo.toString();
    }

    @Override
    public String exportarDados() {
        return exibirResumo();
    }

    public int getIndiceConsulta() {
        return indiceConsulta;
    }

    public void setIndiceConsulta(int indiceConsulta) {
        this.indiceConsulta = indiceConsulta;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }
}
