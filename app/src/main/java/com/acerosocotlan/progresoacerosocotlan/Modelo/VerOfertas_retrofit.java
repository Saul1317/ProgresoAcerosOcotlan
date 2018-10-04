package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerOfertas_retrofit {
    @SerializedName("id_promo")
    @Expose
    private String idPromo;
    @SerializedName("producto")
    @Expose
    private String producto;
    @SerializedName("precio")
    @Expose
    private String precio;
    @SerializedName("vigencia")
    @Expose
    private String vigencia;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("fecha_alta")
    @Expose
    private String fechaAlta;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("sociedad")
    @Expose
    private String sociedad;
    @SerializedName("activo")
    @Expose
    private String activo;

    public VerOfertas_retrofit(String idPromo, String producto, String precio, String vigencia, String usuario, String fechaAlta, String foto, String sociedad, String activo) {
        this.idPromo = idPromo;
        this.producto = producto;
        this.precio = precio;
        this.vigencia = vigencia;
        this.usuario = usuario;
        this.fechaAlta = fechaAlta;
        this.foto = foto;
        this.sociedad = sociedad;
        this.activo = activo;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
}
