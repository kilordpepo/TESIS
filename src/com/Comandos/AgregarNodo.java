package com.Comandos;

import com.Entidades.Fantasma;
import com.Entidades.NodoRF;

import java.io.OutputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

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
public class AgregarNodo extends BaseCommand {

    public static final String COMMAND_NAME="addnode";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        Fantasma fantasma = Fantasma.obtenerInstancia();
        try {
            fantasma.getAnillo().add(new NodoRF(args[0],Integer.parseInt(args[1])));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
