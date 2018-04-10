package com.ControladoresRed;

import com.Comandos.RecibirArchivoCommand;
import com.Entidades.NodoRF;
import com.Entidades.Recurso;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Junior on 08/04/2018.
 */
public class ConexionUtils {

    private String direccion;
    private int puerto;
    private ObjectOutputStream salidaObjeto;
    private Socket reves;
    private ObjectInputStream ois;
    private DataOutputStream output;
    private DataInputStream dis;
    private BufferedInputStream bis;
    private BufferedOutputStream bos;




    private Object respuesta;

    private static ConexionUtils conexion;


    private ConexionUtils(){

    }


    public static ConexionUtils obtenerInstancia(){

        if (conexion==null)
           conexion = new ConexionUtils();
        return conexion;
    }



    public void enviarMensaje(Object dato){
        try {
            this.salidaObjeto.writeObject(dato);
            //El cliente recibe:
            //ObjectInputStream ois = new ObjectInputStream(this.reves.getInputStream());
            //Object respuesta = ois.readObject();
            //return respuesta;
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return null;
    }

    public void enviarArchivo(String nombrearchivo){

        try {
            byte[] receivedData;
            int in;
            String file;
            String[] d = null;
            Recurso re = new Recurso();
            String nombre = nombrearchivo;
            System.out.println("Iniciando proceso de descarga de archivo");
            // Se abre una conexion con Servidor Socket
            Socket cliente = reves;
            this.salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            //Solicito el archivo:
            this.salidaObjeto.writeObject("4:" + nombre);
            this.bis = new BufferedInputStream(cliente.getInputStream());
            this.dis = new DataInputStream(cliente.getInputStream());
            //Recibimos el nombre del fichero
            file = this.dis.readUTF();
            d = file.split(":");
            d[0] = d[0].substring(d[0].indexOf('\\') + 1, d[0].length());
            re.setNombre(d[0]);
            re.setId(d[0].hashCode());
            re.setEstado("Descargando...");
            re.setTamano(Integer.parseInt(d[1]));
            //Sistema.agregarRecibo(re);
            //La data recibida, vendran en paquetes de 1024 bytes.
            receivedData = new byte[1024];
            //Para guardar fichero recibido
            File descargado = new File("Descargas\\" + d[0]);
            boolean recarga;
            if (descargado.exists() && descargado.length()< re.getTamano() && descargado.length()> re.getTamano()/2) {
                System.out.println("Su descarga habia sido interrumpida.. descargando nuevamente desde el 50% ");
                recarga=true;
                BufferedInputStream arreglar = new BufferedInputStream(new FileInputStream(descargado));
                byte [] arreglo = new byte[((int) re.getTamano())/2];
                in=arreglar.read(arreglo);
                recarga = false;
                arreglar.close();
                this.bos.write(arreglo, 0, in);
                this.bos.close();
                this.salidaObjeto.writeObject(1);
                this.bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0],true));
            }
            else{
                recarga=false;
                this.salidaObjeto.writeObject(0);
                this.bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0]));
            }

            int l = 0;
            boolean partido = true;
            //Se manejan los datos acerca del libro recibido
            while ((in = this.bis.read(receivedData)) != -1) {
                // System.out.println("entro");
                this.bos.write(receivedData, 0, in);
                re.setTamano(re.getTamano() + in);
                if (partido && l > (re.getTamano() / 2)) {
                    partido=false;
                    System.out.println("El archivo ha alcanzado el 50%");
                    this.bos.close();
                    this.bos = new BufferedOutputStream(new FileOutputStream("recibidos\\" + d[0], true));
                }

                l += in;
            }
            //Se cierra la conexion con el servidor de descarga.
            this.bos.close();
            this.dis.close();
            System.out.println("Finalizando proceso de descarga de archivo");
        } catch (Exception e) {
            Logger.getLogger(RecibirArchivoCommand.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void iniciarConexion(String ip, int puerto){
        try {
            this.direccion = ip;
            this.puerto = puerto;
            this.reves = new Socket (this.direccion,this.puerto);
            this.salidaObjeto = new ObjectOutputStream(this.reves.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion(){
        try {
            this.reves.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
