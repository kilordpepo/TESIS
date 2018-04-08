package com.Entidades;

import com.Utils.RespuestaUtils;

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
public class NodoRF extends Miembro {


    public NodoRF(String ip, int puerto) throws NoSuchAlgorithmException {
           super(ip, puerto, RespuestaUtils.generarHash(ip));
    }
}
