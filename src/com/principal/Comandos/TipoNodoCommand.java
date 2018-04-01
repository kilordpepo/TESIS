package com.principal.Comandos;

import com.principal.Utils.SistemaUtil;

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
public class TipoNodoCommand extends BaseCommand {

    public static final String COMMAND_NAME ="type";

    @Override
    public String obtenerNombreComando() {
        return COMMAND_NAME;
    }

    @Override
    public void ejecutar(String[] args, OutputStream out) {
        if (args[0].equals("miembro"))
            SistemaUtil.tipo = "miembro";
        else if (args[0].equals("fantasma"))
            SistemaUtil.tipo ="fantasma";
        else
            write(out,"Este tipo de nodo no existe! ");

    }
}
