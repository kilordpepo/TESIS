package com.Comandos;

import com.Entidades.Nodo;
import com.Entidades.NodoRF;

import java.io.OutputStream;
import java.util.Map;

/**
 * Created by Junior on 15/04/2018.
 */
public class VerHashRecursoCommand extends BaseCommand {

    public static final String COMMAND_NAME = "listhashr";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        System.out.println("La tabla de recursos es: ");
        System.out.println("--------------------------------------------");
        if (Nodo.getInstancia().getTablaRecursos()!=null) {
            Nodo nodo = Nodo.obtenerInstancia();
            int i = 1;
            for (Map.Entry<Nodo, Long> entry : nodo.getTablaRecursos().entrySet()) {
                System.out.println("Nodo: " + entry.getKey().getDireccion() + " Recurso: " + entry.getValue());
            }
        }else {
            System.out.println("Usted es un fantasma!. Esta funcion no esta disponible");
        }
    }
}
