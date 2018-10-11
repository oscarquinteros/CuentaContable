package cuentacontable.excepciones;

public class CuentaExistenteException extends RuntimeException {
    public CuentaExistenteException(String mensaje) {
        super(mensaje);
    }
}
