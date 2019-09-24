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

public class PerfilesTubulares extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_tubo_cedula, btn_producto_lamina_galvanizada,
            btn_producto_tubo_estructural, btn_producto_tuberia_conduccio_fluidos, btn_producto_ptr, btn_producto_tubular_pintado;
    ImageView img_perfiles_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles_tubulares);
        iniciador();
    }

    private void iniciador() {
        btn_producto_tubo_cedula = (Button) findViewById(R.id.btn_producto_tubo_cedula);
        btn_producto_tubo_cedula.setOnClickListener(this);

        btn_producto_lamina_galvanizada = (Button) findViewById(R.id.btn_producto_lamina_galvanizada);
        btn_producto_lamina_galvanizada.setOnClickListener(this);

        btn_producto_tubo_estructural = (Button) findViewById(R.id.btn_producto_tubo_estructural);
        btn_producto_tubo_estructural.setOnClickListener(this);

        btn_producto_tuberia_conduccio_fluidos = (Button) findViewById(R.id.btn_producto_tuberia_conduccio_fluidos);
        btn_producto_tuberia_conduccio_fluidos.setOnClickListener(this);

        btn_producto_ptr = (Button) findViewById(R.id.btn_producto_ptr);
        btn_producto_ptr.setOnClickListener(this);

        btn_producto_tubular_pintado = (Button) findViewById(R.id.btn_producto_tubular_pintado);
        btn_producto_tubular_pintado.setOnClickListener(this);

        img_perfiles_background = (ImageView) findViewById(R.id.img_perfiles_background);

        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_perfiles_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_producto_tubo_cedula:
                nextActivity(URLs.URL_PERFILES_TUBO_CEDULA);
                break;
            case R.id.btn_producto_lamina_galvanizada:
                nextActivity(URLs.URL_PERFILES_LAMINA_GALVANIZADA);
                break;
            case R.id.btn_producto_tubo_estructural:
                nextActivity(URLs.URL_PERFILES_TUBO_ESTRUCTURAL);
                break;
            case R.id.btn_producto_tuberia_conduccio_fluidos:
                nextActivity(URLs.URL_PERFILES_TUBO_CONDUCCION_FLUIDO);
                break;
            case R.id.btn_producto_ptr:
                nextActivity(URLs.URL_PERFILES_PTR);
                break;
            case R.id.btn_producto_tubular_pintado:
                nextActivity(URLs.URL_PERFILES_TUBULAR_PINTADO);
                break;
            default:
                break;
        }
    }

    public void nextActivity(String nombre_pdf){
        Intent intent = new Intent(this, CatalogoPDF.class);
        intent.putExtra("url_pdf", nombre_pdf);
        startActivity(intent);
    }
}
