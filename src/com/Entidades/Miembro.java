package com.Entidades;

import java.math.BigInteger;

/**
 * Created by Junior on 01/04/2018.
 */
public abstract class Miembro {
    private int puertopeticion;
    private String direccion;
    private BigInteger hash;

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

    public BigInteger getHash() {
        return hash;
    }

    public void setHash(BigInteger hash) {
        this.hash = hash;
    }
}

