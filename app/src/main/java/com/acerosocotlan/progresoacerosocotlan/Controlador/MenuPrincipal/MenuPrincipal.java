package com.acerosocotlan.progresoacerosocotlan.Controlador.MenuPrincipal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo.CatalogoProductos;
import com.acerosocotlan.progresoacerosocotlan.Controlador.Contacto.Contacto;
import com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.MenuOtrosApartados;
import com.acerosocotlan.progresoacerosocotlan.Controlador.PortalClientes.PortalCliente;
import com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido.CodigoIngreso;
import com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido.FirmaSistemasActivity;
import com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido.ProgresoEntregaActivity;
import com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales.MenuSucursales;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

/*
* Menú principal de la aplicación en donde el usuario tendra varias opciones
*/
public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    //componentes
    ImageView button_rastreo, btn_portal_cliente, button_producto, button_ubicacion_sucursales, button_contacto, button_otros;
    ImageView imagen_fondo_menu;
    FrameLayout Framelayout_background_menu;


    private SharedPreferences prs;
    //contador para activar la ventana secreta
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones2);
        //Se inicializan los componentes
        iniciador();
    }

    private void iniciador() {
        //acceso a las shared preference.
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);

        Framelayout_background_menu = (FrameLayout) findViewById(R.id.Framelayout_background_menu);
        Framelayout_background_menu.setOnClickListener(this);

        imagen_fondo_menu = (ImageView) findViewById(R.id.imagen_fondo_menu);

        button_rastreo = (ImageView) findViewById(R.id.button_rastreo);
        button_rastreo.setOnClickListener(this);

        btn_portal_cliente = (ImageView) findViewById(R.id.btn_portal_cliente);
        btn_portal_cliente.setOnClickListener(this);

        button_producto = (ImageView) findViewById(R.id.button_producto);
        button_producto.setOnClickListener(this);

        button_ubicacion_sucursales = (ImageView) findViewById(R.id.button_ubicacion_sucursales);
        button_ubicacion_sucursales.setOnClickListener(this);

        button_contacto = (ImageView) findViewById(R.id.button_contacto);
        button_contacto.setOnClickListener(this);

        button_otros = (ImageView) findViewById(R.id.button_otros);
        button_otros.setOnClickListener(this);

        //se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(imagen_fondo_menu);
    }

    //Evento click de los botones
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

            case R.id.button_ubicacion_sucursales:
                abrirUbicaciones();
                break;

            case R.id.button_contacto:
                abrirContacto();
                break;

            case R.id.button_otros:
                abrirOtrosApartados();
                break;

            default:
                break;
        }
    }

    //metodos para pasar a la siguiente activity

    private void abrirOtrosApartados() {
        Intent intent = new Intent(MenuPrincipal.this, MenuOtrosApartados.class);
        startActivity(intent);
    }

    private void abrirContacto() {
        Intent intent = new Intent(MenuPrincipal.this, Contacto.class);
        startActivity(intent);
    }

    private void abrirUbicaciones() {
        Intent intent = new Intent(MenuPrincipal.this, MenuSucursales.class);
        startActivity(intent);
    }

    private void abrirPortalCliente() {
        Intent intentCodigoIngreso = new Intent(MenuPrincipal.this, PortalCliente.class);
        startActivity(intentCodigoIngreso);
    }

    private void abrirCatalogo() {
        Intent intent = new Intent(MenuPrincipal.this, CatalogoProductos.class);
        startActivity(intent);
    }

    //validación para entrar en la ventana secreta
    private void validarPantallaSecreta() {
        if(count == 10){
            count = 1;
            Intent pantallaSecreta = new Intent(MenuPrincipal.this, FirmaSistemasActivity.class);
            startActivity(pantallaSecreta);
        }else{
            count = count + 1;
        }
    }

    //Validacion de código de rastreo para saber si el usuario tiene un pedido pendiente
    private void abrirRastreoPedido() {
        Intent intentCodigoIngreso = new Intent(MenuPrincipal.this, CodigoIngreso.class);
        Intent intentProcesoEntrega = new Intent(MenuPrincipal.this, ProgresoEntregaActivity.class);
        if (!TextUtils.isEmpty(MetodosSharedPreference.ObtenerCodigoEntregaPref(prs))){
            startActivity(intentProcesoEntrega);
        }else{
            startActivity(intentCodigoIngreso);
            //overridePendingTransition(R.anim.activity_transition_in,R.anim.activity_transition_in);
        }
    }


}
