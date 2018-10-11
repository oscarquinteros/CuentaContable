package cuentacontable.persistencia;

import cuentacontable.Cuenta;
import cuentacontable.excepciones.CuentaExistenteException;
import cuentacontable.excepciones.CuentaInexistenteException;
import cuentacontable.excepciones.DataAccessException;
import java.util.ArrayList;
import java.util.Collection;

public class ArrayCuentaDAO implements ICuentaDAO{
    private final ArrayList cuentas = new ArrayList();
    
    @Override
    public void delete(String numero) {
        Cuenta existe = findByPK(numero);
        if (existe != null) {
            cuentas.remove(existe);            
            return;
        }        
        throw new CuentaInexistenteException("Cuenta inexistente: " + numero);
    }
    
    @Override
    public Cuenta findByPK(String vnumero)  {
        Cuenta resultado = null;
         for (Object cuenta : cuentas) {
            Cuenta c = (Cuenta)cuenta;
            if (c.getNumero().equals(vnumero)==true){
                resultado=c;
            }
        }
        return resultado;
    }
    
    @Override
    public Collection findAll(){       
        return cuentas;
    }        
    
    @Override
    public void insert(Cuenta insertRecord) {
        Cuenta existe = findByPK(insertRecord.getNumero());
        if (existe != null) {
            throw new CuentaExistenteException("Cuenta existente " + existe);
        }
        cuentas.add(insertRecord);
    }

    @Override
    public void update(Cuenta updateRecord) throws DataAccessException {
    }    
}
