package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.Historial_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Historico_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecylerViewHistoricoEnvio extends RecyclerView.Adapter<AdapterRecylerViewHistoricoEnvio.AdapterRecyclerHolder>{

    private List<Historial_retrofit> arraylistHistorico;
    private int resource;
    private Activity activity;
    private Context context;

    public AdapterRecylerViewHistoricoEnvio(List<Historial_retrofit> arraylistHistorico, int resource, Activity activity, Context context) {
        this.arraylistHistorico = arraylistHistorico;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public AdapterRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new AdapterRecylerViewHistoricoEnvio.AdapterRecyclerHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterRecyclerHolder holder, int position) {
        Historial_retrofit historico_retrofit = arraylistHistorico.get(position);
        holder.txt_fecha_historico.setText(historico_retrofit.getFecha());
        holder.lista_procesos_historico.setText(historico_retrofit.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return arraylistHistorico.size();
    }
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