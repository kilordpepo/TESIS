package com.principal.Comandos;

import com.principal.Entidades.Miembro;
import com.principal.Entidades.Recurso;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Junior on 01/04/2018.
 */
public class EnviarArchivoCommand extends BaseCommand{

    public static final String COMMAND_NAME="sendfile";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {

        if (args.length==2) {
            enviarArchivo(args[1], args[0], 2003);
        }else{
            System.out.println("La cantidad de parametros es erronea!");
        }
    }


    /**
     * Este metodo se encarga del envio de archivos al nodo que lo solicite
     */
    public void enviarArchivo(String ruta,String direccion, int puerto){
        DataInputStream input;
        BufferedInputStream bis;
        BufferedOutputStream bos;
        ServerSocket server;
        Socket connection;
        Miembro miembro = Miembro.obtenerInstancia();
        int in;
        byte[] byteArray;
        byte[] mitad;
        //Fichero a transferir
        String solicitud= null;
        String ip;
        String libro;
        String [] dt=null;
        Recurso re=null;

            int id=0;
            //ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            //solicitud = (String)ois.readObject();
            dt = solicitud.split(":");
            //System.out.println("Iniciando proceso de envio del archivo: "+ buscarArchivo(Integer.parseInt(dt[1])));
            //File localFile = new File("canciones"+miembro.getPuertopeticion()+"/"+buscarArchivo(Integer.parseInt(dt[1])));
            //System.out.println("Recibido es: " + solicitud);
            //System.out.println("El archivo es: " + buscarArchivo(Integer.parseInt(dt[1])));
            re = new Recurso();
            //re.setNombre(buscarArchivo(Integer.parseInt(dt[1])));
            //re.setId(ControladorC.sacarHash(re.getNombre()));
            re.setEstado("Enviando...");
            //Sistema.agregarEnvio(re);
            //bis = new BufferedInputStream(new FileInputStream(localFile));
            //bos = new BufferedOutputStream(connection.getOutputStream());
            //Enviamos el nombre del fichero
            //DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
            //dos.writeUTF(localFile.getName()+":"+Integer.toString((int)localFile.length()));
            //int tamano = (int)localFile.length();
            //if((int)ois.readObject()==0){
                //Enviamos el fichero
                //re.setTamanototal(tamano);
                //byteArray = new byte[(int)localFile.length()];
                //Mando:
                int k=0;
                //while ((in = bis.read(byteArray)) != -1){
                  //  bos.write(byteArray,0,in);
                   // k+=in;
                //}
            //}
            //else{
               // re.setTamanototal(tamano);
               // byteArray = new byte[tamano/2];
               // bis.skip(new Long(tamano/2));
               // while ((in = bis.read(byteArray,0,byteArray.length)) != -1){
               //     bos.write(byteArray,0,in);
            //    }
            //}
            // Se cierra la conexion
            //bis.close();
            //bos.close();
            System.out.println("Envio de Archivo finalizado!");

    }

    /**
     * Este nodo se encarga de reanudar la descarga en caso de ser necesario.
     * @param nombre
     * @return
     */
    public boolean reanudarDescarga(int nombre){
        Miembro miembro = Miembro.obtenerInstancia();
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
