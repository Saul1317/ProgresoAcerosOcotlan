package com.acerosocotlan.progresoacerosocotlan.Modelo;

import android.content.SharedPreferences;

/**
 * Created by Saul on 23/01/2018.
 */

public class MetodosSharedPreference {

    public static String ObtenerCodigoEntregaPref(SharedPreferences prs){
        return prs.getString("codigo_entrega",null);
    }
    public static String ObtenerEstatusEntregaPref(SharedPreferences prs){
        return prs.getString("estatus_entrega",null);
    }

    public static void GuardarCodigoEntrega(SharedPreferences prs,String codigo){
        SharedPreferences.Editor editor = prs.edit();
        editor.putString("codigo_entrega", codigo);
        editor.apply();
    }

    public static void GuardarEstatusEntrega(SharedPreferences prs,String estatus){
        SharedPreferences.Editor editor = prs.edit();
        editor.putString("estatus_entrega", estatus);
        editor.apply();
    }

    public static void GuardarPruebaEntrega(SharedPreferences prs,String estado){
        SharedPreferences.Editor editor = prs.edit();
        editor.putString("estado_entrega", estado);
        editor.apply();
    }

    public static String ObtenerPruebaEntregaPref(SharedPreferences prs){
        return prs.getString("estado_entrega",null);
    }
}
