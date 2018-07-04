package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerViewOfertas extends RecyclerView.Adapter<AdapterRecyclerViewOfertas.AdapterRecyclerHolder>{

    private List<VerOfertas_retrofit> arraylistVerOfertas;
    private int resource;
    private Activity activity;
    private Context context;

    public AdapterRecyclerViewOfertas(List<VerOfertas_retrofit> arraylistVerOfertas,int resource, Activity activity, Context context) {
        this.arraylistVerOfertas = arraylistVerOfertas;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }
    @Override
    public AdapterRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new AdapterRecyclerViewOfertas.AdapterRecyclerHolder(view);
    }
    @Override
    public void onBindViewHolder(AdapterRecyclerHolder holder, int position) {
        VerOfertas_retrofit verOfertas_retrofit = arraylistVerOfertas.get(position);
        holder.txt_nombre_producto_oferta.setText(verOfertas_retrofit.getProducto()+" ");
        holder.txt_fecha_vigencia_producto_oferta.setText(" "+verOfertas_retrofit.getVigencia());
        holder.txt_precio_producto_oferta.setText("$"+verOfertas_retrofit.getPrecio()+" ");
        Picasso.with(context).load(verOfertas_retrofit.getFoto().toString()).placeholder(R.drawable.vigaejemplo).error(R.drawable.viga).into(holder.img_producto_oferta);
    }
    @Override
    public int getItemCount() {
        return arraylistVerOfertas.size();
    }
    public class AdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_nombre_producto_oferta, txt_fecha_vigencia_producto_oferta, txt_precio_producto_oferta;
        private ImageView img_producto_oferta;
        public AdapterRecyclerHolder(View itemView) {
            super(itemView);
            img_producto_oferta= (ImageView) itemView.findViewById(R.id.imagen_producto_oferta);

            txt_nombre_producto_oferta= (TextView) itemView.findViewById(R.id.txt_nombre_producto_oferta);
            txt_fecha_vigencia_producto_oferta= (TextView) itemView.findViewById(R.id.txt_fecha_vigencia_producto_oferta);
            txt_precio_producto_oferta= (TextView) itemView.findViewById(R.id.txt_precio_producto_oferta);
        }
    }
}