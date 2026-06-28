public class ConsultaNaoEncontradaException extends ClinicaException {

    public ConsultaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ConsultaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
