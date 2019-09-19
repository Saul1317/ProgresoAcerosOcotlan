package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;


/**
 * Created by rvazquez on 16/05/2017.
 */
public class adapter_spinner_categorias extends ArrayAdapter<Categorias>
{
    private Context context;

    List<Categorias> datos = null;

    public adapter_spinner_categorias(Context context, List<Categorias> datos)
    {
        //se debe indicar el layout para el item que seleccionado (el que se muestra sobre el botón del botón)
        super(context, R.layout.lista_categoria_spinner, datos);
        this.context = context;
        this.datos = datos;
    }

    //este método establece el elemento seleccionado sobre el botón del spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.lista_categoria_spinner,null);
        }
        ((TextView) convertView.findViewById(R.id.texto)).setText(datos.get(position).getName());

        return convertView;
    }

    //gestiona la lista usando el View Holder Pattern. Equivale a la típica implementación del getView
    //de un Adapter de un ListView ordinario
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.simple_spinner, parent, false);
        }

        if (row.getTag() == null)
        {
            adapter_spinner_categorias.UnidadesHolder UnidadesHolder = new adapter_spinner_categorias.UnidadesHolder();
            UnidadesHolder.setTextView((TextView) row.findViewById(R.id.texto));
            row.setTag(UnidadesHolder);
        }

        //rellenamos el layout con los datos de la fila que se está procesando
        Categorias categorias = datos.get(position);
        ((adapter_spinner_categorias.UnidadesHolder) row.getTag()).getTextView().setText(categorias.getName());

        return row;
    }

    /**
     * Holder para el Adapter del Spinner
     * @author danielme.com
     *
     */

    private static class UnidadesHolder
    {


        private TextView textView;

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }

}
