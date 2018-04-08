package com.Comandos;

import com.Entidades.Fantasma;
import com.Entidades.Finger;
import com.Entidades.NodoRF;

import java.io.OutputStream;
import java.util.ArrayList;

public class GenerarFingerCommand extends BaseCommand {

    public static final String COMMAND_NAME = "generarFinger";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        Fantasma f= Fantasma.obtenerInstancia();
        Finger finger = new Finger();
        ArrayList<Finger> tabla;
        int valorFinger;
        String primero = f.getAnillo().get(0).getDireccion();
        for(NodoRF nodo : f.getAnillo()){
            tabla= new ArrayList<>();
            for(int i=1;i<=5;i++) {
                valorFinger = nodo.getHash().intValue() + (2 ^ (i - 1));
                for(NodoRF aux :f.getAnillo()){
                    if(aux.getHash().intValue() >=valorFinger)
                        finger.setIndice(i);
                        finger.setValor(aux.getDireccion());
                        tabla.add(finger);
                        break;
                }
                if(tabla.isEmpty()){
                    finger.setValor(primero);
                    for(int j =1; j<=5;j++){
                        finger.setIndice(j);
                        tabla.add(finger);
                    }

                }
                EnviarMensajeCommand.enviarDato(tabla,nodo.getDireccion(),nodo.getPuertopeticion());// AQUI SE ENVIA LA TABLA AL NODO
            }
        }
    }
}
