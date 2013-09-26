package ejemplojdbc.excepciones;

public class DataAccessException extends Exception {
    public DataAccessException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
