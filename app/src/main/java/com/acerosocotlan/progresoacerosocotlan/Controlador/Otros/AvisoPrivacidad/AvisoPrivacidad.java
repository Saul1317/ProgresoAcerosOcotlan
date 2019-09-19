package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.AvisoPrivacidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.acerosocotlan.progresoacerosocotlan.R;

public class AvisoPrivacidad extends AppCompatActivity {

    WebView webview_aviso_privacidad;
    String url_base = "https://www.acerosocotlan.mx/avisodeprivacidad/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_privacidad);
        webview_aviso_privacidad = (WebView) findViewById(R.id.webview_aviso_privacidad);
        webview_aviso_privacidad.loadUrl(url_base);
    }
}
