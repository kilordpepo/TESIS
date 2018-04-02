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
        System.out.println("update");
        System.out.println("network");
        System.out.println("search");
        System.out.println("download");
        System.out.println("sendfile");
        System.out.println("send");
        System.out.println("order");
        System.out.println("listen");
        System.out.println("type");
        System.out.println("----------------------------------------------------");

    }
}
