package com.principal.Comandos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

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
public class EnviarMensajeCommand extends BaseCommand {

    public static final String COMMAND_NAME="send";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        if (args.length==3) {
            enviarDato(args[2], args[0], Integer.parseInt(args[1]));
        }else{
            System.out.println("La cantidad de parametros es erronea!");
        }
    }

    /**
     * Este metodo se encarga de enviar datos en forma de objetos serializados
     * a un nodo (fantasma o de anillo) que cuya ip y puerto se conoce perfectamente.
     * @param dato
     * @param ip
     * @param puerto
     * @return
     */
    public static Object enviarDato(Object dato,String ip, int puerto){
        try {
            ObjectOutputStream salidaObjeto;
            //Se colocan los datos del servidor central (Direccion IP y Puerto).
            Socket reves = null;
            reves = new Socket (ip,puerto);
            salidaObjeto = new ObjectOutputStream(reves.getOutputStream());
            //El cliente manda:
            salidaObjeto.writeObject(dato);
            //El cliente recibe:
            ObjectInputStream ois = new ObjectInputStream(reves.getInputStream());
            Object respuesta = ois.readObject();
            //Se cierra la conexion.
            reves.close();
            return respuesta;
        } catch (IOException ex) {
            //Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ServidorCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
