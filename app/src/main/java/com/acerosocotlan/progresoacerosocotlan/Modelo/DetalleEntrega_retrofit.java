package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetalleEntrega_retrofit {
    @SerializedName("sucursal")
    @Expose
    private String sucursal;
    @SerializedName("rastreo")
    @Expose
    private String rastreo;
    @SerializedName("genero")
    @Expose
    private String genero;
    @SerializedName("naturaleza")
    @Expose
    private String naturaleza;
    @SerializedName("grupo")
    @Expose
    private String grupo;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("foliode")
    @Expose
    private String foliode;
    @SerializedName("numpartida")
    @Expose
    private String numpartida;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("cantidadkg")
    @Expose
    private String cantidadkg;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("unidad")
    @Expose
    private String unidad;
    @SerializedName("piezas")
    @Expose
    private String piezas;

    public DetalleEntrega_retrofit(String sucursal, String rastreo, String genero, String naturaleza, String grupo, String tipo, String foliode, String numpartida, String codigo, String cantidadkg, String material, String unidad, String piezas) {
        this.sucursal = sucursal;
        this.rastreo = rastreo;
        this.genero = genero;
        this.naturaleza = naturaleza;
        this.grupo = grupo;
        this.tipo = tipo;
        this.foliode = foliode;
        this.numpartida = numpartida;
        this.codigo = codigo;
        this.cantidadkg = cantidadkg;
        this.material = material;
        this.unidad = unidad;
        this.piezas = piezas;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getRastreo() {
        return rastreo;
    }

    public void setRastreo(String rastreo) {
        this.rastreo = rastreo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFoliode() {
        return foliode;
    }

    public void setFoliode(String foliode) {
        this.foliode = foliode;
    }

    public String getNumpartida() {
        return numpartida;
    }

    public void setNumpartida(String numpartida) {
        this.numpartida = numpartida;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidadkg() {
        return cantidadkg;
    }

    public void setCantidadkg(String cantidadkg) {
        this.cantidadkg = cantidadkg;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getPiezas() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas = piezas;
    }

    @Override
    public String toString() {
        return "DetalleEntrega_retrofit{" +
                "sucursal='" + sucursal + '\'' +
                ", rastreo='" + rastreo + '\'' +
                ", genero='" + genero + '\'' +
                ", naturaleza='" + naturaleza + '\'' +
                ", grupo='" + grupo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", foliode='" + foliode + '\'' +
                ", numpartida='" + numpartida + '\'' +
                ", codigo='" + codigo + '\'' +
                ", cantidadkg='" + cantidadkg + '\'' +
                ", material='" + material + '\'' +
                ", unidad='" + unidad + '\'' +
                ", piezas='" + piezas + '\'' +
                '}';
    }
}
