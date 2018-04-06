package com.Comandos;

import com.Entidades.Nodo;
import com.Entidades.Recurso;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Junior on 02/04/2018.
 */
public class RecibirArchivoCommand extends AsyncCommand {

    public static final String COMMNAND_NAME="download";

    @Override
    public String obtenerNombreComando() {
        return COMMNAND_NAME;
    }


    @Override
    public void executeOnBackground(String[] args, OutputStream out) {
        try {
            DataOutputStream output;
            BufferedInputStream bis;
            BufferedOutputStream bos;
            byte[] receivedData;
            int in;
            String file;
            String[] d = null;
            DataInputStream dis = null;
            Recurso re = new Recurso();
            String ip =args[0];
            int puerto = Integer.parseInt(args[1]);
            String nombre = args[2];
            System.out.println("Iniciando proceso de descarga de archivo");
            // Se abre una conexion con Servidor Socket
            Socket cliente = new Socket(ip, puerto);
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(cliente.getOutputStream());
            //Solicito el archivo:
            salidaObjeto.writeObject("4:" + nombre);
            bis = new BufferedInputStream(cliente.getInputStream());
            dis = new DataInputStream(cliente.getInputStream());
            //Recibimos el nombre del fichero
            file = dis.readUTF();
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
                bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0]));
                bos.write(arreglo, 0, in);
                bos.close();
                salidaObjeto.writeObject(1);
                bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0],true));
            }
            else{
                recarga=false;
                salidaObjeto.writeObject(0);
                bos = new BufferedOutputStream(new FileOutputStream("Descargas\\" + d[0]));
            }

            int l = 0;
            boolean partido = true;
            //Se manejan los datos acerca del libro recibido
            while ((in = bis.read(receivedData)) != -1) {
                // System.out.println("entro");
                bos.write(receivedData, 0, in);
                re.setTamano(re.getTamano() + in);
                if (partido && l > (re.getTamano() / 2)) {
                    partido=false;
                    System.out.println("El archivo ha alcanzado el 50%");
                    bos.close();
                    bos = new BufferedOutputStream(new FileOutputStream("recibidos\\" + d[0], true));
                }

                l += in;
            }
            //Se cierra la conexion con el servidor de descarga.
            bos.close();
            dis.close();
            System.out.println("Finalizando proceso de descarga de archivo");
        } catch (Exception e) {
            Logger.getLogger(RecibirArchivoCommand.class.getName()).log(Level.SEVERE, null, e);
        }

    }


}
