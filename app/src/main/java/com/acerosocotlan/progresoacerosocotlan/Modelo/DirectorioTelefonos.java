package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectorioTelefonos {
    @SerializedName("sociedad")
    @Expose
    private String sociedad;
    @SerializedName("sucursal")
    @Expose
    private String sucursal;
    @SerializedName("telefono")
    @Expose
    private String telefono;

    public DirectorioTelefonos(String sociedad, String sucursal, String telefono) {
        this.sociedad = sociedad;
        this.sucursal = sucursal;
        this.telefono = telefono;
    }

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "DirectorioTelefonos{" +
                "sociedad='" + sociedad + '\'' +
                ", sucursal='" + sucursal + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
