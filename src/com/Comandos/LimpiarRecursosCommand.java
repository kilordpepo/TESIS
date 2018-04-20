package com.Comandos;

import com.Entidades.Nodo;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

public class LimpiarRecursosCommand  extends BaseCommand{

    public static final String COMMAND_NAME ="cleanresources";
    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
            Nodo nodo = Nodo.getInstancia();
            Iterator it = nodo.getTablaRecursos().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry par = (Map.Entry)it.next();
            if(((Nodo)par.getKey()).getDireccion().equals(args[0])
                    &&(((Nodo) par.getKey()).getPuertopeticion())==Integer.parseInt(args[1])){
                it.remove();
            }
        }

    }
}
