package com.Entidades;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Junior on 24/04/2018.
 */
public class Fragmento {
    public ByteArrayOutputStream pedazo = new ByteArrayOutputStream();
    public DataOutputStream w;

    public Fragmento(byte[] arreglo,int in) {
        //this.w = new DataOutputStream(this.getPedazo());
        this.pedazo.write(arreglo,0,in);

    }

    public ByteArrayOutputStream getPedazo() {
        return pedazo;
    }

    public void setPedazo(ByteArrayOutputStream pedazo) {
        this.pedazo = pedazo;
    }

}
