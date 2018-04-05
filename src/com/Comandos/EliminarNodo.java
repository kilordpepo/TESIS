package com.Comandos;

import com.Entidades.Fantasma;

import java.io.OutputStream;

/**
 * Created by Junior on 05/04/2018.
 */
public class EliminarNodo extends BaseCommand {

    public static final String COMMAND_NAME="deletenode";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
      Fantasma fantasma = Fantasma.obtenerInstancia();
      int index =0;
      for (String nodo : fantasma.getAnillo()){
          String [] ip = nodo.split(":");
          if (ip.equals(args[0])) {
              System.out.println("El nodo " +args[0] + " ha salido de la red");
              break;
          }
          index++;
      }
        fantasma.getAnillo().remove(index);
    }


}
