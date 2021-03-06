package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales.Sucursales;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

/**
 * Created by Saul.
 *
 * El adpatador funciona para configurar las propiedades y el comportamiento que tendrá el recyclerview
 * Adaptador para cargar todas las sucursales de aceros ocotlan en el recyclerview de SucursalesDisponibles.class
 */
public class AdapterRecyclerViewSucursales extends RecyclerView.Adapter<AdapterRecyclerViewSucursales.AdapterRecyclerViewSucursalesHolder>{

    //Lista de resultados obtenidos en una consulta
    private List<Sucursales> sucursalesList;
    //el archivo xml que se utilizara para configurar el Cardview
    private int resource;
    private Activity activity;
    private Context context;

    //Constructor donde recibira la lista con los resultados, el xml con la vista del cardview, la activity y el contexto
    public AdapterRecyclerViewSucursales(List<Sucursales> sucursalesList, int resource, Activity activity, Context context) {
        this.sucursalesList = sucursalesList;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }

    //Se utiliza el xml del cardview que se utilizara para el recycler view
    @Override
    public AdapterRecyclerViewSucursalesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterRecyclerViewSucursales.AdapterRecyclerViewSucursalesHolder(view);
    }

    //Se pasan los datos de la lista a los componentes del cardview
    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewSucursalesHolder holder, int i) {
        final Sucursales sucursales = sucursalesList.get(i);
        holder.sucursal_estado.setText(sucursales.getEstado());
        holder.sucursal_disponible.setText(sucursales.getSucursales());
        holder.sucursal_direccion.setText(sucursales.getDireccion());
        holder.cardview_sucursales_disponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String map = "http://maps.google.com/maps?q=" + sucursales.getDireccion();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                activity.startActivity(i);
            }
        });

    }

    //Número de items que se harán en base al número de filas que tiene la lista
    @Override
    public int getItemCount() {
        return sucursalesList.size();
    }

    //Aquí se hacen las referencias del cardview y despues se manda llamar esta clase para acceder a los componentes y pasar los datos
    public class AdapterRecyclerViewSucursalesHolder extends RecyclerView.ViewHolder{
        private TextView sucursal_disponible, sucursal_estado,sucursal_direccion;
        private CardView cardview_sucursales_disponibles;

        public AdapterRecyclerViewSucursalesHolder(View itemView) {
            super(itemView);
            cardview_sucursales_disponibles = (CardView) itemView.findViewById(R.id.cardview_sucursales_disponibles);
            sucursal_disponible = (TextView) itemView.findViewById(R.id.sucursal_disponible);
            sucursal_estado = (TextView) itemView.findViewById(R.id.sucursal_estado);
            sucursal_direccion = (TextView) itemView.findViewById(R.id.sucursal_direccion);
        }
    }
}
