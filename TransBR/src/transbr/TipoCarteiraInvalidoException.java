package transbr;

public class TipoCarteiraInvalidoException extends RuntimeException {
    public TipoCarteiraInvalidoException(String error){
        super(error);
    }
}
