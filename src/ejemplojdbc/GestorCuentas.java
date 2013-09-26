package ejemplojdbc;

import ejemplojdbc.persistencia.CuentaDAO;
import ejemplojdbc.excepciones.DataAccessException;
import ejemplojdbc.excepciones.SaldoInsuficienteException;
import ejemplojdbc.excepciones.CuentaInexistenteException;
import java.util.ArrayList;

public class GestorCuentas {
    private ArrayList cuentas = new ArrayList();
    private CuentaDAO cuentaDAO = new CuentaDAO();
    
    public GestorCuentas() throws DataAccessException {
        cuentas=(ArrayList)cuentaDAO.findAll();
    }
    
    public void agregarCuenta (Cuenta cuenta) throws DataAccessException{
        cuentaDAO.insert(cuenta);
        cuentas.add(cuenta);
    }
    public void debitar(String numero,Float importe) throws SaldoInsuficienteException, CuentaInexistenteException, DataAccessException{
        for (Object obj:cuentas){
            Cuenta cuenta =(Cuenta)obj;
            if (cuenta.getNumero().equals(numero)){
                cuenta.debitar(importe);
                cuentaDAO.update(cuenta);
                return;
            }            
        }
        throw new CuentaInexistenteException("Cuenta Inexistente:"+numero);
    }
    public void acreditar(String numero,Float importe) throws CuentaInexistenteException, DataAccessException {
        for (Object obj:cuentas){
            Cuenta cuenta =(Cuenta)obj;
            if (cuenta.getNumero().equals(numero)){
                cuenta.acreditar(importe);
                cuentaDAO.update(cuenta);
                return;
            }            
        }     
        throw new CuentaInexistenteException("Cuenta Inexistente:"+numero);
    }    
}
