package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrador on 19/04/2018.
 */

public class StatuEntrega {

    @SerializedName("FInicioRuta")
    @Expose
    private String fInicioRuta;
    @SerializedName("HInicioRuta")
    @Expose
    private String hInicioRuta;
    @SerializedName("FInicioEntrega")
    @Expose
    private String fInicioEntrega;
    @SerializedName("HInicioEntrega")
    @Expose
    private String hInicioEntrega;
    @SerializedName("FLlegadaEntrega")
    @Expose
    private String fLlegadaEntrega;
    @SerializedName("HLlegadaEntrega")
    @Expose
    private String hLlegadaEntrega;
    @SerializedName("FSalidaEntrega")
    @Expose
    private String fSalidaEntrega;
    @SerializedName("HSalidaEntrega")
    @Expose
    private String hSalidaEntrega;
    @SerializedName("pedido")
    @Expose
    private String pedido;
    @SerializedName("estatus")
    @Expose
    private String estatus;
    @SerializedName("hizoencuesta")
    @Expose
    private String hizoencuesta;
    @SerializedName("placas")
    @Expose
    private String placas;
    @SerializedName("fotocamion")
    @Expose
    private String fotocamion;
    @SerializedName("chofer")
    @Expose
    private String chofer;
    @SerializedName("sociedad")
    @Expose
    private String sociedad;
    @SerializedName("sucursal")
    @Expose
    private String sucursal;


    public StatuEntrega(String fInicioRuta, String hInicioRuta, String fInicioEntrega, String hInicioEntrega, String fLlegadaEntrega, String hLlegadaEntrega, String fSalidaEntrega, String hSalidaEntrega, String pedido, String estatus, String hizoencuesta, String placas, String fotocamion, String chofer, String sociedad, String sucursal) {
        this.fInicioRuta = fInicioRuta;
        this.hInicioRuta = hInicioRuta;
        this.fInicioEntrega = fInicioEntrega;
        this.hInicioEntrega = hInicioEntrega;
        this.fLlegadaEntrega = fLlegadaEntrega;
        this.hLlegadaEntrega = hLlegadaEntrega;
        this.fSalidaEntrega = fSalidaEntrega;
        this.hSalidaEntrega = hSalidaEntrega;
        this.pedido = pedido;
        this.estatus = estatus;
        this.hizoencuesta = hizoencuesta;
        this.placas = placas;
        this.fotocamion = fotocamion;
        this.chofer = chofer;
        this.sociedad = sociedad;
        this.sucursal = sucursal;
    }

    public String getfInicioRuta() {
        return fInicioRuta;
    }

    public void setfInicioRuta(String fInicioRuta) {
        this.fInicioRuta = fInicioRuta;
    }

    public String gethInicioRuta() {
        return hInicioRuta;
    }

    public void sethInicioRuta(String hInicioRuta) {
        this.hInicioRuta = hInicioRuta;
    }

    public String getfInicioEntrega() {
        return fInicioEntrega;
    }

    public void setfInicioEntrega(String fInicioEntrega) {
        this.fInicioEntrega = fInicioEntrega;
    }

    public String gethInicioEntrega() {
        return hInicioEntrega;
    }

    public void sethInicioEntrega(String hInicioEntrega) {
        this.hInicioEntrega = hInicioEntrega;
    }

    public String getfLlegadaEntrega() {
        return fLlegadaEntrega;
    }

    public void setfLlegadaEntrega(String fLlegadaEntrega) {
        this.fLlegadaEntrega = fLlegadaEntrega;
    }

    public String gethLlegadaEntrega() {
        return hLlegadaEntrega;
    }

    public void sethLlegadaEntrega(String hLlegadaEntrega) {
        this.hLlegadaEntrega = hLlegadaEntrega;
    }

    public String getfSalidaEntrega() {
        return fSalidaEntrega;
    }

    public void setfSalidaEntrega(String fSalidaEntrega) {
        this.fSalidaEntrega = fSalidaEntrega;
    }

    public String gethSalidaEntrega() {
        return hSalidaEntrega;
    }

    public void sethSalidaEntrega(String hSalidaEntrega) {
        this.hSalidaEntrega = hSalidaEntrega;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getHizoencuesta() {
        return hizoencuesta;
    }

    public void setHizoencuesta(String hizoencuesta) {
        this.hizoencuesta = hizoencuesta;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getFotocamion() {
        return fotocamion;
    }

    public void setFotocamion(String fotocamion) {
        this.fotocamion = fotocamion;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
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

    @Override
    public String toString() {
        return "StatuEntrega{" +
                "fInicioRuta='" + fInicioRuta + '\'' +
                ", hInicioRuta='" + hInicioRuta + '\'' +
                ", fInicioEntrega='" + fInicioEntrega + '\'' +
                ", hInicioEntrega='" + hInicioEntrega + '\'' +
                ", fLlegadaEntrega='" + fLlegadaEntrega + '\'' +
                ", hLlegadaEntrega='" + hLlegadaEntrega + '\'' +
                ", fSalidaEntrega='" + fSalidaEntrega + '\'' +
                ", hSalidaEntrega='" + hSalidaEntrega + '\'' +
                ", pedido='" + pedido + '\'' +
                ", estatus='" + estatus + '\'' +
                ", hizoencuesta='" + hizoencuesta + '\'' +
                ", placas='" + placas + '\'' +
                ", fotocamion='" + fotocamion + '\'' +
                ", chofer='" + chofer + '\'' +
                ", sociedad='" + sociedad + '\'' +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}
