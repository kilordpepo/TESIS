package com.principal.Entidades;

/**
 * Created by Junior on 01/04/2018.
 */
public abstract class Nodo {
    private int puertopeticion;
    private String direccion;
    private String hash;

    public int getPuertopeticion() {
        return puertopeticion;
    }

    public void setPuertopeticion(int puertopeticion) {
        this.puertopeticion = puertopeticion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
