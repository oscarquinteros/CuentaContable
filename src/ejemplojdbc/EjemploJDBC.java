package ejemplojdbc;

import ejemplojdbc.excepciones.CuentaInexistenteException;
import ejemplojdbc.excepciones.DataAccessException;
import ejemplojdbc.excepciones.SaldoInsuficienteException;

public class EjemploJDBC {

    public static void main(String[] args) {
        try {
            GestorCuentas gestor = new GestorCuentas();
            gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
            gestor.agregarCuenta(new Cuenta("2", "ventas", 0f));
            gestor.agregarCuenta(new Cuenta("3", "compras", 0f));
            gestor.acreditar("1", 100.00f);
            gestor.acreditar("2", 330.00f);
            gestor.acreditar("3", 540.00f);
            gestor.debitar("1", 50.00f);
            gestor.debitar("2", 30.00f);
            gestor.debitar("3", 40.00f);
        } catch (DataAccessException ex) {
            System.out.println(ex);
        } catch (SaldoInsuficienteException ex) {
            System.out.println(ex);
        } catch (CuentaInexistenteException ex) {
            System.out.println(ex);
        } finally {
            System.out.println("Otras operaciones");
        }
    }
}
