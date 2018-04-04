package com.Comandos;

import com.Entidades.Recurso;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Junior on 03/04/2018.
 */
public class SolicitarArchivoCommand extends BaseCommand {

    public static final String COMMAND_NAME="";

    @Override
    public String obtenerNombreComando() {
        return null;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {

    }
}
