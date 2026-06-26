import java.util.ArrayList;
import java.util.List;

public class Convenio {
    private String nome;
    private double percentualCobertura;
    private List<String> especialidadesCobertas;

    public Convenio(String nome) {
        this(nome, percentualPadrao(nome), especialidadesPadrao(nome));
    }

    public Convenio(String nome, double percentualCobertura, List<String> especialidadesCobertas) {
        setNome(nome);
        setPercentualCobertura(percentualCobertura);
        setEspecialidadesCobertas(especialidadesCobertas);
    }

    private static double percentualPadrao(String nome) {
        if (nome == null) return 0;
        if (nome.equalsIgnoreCase("SaudePlus")) return 40;
        if (nome.equalsIgnoreCase("VidaMais")) return 30;
        if (nome.equalsIgnoreCase("BemEstar")) return 50;
        return 0;
    }

    private static List<String> especialidadesPadrao(String nome) {
        List<String> especialidades = new ArrayList<>();
        if (nome == null || nome.trim().isEmpty()) {
            return especialidades;
        }
        especialidades.add("clinica geral");
        especialidades.add("fisioterapia");
        especialidades.add("psicologia");
        especialidades.add("nutricao");
        return especialidades;
    }

    public boolean cobreEspecialidade(String especialidade) {
        return especialidadesCobertas.contains(especialidade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "";
            return;
        }
        this.nome = nome;
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public void setPercentualCobertura(double percentualCobertura) {
        if (percentualCobertura < 0) {
            this.percentualCobertura = 0;
            return;
        }
        if (percentualCobertura > 100) {
            this.percentualCobertura = 100;
            return;
        }
        this.percentualCobertura = percentualCobertura;
    }

    public List<String> getEspecialidadesCobertas() {
        return new ArrayList<>(especialidadesCobertas);
    }

    public void setEspecialidadesCobertas(List<String> especialidadesCobertas) {
        if (especialidadesCobertas == null) {
            this.especialidadesCobertas = new ArrayList<>();
            return;
        }
        this.especialidadesCobertas = new ArrayList<>(especialidadesCobertas);
    }
}
