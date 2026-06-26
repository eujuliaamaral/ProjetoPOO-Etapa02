public class PagamentoDinheiro extends Pagamento {
    public PagamentoDinheiro(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase, "dinheiro");
    }

    @Override
    public double calcularValorFinal() {
        return calcularValor(getValorBase(), 5);
    }
}
