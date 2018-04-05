package com.Comandos;

import com.Entidades.Fantasma;

import java.io.OutputStream;

/**
 * Created by Junior on 05/04/2018.
 */
public class AgregarNodo extends BaseCommand {

    public static final String COMMAND_NAME="addnode";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        Fantasma fantasma = Fantasma.obtenerInstancia();
        fantasma.getAnillo().add(args[0]+":"+args[1]);
    }
}
