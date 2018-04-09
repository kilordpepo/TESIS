package com.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
public class Nodo extends Miembro implements Serializable {
     private int puertoArchivo;
     private ArrayList<Recurso> cola;
     private ArrayList<Recurso> recursos;
     private HashMap<Integer,String> tablafinger;
     private static Nodo instancia;

    private Nodo(){

    }

    public static Nodo obtenerInstancia(){
        if (instancia == null)
            instancia = new Nodo();
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

    public HashMap<Integer,String> getTabla() {
        return tablafinger;
    }

    public void setTabla(HashMap<Integer,String> tabla) {
        this.tablafinger = tabla;
    }


    public static Nodo getInstancia() {
        return instancia;
    }

    public static void setInstancia(Nodo instancia) {
        Nodo.instancia = instancia;
    }

    /**
     * Metodo que se encarga de devolver los datos de un nodo determinado dato el identificador
     * en hash de un archivo. Este revisa en la tabla finger quien es el nodo que es probable
     * que tenga el archivo.
     * @param archivohash aplicando funcion hash
     * @return none (si no encontro alguno) o la IP (si lo encuentra)
     */
    public String seleccionarNodo(Long archivohash){
        String respuesta = "";
        Nodo nodo = Nodo.obtenerInstancia();
        HashMap<Integer,String> tabla = nodo.getTabla();

        for (String item : tabla.values()){
            if(archivohash<Math.abs(Long.parseLong(item))){
                respuesta = item;
            }
        }
        if (respuesta=="")
         return "none";

        return respuesta;
    }


}
