package com.ControladoresRed;

import com.Comandos.Descarga;
import com.Entidades.Fragmento;
import com.Entidades.Nodo;

import java.io.*;
import java.util.ArrayList;

public class Descargas extends Thread {

    private ArrayList<Nodo> duenos;
    private long tamano;
    private int pedazos;
    private String archivo;
    private Long hash;

    public Descargas(ArrayList<Nodo> duenos, String archivo, Long hash) {
        this.duenos = duenos;
        this.tamano = 0;
        this.archivo = archivo;
        this.hash = hash;
    }


    @Override
    public void run() {
        ArrayList<Nodo> removibles = new ArrayList<Nodo>();
        for (Nodo dueno : duenos) {
            tamano = (long)(ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("size", hash, dueno)));
            if (tamano != 0)
                break;
            else
                removibles.add(dueno);
        }
        duenos.removeAll(removibles);
        removibles = new ArrayList<Nodo>();
        pedazos = duenos.size();
        Descarga[] descargas = new Descarga[pedazos];
        int iteraciones = (int) ((tamano / 1024) / pedazos);
        int posInicial = 0;
        int posFinal = iteraciones-1;
        descargas[0] = new Descarga(posInicial, posFinal, duenos.get(0), hash);
        descargas[0].start();
        for (int i = 1; i < duenos.size(); i++) {
            posInicial += iteraciones;
            if(i==duenos.size()-1)
                posFinal += iteraciones+1;
            else
                posFinal += iteraciones;
            descargas[i] = new Descarga(posInicial, posFinal, duenos.get(i), hash);
            descargas[i].start();
        }
        boolean error = false;
        int contador = 0;
        while (!error && contador != pedazos) {
            contador = 0;
            for (int i = 0; i < pedazos; i++) {
                if ("E".equals(descargas[i].estado)) {
                    duenos.remove(i);
                    if (!duenos.isEmpty()) {
                            descargas[i]= new Descarga(descargas[i].inicial, descargas[i].terminal, duenos.get(0), hash);
                    } else {
                        System.out.println("Error en la descarga, no ha sido posible realizar la descarga");
                        error = true;
                    }
                } else if ("F".equals(descargas[i].estado)) {
                    contador++;
                }
            }
        }

        if(!error){
            ArrayList<Fragmento> fichero = new ArrayList<Fragmento>();
            for(Descarga descarga: descargas){
                fichero.addAll(descarga.cuerpo);
            }
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + archivo));
                for(Fragmento contenido : fichero){
                         bos.write(contenido.getPedazo(), 0, contenido.getCantidad());
                }
                bos.close();
                System.out.println("Descarga finalizada");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}


