package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecylerViewHistoricoEnvio;
import com.acerosocotlan.progresoacerosocotlan.Modelo.AcuseRecibo_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DirectorioTelefonos;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Factura_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Historial_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;

public class ProgresoEntregaActivity extends AppCompatActivity {
    private static final long VIBRACION_TIEMPO = 50;
    //VIEWS
    private Animation clickdedoAnimation,cardviewAnimacion,carroAnimacion,touchAnimation, flatbutton_animation,estrellaAnimation;
    private LinearLayout layout_filtro;
    private ImageView imagen_progress_bar,imagen_touch_mano, imagen_mano_click2, imagen_touch_mano2;
    private CardView cardview__menu_progreso,cardview__historico_proceso;
    private TextView fecha_entregado,text_num_pedido, text_hora_entrega,nombre_chofer_entrega,placas_camion;
    private Button btn_mostrar_detalles_entrega, btn_nuevo_rastreo, btn_ver_ofertas,btn_descargar_factura,boton_salir;
    private FloatingActionButton fab, fab_mostrar_historico;
    private RecyclerView recyclerview_historico_envio_entrega;
    private ImageView foto_camion_entrega;

    //SHARED PREFERENCE
    private SharedPreferences prs;
    //VARIABLES
    private DownloadManager downloadManager;
    private String codigo_entrega;
    private String status;
    private boolean menu_estatus = false;
    private boolean historico_estatus=false;
    private boolean ofertas = false;
    private ProgressDialog progressDoalog;
    private GestureDetector gestureDetector;
    private Vibrator vibrador;

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
        fab_mostrar_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirHistoricoProcesoEntrega();
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
                NuevaVentanaAcuseRecibo();
                //ReenviarAcuseRecibo();
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
                btn_descargar_factura.setEnabled(false);
                MostrarDialogCustomFactura();
            }
        });
        boton_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ofertas==true) {
                    MostrarDialogCustomOfertas();
                }else{
                    remover_variables_sharedpreference();
                    finish();
                }
            }
        });
    }
    @Override protected void onResume() {
        super.onResume();
        Desbloquearbotones();
    }
    @Override public void onBackPressed() {
            if (menu_estatus == true) {
                OcultarMenu();
            }else if(historico_estatus == true){
                OcultarHistorico();
            } else {
               finish();
            }
    }
    private void MostrarDialogCustomOfertas(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_ver_ofertas, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        ImageView estrella = (ImageView) dialoglayout.findViewById(R.id.dialog_img_estrella_oferta);
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_si_ver_ofertas);
        Button botonCancelar = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_ver_ofertas);
        estrellaAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.estrella_ofertas_animation);
        estrella.setAnimation(estrellaAnimation);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                Intent i = new Intent(ProgresoEntregaActivity.this, VerOferta.class);
                startActivity(i);
                alertDialog.dismiss();
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                alertDialog.dismiss();
                remover_variables_sharedpreference();
                finish();
            }
        });
    }
    private void MostrarDialogCustomFactura(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_acerosocotlan, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_si_ver_ofertas);
        Button botonCancelar = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_ver_ofertas);
        TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_titulo);
        TextView txtDescripcion  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_descripcion);
        txtTitutlo.setText(getResources().getString(R.string.dialog_txt_titulo_factura));
        txtDescripcion.setText(getResources().getString(R.string.dialog_txt_descripcion_factura));
        botonEntendido.setText(getResources().getString(R.string.dialog_btn_entendido_factura));
        botonCancelar.setText(getResources().getString(R.string.dialog_btn_cancelar_factura));

        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                ReenviarFactura();
                btn_descargar_factura.setEnabled(true);
                alertDialog.dismiss();
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                btn_descargar_factura.setEnabled(true);
                alertDialog.dismiss();
            }
        });
    }
    private void AbrirMenuUsuario() {
        if (menu_estatus==false) {
            if(historico_estatus==true){
                OcultarHistorico();
            }
            MostrarMenu();
        }else{
            OcultarMenu();
        }
    }
    private void OcultarMenu(){
        carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_ocultar);
        cardview__menu_progreso.setAnimation(carroAnimacion);
        cardview__menu_progreso.setVisibility(View.INVISIBLE);
        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
        fab.startAnimation(flatbutton_animation);
        menu_estatus=false;
    }
    private void MostrarMenu(){
        cardview__menu_progreso.setVisibility(View.VISIBLE);
        cardviewAnimacion= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_mostrar);
        cardview__menu_progreso.setAnimation(cardviewAnimacion);

        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation_close);
        fab.startAnimation(flatbutton_animation);
        menu_estatus=true;
    }
    private void AbrirHistoricoProcesoEntrega() {
        if (historico_estatus==false) {
            if(menu_estatus==true){
                OcultarMenu();
            }
            MostrarHistorico();
        }else{
            OcultarHistorico();
        }
    }
    private void OcultarHistorico(){
        carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.ocultarhistorico_animation);
        cardview__historico_proceso.setAnimation(carroAnimacion);
        cardview__historico_proceso.setVisibility(View.INVISIBLE);

        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation_close);
        fab_mostrar_historico.startAnimation(flatbutton_animation);
        historico_estatus=false;
    }
    private void MostrarHistorico(){
        cardviewAnimacion= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.mostrarhistorico_animation);
        cardview__historico_proceso.setVisibility(View.VISIBLE);
        cardview__historico_proceso.setAnimation(cardviewAnimacion);

        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
        fab_mostrar_historico.startAnimation(flatbutton_animation);
        historico_estatus=true;
    }
    private void MostrarTutorial() {
        imagen_mano_click2.setVisibility(View.VISIBLE);
        imagen_touch_mano2.setVisibility(View.VISIBLE);
        clickdedoAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clickdedo);
        imagen_mano_click2.setAnimation(clickdedoAnimation);
        imagen_mano_click2.setVisibility(View.INVISIBLE);

        touchAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick);
        touchAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imagen_touch_mano.setVisibility(View.VISIBLE);
                touchAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.touchclick);
                imagen_touch_mano.setAnimation(touchAnimation);
                clickdedoAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clickdedoalternativo_animation);
                imagen_mano_click2.setAnimation(clickdedoAnimation);
                imagen_touch_mano.setVisibility(View.INVISIBLE);
                imagen_mano_click2.setVisibility(View.INVISIBLE);
                imagen_touch_mano2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imagen_touch_mano2.setAnimation(touchAnimation);
    }
    private void RecogerEstatusEntrega(){
        progressDoalog.show();
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+codigo_entrega+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                if(response.isSuccessful()) {
                    List<StatuEntrega> respuesta = response.body();
                    if (respuesta.isEmpty()) {
                        Toast.makeText(ProgresoEntregaActivity.this, "Código invalido", Toast.LENGTH_SHORT).show();
                        remover_variables_sharedpreference();
                        Intent i = new Intent(ProgresoEntregaActivity.this, CodigoIngreso.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    } else {
                        status = respuesta.get(0).getEstatus();
                        MetodosSharedPreference.GuardarEstatusEntrega(prs, status);
                        nombre_chofer_entrega.setText(respuesta.get(0).getChofer());
                        placas_camion.setText(respuesta.get(0).getPlacas());
                        Picasso.with(ProgresoEntregaActivity.this).load(respuesta.get(0).getFotocamion()).error(R.drawable.errorcamion).into(foto_camion_entrega);
                        ValidarEstatusActualEntrega(respuesta);
                        RecogerHistorialEntrega();
                    }
                }else{
                    Log.i("ESTATUS_ERROR","No respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.i("ESTATUS_ERROR",t.toString());
                Intent intentErrorConexion = new Intent(ProgresoEntregaActivity.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void RecogerHistorialEntrega(){
        Call<List<Historial_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).HistorialEntrega(
                "historial/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<List<Historial_retrofit>>() {
            @Override
            public void onResponse(Call<List<Historial_retrofit>> call, Response<List<Historial_retrofit>> response) {
                if(response.isSuccessful()){
                    Log.i("CODIGO",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
                    List<Historial_retrofit> historial_retrofits = response.body();
                    LlenarRecyclerView(historial_retrofits);
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<Historial_retrofit>> call, Throwable t) {

            }
        });
    }
    private void LlenarRecyclerView(List<Historial_retrofit> historial_retrofits){
        //Invertir la lista Collections.reverse(historial_retrofits);
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_historico_envio_entrega.setLayoutManager(l);
        AdapterRecylerViewHistoricoEnvio arv = new AdapterRecylerViewHistoricoEnvio(historial_retrofits,R.layout.cardview_historial_envio, ProgresoEntregaActivity.this, getApplicationContext());
        recyclerview_historico_envio_entrega.setAdapter(arv);
    }
    private void ReenviarFactura(){
        Call<Factura_retrofit> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EnviarFactura("factura/gao",
                MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<Factura_retrofit>() {
            @Override
            public void onResponse(Call<Factura_retrofit> call, Response<Factura_retrofit> response) {
                if(response.isSuccessful()){
                    Factura_retrofit factura_retrofit= response.body();
                    Log.i("RESPUESTA FACTURA",response.body().toString());
                    // aun notienefactura
                    if(factura_retrofit.getRuta().equals("sincorreo")){
                        Toast.makeText(ProgresoEntregaActivity.this, "No se proporcionó un correo al registrar el pedido", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ProgresoEntregaActivity.this, factura_retrofit.getRuta(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                }
            }
            @Override
            public void onFailure(Call<Factura_retrofit> call, Throwable t) {
                Log.i("RESPUESTA", t.toString());
            }
        });
    }
    private void NuevaVentanaAcuseRecibo() {
        Intent i = new Intent(ProgresoEntregaActivity.this, AcuseRecibo.class);
        startActivity(i);
    }
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        if(status.equals("Programado")){
            imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            MostrarTutorial();
        }
        else if(status.equals("En Ruta")){
            imagen_progress_bar.setImageResource(R.drawable.proceso1);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            btn_descargar_factura.setEnabled(true);
            MostrarTutorial();
        }
        else if(status.equals("Proximo")){
            imagen_progress_bar.setImageResource(R.drawable.proceso2);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            btn_descargar_factura.setEnabled(true);
        }
        else if(status.equals("En sitio")){
            imagen_progress_bar.setImageResource(R.drawable.proceso3);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            btn_descargar_factura.setEnabled(true);
        }
        else if(status.equals("Descargando")){
            imagen_progress_bar.setImageResource(R.drawable.proceso4);
            fecha_entregado.setVisibility(View.INVISIBLE);
            text_hora_entrega.setVisibility(View.INVISIBLE);
            text_num_pedido.setVisibility(View.INVISIBLE);
            layout_filtro.setVisibility(View.INVISIBLE);
            btn_descargar_factura.setEnabled(true);
        }
        else if(status.equals("Entregado")){
            if (respuesta.get(0).getHizoencuesta().equals("0")){
                imagen_progress_bar.setImageResource(R.drawable.proceso5);
                fecha_entregado.setVisibility(View.VISIBLE);
                text_hora_entrega.setVisibility(View.VISIBLE);
                text_num_pedido.setVisibility(View.VISIBLE);
                layout_filtro.setVisibility(View.VISIBLE);

                text_num_pedido.setText("El pedido "+respuesta.get(0).getPedido()+" fue entregado");
                fecha_entregado.setText(respuesta.get(0).getfSalidaEntrega()+", ");
                text_hora_entrega.setText(respuesta.get(0).gethSalidaEntrega());
                btn_descargar_factura.setEnabled(true);
                Intent i = new Intent(ProgresoEntregaActivity.this, EncuestaActivity.class);
                startActivity(i);
            }else {
                imagen_progress_bar.setImageResource(R.drawable.proceso5);
                fecha_entregado.setVisibility(View.VISIBLE);
                text_hora_entrega.setVisibility(View.VISIBLE);
                text_num_pedido.setVisibility(View.VISIBLE);
                layout_filtro.setVisibility(View.VISIBLE);

                text_num_pedido.setText("El pedido "+respuesta.get(0).getPedido()+" fue entregado");
                fecha_entregado.setText(respuesta.get(0).getfSalidaEntrega()+", ");
                text_hora_entrega.setText(respuesta.get(0).gethSalidaEntrega());
                btn_descargar_factura.setEnabled(true);
            }
        }
        else if(status.equals("Posponer")){
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
    private void ValidarVerOfertas() {
        Call<List<VerOfertas_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).VerOfEntrega("verpromo/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<List<VerOfertas_retrofit>>() {
            @Override
            public void onResponse(Call<List<VerOfertas_retrofit>> call, Response<List<VerOfertas_retrofit>> response) {
                if (response.isSuccessful()){
                    List<VerOfertas_retrofit> verOfertas_retrofits = response.body();
                    if(verOfertas_retrofits.isEmpty()) {
                        btn_ver_ofertas.setEnabled(false);
                        btn_ver_ofertas.setVisibility(View.GONE);
                        ofertas = false;
                    }else{
                        btn_ver_ofertas.setEnabled(true);
                        btn_ver_ofertas.setVisibility(View.VISIBLE);
                        ofertas = true;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<VerOfertas_retrofit>> call, Throwable t) {
            }
        });
    }
    private void Desbloquearbotones(){
        btn_mostrar_detalles_entrega.setEnabled(true);
        if(ofertas==true) {
            btn_ver_ofertas.setEnabled(true);
        }else{
            btn_ver_ofertas.setEnabled(false);
        }
    }
    private void BloquearBotones(){
        btn_mostrar_detalles_entrega.setEnabled(false);
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
            if(menu_estatus==false && historico_estatus==false) {
                RecogerEstatusEntrega();
                ValidarVerOfertas();
            }
            return true;
        }
    }
    private void Inicializador(){
        imagen_progress_bar = (ImageView) findViewById(R.id.imagen_progress_bar_estatus);
        layout_filtro = (LinearLayout) findViewById(R.id.linear_layout_filtro);
        fecha_entregado= (TextView) findViewById(R.id.fecha_llegada);

        text_hora_entrega= (TextView) findViewById(R.id.hora_llegada);
        text_num_pedido= (TextView) findViewById(R.id.pedido);

        btn_mostrar_detalles_entrega = (Button) findViewById(R.id.btn_mostrar_detalles_entrega);
        btn_nuevo_rastreo = (Button) findViewById(R.id.btn_nuevo_rastreo);
        btn_ver_ofertas = (Button) findViewById(R.id.btn_ver_ofertas);
        btn_descargar_factura = (Button) findViewById(R.id.btn_descargar_factura);
        boton_salir = (Button) findViewById(R.id.boton_salir);

        cardview__menu_progreso = (CardView) findViewById(R.id.cardview__menu_progreso);
        cardview__historico_proceso = (CardView) findViewById(R.id.cardview__historico_proceso);

        imagen_mano_click2 = (ImageView) findViewById(R.id.imagen_mano_click2);
        imagen_touch_mano =(ImageView) findViewById(R.id.imagen_touch_mano);
        imagen_touch_mano2 =(ImageView) findViewById(R.id.imagen_touch_mano2);

        cardview__menu_progreso.setVisibility(View.INVISIBLE);
        cardview__historico_proceso.setVisibility(View.INVISIBLE);
        text_hora_entrega.setVisibility(View.INVISIBLE);
        text_num_pedido.setVisibility(View.INVISIBLE);
        fecha_entregado.setVisibility(View.INVISIBLE);
        layout_filtro.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_mostrar_historico = (FloatingActionButton) findViewById(R.id.fab_mostrar_historico);
        progressDoalog = new ProgressDialog(ProgresoEntregaActivity.this);
        recyclerview_historico_envio_entrega = (RecyclerView) findViewById(R.id.RecyclerView_historico_proceso);
        nombre_chofer_entrega =(TextView) findViewById(R.id.nombre_chofer_entrega);
        placas_camion = (TextView) findViewById(R.id.placas_camion);
        foto_camion_entrega = (ImageView) findViewById(R.id.foto_camion_entrega);
        vibrador = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);

        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        codigo_entrega = MetodosSharedPreference.ObtenerCodigoEntregaPref(prs);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Obteniendo los datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ValidarVerOfertas();
        RecogerEstatusEntrega();
    }
}
