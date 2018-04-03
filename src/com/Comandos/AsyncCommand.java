package com.Comandos;

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
public abstract class AsyncCommand extends BaseCommand {

    public void ejecutar(final String[] args, final OutputStream out) {
        new Thread(new Runnable() {
            public void run() {
                executeOnBackground(args, out);
            }
        }).start();
    }

    public abstract void executeOnBackground(String[] args, OutputStream out);
}
