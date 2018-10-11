/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentacontable;

import cuentacontable.excepciones.CuentaExistenteException;
import cuentacontable.excepciones.CuentaInexistenteException;
import cuentacontable.excepciones.SaldoInsuficienteException;
import cuentacontable.persistencia.ArrayCuentaDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oquinter
 */
public class GestorCuentasTest {

    GestorCuentas gestor;

    public GestorCuentasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gestor = new GestorCuentas(new ArrayCuentaDAO());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void agregarCuentas() {
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.agregarCuenta(new Cuenta("2", "ventas", 0f));
        gestor.agregarCuenta(new Cuenta("3", "compras", 0f));        
        assertEquals(gestor.getCuentas().size(), 3);
    }
    @Test
    
    public void eliminarCuentas() {
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.agregarCuenta(new Cuenta("2", "ventas", 0f));
        gestor.eliminarCuenta("1");        
        assertEquals(gestor.getCuentas().size(), 1);
    }
    
    @Test
    public void acreditarCuenta() {   
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.acreditar("1",100.00f);                
        assertEquals(gestor.getCuenta("1").getSaldo(),new Float(100.00));
    }    
    @Test
    public void debitarCuenta() {        
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.acreditar("1",100.00f);                
        gestor.debitar("1",50.00f);                
        assertEquals(gestor.getCuenta("1").getSaldo(),new Float(50.00));
    }
    
    @Test(expected=CuentaExistenteException.class)
    public void agregarCuentaExistente() {     
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
    }
    
    @Test(expected=CuentaInexistenteException.class)
    public void eliminarCuentaInexistente() {     
         gestor.eliminarCuenta("1"); 
    }

    @Test(expected=CuentaInexistenteException.class)
    public void debitarCuentaInexistente() {        
        gestor.debitar("1",50.00f);                
    }

    @Test(expected=SaldoInsuficienteException.class)
    public void debitarSaldoInsuficiente() {        
        gestor.agregarCuenta(new Cuenta("1", "haberes", 0f));
        gestor.debitar("1",50.00f);                
    }
    
}
