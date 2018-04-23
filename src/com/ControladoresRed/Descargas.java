package com.ControladoresRed;

import com.Entidades.Fantasma;
import com.Entidades.Nodo;

import java.util.ArrayList;

public class Descargas extends Thread {

    private ArrayList<Nodo> duenos;
    private long tamano;
    private int pedazos;
    private String archivo;
    private Long hash;

    public Descargas(ArrayList<Nodo> duenos, String archivo, Long hash){
        this.duenos=duenos;
        this.tamano=0;
        this.archivo=archivo;
        this.hash=hash;
    }


    @Override
    public void run(){
        ArrayList<Nodo> removibles = new ArrayList<Nodo>();
        for (Nodo dueno : duenos){
            tamano = (long) ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("size", hash,dueno ));
                if(tamano!=0)
                    break;
                else
                    removibles.add(dueno);
        }
        duenos.removeAll(removibles);
        removibles= new ArrayList<Nodo>();
        pedazos = duenos.size();
        // Descarga [] descargas = Descarga[duenos.size()];


    }

}
