package com.Comandos;

import java.io.OutputStream;

public class HelpCommand extends BaseCommand {

    public static final String COMMAND_NAME = "help";
    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        System.out.println("Aqui se listan los siguientes comandos que se puede realizar:");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("update");
        System.out.println("network - Permite asignarle una IP y puerto a un nodo");
        System.out.println("search - Permite realizar busquedas");
        System.out.println("download - Permite descargar un archivo");
        System.out.println("sendfile - Permite el envio de un archivo ");
        System.out.println("send - Permite el envio de un mensaje ");
        System.out.println("order - Ordena el anillo");
        System.out.println("listen - Habilita un puerto de escucha para solicitudes");
        System.out.println("listenfile - Habilita un puerto de escucha para descargas");
        System.out.println("type - Define el tipo de nodo");
        System.out.println("-----------------------------------------------------------------");

    }
}