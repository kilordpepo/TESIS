package com.Comandos;

import com.Entidades.Fantasma;
import com.Entidades.Nodo;
import com.Entidades.NodoRF;

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
        ArrayList<NodoRF> resultado = new ArrayList<NodoRF>();
        Fantasma fantasma = Fantasma.obtenerInstancia();
        for (NodoRF elemento : fantasma.getAnillo())
        {
            original.add(elemento.hashCode());
        }
        Collections.sort(original);
        for(int elemento : original){
            for(NodoRF organizando : fantasma.getAnillo()){
                if (elemento == organizando.hashCode())
                    resultado.add(organizando);
            }
        }
        fantasma.setAnillo(resultado);
    }
}
