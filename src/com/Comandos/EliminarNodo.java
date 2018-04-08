package com.Comandos;

import com.Entidades.Fantasma;
import com.Entidades.NodoRF;

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
      for (NodoRF nodo : fantasma.getAnillo()){
          if (nodo.getDireccion().equals(args[0])) {
              System.out.println("El nodo " +args[0] + " ha salido de la red");
              break;
          }
          index++;
      }
        fantasma.getAnillo().remove(index);
    }


}
