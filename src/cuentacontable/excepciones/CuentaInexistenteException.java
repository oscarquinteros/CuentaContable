package cuentacontable.excepciones;

public class CuentaInexistenteException extends RuntimeException {
    public CuentaInexistenteException(String mensaje) {
        super(mensaje);
    }
}
