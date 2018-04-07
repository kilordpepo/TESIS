package com.Entidades;

import java.util.ArrayList;

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
public class Fantasma extends Miembro {

    private ArrayList<String> anillo;

    public ArrayList<String> getAnillo() {
        return anillo;
    }

    public void setAnillo(ArrayList<String> anillo) {
        this.anillo = anillo;
    }

    public static Fantasma instancia;

    private Fantasma (){
      anillo = new ArrayList<String>();
    }

    public static Fantasma obtenerInstancia(){
        if (instancia==null)
            instancia = new Fantasma();
        return instancia;
    }

    /**
     * Metodo encargado de devolver la direccion IP y el puerto de escucha de un determinado
     * nodo dando el hash de la direccion IP del mismo.
     * @param iphash
     * @return
     */
    public String obtenerIP(Long iphash){
        String respuesta ="";
        for(String nodo :this.anillo){
            String atributos [] = nodo.split(":");
            if (iphash.equals(atributos[0])){
              respuesta = atributos[1]+":"+atributos[2];
            }
        }
        return respuesta;
    }

}
