package com.Comandos;

import com.Entidades.Nodo;

import java.io.OutputStream;

/**
 * Created by Junior on 17/04/2018.
 */
public class SegundoPlanoCommand extends AsyncCommand {

    public static final String COMMAND_NAME="secondplane";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void executeOnBackground(String[] args, OutputStream out) {
        //while (true){
        //    if(Nodo.getInstancia().isCompartir()){
        //        //EjecutarComando.linea("share");
        //        Nodo.getInstancia().setCompartir(false);
        //    }
       // }
    }
}
