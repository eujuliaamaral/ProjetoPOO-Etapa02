public class PagamentoConvenio extends Pagamento {
    private Convenio convenio;

    public PagamentoConvenio(int indiceConsulta, double valorBase, Convenio convenio) {
        super(indiceConsulta, valorBase, "convenio");
        this.convenio = convenio;
    }

    @Override
    public double calcularValorFinal() {
        if (convenio == null) {
            return getValorBase();
        }
        return calcularValor(getValorBase(), convenio.getPercentualCobertura());
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
}
