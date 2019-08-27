package com.acerosocotlan.progresoacerosocotlan.Controlador.Catalogo;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.acerosocotlan.progresoacerosocotlan.R;

public class CatalogoPDF extends AppCompatActivity {

    String url_base = "https://www.acerosocotlan.mx/catalogos/";
    String url;

    WebView webview_pdf;
    //HABRA DOS IMAGENES QUE SERVIRAN DE BOTONES EN LA BARRA DE NAVEGACION
    DownloadManager dm;
    long downloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_pdf);
        webview_pdf = (WebView) findViewById(R.id.webview_pdf);
        url = getIntent().getStringExtra("url_pdf");
        //SE CONFIGURA EL WEBVIEW
        webview_pdf.setWebViewClient(new WebViewClient());
        webview_pdf.getSettings().setJavaScriptEnabled(true);
        //Le da un estilo diferente al visualizador del pdf
        //webview_pdf.getSettings().setUseWideViewPort(true);
        //Se adapta al contenedor
        webview_pdf.getSettings().setLoadWithOverviewMode(true);

        //webview_pdf.getSettings().setDomStorageEnabled(true);
        //webview_pdf.getSettings().setDatabaseEnabled(true);
        //webview_pdf.getSettings().setMinimumFontSize(1);
        webview_pdf.getSettings().setAllowFileAccess(true);;
        webview_pdf.getSettings().setAllowContentAccess(true);
        webview_pdf.getSettings().setAllowFileAccessFromFileURLs(true);
        webview_pdf.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview_pdf.getSettings().setBuiltInZoomControls(true);
        //SE LE ASIGNA LA URL DEL PORTAL DEL CLIENTE
        webview_pdf.loadUrl("https://acerosocotlan.mx/catalogos/pdf/viewer.php?url=" + url_base + url);
    }

    BroadcastReceiver receiver = new BroadcastReceiver () {
        public void onReceive(Context context, Intent intent) {

        }
    };

}
