package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.Entidades.Fantasma;

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
public class BuscarRecursoCommand extends BaseCommand{

    public static final String COMMAND_NAME = "search";


    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        ConexionUtils.obtenerInstancia().iniciarConexion(
                Fantasma.obtenerInstancia().getDireccion(),
                Fantasma.obtenerInstancia().getPuertopeticion());
        ConexionUtils.obtenerInstancia().enviarMensaje("getip");
        ConexionUtils.obtenerInstancia().enviarMensaje(args[0]);
    }
}
