package com.acerosocotlan.progresoacerosocotlan.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Historico_retrofit {

    private String fecha_proceso;
    private ArrayList<String> proceso_entrega;

    public Historico_retrofit(String fecha_proceso, ArrayList<String> proceso_entrega) {
        this.fecha_proceso = fecha_proceso;
        this.proceso_entrega = proceso_entrega;
    }

    public String getFecha_proceso() {
        return fecha_proceso;
    }
    public void setFecha_proceso(String fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public ArrayList<String> getProceso_entrega() {
        return proceso_entrega;
    }
    public void setProceso_entrega(ArrayList<String> proceso_entrega) {
        this.proceso_entrega = proceso_entrega;
    }
}
