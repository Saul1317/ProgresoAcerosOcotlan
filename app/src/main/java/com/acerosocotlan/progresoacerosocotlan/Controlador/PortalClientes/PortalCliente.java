package com.acerosocotlan.progresoacerosocotlan.Controlador.PortalClientes;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Controlador.MenuPrincipal.MenuPrincipal;
import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;


/*
* ESTA VENTANA SIRVE PARA MOSTRAR EL PORTAL DE CLIENTE POR MEDIO DE UN WEBVIEW
* ESTA CLASE NO CONTINENE LOGICA MAS QUE LA BARRA DE NAVEGACION, SI SE TIENE QUE HACER UN CAMBIO TIENE QUE SER DESDE EL PROYECTO ORIGINAL
*/

public class PortalCliente extends AppCompatActivity implements View.OnClickListener, DownloadListener{


    WebView webview_portal_cliente;
    //HABRA DOS IMAGENES QUE SERVIRAN DE BOTONES EN LA BARRA DE NAVEGACION
    ImageView webview_back, webview_sigout;
    DownloadManager dm;
    long downloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal_cliente);
        inicializadorViews();
    }

    private void inicializadorViews() {
        webview_portal_cliente = (WebView) findViewById(R.id.webview_portal_cliente);
        webview_back = (ImageView) findViewById(R.id.webview_back);
        webview_back.setOnClickListener(this);
        webview_sigout = (ImageView) findViewById(R.id.webview_sigout);
        webview_sigout.setOnClickListener(this);
        configurarPortalCliente();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //BOTON PARA CERRAR EL WEBVIEW Y VOLVER AL MENU PRINCIPAL
            case R.id.webview_back:
                //SE MANDA LLAMAR A LA VENTANA DEL MENU
                Intent intent = new Intent(this, MenuPrincipal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            //CERRAR SESION DEL PORTAL DE CLIENTES
            case R.id.webview_sigout:
                //AL WEBVIEW SE LE ASIGNA UNA NUEVA URL PARA CERRAR LA SESION
                webview_portal_cliente.loadUrl(URLs.URL_PORTAL_CLIENTES_LOGIN_PDF);
                webview_sigout.setVisibility(View.GONE);
                //SE ELIMINA ELIMINA CACHE
                webview_portal_cliente.clearCache(true);
                break;

            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /*
    * se valida que el usuario tenga habilitado los permisos de escritura
    * NOTA en el portal de clientes solo se piden los permisos para poder descargar archivos sin permisos
    * la aplicación no descargara nada.
    */
    public boolean validarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    * Con este método abres el dialogo para solicitar los permisos
    */
    public void solicitarPermisos() {
        ActivityCompat.requestPermissions(
                this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1222);
    }

    private void configurarPortalCliente() {
        //SE CONFIGURA EL WEBVIEW
        webview_portal_cliente.setWebViewClient(new WebViewClient());
        //ACEPTA JAVASCRIPT PARA QUE FUNCIONE LA LOGICA DEL PORTAL DE CLIENTES
        webview_portal_cliente.getSettings().setJavaScriptEnabled(true);
        webview_portal_cliente.setWebChromeClient(new WebChromeClient());
        webview_portal_cliente.getSettings().setDomStorageEnabled(true);
        webview_portal_cliente.getSettings().setAppCacheEnabled(true);
        webview_portal_cliente.getSettings().setLoadsImagesAutomatically(true);
        webview_portal_cliente.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //SE LE ASIGNA LA URL DEL PORTAL DEL CLIENTE
        webview_portal_cliente.loadUrl(URLs.URL_PORTAL_CLIENTES_PDF);
        webview_portal_cliente.setDownloadListener(this);
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


    /*
    * Metodo para configurar las descargas dentro del webview
    * Nota: No se utiliza dentro del web view
    */
    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        //Se valida que los permisos esten activdados
        if (validarPermisos()){
            //si se tienen los permisos entonces se configura el dowload manager para poder descargar
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            String cookies = CookieManager.getInstance().getCookie(url);
            request.setMimeType(mimetype);
            request.addRequestHeader("cookie", cookies);
            request.addRequestHeader("User-Agent", userAgent);
            request.setDescription("Downloading file...");
            request.setTitle(URLUtil.guessFileName(url, contentDisposition,
                    mimetype));
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                            url, contentDisposition, mimetype));
            dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            downloadId = dm.enqueue(request);
        }else {
            //si no se cuenta con los permisos entonces se solicitan los permisos
            solicitarPermisos();
        }

    }
}
