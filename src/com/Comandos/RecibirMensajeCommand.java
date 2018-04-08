package com.Comandos;

import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Utils.SistemaUtil;
import org.omg.PortableInterceptor.ObjectReferenceTemplate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
public class RecibirMensajeCommand extends AsyncCommand{

    public static final String COMMAND_NAME = "listen";


    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void executeOnBackground(String[] args, OutputStream out) {
            try {
                ServerSocket recepcion = null;
                if (SistemaUtil.tipo.equals("miembro")) {
                    Nodo nodo = Nodo.obtenerInstancia();
                    recepcion = new ServerSocket(nodo.getPuertopeticion());
                }else if (SistemaUtil.tipo.equals("fantasma")){
                    Fantasma nodo = Fantasma.obtenerInstancia();
                    recepcion = new ServerSocket(nodo.getPuertopeticion());
                }
                while (true) {
                    System.out.printf("ConexionUtils habilitada y en espera...");
                    Socket recibo = recepcion.accept();
                    ObjectInputStream ois = new ObjectInputStream(recibo.getInputStream());
                    ObjectOutputStream salidaObjeto = new ObjectOutputStream(recibo.getOutputStream());
                    //Mensaje que llega:
                    Object mensaje = ois.readObject();
                    //Falta ejecutar acciones dependiendo del mensaje
                    String tipo = (String) mensaje;
                    realizarAccion(tipo,ois);



                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object respuesta = null;
    }

    /**
     * Metodo encargado de ejecutar una accion en base a un comando recibido por socket
     * @param tipo
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void realizarAccion(String tipo,ObjectInputStream ois ) throws IOException, ClassNotFoundException {
        switch(tipo){
            case"addnode":{
                Object object =  ois.readObject();
                if (object instanceof  NodoRF) {
                    NodoRF nodo = (NodoRF) object;
                    Fantasma.obtenerInstancia().getAnillo().add(nodo);
                    System.out.println("Se ha agregado un nodo de forma exitosa");
                    EjecutarComando.linea("order");
                    EjecutarComando.linea("generarFinger");
                }
                break;
            }
            case"addtable":{
                Nodo.getInstancia().setTabla((HashMap<Integer, String>) ois.readObject());
                System.out.println("Se ha agregado la tabla de forma exitosa");
                break;
            }
        }

    }

}
