package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Factura_retrofit {

    @SerializedName("sociedad")
    @Expose
    private String sociedad;
    @SerializedName("ruta")
    @Expose
    private String ruta;

    public Factura_retrofit(String sociedad, String ruta) {
        this.sociedad = sociedad;
        this.ruta = ruta;
    }

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Factura_retrofit{" +
                "sociedad='" + sociedad + '\'' +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}
