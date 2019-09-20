package com.acerosocotlan.progresoacerosocotlan.Controlador.RastreoPedido;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Prueba_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
* EL OBJETIVO DE ESTA CLASE ES QUE EL USUARIO INGRESE SU CODIGO DE RASTREO Y QUE SE HAGA UNA VALIDACION EN LA QUE
* SI EL CODIGO DE RASTRE EXISTE ENTONCES PODRA INGRESAR A LA SIGUIENTE VENTANA DONDE PODRA DAR SEGUIMIENTO A SU PEDIDO
* DE LO CONTRARIO NO PODRA INGRESAR
*
* NOTA: EL PROGRESS BAR ESTA OBSOLETO POR LO TANTO PUEDEN SER ELIMINADOS POR COMPLETO EN ALGUNA VERSION FUTURA
*/
public class CodigoIngreso extends AppCompatActivity {

    private Button boton_enviar_folio;
    private TextInputEditText codigo_rastreo;
    private Animation carro_animacion,humo_animacion, carro_salida_animacion;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;
    private String txtprueba1;
    private String txtprueba2;
    private String txtprueba3;
    private ImageView camion_click, camion_humo;
    //VARIABLE PARA ACTIVAR LA VENTANA SECRETA :V
    private int firma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        * ESTAS LINEAS DE CODIGO SIRVEN PARA VALIDAR LA VERSION DEL SISTEMA OPERATIVO QUE TIENE EL DISPOSITIVO
        * Y ASI PODER HACER TRANSPARENTE LA BARRA DE NOTIFICACION (SI NO SABES CUAL, ES LA BARRA DE HASTA ARRIBA DONDE APARECE LA HORA)
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            //w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_codigo_ingreso);

        //INICIALIZAMOS TODOS LOS COMPONENTES UTILIZADOS EN ESTA VENTANA
        Inicializador();


        /*
        * ESTOS METODOS ASIGNAN UN ESCUCHADOR AL BOTON EN EL DETECTARA CUANDO SE HACE CLICK EN LOS BOTONES
        */

        //BOTON PARA VALIDAR EL CODIGO DE RASTRE
        boton_enviar_folio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarUsuario();
            }
        });

        //AL CAMION TAMBIEN SE LE ASIGNA UN ESCUCHADOR
        camion_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarroAnimacion();
            }
        });
    }


    /*
    * COMIENZO FIRMA DE SISTEMAS
    */
    private void CarroAnimacion(){
        //SE VALIDAN LOS CLICKS DEL CAMION DEPENDIENDO DEL NUMERO DE CLICKS EL CAMION HARA UNA ANIMACION
        if (firma>=9) {
            firma=0;
            camion_click.startAnimation(carro_salida_animacion);
            carro_salida_animacion.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    AbrirFirma();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }else if(firma>=6 && firma <=9 ){
            camion_humo.setVisibility(View.VISIBLE);
            camion_click.startAnimation(carro_animacion);
            camion_humo.startAnimation(humo_animacion);
            camion_humo.setVisibility(View.INVISIBLE);
            firma = firma + 1;
        }else if (firma<=5){
            camion_click.startAnimation(carro_animacion);
            firma = firma + 1;
        }
    }
    private void AbrirFirma() {
        Intent i = new Intent(CodigoIngreso.this, FirmaSistemasActivity.class);
        startActivity(i);
    }


    /*
     * FIN FIRMA DE SISTEMAS
     */


    /*
    * AQUI SE CREA UN DIALOGO PARA UTILIZARLO EN CASO DE QUE NO HAYA CONEXION
    */
    private void MostrarDialogCustomNoConfiguracion(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        //AQUI SE HACE REFERENCIA A UN XML (DEJAR PRESIONADO EL BOTON COMMAND O CTRL Y PRESIONAR EN EL DIALOG_NO_CONEXION PARA VER EL XML DEL DIALOGO)
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        //alert.setCancelable(false);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        //DENTRO DEL DIALOGO SE HACE REFERENCIA A UN BOTON Y SE LE ASIGNA EL METODO PARA HACER CLICK
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SE VALIDA QUE EL CODIGO DE RASTREO
                ValidarUsuario();
                //DISMINUYE EL ALERT
                alertDialog.dismiss();
            }
        });
    }

    /*
     * AQUI SE CREA UN DIALOGO PARA UTILIZARLO EN CASO DE QUE NO HAYA CONEXION
     */
    private void MostrarDialogCustomNoConexionServidor(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_no_conexion, null);
        //alert.setCancelable(false);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_internet);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptar();
                alertDialog.dismiss();
            }
        });
    }

    /*
    * METODO PARA VALIDAR QUE EL TEXTO DEL TEXT INPUT NO VAYA VACIO
    */
    private void ValidarUsuario(){
        //SE OBTIENE EL TEXTO DEL TEXT INPUT (CODIGO_RASTREO) Y SE ALMACENA EN UNA VARIABLE
        txtprueba3 = codigo_rastreo.getText().toString();
        //SE VALIDA QUE EL TEXTO NO ESTE VACIO DE LO CONTRARIO
        if (!txtprueba3.isEmpty()) {
            prueba();
        } else {
            Toast.makeText(CodigoIngreso.this, "Ingrese el codigo de su entrega", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * AQUI SE HACE UNA PETICION A http://acerosocotlan.mx/gao/ PARA QUE NOS REGRESE LA URL DEL WEN SERVICE
    *
    */
    private void prueba(){

        //SE LE ASIGNAN LAS PROPIEDADES AL PROGRESS BAR
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Validando sus datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //SE MUESTRA EL PROGRESS
        progressDoalog.show();

        /*
        * PETICIÓN POST DE RETROFIT2 SE MANDAN DOS TEXTOS AL ARCHIVO EGAO.PHP LA CUAL HACE UNA VALIDACION Y SI ES CORRECTA ENTONCES
        * RETORNARA UNA URL LA CUAL SERA LA DEL WEB SERVICES
        */
        Call<Prueba_retrofit> call = NetworkAdapter.getApiServiceAlternativo().Solicitarprueba("egao.php",txtprueba1,txtprueba2);
        call.enqueue(new Callback<Prueba_retrofit>() {
            @Override
            public void onResponse(Call<Prueba_retrofit> call, Response<Prueba_retrofit> response) {
                //EN CASO DE TENER UNA RESPUESTA ENTONCES SE QUITA EL PROGRESS BAR
                progressDoalog.dismiss();
                //SE VALIDA SI LA RESPUESTA ES CORRECTA
                if(response.isSuccessful()) {
                    //SE ASIGNA LA RESPUESTA A UNA VARIABLE DE TIPO PRUEBA (CTRL O COMMAND + CLICK EN PRUEBA_RETROFIT PARA VER EL OBJETO)
                    Prueba_retrofit  prueba_retrofit= response.body();
                    //SE VALIDA SI LA VARIABLE NO ESTA VACIA
                    if (!prueba_retrofit.getResp().isEmpty()) {
                        //EN CASO DE QUE SI HAYA UNA RESPUESTA ENTONCES SE ALMACENA EN LAS SHARED PREFERENCE
                        MetodosSharedPreference.GuardarPruebaEntrega(prs, prueba_retrofit.getResp());
                        Log.i("URL", MetodosSharedPreference.ObtenerPruebaEntregaPref(prs));
                        //SE VALIDA EL CODIGO DE RASTREO CON LA NUEVA URL
                        ConsultarCodigo();
                    }else{
                        //EN CASO DE QUE NO HAYA UNA RESPUESTA ENTONCES SE MANDA A LLAMAR EL ALERT DE ERROR
                        MostrarDialogCustomNoConfiguracion();
                    }
                }else{
                    //EN CASO DE QUE LA RESPUESTA NO SEA CORRECTA ENTONCES SE MANDA A LLAMAR EL ALERT DE ERROR
                    MostrarDialogCustomNoConfiguracion();
                }
            }

            @Override
            public void onFailure(Call<Prueba_retrofit> call, Throwable t) {
                //MostrarDialogCustomNoConfiguracion();
                Log.e("ERROR SERVER","Primer link fallo " + t.getMessage());
                //EN CASO DE QUE FALLE LA CONEXION AL SERVIDOR DE ACEROS OCOTLAN SE HARA UNA NUEVA PETICION A OTRO SERVIDOR ALTERNO
                prueba2();
                progressDoalog.dismiss();
            }
        });
    }

    /*
    * METODO QUE HACE LO MISMO QUE EL METODO PRUEBA SOLO QUE EESTE METODO UTILIZARA OTRO DOMINIO
    */
    private void prueba2(){
        //SE LE ASIGNAN LAS PROPIEDADES AL PROGRESS BAR
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Validando sus datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        /*
         * PETICIÓN POST DE RETROFIT2 SE MANDAN DOS TEXTOS AL ARCHIVO EGAO.PHP LA CUAL HACE UNA VALIDACION Y SI ES CORRECTA ENTONCES
         * RETORNARA UNA URL LA CUAL SERA LA DEL WEB SERVICES
         */
        Call<Prueba_retrofit> call = NetworkAdapter.getApiServiceAlternativo2().Solicitarprueba("egao.php",txtprueba1,txtprueba2);
        call.enqueue(new Callback<Prueba_retrofit>() {
            @Override
            public void onResponse(Call<Prueba_retrofit> call, Response<Prueba_retrofit> response) {
                progressDoalog.dismiss();
                if(response.isSuccessful()) {
                    Prueba_retrofit  prueba_retrofit= response.body();
                    if (!prueba_retrofit.getResp().isEmpty()) {
                        MetodosSharedPreference.GuardarPruebaEntrega(prs, prueba_retrofit.getResp());
                        Log.i("URL", MetodosSharedPreference.ObtenerPruebaEntregaPref(prs));
                        ConsultarCodigo();
                    }else{
                        MostrarDialogCustomNoConexionServidor();
                    }
                }else{
                        MostrarDialogCustomNoConexionServidor();
                }
            }

            @Override
            public void onFailure(Call<Prueba_retrofit> call, Throwable t) {
                progressDoalog.dismiss();
                Log.i("ERROR SERVER","Segundo link fallo");
                MostrarDialogCustomNoConexionServidor();
            }
        });
    }

    /*
    *
    * YA QUE SE HAYA OBTENIDO LA URL CORRECTA DEL WEB SERVICE ENTONCES SE HACE USO DE ESTE METODO
    * PARA PODER HACER UNA PETICION QUE VALIDE SI EXISTE EL CODIGO DE RASTREO
    *
    */
    private void ConsultarCodigo(){
        //SE MANDA EL CODIGO DE RASTREO DENTRO DE LA URL
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_" + txtprueba3 + "/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                //SE OCULTA EL PROGRESS DIALOG
                progressDoalog.dismiss();
                //SE VALIDA LA RESPUESTA
                if (response.isSuccessful()) {
                    //LA RESPUESTA SE ALMACENA EN UNA LISTA DE OBJETOS TIPO STATUSENTREGA
                    List<StatuEntrega> respuesta = response.body();
                    //SE VALIDA SI LA RESPUESTA ESTA VACIA
                    if (respuesta.isEmpty()) {
                        Toast.makeText(CodigoIngreso.this, "No existe el codigo", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("CONSULTA", "Correcto");
                        //SI NO ESTA VACIA ENTONCES SE ALMACENARA EL CODIGO EN LAS SHARED PREFERENCES
                        MetodosSharedPreference.GuardarCodigoEntrega(prs, txtprueba3);
                        //SE ABRE LA VENTANA DE PROGRESO ENTREGA ACTIVITY EN LA QUE SE PODRA VER EL ESTATUS DE TU ENTREGA
                        Intent i = new Intent(CodigoIngreso.this, ProgresoEntregaActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                }else{
                    //EN CASO DE QUE LA RESPUESTA TENGA ERRORES ENTONCES SE MANDA LLAMAR EL ALERT DIALOG
                    MostrarDialogCustomNoConfiguracion();
                }
            }
            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                //EN CASO DE QUE LA RESPUESTA TENGA ERRORES ENTONCES SE MANDA LLAMAR EL ALERT DIALOG
                progressDoalog.dismiss();
                MostrarDialogCustomNoConfiguracion();
            }
        });
    }

    /*
    *
    * SE ENCRIPTAN DOS VARIABLES DE TIPO STRING LAS CUALES SE MANDAN EN UNA PETICION POST
    * EN EL METODO DE PRUEBA Y PRUEBA2
    *
    * */
    private void encryptar(){
        String text1 = "codigo";
        String text2 = "binarioxd";
        byte[] encrpt1;
        byte[] encrpt2;
        
        try {
            encrpt1 = text1.getBytes("UTF-8");
            encrpt2 = text2.getBytes("UTF-8");
            txtprueba1 = Base64.encodeToString(encrpt1, Base64.DEFAULT);
            txtprueba2 = Base64.encodeToString(encrpt2, Base64.DEFAULT);
            Log.i("USER", txtprueba1);
            Log.i("PASS", txtprueba2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * SE INICIALIZAN LOS COMPONENTES UTILIZADOS EN ESTA VISTA
     *
     * */
    private void Inicializador(){
        codigo_rastreo = (TextInputEditText) findViewById(R.id.edttxt_codigo_rastreo_entrega);
        boton_enviar_folio = (Button) findViewById(R.id.btn_enviar_formulario);
        progressDoalog = new ProgressDialog(CodigoIngreso.this);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        camion_click = (ImageView) findViewById(R.id.camion_click);
        camion_humo = (ImageView) findViewById(R.id.camion_humo);
        carro_animacion = AnimationUtils.loadAnimation(this,R.anim.camion_animacion);
        humo_animacion  = AnimationUtils.loadAnimation(this,R.anim.humo_animacion);
        carro_salida_animacion = AnimationUtils.loadAnimation(this,R.anim.camion_salida_animacion);
        encryptar();
    }
}
