package com.Comandos;

import com.Entidades.Nodo;
import com.Entidades.Recurso;
import com.Utils.RespuestaUtils;

import java.io.File;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class CargarRecursosCommand extends BaseCommand {

    public static final String COMMAND_NAME = "cargarRecursos";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        try {
            File carpeta = new File("recursos");
            System.out.println("Recursos ofrecidos:");
            Nodo nodo = Nodo.obtenerInstancia();
            ArrayList<Recurso> recursos = new ArrayList<>();
            Recurso recurso;
            int id = 0;
            for (File archivo : carpeta.listFiles()) {
                recurso = new Recurso();
                recurso.setNombre(archivo.getName());
                recurso.setTamano(archivo.length());
                recurso.setId(id++);
                recurso.setDescargas(0);
                recurso.setHash(RespuestaUtils.generarHash(recurso.getNombre()));
                recurso.setRuta(archivo.getPath());
                recurso.setPropietario(nodo.getDireccion());
                recursos.add(recurso);
            }
            nodo.setRecursos(recursos);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
