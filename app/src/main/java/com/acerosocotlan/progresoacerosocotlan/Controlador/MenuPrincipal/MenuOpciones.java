package com.acerosocotlan.progresoacerosocotlan.Controlador.MenuPrincipal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.acerosocotlan.progresoacerosocotlan.Controlador.PortalClientes.PortalCliente;
import com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido.CodigoIngreso;
import com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido.ProgresoEntregaActivity;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.R;

/*
*
* ESTA CLASE SE ENCARGA DE MOSTRAR TODAS LAS OPCIONES QUE TIENE EL CLIENTE
* - REASTREAR PEDIDO
* - PORTAL DE CLIENTE
*
* */

public class MenuOpciones extends AppCompatActivity implements View.OnTouchListener {

    //DURACION DE LA ANIMACION DE LOS BOTONES AL MOMENTO DE HACER CLICK
    private static final int DURATION_ANIMATION_CARD = 200;
    CardView cardview_rastreo_pedido, cardview_portal_cliente;
    private SharedPreferences prs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        //iniciamos todas las vistas y variables
        inicializadorViews();
    }

    /*
    * REFERENCIA DE TODOS LOS VIEWS E INICIALIZACION DE VARIABLES
    */
    private void inicializadorViews() {
        //REFERENCIA DEL SHARED PREFERENCES
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);

        cardview_rastreo_pedido = (CardView) findViewById(R.id.cardview_rastreo_pedido);
        /*
        * EN ESTE CASO SE UTILIZO UN ESCUCHADOR DE TIPO TOUCH EN LUGAR DE CLICK PARA CAPTURAR EL MOMENTO EN EL QUE SE TOCA EL BOTON
        * NO SOLO PARA EL CLICK
        */
        cardview_rastreo_pedido.setOnTouchListener(this);
        cardview_portal_cliente = (CardView) findViewById(R.id.cardview_portal_cliente);
        cardview_portal_cliente.setOnTouchListener(this);
    }


    /*
    * EL METODO ON TOUCH SE UTILIZA PARA CAPTURAR DIFERENTES EVENTOS AL MOMENTO DE INTERACTUAR CON UN BOTON
    * Y ASI PODER USAR DIFERENTES ANIMACIONES
    */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.cardview_rastreo_pedido:
                getActionCardview(event.getAction(), cardview_rastreo_pedido, 1);
                break;

            case R.id.cardview_portal_cliente:
                getActionCardview(event.getAction(), cardview_portal_cliente,2);
                break;

            default:
                break;
        }
        return true;
    }

    /*
    * SACAMOS TRES TIPOS DE EVENTOS PARA EL TOUCH LISTENER
    * 1.- ACTION DOWN: PRESIONAR EL BOTON
    * 2.- ACTION UP: DEJAR DE PRESIONAR EL BOTON
    * 3.- ACTION CANCEL: CANCELAR EL TOUCH (CUANDO NO SE COMPLETA EL ACTION UP)
    *
    * NOTA SE SACAN ESTOS EVENTOS PARA REALIZAR LAS DIFERENTES ANIMACIONES AL MOMENTO DE INTERACTUAR CON EL BOTON
    */
    private void getActionCardview(int action, CardView cardView, int opcion) {
        switch (action) {
            //PRESIONAS EL BOTON Y EJECUTA UNA ANIMACION
            case MotionEvent.ACTION_DOWN:
                //EL BOTON SE HACE PEQUEÑO SIMULANDO QUE LO ESTAS PRESIONANDO
                animationCardPushDown(cardView);
                break;

                //CUANDO SE DEJA DE PRESIONAR EL BOTON EJECUTA UNA ANIMACION CONTRARIA A LA DE ACTION_DOWN
            case MotionEvent.ACTION_UP:
                //EL BOTON VUELVE A SU TAMAÑO NORMAL PERO DESPUES DE LA ANIMACION EJECUTA UNA ACCION
                animationCardPushUp(cardView);
                //SE VALIDA QUE BOTON SE PRESIONO Y DESPUES SE EJECUTA UN METODO PARA ABRIR LA SIGUIENTE VENTANA
                if (opcion == 1){
                    // EN ESTE CASO LA OPCION 1 EJECUTA ABRIRRASTREOPEDIDO() EL CUAL ABRE LA VENTA DEL CODIGO DE RASTREO
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            abrirRastreoPedido();
                        }
                    },150);
                }else{
                    //SI ES OTRA OPCION DIFERENTE A 1 ENTONCES EJECUTA EL PORTAL DE CLIENTES
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() { abrirPortalCliente();
                        }
                    },150);
                }
                //NOTA: EN CASO DE QUE SE REQUIERA AGREGAR MAS OPCIONES EN EL MENU RECOMIENDO SUSTITUIR LOS IF POR SWITCH PARA TENER MAYOR CONTROL Y ORDEN DEL CODIGO
                break;

                //EN CASO DE QUE NO SE COMPLETE EL ACTION UP ENTONCES EJECUTA LA MISMA ANIMACION PARA RECUPERAR EL TAMAÑO DEL BOTON PERO SOLO SE EJECUTA LA ACCION
            case MotionEvent.ACTION_CANCEL:
                //ANIMACION PARA HACER QUE EL BOTON SE HAGA GRANDE DESPUES DE DEJAR DE PRESIONARLO
                animationCardPushUp(cardView);
                break;
        }
    }


    /*
    * ANIMACION DEL CARDVIEW AL MOMENTO DE PRESIONAR
    * LA ANIMACION HACE QUE EL CARDVIEW SE HAGA MAS PEQUEÑO
    * PARA SIMULAR QUE SE CONTRAE AL MOMENTO DE PRESIONARLO
    */
    public void animationCardPushDown(CardView cardView){
        //SE REDUCE EL TAMAÑO AL 94% DEL TAMAÑO ORIGINAL
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(cardView,
                "scaleX", 0.94f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(cardView,
                "scaleY", 0.94f);
        scaleDownX.setDuration(DURATION_ANIMATION_CARD);
        scaleDownY.setDuration(DURATION_ANIMATION_CARD);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();
    }

    /*
     * ANIMACION DEL CARDVIEW AL MOMENTO DE DEJAR PRESIONADO EL BOTON
     * EL CARDVIEW VUELVE A SU TAMAÑO ORIGINAR
     */
    public void animationCardPushUp(CardView cardView){
        //SE INCREMENTA AL TAMAÑO ORIGINAL
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(
                cardView, "scaleX", 1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(
                cardView, "scaleY", 1f);
        scaleDownX.setDuration(DURATION_ANIMATION_CARD);
        scaleDownY.setDuration(DURATION_ANIMATION_CARD);

        AnimatorSet scaleDown3 = new AnimatorSet();
        scaleDown3.play(scaleDownX).with(scaleDownY);

        scaleDown3.start();
    }

    /*
     * SE HACE UNA VALIDACION PARA SABER SI EL USUARIO YA HABIA INGRESADO UN CODIGO DE RASTREO
     * EN CASO DE QUE SI ENTONCES SE BRINCA LA VENTANA DONDE SE SOLICITA EL CODIGO DE RASTREO
     */
    private void abrirRastreoPedido() {
        Intent intentCodigoIngreso = new Intent(MenuOpciones.this, CodigoIngreso.class);
        Intent intentProcesoEntrega = new Intent(MenuOpciones.this, ProgresoEntregaActivity.class);

        if (!TextUtils.isEmpty(MetodosSharedPreference.ObtenerCodigoEntregaPref(prs))){
            startActivity(intentProcesoEntrega);
        }else{
            startActivity(intentCodigoIngreso);
            //overridePendingTransition(R.anim.activity_transition_in,R.anim.activity_transition_in);
        }
    }

    /*
    * ABRE EL PORTAL DEL CLIENTE
    */
    private void abrirPortalCliente() {
        /*Uri uri = Uri.parse("https://portalcliente.acerosocotlan.mx/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
        Intent intentCodigoIngreso = new Intent(MenuOpciones.this, PortalCliente.class);
        startActivity(intentCodigoIngreso);
    }
}
