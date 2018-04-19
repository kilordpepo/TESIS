package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
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
            Long hash = RespuestaUtils.generarHash(args[0]).longValue();
            NodoRF nodo = Nodo.obtenerInstancia().seleccionarNodo(hash);
            System.out.println("Hash del recurso: "+hash);
            //Obtiene la IP y Descarga el archivo
            if (hash > Nodo.obtenerInstancia().getHash().longValue()) {
                Nodo.getInstancia().setSolicitante(true);
                Mensaje mensaje = new Mensaje("who", hash, Nodo.getInstancia(), nodo);
                Nodo dueno = (Nodo) ConexionUtils.obtenerInstancia().enviarMensaje(mensaje);

                if (dueno != null) {
                    EjecutarComando.linea("download " + dueno.getDireccion() + " " + dueno.getPuertopeticion() + " " + hash);
                } else {
                    System.out.println("Archivo no encontrado");
                }
            }else{
                System.out.println("Busco desde el primero");
                Nodo.getInstancia().setSolicitante(true);
                NodoRF primero = (NodoRF) ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("first", Fantasma.obtenerInstancia()));
                System.out.println("Le pregunto a "+primero.getDireccion());
                ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("who",hash,
                        Nodo.getInstancia(), primero));
                if (primero != null) {
                    EjecutarComando.linea("download " + primero.getDireccion() + " " + primero.getPuertopeticion() + " " + hash);
                } else {
                    System.out.println("Archivo no encontrado");
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
