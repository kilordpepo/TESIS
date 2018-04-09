package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.Entidades.Fantasma;
import com.Entidades.NodoRF;

import java.io.OutputStream;
import java.util.ArrayList;
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
        HashMap<Integer,String> tabla = new HashMap<Integer,String>();
        String primero = f.getAnillo().get(0).getDireccion();
        ArrayList<NodoRF> anillo = f.getAnillo();
        for(NodoRF nodo : anillo){
            tabla= new HashMap<Integer,String>();
            for(int i=1;i<=5;i++) {
                int indice = 1;
                valorFinger = nodo.getHash().intValue() + (2 ^ (i - 1));
                for (NodoRF aux : anillo) {

                    if (aux.getHash().intValue() >= valorFinger) {
                        tabla.put(indice, aux.getDireccion());
                        indice++;
                        break;
                    }
                }
            }
                if (tabla.isEmpty()) {
                        tabla.put(1, primero);
            }
                ConexionUtils.obtenerInstancia().iniciarConexion(nodo.getDireccion(),nodo.getPuertopeticion());
                EnviarMensajeCommand.enviarDato("addtable",nodo.getDireccion(),nodo.getPuertopeticion());
                EnviarMensajeCommand.enviarDato(tabla,nodo.getDireccion(),nodo.getPuertopeticion());// AQUI SE ENVIA LA TABLA AL NODO
                //ConexionUtils.obtenerInstancia().cerrarConexion();

        }
    }
}
