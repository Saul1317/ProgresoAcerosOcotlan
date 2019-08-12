package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.R;


/*
* ESTA VENTANA SIRVE PARA MOSTRAR EL PORTAL DE CLIENTE POR MEDIO DE UN WEBVIEW
* ESTA CLASE NO CONTINENE LOGICA MAS QUE LA BARRA DE NAVEGACION, SI SE TIENE QUE HACER UN CAMBIO TIENE QUE SER DESDE EL PROYECTO ORIGINAL
*/

public class PortalCliente extends AppCompatActivity implements View.OnClickListener {


    WebView webview_portal_cliente;
    //HABRA DOS IMAGENES QUE SERVIRAN DE BOTONES EN LA BARRA DE NAVEGACION
    ImageView webview_back, webview_sigout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_cliente);
        webview_portal_cliente = (WebView) findViewById(R.id.webview_portal_cliente);
        webview_back = (ImageView) findViewById(R.id.webview_back);
        webview_back.setOnClickListener(this);
        webview_sigout = (ImageView) findViewById(R.id.webview_sigout);
        webview_sigout.setOnClickListener(this);

        //SE CONFIGURA EL WEBVIEW
        webview_portal_cliente.setWebViewClient(new WebViewClient());
        //ACEPTA JAVASCRIPT PARA QUE FUNCIONE LA LOGICA DEL PORTAL DE CLIENTES
        webview_portal_cliente.getSettings().setJavaScriptEnabled(true);
        webview_portal_cliente.setWebChromeClient(new WebChromeClient());
        //SE LE ASIGNA LA URL DEL PORTAL DEL CLIENTE
        webview_portal_cliente.loadUrl("https://portalcliente.acerosocotlan.mx/");
    }

    //METODO PARA REGRESAR A VENTANA ANTERIOR
    @Override
    public void onBackPressed() {
        //SE VALIDA SI DENTRO DEL PORTAL DE CLIENTES SE PUEDE RETROCEDER DE VENTANA
        if (webview_portal_cliente.canGoBack()) {
            //RETROCEDER DENTRO DEL WEBVIEW
            webview_portal_cliente.goBack();
        }
        else{
            //METODO DESAHIBILITADO PARA RETROCEDER DENTRO DE LA APLICACION
            //super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //BOTON PARA CERRAR EL WEBVIEW Y VOLVER AL MENU PRINCIPAL
            case R.id.webview_back:
                //SE MANDA LLAMAR A LA VENTANA DEL MENU
                Intent intent = new Intent(this, MenuOpciones.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            //CERRAR SESION DEL PORTAL DE CLIENTES
            case R.id.webview_sigout:
                //AL WEBVIEW SE LE ASIGNA UNA NUEVA URL PARA CERRAR LA SESION
                webview_portal_cliente.loadUrl("https://portalcliente.acerosocotlan.mx/login");
                webview_sigout.setVisibility(View.GONE);
                //SE ELIMINA ELIMINA CACHE
                webview_portal_cliente.clearCache(true);
                break;

            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
