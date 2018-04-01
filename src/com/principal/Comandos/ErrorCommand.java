package com.principal.Comandos;

import java.io.OutputStream;

/**
 * Created by Junior on 01/04/2018.
 */
public class ErrorCommand extends BaseCommand {

    @Override
    public String obtenerNombreComando() {
        return null;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        String message = "Error al invokar el comando";
        write(out, message);
    }
}
