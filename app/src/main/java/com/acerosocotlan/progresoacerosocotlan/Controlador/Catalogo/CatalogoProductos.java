package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class CatalogoProductos extends AppCompatActivity implements View.OnClickListener {

    Button btn_catalogo_galvanizados, btn_catalogo_comercial, btn_catalogo_perfiles, btn_catalogo_estructurales, btn_catalogo_planos, btn_catalogo_corrugados;
    ImageView img_catalago_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_productos);
        //Inicializamos los componentes
        iniciador();

    }

    private void iniciador() {
        btn_catalogo_galvanizados = (Button) findViewById(R.id.btn_catalogo_galvanizados);
        btn_catalogo_galvanizados.setOnClickListener(this);
        btn_catalogo_comercial = (Button) findViewById(R.id.btn_catalogo_comercial);
        btn_catalogo_comercial.setOnClickListener(this);
        btn_catalogo_perfiles = (Button) findViewById(R.id.btn_catalogo_perfiles);
        btn_catalogo_perfiles.setOnClickListener(this);
        btn_catalogo_estructurales = (Button) findViewById(R.id.btn_catalogo_estructurales);
        btn_catalogo_estructurales.setOnClickListener(this);
        btn_catalogo_planos = (Button) findViewById(R.id.btn_catalogo_planos);
        btn_catalogo_planos.setOnClickListener(this);
        btn_catalogo_corrugados = (Button) findViewById(R.id.btn_catalogo_corrugados);
        btn_catalogo_corrugados.setOnClickListener(this);
        img_catalago_background = (ImageView) findViewById(R.id.img_catalago_background);
        //se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_catalago_background);
    }

    //Eventos de click de los botones por medio de su id
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_catalogo_galvanizados:
                abrirProductos(Galvanizados.class);
                break;

            case R.id.btn_catalogo_comercial:
                abrirProductos(Comerciales.class);
                break;

            case R.id.btn_catalogo_perfiles:
                abrirProductos(PerfilesTubulares.class);
                break;

            case R.id.btn_catalogo_estructurales:
                abrirProductos(Estructurales.class);
                break;

            case R.id.btn_catalogo_planos:
                abrirProductos(Planos.class);
                break;

            case R.id.btn_catalogo_corrugados:
                abrirProductos(CorrugadosTrefilados.class);
                break;
            default:
                break;
        }
    }


    //abrir el catálogo del tipo de acero seleccionado
    public void abrirProductos(Class tipo_producto){
        Intent intent = new Intent(this, tipo_producto);
        startActivity(intent);
    }

}
