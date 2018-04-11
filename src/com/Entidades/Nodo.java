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
     private HashMap<Integer,Long> tablafinger;
     private static Nodo instancia;
     private Long enespera;
     private String redireccion;

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

    public HashMap<Integer,Long> getTabla() {
        return tablafinger;
    }

    public void setTabla(HashMap<Integer,Long> tabla) {
        this.tablafinger = tabla;
    }


    public static Nodo getInstancia() {
        return instancia;
    }

    public static void setInstancia(Nodo instancia) {
        Nodo.instancia = instancia;
    }

    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    /**
     * Metodo que se encarga de devolver los datos de un nodo determinado dato el identificador
     * en hash de un archivo. Este revisa en la tabla finger quien es el nodo que es probable
     * que tenga el archivo.
     * @param archivohash aplicando funcion hash
     * @return none (si no encontro alguno) o la IP (si lo encuentra)
     */
    public Long seleccionarNodo(Long archivohash){
        Long respuesta = new Long(0);
        Nodo nodo = Nodo.obtenerInstancia();
        HashMap<Integer,Long> tabla = nodo.getTabla();
        for (Long item : tabla.values()){
            if(archivohash<Math.abs(item)){
                respuesta = item;
            }
        }
        if (respuesta == 0){
            respuesta = tabla.get(tabla.size());
        }
        return respuesta;
    }

    public Recurso buscarRecurso(Long archivohash){
        for (Recurso recurso : recursos){
            if(archivohash==recurso.getHash().longValue())
                return recurso;
        }
        return null;
    }

    public Recurso buscarRecurso(String archivo){
        for (Recurso recurso : recursos){
            if(archivo.equals(recurso.getNombre()))
                return recurso;
        }
        return null;
    }

    public HashMap<Integer, Long> getTablafinger() {
        return tablafinger;
    }

    public void setTablafinger(HashMap<Integer, Long> tablafinger) {
        this.tablafinger = tablafinger;
    }

    public Long getEnespera() {
        return enespera;
    }

    public void setEnespera(Long enespera) {
        this.enespera = enespera;
    }
}
