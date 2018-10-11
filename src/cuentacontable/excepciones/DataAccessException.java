package cuentacontable.excepciones;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
