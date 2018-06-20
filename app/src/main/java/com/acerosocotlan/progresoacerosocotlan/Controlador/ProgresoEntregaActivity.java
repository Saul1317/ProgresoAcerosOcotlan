package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.DirectorioTelefonos;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Factura_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.Modelo.ValidarDescarga;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;

public class ProgresoEntregaActivity extends AppCompatActivity {
    //VIEWS
    private Animation clickdedoAnimation,cardviewAnimacion,carroAnimacion,touchAnimation, flatbutton_animation;
    private LinearLayout layout_filtro, linear_layout_menu_progreso;
    private ImageView imagen_progress_bar,imagen_mano_click,imagen_touch_mano;
    private CardView cardview__menu_progreso;
    private TextView fecha_entregado,text_num_pedido, text_hora_entrega;
    private Button btn_mostrar_detalles_entrega, btn_nuevo_rastreo, btn_ver_ofertas,btn_descargar_factura;
    private FloatingActionButton fab;
    //SHARED PREFERENCE
    private SharedPreferences prs;
    //VARIABLES
    private DownloadManager downloadManager;
    private String codigo_entrega;
    private String status;
    private boolean menu_estatus = false;

    GestureDetector gestureDetector;
    boolean tapped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progreso_entrega);
        Inicializador();
        gestureDetector = new GestureDetector(getApplicationContext(),new GestureListener());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirMenuUsuario();
            }
        });
        imagen_progress_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        btn_mostrar_detalles_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BloquearBotones();
                Intent i = new Intent(ProgresoEntregaActivity.this, DetalleEntregaActivity2.class);
                startActivity(i);
            }
        });

        btn_nuevo_rastreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoConfirmacionSalida();
            }
        });

        btn_ver_ofertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BloquearBotones();
                Intent i = new Intent(ProgresoEntregaActivity.this, VerOferta.class);
                startActivity(i);
            }
        });

        btn_descargar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoConfirmacionEnviarFactura();
            }
        });
    }
    @Override protected void onResume() {
        super.onResume();
        Desbloquearbotones();
    }
    @Override public void onBackPressed() {
        if (menu_estatus==true) {
            carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_ocultar);
            cardview__menu_progreso.setAnimation(carroAnimacion);
            cardview__menu_progreso.setVisibility(View.INVISIBLE);
            flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
            fab.startAnimation(flatbutton_animation);
            menu_estatus=false;
        }else{
            DialogoMandarOfertas();
        }
    }

    private void DialogoMandarOfertas() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Antes de salir ¿No le gustaria ver nuestras ofertas?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent i = new Intent(ProgresoEntregaActivity.this, VerOferta.class);
                startActivity(i);
            }
        });
        alert.show();
    }

    private void DialogoConfirmacionSalida(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Aceros Ocotlán");
//        alert.setIcon(getResources().getDrawable(R.drawable.acerosocotlan));
        alert.setMessage("Esta a punto de volver a la pantalla de ingreso, ¿Desea continuar?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                BloquearBotones();
                Intent i = new Intent(ProgresoEntregaActivity.this, CodigoIngreso.class);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                remover_variables_sharedpreference();
            }
        });
        alert.show();
    }
    private void DialogoConfirmacionEnviarFactura(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Esta a punto de enviar la factura de su entrega a su correo, ¿Desea continuar?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                /*registerReceiver(new ValidarDescarga(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                Toast.makeText(ProgresoEntregaActivity.this, "Descargando factura", Toast.LENGTH_SHORT).show();
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri= Uri.parse("http://entregas.dyndns.org/entregas/resource/Facturas/doc.pdf");
                DownloadManager.Request request= new DownloadManager.Request(uri);
                request.setDestinationInExternalFilesDir(ProgresoEntregaActivity.this, Environment.DIRECTORY_DOWNLOADS,
                        MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"-factura.pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference= downloadManager.enqueue(request);*/
                ReenviarFactura();
            }
        });
        alert.show();
    }

    private void AbrirMenuUsuario() {
        if (menu_estatus==false) {
            cardview__menu_progreso.setVisibility(View.VISIBLE);
            cardviewAnimacion= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_mostrar);
            cardview__menu_progreso.setAnimation(cardviewAnimacion);

            flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation_close);
            fab.startAnimation(flatbutton_animation);
            menu_estatus=true;
        }else{
            carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_ocultar);
            cardview__menu_progreso.setAnimation(carroAnimacion);
            cardview__menu_progreso.setVisibility(View.INVISIBLE);
            flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
            fab.startAnimation(flatbutton_animation);
            menu_estatus=false;
        }
    }
    private void MostrarTutorial() {
        imagen_mano_click.setVisibility(View.VISIBLE);
        imagen_touch_mano.setVisibility(View.VISIBLE);
        clickdedoAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clickdedo);
        imagen_mano_click.setAnimation(clickdedoAnimation);
        imagen_mano_click.setVisibility(View.INVISIBLE);

        touchAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick);
        imagen_touch_mano.setAnimation(touchAnimation);
        imagen_touch_mano.setVisibility(View.INVISIBLE);
    }
    private void Inicializador(){
        imagen_progress_bar = (ImageView) findViewById(R.id.imagen_progress_bar_estatus);
        layout_filtro = (LinearLayout) findViewById(R.id.linear_layout_filtro);
        linear_layout_menu_progreso = (LinearLayout) findViewById(R.id.linear_layout_menu_progreso);
        fecha_entregado= (TextView) findViewById(R.id.fecha_llegada);

        text_hora_entrega= (TextView) findViewById(R.id.hora_llegada);
        text_num_pedido= (TextView) findViewById(R.id.pedido);

        btn_mostrar_detalles_entrega = (Button) findViewById(R.id.btn_mostrar_detalles_entrega);
        btn_nuevo_rastreo = (Button) findViewById(R.id.btn_nuevo_rastreo);
        btn_ver_ofertas = (Button) findViewById(R.id.btn_ver_ofertas);
        btn_descargar_factura = (Button) findViewById(R.id.btn_descargar_factura);

        cardview__menu_progreso = (CardView) findViewById(R.id.cardview__menu_progreso);
        imagen_mano_click = (ImageView) findViewById(R.id.imagen_mano_click);
        imagen_touch_mano =(ImageView) findViewById(R.id.imagen_touch_mano);

        cardview__menu_progreso.setVisibility(View.INVISIBLE);
        text_hora_entrega.setVisibility(View.INVISIBLE);
        text_num_pedido.setVisibility(View.INVISIBLE);
        fecha_entregado.setVisibility(View.INVISIBLE);
        layout_filtro.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        codigo_entrega = MetodosSharedPreference.ObtenerCodigoEntregaPref(prs);
        RecogerEstatusEntrega();
    }
    private void RecogerEstatusEntrega(){
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(ProgresoEntregaActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Obteniendo los datos");
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService().EstatusEntrega(
                "statusentrega_"+codigo_entrega+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                List<StatuEntrega> respuesta = response.body();
                status = respuesta.get(0).getEstatus();
                MetodosSharedPreference.GuardarEstatusEntrega(prs,status);
                ValidarEstatusActualEntrega(respuesta);
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                Intent intentErrorConexion = new Intent(ProgresoEntregaActivity.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void ReenviarFactura(){
        Call<Factura_retrofit> call = NetworkAdapter.getApiService().EnviarFactura("factura/gao",
                MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<Factura_retrofit>() {
            @Override
            public void onResponse(Call<Factura_retrofit> call, Response<Factura_retrofit> response) {
                if(response.isSuccessful()){
                    Factura_retrofit factura_retrofit= response.body();
                    if(factura_retrofit.getRuta().toString().equals("sincorreo")){
                        DialogoSolicitarLLamada();
                    }
                }
            }

            @Override
            public void onFailure(Call<Factura_retrofit> call, Throwable t) {
                Log.i("RESPUESTA", t.toString());
            }
        });
    }
    private void DialogoSolicitarLLamada() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Al parecer no contamos con ningun correo suyo, ¿Desea comunicarse con nosotros?");
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
               SolicitarTelefono();
            }
        });
        alert.show();
    }

    private void SolicitarTelefono(){
        Call<DirectorioTelefonos> call = NetworkAdapter.getApiService().SolicitarTelefono(
                "directorio/gao",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<DirectorioTelefonos>() {
            @Override
            public void onResponse(Call<DirectorioTelefonos> call, Response<DirectorioTelefonos> response) {
                if(response.isSuccessful()) {
                    DirectorioTelefonos directorioTelefonos = response.body();
                    RealizarLLamada(directorioTelefonos.getTelefono());
                    Log.i("DIRECTORIO DE TELEFONOS", directorioTelefonos.getTelefono());
                }
            }

            @Override
            public void onFailure(Call<DirectorioTelefonos> call, Throwable t) {
                Log.i("DIRECTORIO DE TELEFONOS", "EROR");
            }
        });
    }
    private void RealizarLLamada(String phoneNumber) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ProgresoEntregaActivity.this, new String[]{CALL_PHONE},100);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(intent);
            }
        }else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        if(status.equals("Programado")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            btn_descargar_factura.setEnabled(false);
            MostrarTutorial();
        }
        else if(status.equals("En Ruta")){
            imagen_progress_bar.setImageResource(R.drawable.proceso1);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            MostrarTutorial();
        }
        else if(status.equals("Proximo")){
            imagen_progress_bar.setImageResource(R.drawable.proceso2);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("En sitio")){
            imagen_progress_bar.setImageResource(R.drawable.proceso3);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("Descargando")){
            imagen_progress_bar.setImageResource(R.drawable.proceso4);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("Entregado")){
            if (respuesta.get(0).getHizoencuesta().toString().equals("0")){
                Intent i = new Intent(ProgresoEntregaActivity.this, EncuestaActivity.class);
                startActivity(i);
            }else {
                imagen_progress_bar.setImageResource(R.drawable.proceso5);
                fecha_entregado.setVisibility(View.VISIBLE);
                text_hora_entrega.setVisibility(View.VISIBLE);
                text_num_pedido.setVisibility(View.VISIBLE);
                layout_filtro.setVisibility(View.VISIBLE);

                text_num_pedido.setText("El pedido "+respuesta.get(0).getPedido().toString()+" fue entregado");
                fecha_entregado.setText(respuesta.get(0).getfSalidaEntrega().toString()+", ");
                text_hora_entrega.setText(respuesta.get(0).gethSalidaEntrega().toString());
            }
        }
        else if(status.equals("Cancelado")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
        }
    }
    private void remover_variables_sharedpreference(){
        prs.edit().clear().apply();
    }
    private void Desbloquearbotones(){
        btn_mostrar_detalles_entrega.setEnabled(true);
        btn_nuevo_rastreo.setEnabled(true);
        btn_ver_ofertas.setEnabled(true);
    }
    private void BloquearBotones(){
        btn_mostrar_detalles_entrega.setEnabled(false);
        btn_nuevo_rastreo.setEnabled(false);
        btn_ver_ofertas.setEnabled(false);
    }
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            RecogerEstatusEntrega();
            return true;
        }
    }

}
