public abstract class Pagamento implements Exportavel {
    private int indiceConsulta;
    private double valorBase;
    private String tipoPagamento;
    private int parcelas;

    public Pagamento(int indiceConsulta, double valorFinal, String tipoPagamento) {
        this(indiceConsulta, valorFinal, tipoPagamento, 1);
    }

    // com parcelas (so pra cartao)
    public Pagamento(int indiceConsulta, double valorFinal, String tipoPagamento, int parcelas) {
        setIndiceConsulta(indiceConsulta);
        setValorBase(valorFinal);
        setTipoPagamento(tipoPagamento);
        setParcelas(parcelas);
    }

    // sem desconto nenhum
    public static double calcularValor(double valorBase) {
        return valorBase;
    }

    // com desconto em percentual
    public static double calcularValor(double valorBase, double percentualDesconto) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    // com desconto e multa somada
    public static double calcularValor(double valorBase, double percentualDesconto, double multa) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto + multa;
        if (valor < 0) {
            valor = 0;
        }
        return valor;
    }

    public abstract double calcularValorFinal();

    public String exibirResumo() {
        // arredonda pra 2 casas
        double valorFinal = calcularValorFinal();
        double valorArredondado = Math.round(valorFinal * 100.0) / 100.0;
        String resumo = "Consulta #" + indiceConsulta + " | Valor: R$" + valorArredondado
                + " | Tipo: " + tipoPagamento + " | Parcelas: " + parcelas;
        if (parcelas > 1) {
            double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
            resumo = resumo + " (R$" + valorParcela + " cada)";
        }
        return resumo;
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

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        if (valorBase < 0) {
            this.valorBase = 0;
            return;
        }
        this.valorBase = valorBase;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        if (tipoPagamento == null || tipoPagamento.trim().isEmpty()) {
            this.tipoPagamento = "nao informado";
            return;
        }
        this.tipoPagamento = tipoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        if (parcelas < 1) {
            this.parcelas = 1;
            return;
        }
        this.parcelas = parcelas;
    }
}
