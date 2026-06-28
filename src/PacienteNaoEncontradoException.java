public class PacienteNaoEncontradoException extends ClinicaException {

    public PacienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PacienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
