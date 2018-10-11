/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentacontable.persistencia;

import cuentacontable.Cuenta;
import java.util.Collection;

/**
 *
 * @author oquinter
 */
public interface ICuentaDAO {
    public void delete(String vnumero);
    public Cuenta findByPK(String vnumero);
    public Collection findAll();
    public void insert(Cuenta insertRecord);
    public void update(Cuenta updateRecord);
}
