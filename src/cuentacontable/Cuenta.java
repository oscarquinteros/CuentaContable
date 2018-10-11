package cuentacontable;

import cuentacontable.excepciones.SaldoInsuficienteException;

public class Cuenta {
    private String numero;
    private String nombre;
    private Float saldo;    

    public Cuenta() {}
    
    public Cuenta (String numero,String nombre,Float saldo) {
        this.numero=numero;
        this.nombre=nombre;
        this.saldo=saldo;
    }
    public Float debitar(Float importe) throws SaldoInsuficienteException{
        if (saldo-importe<0){
            Float resto=saldo-importe;
            throw new SaldoInsuficienteException("Saldo insuficiente cuenta: "+numero+" faltan:"+resto);
        }
        saldo=saldo-importe;        
        return saldo;
    }
    public Float acreditar(Float importe){
        saldo=saldo+importe;
        return saldo;
    }        
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Float getSaldo() {
        return saldo;
    }
    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
    public String toString(){
        return this.getNumero()+" "+this.getNombre();
    }
}
