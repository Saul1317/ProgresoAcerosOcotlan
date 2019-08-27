package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class CorrugadosTrefilados extends AppCompatActivity implements View.OnClickListener {

    Button btn_producto_varilla, btn_producto_alambre, btn_producto_castillos, btn_producto_malla;
    ImageView img_corrugados_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corrugados_trefilados);
        btn_producto_varilla = (Button) findViewById(R.id.btn_producto_varilla);
        btn_producto_varilla.setOnClickListener(this);
        btn_producto_alambre = (Button) findViewById(R.id.btn_producto_alambre);
        btn_producto_alambre.setOnClickListener(this);
        btn_producto_castillos = (Button) findViewById(R.id.btn_producto_castillos);
        btn_producto_castillos.setOnClickListener(this);
        btn_producto_malla = (Button) findViewById(R.id.btn_producto_malla);
        btn_producto_malla.setOnClickListener(this);
        img_corrugados_background = (ImageView) findViewById(R.id.img_corrugados_background);
        Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(img_corrugados_background);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_producto_varilla:
                nextActivity("corrugados/varilla_corrugada.pdf");
                break;

            case R.id.btn_producto_alambre:
                nextActivity("corrugados/alambre.pdf");
                break;

            case R.id.btn_producto_castillos:
                nextActivity("corrugados/castillos.pdf");
                break;

            case R.id.btn_producto_malla:
                nextActivity("corrugados/malla.pdf");
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
