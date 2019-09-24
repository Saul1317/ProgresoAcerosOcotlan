package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.net.URL;

/*
*
* Clase que sirve para visualizar los PDF del catálogo de productos.
* se utiliza un webview para mostrar el PDF.
*
*/
public class CatalogoPDF extends AppCompatActivity {

    String URL_TIPO_PRODUCTO;
    WebView webview_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_pdf);
        //Inicializador de componentes
        iniciador();
        //se configura el webview para visualizar el pdf
        configuracionWebViewCatalogoPDF();
    }

    private void iniciador() {
        webview_pdf = (WebView) findViewById(R.id.webview_pdf);
        //recibe el tipo de producto
        URL_TIPO_PRODUCTO = getIntent().getStringExtra("url_pdf");
    }

    public void configuracionWebViewCatalogoPDF(){
        webview_pdf.setWebViewClient(new WebViewClient());
        webview_pdf.getSettings().setJavaScriptEnabled(true);
        webview_pdf.getSettings().setLoadWithOverviewMode(true);
        webview_pdf.getSettings().setAllowFileAccess(true);;
        webview_pdf.getSettings().setAllowContentAccess(true);
        webview_pdf.getSettings().setAllowFileAccessFromFileURLs(true);
        webview_pdf.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview_pdf.getSettings().setBuiltInZoomControls(true);
        //SE LE ASIGNA LA URL DEL PORTAL DEL CLIENTE
        webview_pdf.loadUrl( URLs.URL_VISUALIZADOR_PDF + URLs.URL_BASE_CATALOGO_PRODUCTOS + URL_TIPO_PRODUCTO);
    }

}
