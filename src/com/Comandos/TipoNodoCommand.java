package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Utils.SistemaUtil;

import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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
public class TipoNodoCommand extends BaseCommand {

    public static final String COMMAND_NAME ="type";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        if (args[0].equals("miembro")) {
            SistemaUtil.tipo = "miembro";
            EjecutarComando.linea("listen");
            EjecutarComando.linea("listenfile");
            System.out.println("Se ha asignado el tipo de nodo exitosamente");
            try {
                NodoRF mynodorf = new NodoRF(Nodo.obtenerInstancia().getDireccion(),Nodo.getInstancia().getPuertopeticion());
                ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("addnode",mynodorf,Fantasma.obtenerInstancia()));
                EjecutarComando.linea("secondplane");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
                }else if (args[0].equals("fantasma")) {
                    SistemaUtil.tipo = "fantasma";
                    EjecutarComando.linea("network localhost 2000 central");
                    EjecutarComando.linea("listen");
                    System.out.println("Se ha asignado el tipo de nodo exitosamente");
        }else
            write(out,"Este tipo de nodo no existe! ");

    }
}
