public class ConvenioNaoCobreException extends ClinicaException {

    public ConvenioNaoCobreException(String mensagem) {
        super(mensagem);
    }

    public ConvenioNaoCobreException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
