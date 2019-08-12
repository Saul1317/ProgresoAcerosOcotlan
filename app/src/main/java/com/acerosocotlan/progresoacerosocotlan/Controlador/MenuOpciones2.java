package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.R;

public class MenuOpciones2 extends AppCompatActivity implements View.OnClickListener {

    Button button_rastreo, btn_portal_cliente;
    private SharedPreferences prs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones2);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        button_rastreo = (Button) findViewById(R.id.button_rastreo);
        button_rastreo.setOnClickListener(this);
        btn_portal_cliente = (Button) findViewById(R.id.btn_portal_cliente);
        btn_portal_cliente.setOnClickListener(this);
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
            default:
                break;
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
}
