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

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Mensaje mensaje;

    public RedProcesos(Mensaje mensaje,ObjectInputStream ois,ObjectOutputStream oos){
       this.mensaje = mensaje;
       this.ois = ois;
       this.oos = oos;
    }


    public void run(){
        try {
            this.realizarAccion(this.mensaje,this.ois,this.oos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo encargado de ejecutar una accion en base a un comando recibido por socket
     * @param mensaje
     * @param ois
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void realizarAccion(Mensaje mensaje,ObjectInputStream ois,ObjectOutputStream oos)
                                                              throws IOException, ClassNotFoundException {
        String funcion = mensaje.getFuncion();

        switch(funcion){
            case"addnode":{
                    NodoRF nodo = (NodoRF) mensaje.getData();
                    EjecutarComando.linea("addnode "+nodo.getDireccion()+" "+nodo.getPuertopeticion());
                    System.out.println("Se ha agregado un nodo de forma exitosa");
                    EjecutarComando.linea("order");
                    oos.writeObject(new Mensaje("addnode","",nodo));
                     EjecutarComando.linea("generarFinger");
                break;
            }
            case"deletenode":{
                    Nodo nodo = (Nodo) mensaje.getData();
                    String direccion = nodo.getDireccion();
                    EjecutarComando.linea("deletenode "+direccion);
                    EjecutarComando.linea("order");
                    oos.writeObject(new Mensaje("finalice","",nodo));
                    EjecutarComando.linea("generarFinger");
                break;
            }
            case"addtable":{
                    Nodo.getInstancia().setTabla((HashMap<Integer, Long>)mensaje.getData());
                    System.out.println("Se ha agregado la tabla de forma exitosa");
                break;
            }
            case"getip":{
                    Long hash = (Long)mensaje.getData();
                    NodoRF nodo = Fantasma.obtenerInstancia().obtenerNodo(hash);
                    oos.writeObject(new Mensaje("getip",nodo,nodo));
                break;
            }

            case"havefile":{
                    Long hash = (Long)mensaje.getData();
                    if(Nodo.getInstancia().buscarRecurso(hash)!=null){
                        oos.writeObject(new Mensaje("havefile",Nodo.getInstancia(),Nodo.getInstancia()));
                    }else{

                    }
                break;
            }

            case"download":{
                    Long hash = (Long) mensaje.getData();
                    if (Nodo.getInstancia().buscarRecurso(hash) != null) {
                        oos.writeObject(new Mensaje("havefile",Nodo.getInstancia(),Nodo.getInstancia()));
                    }else {
                        System.out.println("redireccionando...");
                        Long hashnode = Nodo.obtenerInstancia().seleccionarNodo(hash);
                        Mensaje data = new Mensaje("getip",hashnode,Fantasma.obtenerInstancia());
                        Mensaje respuesta = (Mensaje) ConexionUtils.obtenerInstancia().enviarMensaje(data);
                        NodoRF nodo = (NodoRF) respuesta.getData();
                        data = new Mensaje("download",hashnode,nodo);
                        nodo = (NodoRF)ConexionUtils.obtenerInstancia().enviarMensaje(data);
                        oos.writeObject(nodo);
                    }
            }

            case"redirect":{
                /*Object object =  ois.readObject();
                if (object instanceof String){
                    String datos = (String)object;
                    String atributos [] = datos.split(":");
                    Nodo.getInstancia().seleccionarNodo(Long.parseLong(atributos[0]));

                }
                */
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
        }

    }
}
