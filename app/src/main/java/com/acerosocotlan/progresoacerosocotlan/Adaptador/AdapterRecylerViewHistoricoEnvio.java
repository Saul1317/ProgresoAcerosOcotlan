package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecylerViewHistoricoEnvio extends RecyclerView.Adapter<AdapterRecylerViewHistoricoEnvio.AdapterRecyclerHolder>{

    private List<VerOfertas_retrofit> arraylistVerOfertas;
    private int resource;
    private Activity activity;
    private Context context;

    public AdapterRecylerViewHistoricoEnvio(List<VerOfertas_retrofit> arraylistVerOfertas, int resource, Activity activity, Context context) {
        this.arraylistVerOfertas = arraylistVerOfertas;
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
        VerOfertas_retrofit verOfertas_retrofit = arraylistVerOfertas.get(position);
        holder.txt_fecha_historico.setText("5 de Julio");
        //holder.lista_procesos_historico.setAdapter();
    }

    @Override
    public int getItemCount() {
        return arraylistVerOfertas.size();
    }
    public class AdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_fecha_historico;
        private ListView lista_procesos_historico;
        public AdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_fecha_historico= (TextView) itemView.findViewById(R.id.cardiview_historico_fecha);
            lista_procesos_historico= (ListView) itemView.findViewById(R.id.list_historial_proceso);
        }
    }
}