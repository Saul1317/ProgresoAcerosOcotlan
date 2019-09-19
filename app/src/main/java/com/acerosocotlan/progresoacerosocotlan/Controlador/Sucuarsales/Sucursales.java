package com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales;

public class Sucursales {

    private String estado;
    private String sucursales;
    private String direccion;

    public Sucursales(String estado, String sucursales, String direccion) {
        this.estado = estado;
        this.sucursales = sucursales;
        this.direccion = direccion;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSucursales() {
        return sucursales;
    }

    public void setSucursales(String sucursales) {
        this.sucursales = sucursales;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Sucursales{" +
                "estado='" + estado + '\'' +
                ", sucursales='" + sucursales + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
