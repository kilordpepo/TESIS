package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Entidades.Recurso;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
public class Descarga extends Thread {

    public ArrayList<byte[]> cuerpo = new ArrayList<byte[]>();
    public int inicial;
    public int terminal;
    public Nodo nodo;
    public Long archivo;
    public String estado; // Estados: "F","D","E"

    public Descarga(int inicial, int terminal, Nodo nodo, Long archivo){
           this.inicial = inicial;
           this.terminal = terminal;
           this.nodo = nodo;
           this.archivo = archivo;
    }

    public void run(){
        try {
            DataOutputStream output;
            BufferedInputStream bis;
            BufferedOutputStream bos;
            byte[] receivedData;
            int in;
            String file;
            String[] d = null;
            DataInputStream dis = null;
            String ip =this.nodo.getDireccion();
            int puerto = this.nodo.getPuertopeticion()+1;
            Long nombre = this.archivo;
            System.out.println("Iniciando proceso de descarga de archivo");
            this.estado = "D";
            // Se abre una conexion con Servidor Socket
            Socket cliente = new Socket(ip, puerto);
            cliente.setSoTimeout(5000);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            //Solicito el archivo:
            salidaObjeto.writeObject("4:" + nombre+":"+Nodo.getInstancia().getDireccion()
                    +":"+Nodo.getInstancia().getPuertopeticion());
            bis = new BufferedInputStream(cliente.getInputStream());
            dis = new DataInputStream(cliente.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
            d = file.split(":");
            d[0] = d[0].substring(d[0].indexOf('\\') + 1, d[0].length());
            //La data recibida, vendran en paquetes de 1024 bytes.
            receivedData = new byte[1024];
            //Para guardar fichero recibido
            File descargado = new File("Descargas\\" + d[0]);
            boolean recarga;

            recarga=false;
            salidaObjeto.writeObject(0);
            //bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0]));

            int l = 0;
            boolean partido = true;
            //Se manejan los datos acerca del libro recibido
            int recorrido =0;

            while ((in = bis.read(receivedData)) != -1) {
                //bos.write(receivedData, 0, in);
                if ((this.inicial<=recorrido)&&(recorrido<=this.terminal))
                this.cuerpo.add(receivedData);
                l += in;
                recorrido++;
            }
            //Se cierra la conexion con el servidor de descarga.
            //bos.close();
            this.estado = "F";
            dis.close();
            System.out.println("Finalizando proceso de descarga de archivo");
        } catch (Exception e) {
            //Logger.getLogger(RecibirArchivoCommand.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Descarga fallida!");
            this.estado = "E";
            try {
                Mensaje mensaje =(Mensaje) ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("deletenode",
                        new NodoRF(nodo.getDireccion(),nodo.getPuertopeticion()), Fantasma.obtenerInstancia()));
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        }

    }

}
