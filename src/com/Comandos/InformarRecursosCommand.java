package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Entidades.Recurso;

import java.io.OutputStream;

/**
 * Created by Junior on 17/04/2018.
 */
public class InformarRecursosCommand extends BaseCommand {

    public static final String COMMAND_NAME="share";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
          for(Recurso recurso : Nodo.getInstancia().getRecursos()){
              NodoRF node = Nodo.obtenerInstancia().seleccionarNodo(recurso.getHash().longValue());
              //Obtiene la IP y Descarga el archivo
              Nodo.getInstancia().setSolicitante(true);
              ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("resource",recurso.getHash(),
                      Nodo.getInstancia(),node));
          }
    }
}
