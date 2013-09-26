package ejemplojdbc.persistencia;

import ejemplojdbc.Cuenta;
import ejemplojdbc.excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class CuentaDAO {
    public void delete(String vnumero) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            smt.executeUpdate("Delete from cuenta where numero='"+vnumero+"'");
            smt.close();
        } catch (Exception e) {
            throw new DataAccessException("Error en CuentaDAO.delete() "+e);
        }
    }
    public Cuenta findByPK(String vnumero) throws DataAccessException {
         try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cuenta where numero='"+vnumero+"'");            
            Cuenta cuenta = null;
            while (result.next())  {
                cuenta = new Cuenta();
                cuenta.setNumero(result.getString("numero"));
                cuenta.setNombre(result.getString("nombre"));
                cuenta.setSaldo(result.getFloat("saldo"));
            }
            result.close();
            smt.close();
            return cuenta;
        } catch (Exception e) {
            throw new DataAccessException("Error en CuentaDAO.findByPK() "+e);
        }
    }
    public Collection findAll() throws DataAccessException{       
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cuenta order by numero");            
            Cuenta cuenta = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                cuenta = new Cuenta();
                cuenta.setNumero(result.getString("numero"));
                cuenta.setNombre(result.getString("nombre"));
                cuenta.setSaldo(result.getFloat("saldo"));
                array.add(cuenta);
            }
            result.close();
            smt.close();
            return array;
        } catch (Exception e) {
            throw new DataAccessException("Error en CuentaDAO.findAll() "+e);
        }        
    }        
    public void insert(Cuenta insertRecord) throws DataAccessException {
        try {
            Cuenta existe = findByPK(insertRecord.getNumero());
            if (existe!=null) {
                throw new DataAccessException("Cuenta existente en CuentaDAO.insert()");
            }
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("Insert into cuenta (numero,nombre,saldo) values (?,?,?)");
            smt.setString(1,insertRecord.getNumero());
            smt.setString(2,insertRecord.getNombre());
            smt.setFloat(3,insertRecord.getSaldo());
            smt.execute();            
        } catch (Exception e) {
            throw new DataAccessException("Error en CuentaDAO.insert() "+e);
        }
    }
    public void update(Cuenta updateRecord) throws DataAccessException {
       try {
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("Update cuenta set nombre=?,saldo=? where numero=?");
            smt.setString(1,updateRecord.getNombre());
            smt.setFloat(2,updateRecord.getSaldo());
            smt.setString(3,updateRecord.getNumero());               
            smt.execute();                        
        } catch (Exception e) {
            throw new DataAccessException("Error en CuentaDAO.update() "+e);
        }
    }    
}
