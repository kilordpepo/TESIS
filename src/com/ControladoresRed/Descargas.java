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
        System.out.println("Tamano "+tamano);
        System.out.println("Pedazos "+pedazos);
        int vueltas = (int) (tamano / 1024);
        System.out.println("Iteraciones "+ vueltas);
        int iteraciones=0;
        if ((tamano-(vueltas*1024))>0) {
         iteraciones = (int) ((vueltas + 1) / pedazos);
         if ((vueltas+1)-(iteraciones*pedazos)>0){
            iteraciones = iteraciones +1;
         }
        }else{
            iteraciones = (int) ((vueltas) / pedazos);
            if ((vueltas)-(iteraciones*pedazos)>0){
                iteraciones = iteraciones +1;
            }
        }
        int posInicial = 0;
        int posFinal = iteraciones;
        System.out.println("Posicion inicial: "+posInicial+" Posicion final:"+posFinal);
        descargas[0] = new Descarga(posInicial, posFinal, duenos.get(0), hash);
        descargas[0].start();
        for (int i = 1; i < duenos.size(); i++) {
            posInicial += iteraciones+1;
            if(i==duenos.size()-1)
                posFinal += iteraciones;
            else
                posFinal += iteraciones;
            System.out.println("Posicion inicial: "+posInicial+" Posicion final:"+posFinal);
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
                int vuel=0;
                for(Fragmento contenido : fichero){
                         bos.write(contenido.getPedazo().toByteArray());
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


