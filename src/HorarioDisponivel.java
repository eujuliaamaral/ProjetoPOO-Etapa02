public class HorarioDisponivel {
    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana) {
        this(diaSemana, "integral");
    }

    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana == null || diaSemana.trim().isEmpty()) {
            this.diaSemana = "";
            return;
        }
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            this.turno = "integral";
            return;
        }
        this.turno = turno;
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }
}
