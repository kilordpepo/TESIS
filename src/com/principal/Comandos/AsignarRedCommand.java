package com.principal.Comandos;

import com.principal.Entidades.Fantasma;
import com.principal.Entidades.Miembro;
import com.principal.Utils.SistemaUtil;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.OutputStream;

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
public class AsignarRedCommand extends BaseCommand {

    public static final String COMMAND_NAME = "network";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        if (args.length>1){
            if (!SistemaUtil.tipo.equals("")) {
                if (args[2].equals("member")) {
                    Miembro miembro = Miembro.obtenerInstancia();
                    miembro.setDireccion(args[0]);
                    miembro.setPuertopeticion(Integer.parseInt(args[1]));
                    miembro.setPuertoArchivo(Integer.parseInt(args[1]) + 1);
                } else if (args[2].equals("center")) {
                    Fantasma fantasma = Fantasma.obtenerInstancia();
                    fantasma.setDireccion(args[0]);
                    fantasma.setPuertopeticion(Integer.parseInt(args[1]));
                } else {
                    write(out, "Debe especificar el tipo de nodo");
                }
            }else{
                System.out.println("Debe especificar el tipo de nodo primero! ");
            }

        }else if(args.length == 1)
            {
                if (args[0].equals("-s"))
                {
                    if (SistemaUtil.tipo.equals("miembro")) {
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Sus datos de conexion son: ");
                        System.out.println("-------------------------------------------------------");
                        Miembro miembro = Miembro.obtenerInstancia();
                        System.out.println("IP: " +  miembro.getDireccion());
                        System.out.println("Hash IP: " + miembro.getHash());
                        System.out.println("Puerto de Escucha: "+ miembro.getPuertopeticion());
                        System.out.println("Puerto de Archivos: "+miembro.getPuertoArchivo() );
                        System.out.println("-------------------------------------------------------");
                    }

                    if (SistemaUtil.tipo.equals("fantasma")) {
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Sus datos de conexion son: ");
                        System.out.println("-------------------------------------------------------");
                        Fantasma fantasma = Fantasma.obtenerInstancia();
                        System.out.println("IP: " +  fantasma.getDireccion());
                        System.out.println("Hash IP: " + fantasma.getHash());
                        System.out.println("Puerto de Escucha: "+ fantasma.getPuertopeticion());
                        System.out.println("-------------------------------------------------------");
                    }

                    if (SistemaUtil.tipo.equals("")){
                        System.out.println("Usted no ha especificado su tipo de nodo");
                    }
                }
            }
    }
}
