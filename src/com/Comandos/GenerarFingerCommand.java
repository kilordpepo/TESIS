package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Fantasma;
import com.Entidades.NodoRF;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

public class GenerarFingerCommand extends BaseCommand {

    public static final String COMMAND_NAME = "generarFinger";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        Fantasma f= Fantasma.obtenerInstancia();
        int valorFinger;
        int suma=2;
        HashMap<Integer,Long> tabla = new HashMap<Integer,Long>();
        if (!f.getAnillo().isEmpty()){
        Long primero = f.getAnillo().get(0).getHash().longValue();
        ArrayList<NodoRF> anillo = f.getAnillo();
            try {
                for (NodoRF nodo : anillo) {
                    tabla = new HashMap<Integer, Long>();
                    for (int i = 1; i <= 5; i++) {
                        int indice = 1;
                        valorFinger = nodo.getHash().intValue() + ((int) Math.pow(2, i - 1));
                        for (NodoRF aux : anillo) {

                            if (aux.getHash().intValue() >= valorFinger) {
                                tabla.put(indice, aux.getHash().longValue());
                                indice += 1;
                                break;
                            }
                        }
                    }
                    if (tabla.isEmpty()) {
                        tabla.put(1, primero);
                    }
                    ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("addtable", tabla, nodo));
                }
            }catch(ConcurrentModificationException e){


            }
        }
    }
}
