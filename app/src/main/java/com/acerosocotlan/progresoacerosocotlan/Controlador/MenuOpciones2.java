package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo.CatalogoProductos;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

public class MenuOpciones2 extends AppCompatActivity implements View.OnClickListener {

    ImageView button_rastreo, btn_portal_cliente, button_producto;
    FrameLayout Framelayout_background_menu;
    private SharedPreferences prs;
    ImageView imagen_fondo_menu;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones2);
        Framelayout_background_menu = (FrameLayout) findViewById(R.id.Framelayout_background_menu);
        Framelayout_background_menu.setOnClickListener(this);
        imagen_fondo_menu = (ImageView) findViewById(R.id.imagen_fondo_menu);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        button_rastreo = (ImageView) findViewById(R.id.button_rastreo);
        button_rastreo.setOnClickListener(this);
        btn_portal_cliente = (ImageView) findViewById(R.id.btn_portal_cliente);
        btn_portal_cliente.setOnClickListener(this);
        button_producto = (ImageView) findViewById(R.id.button_producto);
        button_producto.setOnClickListener(this);
        Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(imagen_fondo_menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_rastreo:
                abrirRastreoPedido();
                break;

            case R.id.btn_portal_cliente:
                abrirPortalCliente();
                break;

            case R.id.button_producto:
                abrirCatalogo();
                break;

            case R.id.Framelayout_background_menu:
                validarPantallaSecreta();
                break;
            default:
                break;
        }
    }

    private void validarPantallaSecreta() {
        if(count == 10){
            count = 1;
            Intent pantallaSecreta = new Intent(MenuOpciones2.this, FirmaSistemasActivity.class);
            startActivity(pantallaSecreta);
        }else{
            count = count + 1;
        }
    }

    private void abrirRastreoPedido() {
        Intent intentCodigoIngreso = new Intent(MenuOpciones2.this,CodigoIngreso.class);
        Intent intentProcesoEntrega = new Intent(MenuOpciones2.this, ProgresoEntregaActivity.class);
        if (!TextUtils.isEmpty(MetodosSharedPreference.ObtenerCodigoEntregaPref(prs))){
            startActivity(intentProcesoEntrega);
        }else{
            startActivity(intentCodigoIngreso);
            //overridePendingTransition(R.anim.activity_transition_in,R.anim.activity_transition_in);
        }
    }

    private void abrirPortalCliente() {
        /*Uri uri = Uri.parse("https://portalcliente.acerosocotlan.mx/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        Intent intentCodigoIngreso = new Intent(MenuOpciones2.this,PortalCliente.class);
        startActivity(intentCodigoIngreso);
    }

    private void abrirCatalogo() {
        Intent intent = new Intent(MenuOpciones2.this, CatalogoProductos.class);
        startActivity(intent);
    }
}
