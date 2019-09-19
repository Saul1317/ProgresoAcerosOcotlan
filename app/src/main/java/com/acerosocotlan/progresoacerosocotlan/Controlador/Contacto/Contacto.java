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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.R;

public class Contacto extends AppCompatActivity implements View.OnClickListener {

    ImageView btn_correo, btn_whatsapp, btn_facebook, btn_phone, btn_sms, btn_twitter;
    String telefono = "3319185486";
    String email = "servicio.cliente@acerosocotlan.mx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
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
    }

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
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=520574524"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/acerosocotlan"));
        }
        this.startActivity(intent);
    }

    private void abrirAplicacionCorreo() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacto");
        startActivity(Intent.createChooser(emailIntent,  "Correo"));
    }

    private void abrirAplicacionFacebook() {
        Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show();
        String facebookId = "fb://page/381248521931192";
        String urlPage = "http://www.facebook.com/Acerosocotlanmx";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
        } catch (Exception e) {
            Log.e("Facebook App", "Aplicación no instalada.");
            //Abre url de pagina.
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
        }
    }

    private void abrirAplicacionLlamadas() {
        Toast.makeText(this, "Llamada", Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(telefono)) {
            String dial = "tel:" + telefono;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }else {
            Toast.makeText(Contacto.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirAplicacionMensajes() {
        Toast.makeText(this, "Mensaje", Toast.LENGTH_SHORT).show();
        if(!TextUtils.isEmpty(telefono)) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + telefono));
            startActivity(smsIntent);
        }
    }

    private void abrirAplicacionWhatsapp() {
        String smsNumber = "521" + telefono;
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
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
