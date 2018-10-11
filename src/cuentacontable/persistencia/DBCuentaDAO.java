package cuentacontable.persistencia;

import cuentacontable.Cuenta;
import cuentacontable.excepciones.CuentaExistenteException;
import cuentacontable.excepciones.CuentaInexistenteException;
import cuentacontable.excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DBCuentaDAO implements ICuentaDAO{
    @Override
    public void delete(String numero) {
        try {
            Cuenta cuenta = findByPK(numero);
            if (cuenta==null){
               throw new CuentaInexistenteException("Cuenta inexistente: " + numero);
            }            
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            smt.executeUpdate("Delete from cuenta where numero='"+numero.trim()+"'");
            smt.close();
        } catch (SQLException e) {
            throw new DataAccessException("Error en DBCuentaDAO.delete() "+e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Error en DBCuentaDAO.delete() "+e);
        }
    }
    @Override
    public Cuenta findByPK(String numero) {
         try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cuenta where numero='"+numero.trim()+"'");            
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
        } catch (SQLException e) {
            throw new DataAccessException("Error en DBCuentaDAO.findByPK() "+e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Error en DBCuentaDAO.findByPK() "+e);
        }
    }
    @Override
    public Collection findAll() {       
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cuenta order by numero");            
            Cuenta cuenta = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                cuenta = new Cuenta();
                cuenta.setNumero(result.getString("numero").trim());
                cuenta.setNombre(result.getString("nombre").trim());
                cuenta.setSaldo(result.getFloat("saldo"));
                array.add(cuenta);
            }
            result.close();
            smt.close();
            return array;
        } catch (SQLException e) {
            throw new DataAccessException("Error en DBCuentaDAO.findAll() "+e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Error en DBCuentaDAO.findAll() "+e);
        }        
    }        
    @Override
    public void insert(Cuenta insertRecord) {
        try {
            Cuenta existe = findByPK(insertRecord.getNumero());
            if (existe!=null) {
                throw new CuentaExistenteException("Cuenta existente " + existe);        
            }
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("Insert into cuenta (numero,nombre,saldo) values (?,?,?)");
            smt.setString(1,insertRecord.getNumero());
            smt.setString(2,insertRecord.getNombre());
            smt.setFloat(3,insertRecord.getSaldo());
            smt.execute();            
        } catch (SQLException e) {
            throw new DataAccessException("Error en DBCuentaDAO.insert() "+e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Error en DBCuentaDAO.insert() "+e);
        }
    }
    @Override
    public void update(Cuenta updateRecord) {
       try {
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("Update cuenta set nombre=?,saldo=? where numero=?");
            smt.setString(1,updateRecord.getNombre());
            smt.setFloat(2,updateRecord.getSaldo());
            smt.setString(3,updateRecord.getNumero());               
            smt.execute();                        
        } catch (SQLException e) {
            throw new DataAccessException("Error en CuentaDAO.update() "+e);
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("Error en CuentaDAO.update() "+e);
        }
    }    
}
