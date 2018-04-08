package com.Entidades;

import java.io.Serializable;
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
public class Fantasma extends Miembro  implements Serializable {

    private ArrayList<NodoRF> anillo;

    public ArrayList<NodoRF> getAnillo() {
        return anillo;
    }

    public void setAnillo(ArrayList<NodoRF> anillo) {
        this.anillo = anillo;
    }

    public static Fantasma instancia;

    private Fantasma (){
      anillo = new ArrayList<NodoRF>();
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
        for(NodoRF nodo :this.anillo){
            if (iphash.equals(nodo.getHash())){
              respuesta = nodo.getDireccion()+":"+nodo.getPuertopeticion();
            }
        }
        return respuesta;
    }

}
