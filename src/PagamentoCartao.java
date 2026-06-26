public class PagamentoCartao extends Pagamento {
    public PagamentoCartao(int indiceConsulta, double valorBase, int parcelas) {
        super(indiceConsulta, valorBase, "cartao", parcelas);
    }

    @Override
    public void setParcelas(int parcelas) {
        if (parcelas < 1) {
            super.setParcelas(1);
            return;
        }
        if (parcelas > 6) {
            super.setParcelas(6);
            return;
        }
        super.setParcelas(parcelas);
    }

    @Override
    public double calcularValorFinal() {
        double valor = getValorBase();
        if (getParcelas() > 3) {
            int parcelasExtras = getParcelas() - 3;
            valor = valor + (valor * 0.025 * parcelasExtras);
        }
        return valor;
    }
}
