package com.principal.ControladoresRed;

import com.principal.Entidades.Miembro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Universidad Catolica Andres Bello
 * Facultad de Ingenieria
 * Escuela de Ingenieria Informatica
 * Trabajo Especial de Grado
 * ----------------------------------
 * Tutor:
 * --------------
 * Wilmer Pereira
 *
 * Autores:
 * --------------
 * Garry Bruno
 * Carlos Valero
 */
public class RedNodo extends Thread {



    /**
     * Inicializa el hilo que se encarga de la recepcion de datos en forma
     * de objetos serializados.
     */
    public void run ()
    {
        try {
            Miembro nodo = Miembro.obtenerInstancia();
            ServerSocket recepcion = new ServerSocket(nodo.getPuertopeticion());
            //Contador de procesos;
            int i=1;
            //Solicitudes concurrentes:
            while (true)
            {
                Socket recibo = recepcion.accept();
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(RedNodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
