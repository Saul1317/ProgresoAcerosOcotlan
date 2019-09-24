package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.PortalTransaparencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;

public class PortalTransparencia extends AppCompatActivity {

    WebView webview_portal_transparencia;
    String url_base = "https://www.acerosocotlan.mx/transparencia/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_transparencia);
        iniciador();
    }

    private void iniciador() {
        webview_portal_transparencia = (WebView) findViewById(R.id.webview_portal_transparencia);
        webview_portal_transparencia.loadUrl(URLs.URL_PORTAL_TRANSPARENCIA_PDF);
    }
}
