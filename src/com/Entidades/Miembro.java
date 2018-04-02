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
public class Miembro extends Nodo {
     private int puertoArchivo;
     private ArrayList<Recurso> cola;
     private ArrayList<Recurso> recursos;
     private ArrayList<Finger> tabla;
     private static Miembro instancia;

    private Miembro(){

    }

    public static Miembro obtenerInstancia(){
        if (instancia == null)
            instancia = new Miembro();
        return instancia;
    }

    public int getPuertoArchivo() {
        return puertoArchivo;
    }

    public void setPuertoArchivo(int puertoArchivo) {
        this.puertoArchivo = puertoArchivo;
    }

    public ArrayList<Recurso> getCola() {
        return cola;
    }

    public void setCola(ArrayList<Recurso> cola) {
        this.cola = cola;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(ArrayList<Recurso> recursos) {
        this.recursos = recursos;
    }

    public ArrayList<Finger> getTabla() {
        return tabla;
    }

    public void setTabla(ArrayList<Finger> tabla) {
        this.tabla = tabla;
    }


}
