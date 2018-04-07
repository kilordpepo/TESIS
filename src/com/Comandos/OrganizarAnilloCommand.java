package com.Comandos;

import com.Entidades.Fantasma;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

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
public class OrganizarAnilloCommand extends BaseCommand{

    public static final String COMMAND_NAME = "order";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        ArrayList<Integer> original = new ArrayList<Integer>();
        ArrayList<String> resultado = new ArrayList<String>();
        Fantasma fantasma = Fantasma.obtenerInstancia();
        for (String elemento : fantasma.getAnillo())
        {
            original.add(Integer.parseInt(elemento.split(":")[1]));
        }
        Collections.sort(original);
        for(int elemento : original){
            for(String organizando : fantasma.getAnillo()){
                if (elemento == Integer.parseInt(organizando.split(":")[1]))
                    resultado.add(organizando);
            }
        }
        fantasma.setAnillo(resultado);
    }
}
