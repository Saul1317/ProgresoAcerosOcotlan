package com.acerosocotlan.progresoacerosocotlan.Modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcuseRecibo_retrofit {
    @SerializedName("resp")
    @Expose
    private String resp;

    public AcuseRecibo_retrofit(String resp) {
        this.resp = resp;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "AcuseRecibo_retrofit{" +
                "resp='" + resp + '\'' +
                '}';
    }
}
