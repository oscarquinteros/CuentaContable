package cuentacontable;

import cuentacontable.persistencia.ArrayCuentaDAO;
import cuentacontable.persistencia.DBCuentaDAO;
import cuentacontable.persistencia.ICuentaDAO;
import cuentacontable.ui.UIPrincipal;

public class CuentaContable {

    public static void main(String[] args) {
        ICuentaDAO cuentaDAO = new ArrayCuentaDAO();
        UIPrincipal principal = new UIPrincipal(cuentaDAO);
        principal.setVisible(true);
    }
}
