package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
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
import com.acerosocotlan.progresoacerosocotlan.Modelo.Factura_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.Historial_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.Modelo.VerOfertas_retrofit;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

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
    private Button btn_mostrar_detalles_entrega, btn_acuse_recibo, btn_ver_ofertas,btn_descargar_factura,boton_salir;
    private FloatingActionButton fab, fab_mostrar_historico;
    private RecyclerView recyclerview_historico_envio_entrega;
    private ImageView foto_camion_entrega;

    //SHARED PREFERENCE
    private SharedPreferences prs;
    //VARIABLES
    private String codigo_entrega;
    private String status;
    //VARIABLE PARA SABER SI EL MENU PRINCIPAL ESTA ABIERTO O CERRADO
    private boolean menu_estatus = false;
    //VARIABLE PARA SABER SI EL HISTORIAL ESTA ABIERTO O CERRADO
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

        /*
        * ESTE BOTON SIRVE PARA MOSTRA EL MENU PRINCIPAL DE LA APLICACION
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                AbrirMenuUsuario();
            }
        });

        /*
         * ESTE BOTON SIRVE PARA MOSTRA EL PROCEDIMIENTO EN EL QUE SE ENCUENTRA EL PEDIDO
         * Y PARA VER LA INFORMACION DEL CAMION Y CHOFER
         */
        fab_mostrar_historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                AbrirHistoricoProcesoEntrega();
            }
        });

        /*
         * AQUI SE HABILITA DOBLE CLICK A LA IMAGEN DE LA BARRA DE PROGRESO PARA QUE VUELVA A RECARGAR LA INFORMACION
         */
        imagen_progress_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        /*
        * ESTE BOTON ESTA DENTRO DEL MENU PRINCIPAL Y SIRVE PARA ABRIR UNA NUEVA VENTANA
        * EN DONDE SE MOSTRARA EL MATERIAL DEL PEDIDO
        */
        btn_mostrar_detalles_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                /*
                * AL PRESIONAR EL BOTON SE BLOQUEAN LOS BOTONES PARA EVITAR QUE EL USUARIO PRESIONE DOS VECES EL BOTON
                * Y QUE SE ABRAN DOS VENTANAS (LOS BOTONES VUELVEN HACER VISIBLES CUANDO LA VENTANA VUELVA A PASAR POR SU METODO OnResume())
                */
                BloquearBotones();
                Intent i = new Intent(ProgresoEntregaActivity.this, DetalleEntregaActivity2.class);
                startActivity(i);
            }
        });

        /*
        * CUANDO SE HAGA CLICK EN ESTE BOTON SE ABRIRA LA VENTANA PARA VISUALIZAR EL ACUSE DE RECIBO
        */
        btn_acuse_recibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                //SE ABRE EL ACUSE DE RECIBO
                NuevaVentanaAcuseRecibo();
            }
        });

        /*
        * ESTE BOTON SOLO SE VERA CUANDO HAYA UNA OFERTA DISPONIBLE EN LA APLICACION, CUANDO SE PRESIONA
        * SE ABRE LA PANTALLA DE LAS OFERTAS
        */
        btn_ver_ofertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                BloquearBotones();
                Intent i = new Intent(ProgresoEntregaActivity.this, VerOferta.class);
                startActivity(i);
            }
        });

        /*
        * CON ESTE BOTON EL USUARIO PODRA REENVIAR LA FACTURA DEL PEDIDO A SU CORREO
        * APARECERA UN DIALOGO DE CONFIRMACION
        */
        btn_descargar_factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                //SE MUESTRA EL DIALOG
                MostrarDialogCustomFactura();
            }
        });

        /*
        * BOTON PARA FUNALIZAR LA SESION DEL CODIGO DE RASTREO ACTUAL.
        * AL HACER CLICK TE DEVOLVERA AL MENU PRINCIPAL Y LA SIGUIENTE VES QUE SE INGRESE
        * TE VOLVERA A PEDIR UN CODIGO DE RASTREO
        */
        boton_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                //SE HACE UNA VALIDACION PARA SABER SI HAY OFERTAS DISPONIBLES
                if (ofertas==true) {
                    //SI HAY UNA OFERTA ENTONCES SE MOSTRARA UNA DIALOG DONDE PREGUNTE SI NO QUIERE VER LAS OFERTAS ANTES DE SALIR
                    MostrarDialogCustomOfertas();
                }else{
                    //SI NO HAY OFERTAS ENTONCES SOLO SE ELIMINAN LAS SHARED PREFERENCES Y SE FINALIZA LA VENTANA
                    remover_variables_sharedpreference();
                    finish();
                }
            }
        });
    }

    private void Inicializador(){
        imagen_progress_bar = (ImageView) findViewById(R.id.imagen_progress_bar_estatus);
        layout_filtro = (LinearLayout) findViewById(R.id.linear_layout_filtro);
        fecha_entregado= (TextView) findViewById(R.id.fecha_llegada);

        text_hora_entrega= (TextView) findViewById(R.id.hora_llegada);
        text_num_pedido= (TextView) findViewById(R.id.pedido);

        btn_mostrar_detalles_entrega = (Button) findViewById(R.id.btn_mostrar_detalles_entrega);
        btn_acuse_recibo = (Button) findViewById(R.id.btn_acuse_recibo);
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
        //btn_acuse_recibo.setEnabled(false);

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

    /*
    * METODO QUE FORMA PARTE DEL CICLO DE VIDA DE UNA ACTIVITY
    * SE MANDA A LLAMAR ESTE METODO SIEMPRE QUE UNA ACTIVITY PASA A PRIMER PLANO
    */
    @Override protected void onResume() {
        super.onResume();
        /*
        * CADA QUE UN BOTON SE PRESIONA SE BLOQUEA PARA EVITAR LOS DOBLES CLICK
        * PERO CUANDO SE PASA POR ESTE METODO SE DESBLOQUEARAN
        */
        Desbloquearbotones();
    }

    @Override public void onBackPressed() {
        //SE VALIDA EL MENU PRINCIPAL DE LA APLICACION Y EL HISTORIAL ESTEN CERRADOS DE LO CONTRARIO SE CERRARAN
        if (menu_estatus == true) {
            OcultarMenu();
        }else if(historico_estatus == true){
            OcultarHistorico();
        } else {
           finish();
        }
    }

    /*
    * DIALOG QUE LE INDICA AL USUARIO ANTES DE SALIR QUE HAY OFERTAS DISPONIBLES
    */
    private void MostrarDialogCustomOfertas(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_ver_ofertas, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();
        ImageView estrella = (ImageView) dialoglayout.findViewById(R.id.dialog_img_estrella_oferta);
        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_si_ver_ofertas);
        Button botonCancelar = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_ver_ofertas);
        //SE LE ASIGNA UNA ANIMACION A LA ESTRELLA QUE APARECE EN EL DIALOG
        estrellaAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.estrella_ofertas_animation);
        estrella.setAnimation(estrellaAnimation);
        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SE ABRE LA VENTA DE LAS OFERTAS
                vibrador.vibrate(VIBRACION_TIEMPO);
                Intent i = new Intent(ProgresoEntregaActivity.this, VerOferta.class);
                startActivity(i);
                alertDialog.dismiss();
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SE ELIMINAN LAS SHARED PREFERENCES Y SE FINALIZA LA VENTANA
                vibrador.vibrate(VIBRACION_TIEMPO);
                alertDialog.dismiss();
                remover_variables_sharedpreference();
                finish();
            }
        });
    }

    /*
     * DIALOG DE CONFIRMACION PARA REENVIAR LA FACTURA
     */
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

    /*
    * METODO PARA ABRIR O CERRAR EL MENU DEL USUARIO
    */
    private void AbrirMenuUsuario() {
        //SE VALIDA DE QUE EL MENU ESTE CERRADO
        if (menu_estatus==false) {
            //SE VALIDA QUE SI EL HISTORIAL ESTA ABIERTO ENTONCES MANDA LLAMAR EL METODO PARA OCULTARLO
            if(historico_estatus==true){
                OcultarHistorico();
            }else{
                //SI NO ENTONCES SE ABRE EL MENU
                MostrarMenu();
            }
        }else{
            //SI EL MENU SE ENCUENTRA ABIERTO ENTONCES SE OCULTA EL MENU
            OcultarMenu();
        }
    }

    /*
    * METODO PARA CERRAR MENU
    */
    private void OcultarMenu(){
        //SE LE ASIGNA LA ANIMACION AL MENU PARA QUE PAREZCA QUE EL MENU ENTRA DESDE AFUERA DE LA VENTANA
        carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_ocultar);
        cardview__menu_progreso.setAnimation(carroAnimacion);
        cardview__menu_progreso.setVisibility(View.INVISIBLE);
        //EL BOTON GIRA
        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
        fab.startAnimation(flatbutton_animation);
        //EL ESTATUS DEL MENU CAMBIA A FALSO
        menu_estatus=false;
    }

    /*
     * METODO PARA MOSTRAR MENU
     */
    private void MostrarMenu(){
        //SE LE ASIGNA LA ANIMACION AL MENU PARA QUE PAREZCA QUE EL MENU SALE AFUERA DE LA VENTANA
        cardview__menu_progreso.setVisibility(View.VISIBLE);
        cardviewAnimacion= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.menu_mostrar);
        cardview__menu_progreso.setAnimation(cardviewAnimacion);
        //EL BOTON GIRA
        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation_close);
        fab.startAnimation(flatbutton_animation);
        //EL ESTATUS DEL MENU CAMBIA A VERDADERO
        menu_estatus=true;
    }

    /*
    * AL IGUAL QUE EL MENU PRINCIPAL TAMBIEN SE TIENE UNA VALIDACION PARA COMPROBAR SI EL HISTORICO O EL MENU ESTA ABIERTO
    */
    private void AbrirHistoricoProcesoEntrega() {
        //SE VALIDA QUE EL HISTORICO ESTE CERRADO
        if (historico_estatus==false) {
            //EN CASO DE QUE EL MENU ESTE ABIERTO ENTONCES LO OCULTAMOS
            if(menu_estatus==true){
                OcultarMenu();
            }
            //MOSTRAMOS EL HISTORICO
            MostrarHistorico();
        }else{
            //SI EL HISTORIAL ESTA ABIERTO ENTONCES LO OCULTAMOS
            OcultarHistorico();
        }
    }

    /*
    * OCULTAR EL HISTORIAL Y USAR LAS ANIMACIONES
    */
    private void OcultarHistorico(){
        carroAnimacion = AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.ocultarhistorico_animation);
        cardview__historico_proceso.setAnimation(carroAnimacion);
        cardview__historico_proceso.setVisibility(View.INVISIBLE);

        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation_close);
        fab_mostrar_historico.startAnimation(flatbutton_animation);
        historico_estatus=false;
    }

    /*
     * MOSTRAR EL HISTORIAL Y USAR LAS ANIMACIONES
     */
    private void MostrarHistorico(){
        cardviewAnimacion= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.mostrarhistorico_animation);
        cardview__historico_proceso.setVisibility(View.VISIBLE);
        cardview__historico_proceso.setAnimation(cardviewAnimacion);

        flatbutton_animation= AnimationUtils.loadAnimation(ProgresoEntregaActivity.this,R.anim.flatbutton_animation);
        fab_mostrar_historico.startAnimation(flatbutton_animation);
        historico_estatus=true;
    }

    /*
    * METODO QUE MUESTRA LOS CIRCULOS ANIMADOS AL REDEDOR DE LOS BOTONES
    */
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

    /*
     * PETICION PARA OBTENER LA INFORMCION DEL PEDIDO DEL CLIENTE
     * SE MANDA DENTRO LA URL EL CODIGO DE RASTREO
     */
    private void RecogerEstatusEntrega(){
        progressDoalog.show();
        //METODO RETROFIT QUE MANDA POR LA URL EL CODIGO DE RASTRE Y QUE RECIBE UNA LISTA DE OBJETOS DE TIPO STATUS ENTREGA
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+codigo_entrega+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if(response.isSuccessful()) {
                    //SI LA RESPUESTA ES CORRECTA ENTONCES SE ALMACENA EN UNA LISTA DE TIPO STATU ENTREGA
                    List<StatuEntrega> respuesta = response.body();
                    //SE VALIDA QUE LA RESPUESTA NO ESTE VACIA
                    if (respuesta.isEmpty()) {
                        //SI LA RESPUESTA ES NULA ENTONCES SE LIMPIARAN LAS SHARED PREFERENCES Y SE ABRIRA LA VENTANA DE CODIGO DE RASTREO
                        Toast.makeText(ProgresoEntregaActivity.this, "Código invalido", Toast.LENGTH_SHORT).show();
                        remover_variables_sharedpreference();
                        Intent i = new Intent(ProgresoEntregaActivity.this, CodigoIngreso.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    } else {

                        //status = respuesta.get(0).getEstatus();
                        //MetodosSharedPreference.GuardarEstatusEntrega(prs, status);
                        //SE MANDA EL NOMBRE DEL CHOFER AL MENU DEL HISTORIAL
                        nombre_chofer_entrega.setText(respuesta.get(0).getChofer());
                        //SE MANDAN LAS PLACAS DEL CAMION AL MENU DEL HISTORICO
                        placas_camion.setText(respuesta.get(0).getPlacas());
                        //SE DESCARGA LA IMAGEN DEL CAMION CON PICASSO
                        Picasso.with(ProgresoEntregaActivity.this).load(respuesta.get(0).getFotocamion()).error(R.drawable.errorcamion).into(foto_camion_entrega);
                        Log.e("IMG URL", respuesta.get(0).getFotocamion());
                        //SE MANDA LLAMAR ESTE METODO PARA VALIDAR EL ESTATUS DEL PEDIDO Y ASI CONFIGURAR LAS IMAGENES, SE MANDA LA RESPUESTA POR PARAMETRO
                        ValidarEstatusActualEntrega(respuesta);
                        //SE HACE UNA NUEVA PETICION PARA SACAR EL HISTORIAL DEL PEDIDO
                        RecogerHistorialEntrega();
                    }
                }else{
                    Log.i("ESTATUS_ERROR","No respuesta");
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                //SI NO SE ESTABLECE LA CONEXION AL SERVIDOR ENTONCES SE ABRIRA UNA VENTANA DE ERROR DE CONEXION
                Intent intentErrorConexion = new Intent(ProgresoEntregaActivity.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }

    /*
    * PETICION PARA OBTENER EL HISTORIAL DEL PEDIDO EN DONDE SE VE MAS A DETALLE EL PROCESO DE ENTREGA
    */
    private void RecogerHistorialEntrega(){
        /*
        * PETICION PARA OBTENER EL HISTORIAL DE ENTREGA
        * SE MANDA POR POST EL CODIGO DE ENTREGA
        */
        Call<List<Historial_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).HistorialEntrega(
                "historial/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<List<Historial_retrofit>>() {
            @Override
            public void onResponse(Call<List<Historial_retrofit>> call, Response<List<Historial_retrofit>> response) {
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if(response.isSuccessful()){
                    //SI LA RESPUESTA ES CORRECTA ENTONCES SE ALMACENA EN UNA LISTA DE TIPO Historial_retrofit
                    List<Historial_retrofit> historial_retrofits = response.body();
                    //PASAMOS LA RESPUESTA QUE CONTIENE EL HISTORIAL PARA LLENAR EL RECYCLERVIEW
                    LlenarRecyclerView(historial_retrofits);
                }else{

                }
            }

            @Override
            public void onFailure(Call<List<Historial_retrofit>> call, Throwable t) {
                Log.e("historico: ","ERROR" + t.toString());
            }
        });
    }

    /*
    * EN ESTE METODO SE RECIBE LA LISTA CON EL HISTORIAL PARA PODER MOSTRARLA EN EL RECYCLERVIEW
    */
    private void LlenarRecyclerView(List<Historial_retrofit> historial_retrofits){
        //Invertir la lista Collections.reverse(historial_retrofits);
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_historico_envio_entrega.setLayoutManager(l);
        //SE HACE UNA INSTANCIA DEL ADAPTAROR DEL RECYCLER VIEW PARA PODER PASARLE LA LISTA POR SU CONSTRUCTOR
        AdapterRecylerViewHistoricoEnvio arv = new AdapterRecylerViewHistoricoEnvio(historial_retrofits,R.layout.cardview_historial_envio, ProgresoEntregaActivity.this, getApplicationContext());
        //AL RECYCLERVIEW SE LE ASIGNA EL ADAPTADOR
        recyclerview_historico_envio_entrega.setAdapter(arv);
    }

    /*
    * METODO PARA REENVIAR LA FACTURA DEL PEDIDO AL CORREO DEL CLIENTE
    * AQUI SE HACE UNA SOLICITUD AL WEB SERVICE EL CUAL SE ENCARGA DE HACER EL REENVIO
    */
    private void ReenviarFactura(){
        Call<Factura_retrofit> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EnviarFactura("factura/gao",
                MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<Factura_retrofit>() {
            @Override
            public void onResponse(Call<Factura_retrofit> call, Response<Factura_retrofit> response) {
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if(response.isSuccessful()){
                    //LA RESPUESTA SE ALMACENA EN UN OBJETO
                    Factura_retrofit factura_retrofit= response.body();
                    //SE VALIDA LA RESPUESTA DEL WEB SERVICE
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

    //METODOPARA ABRIR UNA VENTANA CON EL ACUSE DE RECIBO
    private void NuevaVentanaAcuseRecibo() {
        Intent i = new Intent(ProgresoEntregaActivity.this, AcuseRecibo.class);
        startActivity(i);
    }

    /*
    * AQUI SE VALIDA EL ESTATUS ACTUAL DEL PEDIDO PARA SABER QUE IMAGEN PONER DEPENDIENDO DEL ESTATUS
    *
    * NOTA: SE HABLO DE CAMBIAR LA BARRA DE PROGRESO EN ZULA, QUITAR EL PROCESO EN RUTA POR QUESTIONES DE SEGURIDAD
    * SE REALIZO EL CODIGO Y LAS NUEVAS IMAGENES PERO NO SE A VUELTO A MENCIONAR
    */
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        switch (respuesta.get(0).getEstatus()){
            case "Programado":
                imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                //SE MOSTRARAN LOS CIRCULOS EN LOS BOTONES SOLO EN LOS PRIMEROS DOS PROCESOS
                MostrarTutorial();

                break;

            case "En Ruta":
                //VALIDACION PARA ZULA
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_progress_bar.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imagen_progress_bar.setImageResource(R.drawable.proceso1);
                }
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                btn_descargar_factura.setEnabled(true);
                //SE MOSTRARAN LOS CIRCULOS EN LOS BOTONES SOLO EN LOS PRIMEROS DOS PROCESOS
                MostrarTutorial();

                break;

            case "Proximo":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_progress_bar.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imagen_progress_bar.setImageResource(R.drawable.proceso2);
                }
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                btn_descargar_factura.setEnabled(true);

                break;

            case "En sitio":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_progress_bar.setImageResource(R.drawable.progreso_zula_en_sitio);
                }else{
                    imagen_progress_bar.setImageResource(R.drawable.proceso3);
                }
                imagen_progress_bar.setImageResource(R.drawable.proceso3);
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                btn_descargar_factura.setEnabled(true);
                break;

            case "Descargando":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imagen_progress_bar.setImageResource(R.drawable.progreso_zula_descargando);
                }else{
                    imagen_progress_bar.setImageResource(R.drawable.proceso4);
                }
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                btn_descargar_factura.setEnabled(true);
                break;

            case "Entregado":
                /*
                * CUANDO EL PEDIDO SE HAYA ENTREGADO ENTONCES SE MOSTRARA UNA ENCUESTA
                */
                if (respuesta.get(0).getHizoencuesta().equals("0")){
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imagen_progress_bar.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imagen_progress_bar.setImageResource(R.drawable.proceso5);
                    }
                    fecha_entregado.setVisibility(View.VISIBLE);
                    text_hora_entrega.setVisibility(View.VISIBLE);
                    text_num_pedido.setVisibility(View.VISIBLE);
                    layout_filtro.setVisibility(View.VISIBLE);
                    btn_acuse_recibo.setEnabled(true);

                    text_num_pedido.setText("El pedido "+respuesta.get(0).getPedido()+" fue entregado");
                    fecha_entregado.setText(respuesta.get(0).getfSalidaEntrega()+", ");
                    text_hora_entrega.setText(respuesta.get(0).gethSalidaEntrega());
                    btn_descargar_factura.setEnabled(true);
                    Intent i = new Intent(ProgresoEntregaActivity.this, EncuestaActivity.class);
                    startActivity(i);
                }else {
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imagen_progress_bar.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imagen_progress_bar.setImageResource(R.drawable.proceso5);
                    }
                    fecha_entregado.setVisibility(View.VISIBLE);
                    text_hora_entrega.setVisibility(View.VISIBLE);
                    text_num_pedido.setVisibility(View.VISIBLE);
                    layout_filtro.setVisibility(View.VISIBLE);

                    text_num_pedido.setText("El pedido "+respuesta.get(0).getPedido()+" fue entregado");
                    fecha_entregado.setText(respuesta.get(0).getfSalidaEntrega()+", ");
                    text_hora_entrega.setText(respuesta.get(0).gethSalidaEntrega());
                    btn_descargar_factura.setEnabled(true);

                }
                break;

            case "Posponer":

                imagen_progress_bar.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
                fecha_entregado.setVisibility(View.INVISIBLE);
                text_hora_entrega.setVisibility(View.INVISIBLE);
                text_num_pedido.setVisibility(View.INVISIBLE);
                layout_filtro.setVisibility(View.INVISIBLE);
                break;

            default:
                Toast.makeText(this, "Ocurrió un problema, intente de nuevo", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //LIMPIA LAS SHARED PREFERENCE
    private void remover_variables_sharedpreference(){
        prs.edit().clear().apply();
    }

    /*
    * PETICION PARA SABER SI HAY PROMOCIONES ACTIVAS EN LA APLICACION Y PODER MOSTRAR EL BOTON PARA VER OFERTAS DENTRO DEL MENU PRINCIPAL
    */
    private void ValidarVerOfertas() {
        Call<List<VerOfertas_retrofit>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).VerOfEntrega("verpromo/gao", MetodosSharedPreference.ObtenerCodigoEntregaPref(prs));
        call.enqueue(new Callback<List<VerOfertas_retrofit>>() {
            @Override
            public void onResponse(Call<List<VerOfertas_retrofit>> call, Response<List<VerOfertas_retrofit>> response) {
                if (response.isSuccessful()){
                    //ALMACENAMOS LA RESPUESTA EN UNA VARIABLE DE TIPO LIST
                    List<VerOfertas_retrofit> verOfertas_retrofits = response.body();
                    //VALIDAMOS QUE LA LISTA NO ESTE VACIA
                    if(verOfertas_retrofits.isEmpty()) {
                        //SI ESTA VACIA ENTONCES OCULTAREMOS EL BOTON DEL MENU PRINCIPAL
                        btn_ver_ofertas.setEnabled(false);
                        btn_ver_ofertas.setVisibility(View.GONE);
                        ofertas = false;
                    }else{
                        //DE LO CONTRARIO ENTONCES SI MOSTRAREMOS EL BOTON
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

    //SE DESBLOQUEAN TODOS LOS BOTONES
    private void Desbloquearbotones(){
        btn_mostrar_detalles_entrega.setEnabled(true);
        if(ofertas==true) {
            btn_ver_ofertas.setEnabled(true);
        }else{
            btn_ver_ofertas.setEnabled(false);
        }
    }

    //SE BLOQUEAN TODOS LOS BOTONES
    private void BloquearBotones(){
        btn_mostrar_detalles_entrega.setEnabled(false);
        btn_ver_ofertas.setEnabled(false);
    }

    //AQUI SE CONFIGURA EL DOBLE CLICK PARA VOLVER A CARGAR LA VENTANA
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //SE VALIDA QUE EL MENU Y EL HISTORIAL ESTEN CERRADOS
            if(menu_estatus==false && historico_estatus==false) {
                RecogerEstatusEntrega();
                ValidarVerOfertas();
            }
            return true;
        }
    }


}
