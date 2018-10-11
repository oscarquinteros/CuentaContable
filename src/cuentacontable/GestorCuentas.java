package cuentacontable;

import cuentacontable.excepciones.CuentaInexistenteException;
import cuentacontable.persistencia.ICuentaDAO;
import java.util.ArrayList;

public class GestorCuentas {

    private final ICuentaDAO cuentaDAO;

    public GestorCuentas(ICuentaDAO cuentaDAO) {
        this.cuentaDAO = cuentaDAO;
    }

    public ArrayList getCuentas() {
        return (ArrayList) cuentaDAO.findAll();
    }

    public Cuenta getCuenta(String numero) {
        Cuenta cuenta = cuentaDAO.findByPK(numero);
        if (cuenta!=null){
            return cuenta;
        }
        throw new CuentaInexistenteException("Cuenta Inexistente: "+numero);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentaDAO.insert(cuenta);        
    }
    
    public void eliminarCuenta(String numero) {
        cuentaDAO.delete(numero);        
    }

    public void debitar(String numero, Float importe) {
        Cuenta cuenta = getCuenta(numero);
        cuenta.debitar(importe);
        cuentaDAO.update(cuenta);
    }

    public void acreditar(String numero, Float importe) {
        Cuenta cuenta = getCuenta(numero);
        cuenta.acreditar(importe);
        cuentaDAO.update(cuenta);
    }

    public Float getSaldo(String numero) {
        return getCuenta(numero).getSaldo();
    }
}
