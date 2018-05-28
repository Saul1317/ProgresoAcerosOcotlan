package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

/**
 * Created by Saul on 12/02/2018.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.AdapterRecyclerHolder>{

    private List<DetalleEntrega_retrofit> arraylistDetalleEntrega;
    private int resource;
    private Activity activity;
    private Context context;

    public AdapterRecyclerView(List<DetalleEntrega_retrofit> arraylistDetalleEntrega,int resource, Activity activity, Context context) {
        this.arraylistDetalleEntrega = arraylistDetalleEntrega;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }
    @Override
    public AdapterRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new AdapterRecyclerHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterRecyclerHolder holder, final int position) {
        DetalleEntrega_retrofit detalleEntrega_retrofit = arraylistDetalleEntrega.get(position);
        holder.txt_cantidad_entrega.setText(detalleEntrega_retrofit.getPiezas()+" ");
        holder.txt_unidad_entrega.setText(detalleEntrega_retrofit.getUnidad());
        holder.txt_cantidad_kg.setText(detalleEntrega_retrofit.getCantidadkg());
        holder.txt_descripcion_entrega.setText(detalleEntrega_retrofit.getMaterial());
    }
    @Override
    public int getItemCount() {
        return arraylistDetalleEntrega.size();
    }
    public class AdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_cantidad_entrega, txt_unidad_entrega,txt_cantidad_kg,txt_descripcion_entrega ;
        public AdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_cantidad_entrega = (TextView) itemView.findViewById(R.id.txt_cantidad_entrega);
            txt_unidad_entrega = (TextView) itemView.findViewById(R.id.txt_unidad_entrega);
            txt_cantidad_kg = (TextView) itemView.findViewById(R.id.txt_cantidad_kg);
            txt_descripcion_entrega = (TextView) itemView.findViewById(R.id.txt_descripcion_entrega);
        }
    }
}
