public class Paciente extends Pessoa{
    // ASSOCIACAO: Paciente conhece Convenio, mas ambos existem independentemente.
    private Convenio convenio;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome,cpf, 0, "");
        this.convenio = null;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, idade, telefone); 
        this.convenio = null;
        this.ativo = true;
    }

    // construtor com todos os dados
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {
        super(nome, cpf, idade, telefone);
        setConvenio(convenioNome);
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, idade, telefone);
        this.convenio = convenio;
        this.ativo = true;
    }

    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, String convenioNome) {
        setIdade(idade);
        setTelefone(telefone);
        setConvenio(convenioNome);
    }

    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        setTelefone(telefone);
        this.convenio = convenio;
    }

    public void desativar() {
        this.ativo = false;
    }

    public String getConvenio() {
        if (convenio == null) {
            return "";
        }
        return convenio.getNome();
    }

    public Convenio getConvenioObjeto() {
        return convenio;
    }

    public void setConvenio(String convenioNome) {
        if (convenioNome == null || convenioNome.trim().isEmpty()) {
            this.convenio = null;
            return;
        }
        this.convenio = new Convenio(convenioNome);
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!getAtivo()) {
            status = "Nao";
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + getIdade()
                + " | Tel: " + getTelefone() + " | Convenio: " + getConvenio()
                + " | Ativo: " + status;
    }
}
