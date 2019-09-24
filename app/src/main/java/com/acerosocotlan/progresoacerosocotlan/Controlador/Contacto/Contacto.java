package com.acerosocotlan.progresoacerosocotlan.Controlador.Contacto;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.URLs;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

/*
* Clase donde el usuario tiene varias formas de ponerse en contacto con la empresa.
*/
public class Contacto extends AppCompatActivity implements View.OnClickListener {

    //se utilizan imagenes para los botones
    ImageView btn_correo, btn_whatsapp, btn_facebook, btn_phone, btn_sms, btn_twitter, img_contacto_background;
    //teléfono y correo de ventas gao (Celular de Mariana)
    String telefono = "3319185486";
    String email = "servicio.cliente@acerosocotlan.mx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        //inicializamos los componentes de la vista
        iniciador();
    }

    public void iniciador(){
        img_contacto_background = (ImageView) findViewById(R.id.img_contacto_background);
        btn_correo = (ImageView) findViewById(R.id.btn_correo);
        btn_correo.setOnClickListener(this);
        btn_whatsapp = (ImageView) findViewById(R.id.btn_whatsapp);
        btn_whatsapp.setOnClickListener(this);
        btn_facebook = (ImageView) findViewById(R.id.btn_facebook);
        btn_facebook.setOnClickListener(this);
        btn_phone = (ImageView) findViewById(R.id.btn_phone);
        btn_phone.setOnClickListener(this);
        btn_sms = (ImageView) findViewById(R.id.btn_sms);
        btn_sms.setOnClickListener(this);
        btn_twitter = (ImageView) findViewById(R.id.btn_twitter);
        btn_twitter.setOnClickListener(this);
        //se descarga la imagen de fondo
        Picasso.with(this).load(URLs.URL_IMAGEN_FONDO_APP).error(R.drawable.ao_portada_fondo1).placeholder(R.drawable.ao_portada_fondo1).into(img_contacto_background);
    }

    //Eventos para detectar el click de los botones
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_correo:
                abrirAplicacionCorreo();
                break;

            case R.id.btn_whatsapp:
                abrirAplicacionWhatsapp();
                break;

            case R.id.btn_facebook:
                abrirAplicacionFacebook();
                break;

            case R.id.btn_phone:
                abrirAplicacionLlamadas();
                break;

            case R.id.btn_sms:
                abrirAplicacionMensajes();
                break;

            case R.id.btn_twitter:
                abrirAplicacionTwitter();
                break;
            default:
                break;
        }
    }

    private void abrirAplicacionTwitter() {
        Toast.makeText(this, "Twitter", Toast.LENGTH_SHORT).show();
        Intent intent = null;
        try {
            // se abre el twitter si es posible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=520574524"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // si no esta instalado Twitter entonces se abre desde el navegador
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URLs.URL_TWITTER));
        }
        this.startActivity(intent);
    }

    private void abrirAplicacionCorreo() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacto");
        startActivity(Intent.createChooser(emailIntent,  "Correo"));
    }

    private void abrirAplicacionFacebook() {
        //identificador que se asigna a tu perfil de facebook (lo pueden consultar en https://findmyfbid.com/)
        String facebookId = "fb://page/381248521931192";
        try {
            //Inicializa la aplicación de facebook
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
        } catch (Exception e) {
            //Log.e("Facebook App", "Aplicación no instalada.");
            //si el usuario no cuenta con la aplicación entonces abre el navegador
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URLs.URL_FACEBOOK)));
        }
    }

    private void abrirAplicacionLlamadas() {
        //Toast.makeText(this, "Llamada", Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(telefono)) {
            //abre la aplicación de teléfono
            String dial = "tel:" + telefono;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }else {
            Toast.makeText(Contacto.this, "Ingresa un teléfono", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirAplicacionMensajes() {
        Toast.makeText(this, "Mensaje", Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(telefono)) {
            //se abre la apliación de mensajes
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + telefono));
            startActivity(smsIntent);
        }
    }

    private void abrirAplicacionWhatsapp() {
        String whatsapp_telefono = "521" + telefono;
        //validación de whatsapp para ver si esta instalado
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {
            //si esta instalado entonces se abre la aplicación
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(whatsapp_telefono) + "@s.whatsapp.net");//phone number without "+" prefix
            startActivity(sendIntent);
        } else {
            //Avisa al usuario que no esta instalado y lo lleva a la descarga
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp no esta instalado",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        //Se verifica si el paquete com.whatsapp este instalado
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
