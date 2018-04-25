package com.Entidades;

/**
 * Created by Junior on 24/04/2018.
 */
public class Fragmento {
    byte[] pedazo;
    int cantidad;


    public Fragmento(byte[] pedazo, int cantidad) {
        this.pedazo = pedazo;
        this.cantidad = cantidad;
    }

    public byte[] getPedazo() {
        return pedazo;
    }

    public void setPedazo(byte[] pedazo) {
        this.pedazo = pedazo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
