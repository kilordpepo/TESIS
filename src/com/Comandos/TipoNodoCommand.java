package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Utils.SistemaUtil;

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
            EjecutarComando.linea("network localhost 2000 central");
            EjecutarComando.linea("network localhost 2004 miembro");
            ConexionUtils.obtenerInstancia().iniciarConexion(Fantasma.obtenerInstancia().getDireccion(),Fantasma.obtenerInstancia().getPuertopeticion());
            EjecutarComando.linea("listen");
            System.out.println("Se ha asignado el tipo de nodo exitosamente");
            EnviarMensajeCommand.enviarDato("addnode",Fantasma.obtenerInstancia().getDireccion(),Fantasma.obtenerInstancia().getPuertopeticion());
            try {
                NodoRF mynodorf = new NodoRF(Nodo.obtenerInstancia().getDireccion(),Nodo.getInstancia().getPuertopeticion());
                EnviarMensajeCommand.enviarDato( mynodorf,Fantasma.obtenerInstancia().getDireccion(),Fantasma.obtenerInstancia().getPuertopeticion());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
                }else if (args[0].equals("fantasma")) {
            SistemaUtil.tipo = "fantasma";
            EjecutarComando.linea("network localhost 2000 central");
            //ConexionUtils.obtenerInstancia().iniciarConexion(Fantasma.obtenerInstancia().getDireccion(),Fantasma.obtenerInstancia().getPuertopeticion());

            EjecutarComando.linea("listen");
            System.out.println("Se ha asignado el tipo de nodo exitosamente");
        }else
            write(out,"Este tipo de nodo no existe! ");

    }
}
