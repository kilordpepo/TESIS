package com.Comandos;

import java.util.ArrayList;
import java.util.List;

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
public class CommandUtil {

    public static String[] tokenizerArgs(String args) {
        List<String> tokens = new ArrayList<String>();
        char[] charArray = args.toCharArray();
        String contact = "";
        boolean inText = false;
        for (char c : charArray) {
            if (c == ' ' && !inText) {
                if (contact.length() != 0) {
                    tokens.add(contact);
                    contact = "";
                }
            } else if (c == '"') {
                if (inText) {
                    tokens.add(contact);
                    contact = "";
                    inText = false;
                } else {
                    inText = true;
                }
            } else {
                contact += c;
            }
        }
        if (contact.trim().length() != 0) {
            tokens.add(contact.trim());
        }
        String[] argsArray = new String[tokens.size()];
        argsArray = tokens.toArray(argsArray);
        return argsArray;

    }
}
