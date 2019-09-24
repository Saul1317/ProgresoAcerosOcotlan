package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.Historial_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

/**
 * Created by Saul.
 *
 * El adpatador funciona para configurar las propiedades y el comportamiento que tendrá el recyclerview
 * Adaptador para cargar todas las sucursales de aceros ocotlan en el recyclerview de SucursalesDisponibles.class
 */
public class AdapterRecylerViewHistoricoEnvio extends RecyclerView.Adapter<AdapterRecylerViewHistoricoEnvio.AdapterRecyclerHolder>{

    //Lista de resultados obtenidos en una consulta
    private List<Historial_retrofit> arraylistHistorico;
    //el archivo xml que se utilizara para configurar el Cardview
    private int resource;
    private Activity activity;
    private Context context;

    //Constructor donde recibira la lista con los resultados, el xml con la vista del cardview, la activity y el contexto
    public AdapterRecylerViewHistoricoEnvio(List<Historial_retrofit> arraylistHistorico, int resource, Activity activity, Context context) {
        this.arraylistHistorico = arraylistHistorico;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }

    //Se utiliza el xml del cardview que se utilizara para el recycler view
    @Override
    public AdapterRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new AdapterRecylerViewHistoricoEnvio.AdapterRecyclerHolder(view);
    }

    //Se pasan los datos de la lista a los componentes del cardview
    @Override
    public void onBindViewHolder(AdapterRecyclerHolder holder, int position) {
        Historial_retrofit historico_retrofit = arraylistHistorico.get(position);
        holder.txt_fecha_historico.setText(historico_retrofit.getFecha());
        holder.lista_procesos_historico.setText(historico_retrofit.getDescripcion());
    }


    //Número de items que se harán en base al número de filas que tiene la lista
    @Override
    public int getItemCount() {
        return arraylistHistorico.size();
    }

    //Aquí se hacen las referencias del cardview y despues se manda llamar esta clase para acceder a los componentes y pasar los datos
    public class AdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_fecha_historico;
        private TextView lista_procesos_historico;
        public AdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_fecha_historico= (TextView) itemView.findViewById(R.id.cardiview_historico_fecha);
            lista_procesos_historico= (TextView) itemView.findViewById(R.id.txt_proceso);
        }
    }
}