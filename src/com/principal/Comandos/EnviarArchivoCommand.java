package com.principal.Comandos;

import com.principal.Entidades.Nodo;
import com.principal.Entidades.Recurso;

import java.io.*;
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
public class EnviarArchivoCommand  extends AsyncCommand{

    public static final String COMMAND_NAME="sendfile";

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
                System.out.printf("Conexion para archivos habilitada y en espera...");
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
            //System.out.println("Iniciando proceso de envio del archivo: "+ buscarArchivo(Integer.parseInt(dt[1])));
            File localFile = new File("recursos/"+dt[1]);
            //System.out.println("Recibido es: " + solicitud);
            //System.out.println("El archivo es: " + buscarArchivo(Integer.parseInt(dt[1])));
            //re = new Recurso();
            //re.setNombre(buscarArchivo(Integer.parseInt(dt[1])));
            //re.setId(ControladorC.sacarHash(re.getNombre()));
            //re.setEstado("Enviando...");
            //Sistema.agregarEnvio(re);
            bis = new BufferedInputStream(new FileInputStream(localFile));
            bos = new BufferedOutputStream(connection.getOutputStream());
            //Enviamos el nombre del fichero
            DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            dos.writeUTF(localFile.getName()+":"+Integer.toString((int)localFile.length()));
            int tamano = (int)localFile.length();
            if((int)ois.readObject()==0){
                //Enviamos el fichero
                re.setTamano(tamano);
                byteArray = new byte[(int)localFile.length()];
                //Mando:
                int k=0;
                while ((in = bis.read(byteArray)) != -1){
                    bos.write(byteArray,0,in);
                    k+=in;
                }
            }
            else{
                re.setTamano(tamano);
                byteArray = new byte[tamano/2];
                bis.skip(new Long(tamano/2));
                while ((in = bis.read(byteArray,0,byteArray.length)) != -1){
                    bos.write(byteArray,0,in);
                }
            }
            // Se cierra la conexion
            bis.close();
            bos.close();
            System.out.println("Envio de Archivo finalizado!");
        }catch ( Exception e ) {
            Logger.getLogger(EnviarArchivoCommand.class.getName()).log(Level.SEVERE, null, e);
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
