package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Utils.RespuestaUtils;

import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

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
        try {
        ConexionUtils.obtenerInstancia().iniciarConexion(
                Fantasma.obtenerInstancia().getDireccion(),
                Fantasma.obtenerInstancia().getPuertopeticion());
        ConexionUtils.obtenerInstancia().enviarMensaje("getip");
            ConexionUtils.obtenerInstancia().enviarMensaje(RespuestaUtils.generarHash(args[0])+":"+Nodo.obtenerInstancia().getDireccion()+
            ":"+Integer.toString(Nodo.obtenerInstancia().getPuertopeticion()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
