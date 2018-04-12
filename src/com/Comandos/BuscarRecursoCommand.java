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
            Long hashnode = Nodo.obtenerInstancia().seleccionarNodo(hash);
            //Obtiene la IP y Descarga el archivo
            Mensaje mensaje = new Mensaje("getip",hashnode,Fantasma.obtenerInstancia());
            Mensaje respuesta = (Mensaje) ConexionUtils.obtenerInstancia().enviarMensaje(mensaje);
            NodoRF nodo = (NodoRF) respuesta.getData();
            mensaje = new Mensaje("download",hash,Nodo.getInstancia(),nodo);
            mensaje = (Mensaje)ConexionUtils.obtenerInstancia().enviarMensaje(mensaje);
            if (mensaje!=null){
              Nodo nodor = (Nodo)mensaje.getData();
              EjecutarComando.linea("download "+nodor.getDireccion()+" "+nodor.getPuertopeticion()+" "+hash);
            }else{
                System.out.println("Archivo no encontrado");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
