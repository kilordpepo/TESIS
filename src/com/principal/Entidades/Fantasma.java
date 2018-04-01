package com.principal.Entidades;

import java.util.ArrayList;

/**
 * Created by Junior on 01/04/2018.
 */
public class Fantasma extends Nodo {

    private ArrayList<String> anillo;

    public ArrayList<String> getAnillo() {
        return anillo;
    }

    public void setAnillo(ArrayList<String> anillo) {
        this.anillo = anillo;
    }

    public static Fantasma instancia;

    private Fantasma (){

    }

    public static Fantasma obtenerInstancia(){
        if (instancia==null)
            instancia = new Fantasma();
        return instancia;
    }


}
