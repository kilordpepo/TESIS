package com.Comandos;

import com.ControladoresRed.ConexionUtils;

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
public class DescargarRecursoCommand extends BaseCommand {

    public static final String COMMAND_NAME = "download";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        ConexionUtils.obtenerInstancia().enviarMensaje(args[0]);
    }
}
