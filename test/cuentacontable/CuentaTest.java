/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuentacontable;

import cuentacontable.excepciones.SaldoInsuficienteException;
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
public class CuentaTest {
    
    public CuentaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void crearCuenta() {
         Cuenta cuenta = new Cuenta("1","SUELDO",0.00f);
         assertEquals(cuenta.toString(),"1 SUELDO");        
     }
     @Test
     public void acreditarCuenta() {
         Cuenta cuenta = new Cuenta("1","SUELDO",0.00f);
         cuenta.acreditar(10.00f);
         assertEquals(cuenta.getSaldo(),new Float(10.00));        
     }
     @Test
     public void debitarCuenta() {
         Cuenta cuenta = new Cuenta("1","SUELDO",0.00f);         
         cuenta.acreditar(100.00f);
         cuenta.debitar(50.00f);
         assertEquals(cuenta.getSaldo(),new Float(50.00));        
     }
     @Test(expected=SaldoInsuficienteException.class)
     public void debitarCuentaSinSaldo() {
         Cuenta cuenta = new Cuenta("1","SUELDO",0.00f);         
         cuenta.acreditar(100.00f);
         cuenta.debitar(150.00f);
     }
}
