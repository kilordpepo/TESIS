package com.Comandos;

import com.Entidades.Finger;
import com.Entidades.Miembro;
import com.Entidades.Nodo;

import java.io.OutputStream;

/**
 * Universidad Catolica Andres Bello
 * Facultad de Ingenieria
 * Escuela de Ingenieria Informatica
 * Trabajo Especial de Grado
 * ----------------------------------
 * Tutor:
 * --------------
 * Wilmer Pereira
 *
 * Autores:
 * --------------
 * Garry Bruno
 * Carlos Valero
 */
public class SeleccionarNodoCommand extends BaseCommand{

    public static final String COMMAND_NAME="selectnode";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {

        Long valor = Long.parseLong(args[0]);
        Nodo nodo = Nodo.obtenerInstancia();
        for (Finger item : nodo.getTabla()){
            
        }

    }
}
