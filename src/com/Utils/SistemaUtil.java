package com.Utils;

import java.util.Calendar;

/**
 * Created by Junior on 01/04/2018.
 */
public class SistemaUtil {

    public static String tipo ="";

    public static String obtenerHora(){
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        return hora + ":" + minutos + ":" + segundos;
    }
}
