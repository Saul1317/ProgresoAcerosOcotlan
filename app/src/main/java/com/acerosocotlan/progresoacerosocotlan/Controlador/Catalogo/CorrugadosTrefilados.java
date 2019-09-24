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

public class CorrugadosTrefilados extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_varilla, btn_producto_alambre, btn_producto_castillos, btn_producto_malla;
    ImageView img_corrugados_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrugados_trefilados);
        //se inicializa los componentes de la vista
        iniciador();
    }

    public void iniciador(){
        btn_producto_varilla = (Button) findViewById(R.id.btn_producto_varilla);
        btn_producto_varilla.setOnClickListener(this);
        btn_producto_alambre = (Button) findViewById(R.id.btn_producto_alambre);
        btn_producto_alambre.setOnClickListener(this);
        btn_producto_castillos = (Button) findViewById(R.id.btn_producto_castillos);
        btn_producto_castillos.setOnClickListener(this);
        btn_producto_malla = (Button) findViewById(R.id.btn_producto_malla);
        btn_producto_malla.setOnClickListener(this);
        img_corrugados_background = (ImageView) findViewById(R.id.img_corrugados_background);
        //Descarga imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_corrugados_background);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_producto_varilla:
                nextActivity(URLs.URL_CORRUGADOS_VARILLA);
                break;

            case R.id.btn_producto_alambre:
                nextActivity(URLs.URL_CORRUGADOS_ALAMBRE);
                break;

            case R.id.btn_producto_castillos:
                nextActivity(URLs.URL_CORRUGADOS_CASTILLO);
                break;

            case R.id.btn_producto_malla:
                nextActivity(URLs.URL_CORRUGADOS_MALLA);
                break;

            default:
                break;
        }
    }

    //Se abre CataloPDF.class para visualizar el producto seleccionado
    public void nextActivity(String nombre_pdf){
        Intent intent = new Intent(this, CatalogoPDF.class);
        intent.putExtra("url_pdf", nombre_pdf);
        startActivity(intent);
    }
}
