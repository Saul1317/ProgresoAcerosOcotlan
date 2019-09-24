package com.acerosocotlan.progresoacerosocotlan.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Saul on 12/02/2018.
 *
 * El adpatador funciona para configurar las propiedades y el comportamiento que tendrá el recyclerview
 * Adaptador para cargar las ofertas semanales en el recyclerview de VerOferta.class
 */

public class AdapterRecyclerViewOfertas extends RecyclerView.Adapter<AdapterRecyclerViewOfertas.AdapterRecyclerHolder>{

    //Lista de resultados obtenidos en una consulta
    private List<VerOfertas_retrofit> arraylistVerOfertas;
    //el archivo xml que se utilizara para configurar el Cardview
    private int resource;
    private Activity activity;
    private Context context;

    //Constructor donde recibira la lista con los resultados, el xml con la vista del cardview, la activity y el contexto
    public AdapterRecyclerViewOfertas(List<VerOfertas_retrofit> arraylistVerOfertas,int resource, Activity activity, Context context) {
        this.arraylistVerOfertas = arraylistVerOfertas;
        this.resource = resource;
        this.activity = activity;
        this.context = context;
    }

    //Se utiliza el xml del cardview que se utilizara para el recycler view
    @Override
    public AdapterRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent,false);
        return new AdapterRecyclerViewOfertas.AdapterRecyclerHolder(view);
    }

    //Se pasan los datos de la lista a los componentes del cardview
    @Override
    public void onBindViewHolder(AdapterRecyclerHolder holder, int position) {
        VerOfertas_retrofit verOfertas_retrofit = arraylistVerOfertas.get(position);
        holder.txt_nombre_producto_oferta.setText("Sucursal: " + verOfertas_retrofit.getProducto()+", ");
        holder.txt_fecha_vigencia_producto_oferta.setText(" "+verOfertas_retrofit.getVigencia());
        holder.txt_precio_producto_oferta.setText("$"+verOfertas_retrofit.getPrecio()+" ");
        Picasso.with(context).load(verOfertas_retrofit.getFoto().toString()).placeholder(R.drawable.vigaejemplo).into(holder.img_producto_oferta);
    }


    //Número de items que se harán en base al número de filas que tiene la lista
    @Override
    public int getItemCount() {
        return arraylistVerOfertas.size();
    }

    //Aquí se hacen las referencias del cardview y despues se manda llamar esta clase para acceder a los componentes y pasar los datos
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