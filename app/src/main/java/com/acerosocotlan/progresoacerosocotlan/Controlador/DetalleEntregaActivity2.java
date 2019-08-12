package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerView;
import com.acerosocotlan.progresoacerosocotlan.Modelo.DetalleEntrega_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleEntregaActivity2 extends AppCompatActivity {
    private RecyclerView detallesRecyclerview;
    private ImageView imageViewFondoDetallesEntrega;
    private LinearLayout linear_layout_filtro_detalle;
    private String status;
    private SharedPreferences prs;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_entrega2);
        inicializador();
        ObtenerDetalleEntrega();
    }


    /*
    * PETICION AL SERVIDOR PARA OBTENER TODOS LOS MATERIALES DEL PEDIDO
    * SE MANDA EL CODIGO DE RASTREO DENTRO DE LA URL
    */
    private void ObtenerDetalleEntrega(){
        Call<List<DetalleEntrega_retrofit>> call = NetworkAdapter.getApiService(
                MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).detalleEntrega("detalle_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<DetalleEntrega_retrofit>>() {
            @Override
            public void onResponse(Call<List<DetalleEntrega_retrofit>> call, Response<List<DetalleEntrega_retrofit>> response) {
                //SI HAY RESPUESTA DEL SERVIDOR OCULTAMOS EL PROGRESS DIALOG
                progressDoalog.dismiss();
                //VALIDAMOS QUE LA RESPUESTA SEA CORRECTA
                if (response.isSuccessful()){
                    //GUARDAMOS LA RESPUESTA EN UNA LISTA DE OBJETOS DetalleEntrega_retrofit
                    List<DetalleEntrega_retrofit> materiales = response.body();
                    //MANDAMOS LLENAR EL RECYCLERVIEW CON LA LISTA DE LOS MATERIALES
                    LlenarRecyclerView(materiales);
                }else{
                    //SI LA RESPUESTA ES INCORRECTA ENTONCES ABRIMOS LA VENTANA DE ERROR
                    Intent intentErrorConexion = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
                }
            }

            @Override
            public void onFailure(Call<List<DetalleEntrega_retrofit>> call, Throwable t) {
                //SI LA RESPUESTA CON EL SERVIDOR ES ERRONEA ENTONCES ABRIMOS LA VENTANA DE ERROR
                progressDoalog.dismiss();
                Intent i = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    /*
    * ESTE METODO SE ENCARGA DE LLENAR EL RECYCLER VIEW CON LOS MATERIALES DEL PEDIDO
    * SE RECIBE POR PARAMETRO LA LISTA DE MATERIALES
    */
    private void LlenarRecyclerView(List<DetalleEntrega_retrofit> material){
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        detallesRecyclerview.setLayoutManager(l);
        /*
        * SE HACE UNA INSTANCIA DEL ADAPTADOR QUE LE CORRESPONDE AL RECYCLERVIEW
        * POR PARAMETRO SE MANDA LA LISTA DEL MATERIAL, LA ACTIVIDAD Y EL CONTEXTO
        */
        AdapterRecyclerView arv = new AdapterRecyclerView(material,R.layout.cardview_detalle_entrega, DetalleEntregaActivity2.this, getApplicationContext());
        detallesRecyclerview.setAdapter(arv);
    }

    /*
    * SE HACE UNA PETICION AL SERVIDOR PARA SABER EL ESTATUS DEL PEDIDO Y PONER EL FONDO
    * EN LA CLASE ProgresoEntregaActivity SE ESPECIFICA MAS DETALLADAMENTE COMO FUNCIONA
    */
    public void RecogerEstatusEntrega(){
        progressDoalog.show();
        //METODO RETROFIT QUE MANDA POR LA URL EL CODIGO DE RASTRE Y QUE RECIBE UNA LISTA DE OBJETOS DE TIPO STATUS ENTREGA
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_"+MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)+"/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                progressDoalog.dismiss();
                //SE VALIDA QUE LA RESPUESTA SEA CORRECTA
                if (response.isSuccessful()) {
                    //SI LA RESPUESTA ES CORRECTA ENTONCES SE ALMACENA EN UNA LISTA DE TIPO STATU ENTREGA
                    List<StatuEntrega> respuesta = response.body();
                    //status = respuesta.get(0).getEstatus();
                    //MetodosSharedPreference.GuardarEstatusEntrega(prs, status);
                    //SE MANDA LLAMAR ESTE METODO PARA VALIDAR EL ESTATUS DEL PEDIDO Y ASI CONFIGURAR LAS IMAGENES, SE MANDA LA RESPUESTA POR PARAMETRO
                    ValidarEstatusActualEntrega(respuesta);
                }else{
                    //SI LA RESPUESTA ES ERRONEA ENTONCES SE ABRIRA UNA VENTANA DE ERROR DE CONEXION
                    Intent intentErrorConexion = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                    intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentErrorConexion);
                }
            }

            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
                progressDoalog.dismiss();
                //SI NO SE ESTABLECE LA CONEXION AL SERVIDOR ENTONCES SE ABRIRA UNA VENTANA DE ERROR DE CONEXION
                Intent intentErrorConexion = new Intent(DetalleEntregaActivity2.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }

    /*
     * METODO PARA VALIDAR EL ESTATUS DEL PEDIDO Y PODER ASIGNAR LA IMAGEN DE FONDO
     *
     * NOTA: SE HABIA HABLA DE QUE SE IMPLEMENTARIA UNA VALIDACION EN DONDE SI LA SOCIEDAD ERA ZULA
     * ENTONCES SE USARIA UNA BARRA DE PROGRESO DIFERENTE CON SOLO 4 PROCESOS EN LUGAR DE 5 POR CUESTIONES DE SEGURIDAD
     * PERO A DIA DE HOY 29-JULIO-2019 NO SE A DICHO NADA PERO LA VALIDACION Y LA BARRA DE 4 ESTATUS YA ESTAN IMPLEMENTADOS
     *
     */
    private void ValidarEstatusActualEntrega(List<StatuEntrega> respuesta) {
        switch (respuesta.get(0).getEstatus()){
            case "Programado":
                imageViewFondoDetallesEntrega.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_5_4);
                break;

            case "En Ruta":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso1);
                }
                break;

            case "Proximo":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_ya_vamos);
                }else{
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso2);
                }
                break;

            case "En sitio":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_en_sitio);
                }else{
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso3);
                }
                break;

            case "Descargando":
                if(respuesta.get(0).getSociedad().equals("ZULA")){
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_descargando);
                }else{
                    imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso4);
                }
                break;

            case "Entregado":

                if (respuesta.get(0).getHizoencuesta().equals("0")){
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso5);
                    }
                }else {
                    if(respuesta.get(0).getSociedad().equals("ZULA")){
                        imageViewFondoDetallesEntrega.setImageResource(R.drawable.progreso_zula_listo);
                    }else{
                        imageViewFondoDetallesEntrega.setImageResource(R.drawable.proceso5);
                    }
                }
                break;

            case "Posponer":
                imageViewFondoDetallesEntrega.setImageResource(R.drawable.progressbar_aceros_ocotlan_version_3_revision);
                break;

            default:
                Toast.makeText(this, "Ocurrió un problema, intente de nuevo", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void inicializador(){
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        progressDoalog = new ProgressDialog(DetalleEntregaActivity2.this);
        detallesRecyclerview = (RecyclerView) findViewById(R.id.detalles_entregas_recyclerview);
        linear_layout_filtro_detalle = (LinearLayout) findViewById(R.id.linear_layout_filtro_detalle);
        imageViewFondoDetallesEntrega = (ImageView) findViewById(R.id.imagen_fondo_detalles_estatus);
        linear_layout_filtro_detalle.setVisibility(View.INVISIBLE);
        progressDoalog.setMax(100);
        progressDoalog.setTitle("Aceros Ocotlán");
        progressDoalog.setIcon(R.drawable.logo);
        progressDoalog.setMessage("Obteniendo los datos");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        RecogerEstatusEntrega();
    }
}
