package com.ControladoresRed;

import com.Comandos.EjecutarComando;
import com.Comandos.EnviarMensajeCommand;
import com.Comandos.RecibirArchivoCommand;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Utils.RespuestaUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class RedProcesos extends Thread {

    private String tipo;
    private ObjectInputStream ois;

    public RedProcesos(String tipo,ObjectInputStream ois){
       this.tipo = tipo;
       this.ois = ois;
    }


    public void run(){
        try {
            this.realizarAccion(this.tipo,this.ois);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                if (object instanceof NodoRF) {
                    NodoRF nodo = (NodoRF) object;
                    EjecutarComando.linea("addnode "+nodo.getDireccion()+" "+nodo.getPuertopeticion());
                    System.out.println("Se ha agregado un nodo de forma exitosa");
                    EjecutarComando.linea("order");
                    EjecutarComando.linea("generarFinger");
                }
                break;
            }
            case"deletenode":{
                Object object =  ois.readObject();
                if (object instanceof String) {
                    String direccion = (String) object;
                    EjecutarComando.linea("deletenode "+direccion);
                    EjecutarComando.linea("order");
                    EjecutarComando.linea("generarFinger");
                    ConexionUtils.obtenerInstancia().iniciarConexion(direccion,2004);
                    ConexionUtils.obtenerInstancia().enviarMensaje("finalice");
                }
                break;
            }
            case"addtable":{
                Nodo.getInstancia().setTabla((HashMap<Integer, Long>) ois.readObject());
                System.out.println("Se ha agregado la tabla de forma exitosa");
                ConexionUtils.obtenerInstancia().cerrarConexion();
                break;
            }
            case"getip":{
                Object object =  ois.readObject();
                if (object instanceof  String) {
                    String hash = (String)object;
                    String atributos [] = hash.split(":");
                    ConexionUtils.obtenerInstancia().iniciarConexion(atributos[1],Integer.parseInt(atributos[2]));
                    ConexionUtils.obtenerInstancia().enviarMensaje("noderesource");
                    ConexionUtils.obtenerInstancia()
                            .enviarMensaje(Fantasma.obtenerInstancia().obtenerIP(Long.parseLong(atributos[0]))+ ":"+
                             Long.parseLong(atributos[0]));
                }
                break;
            }
            case"noderesource":{
                Object object =  ois.readObject();
                if (object instanceof String){
                    String datos = (String)object;
                    String atributos [] = datos.split(":");
                    ConexionUtils.obtenerInstancia().iniciarConexion(atributos[0],Integer.parseInt(atributos[1]));
                    ConexionUtils.obtenerInstancia().enviarMensaje("havefile");
                    ConexionUtils.obtenerInstancia().enviarMensaje(Nodo.obtenerInstancia().getEnespera()+":"+
                    atributos[0]+":"+Integer.parseInt(atributos[1]));

                }
                break;
            }

            case"havefile":{
                Object object =  ois.readObject();
                if (object instanceof String){
                    String datos = (String)object;
                    String atributos[] = datos.split(":");
                    if(Nodo.getInstancia().buscarRecurso(Long.parseLong(atributos[0]))!=null){
                        ConexionUtils.obtenerInstancia().iniciarConexion(atributos[1],Integer.parseInt(atributos[2]));
                        ConexionUtils.obtenerInstancia().enviarMensaje("download");
                        ConexionUtils.obtenerInstancia().enviarMensaje(atributos[0]+":"+atributos[1]+":"+atributos[2]);
                    }else{
                        /*
                        System.out.println("Redireccionando busqueda...");
                        ConexionUtils.obtenerInstancia().iniciarConexion(
                                Fantasma.obtenerInstancia().getDireccion(),
                                Fantasma.obtenerInstancia().getPuertopeticion());
                        ConexionUtils.obtenerInstancia().enviarMensaje("getip");
                        ConexionUtils.obtenerInstancia().enviarMensaje(
                                Nodo.getInstancia().seleccionarNodo(Long.parseLong(atributos[0]))
                                        +":"+atributos[1]+":"+atributos[2]);
                        */
                    }
                }
                break;
            }

            case"download":{
                Object object =  ois.readObject();
                if (object instanceof String) {
                    String datos = (String) object;
                    String atributos[] = datos.split(":");

                    if (Nodo.getInstancia().buscarRecurso(Long.parseLong(atributos[0])) != null) {
                        ConexionUtils.obtenerInstancia().iniciarConexion(atributos[1], Integer.parseInt(atributos[2]));
                        EjecutarComando.linea("download "+atributos[1]+" "+atributos[2]+" "+atributos[0]);
                    }
                }

            }

            case"redirect":{
                Object object =  ois.readObject();
                if (object instanceof String){
                    String datos = (String)object;
                    String atributos [] = datos.split(":");
                    Nodo.getInstancia().seleccionarNodo(Long.parseLong(atributos[0]));

                }
                break;
            }

            case"getresourse":{
                Object object =  ois.readObject();
                if (object instanceof String){
                    String datos = (String)object;
                    String atributos [] = datos.split(":");

                }
                break;
            }
            case"finalice":{
                ConexionUtils.obtenerInstancia().cerrarConexion();
                System.exit(0);
            }
        }

    }
}
