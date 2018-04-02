package com.Comandos;

import java.io.OutputStream;

/**
 * Created by Junior on 01/04/2018.
 */
public class BuscarRecursoCommand extends BaseCommand{

    public static final String COMMAND_NAME = "search";


    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {

    }
}
