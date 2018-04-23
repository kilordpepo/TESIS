package com.Comandos;

import com.ControladoresRed.ConexionUtils;
import com.ControladoresRed.Mensaje;
import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;
import com.Entidades.Recurso;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
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
public class EnviarArchivoCommand  extends AsyncCommand{

    public static final String COMMAND_NAME="listenfile";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }


    @Override
    public void executeOnBackground(String[] args, OutputStream out) {
        //Solicitudes concurrentes:
        try {
            Nodo nodo = Nodo.obtenerInstancia();
            ServerSocket server = new ServerSocket(nodo.getPuertoArchivo());
            while (true)
            {
                //System.out.println("ConexionUtils para archivos habilitada y en espera...");
                Socket socket = server.accept();
                procesarEnvio(server,socket);
            }
        } catch (IOException ex) {
            //Logger.getLogger(Recepcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Este metodo se encarga del envio de archivos al nodo que lo solicite
     */
    public void procesarEnvio(ServerSocket server, Socket connection){
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        int in;
        byte[] byteArray;
        byte[] mitad;
        String [] dt=null;
        Recurso re=null;
        try{
            int id=0;
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            String solicitud = (String)ois.readObject();
            dt = solicitud.split(":");
            File localFile = new File(Nodo.obtenerInstancia().buscarRecurso(Long.parseLong(dt[1])).getRuta());
            re = Nodo.obtenerInstancia().buscarRecurso(Long.parseLong(dt[1]));
            if (re!=null) {
                bis = new BufferedInputStream(new FileInputStream(localFile));
                bos = new BufferedOutputStream(connection.getOutputStream());
                //Enviamos el nombre del fichero
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeUTF(localFile.getName() + ":" + Integer.toString((int) localFile.length()));
                int tamano = (int) localFile.length();
                if ((int) ois.readObject() == 0) {
                    //Enviamos el fichero
                    re.setTamano(tamano);
                    byteArray = new byte[(int) localFile.length()];
                    //Mando:
                    int k = 0;
                    while ((in = bis.read(byteArray)) != -1) {
                        bos.write(byteArray, 0, in);
                        k += in;
                    }
                } else {
                    //re.setTamano(tamano);
                    byteArray = new byte[tamano / 2];
                    bis.skip(new Long(tamano / 2));
                    while ((in = bis.read(byteArray, 0, byteArray.length)) != -1) {
                        bos.write(byteArray, 0, in);
                    }
                }
                // Se cierra la conexion
                bis.close();
                bos.close();
                System.out.println("Envio de Archivo finalizado!");
            }
        }catch ( Exception e ) {
            Logger.getLogger(EnviarArchivoCommand.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Envio fallido!");
            try {
                Mensaje mensaje =(Mensaje) ConexionUtils.obtenerInstancia().enviarMensaje(new Mensaje("deletenode",
                        new NodoRF(dt[2],Integer.parseInt(dt[3])), Fantasma.obtenerInstancia()));
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Este nodo se encarga de reanudar la descarga en caso de ser necesario.
     * @param nombre
     * @return
     */
    public boolean reanudarDescarga(int nombre){
        Nodo miembro = Nodo.obtenerInstancia();
        for (Recurso r : miembro.getCola() )
        {
            if ((r.getId()==nombre)&&(r.getEstado().equals("Fallido")))
            {
                return true;
            }
        }
        return false;
    }
}
