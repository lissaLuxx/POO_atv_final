package transbr;

public class CPFInvalidoException extends RuntimeException {
    public CPFInvalidoException(String error){
        super(error);
    }
}
