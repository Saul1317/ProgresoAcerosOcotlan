package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acerosocotlan.progresoacerosocotlan.Modelo.AcuseRecibo_retrofit;
import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcuseRecibo extends AppCompatActivity {

    private ImageView img_acuse_recibo;
    private SharedPreferences prs;
    private Vibrator vibrador;
    private static final long VIBRACION_TIEMPO = 50;
    private TextView txt_mensaje_acuse_recibo;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acuse_recibo);
        IniciadorViews();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogoConfimacionEnviarAcuse();
            }
        });
    }
    private void IniciadorViews() {
        img_acuse_recibo = (ImageView) findViewById(R.id.img_acuserecibo);
        Picasso.with(this).load("https://cdn.pagina24.com.mx/content/images/2017/10/18/zacatecas/05.jpg").error(R.drawable.errorconexionvertical).into(img_acuse_recibo);
        prs = getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        vibrador = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        txt_mensaje_acuse_recibo = (TextView) findViewById(R.id.txt_mensaje_acuse_recibo);
        VerAcuseRecibo();
    }
    private void DialogoConfimacionEnviarAcuse(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_acerosocotlan, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();

        Button botonEntendido = (Button) dialoglayout.findViewById(R.id.btn_dialog_si_ver_ofertas);
        Button botonCancelar = (Button) dialoglayout.findViewById(R.id.btn_dialog_no_ver_ofertas);
        TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_titulo);
        TextView txtDescripcion  = (TextView) dialoglayout.findViewById(R.id.dialog_acerosocotlan_descripcion);

        txtTitutlo.setText(getResources().getString(R.string.dialog_txt_titulo_acuse_recibo));
        txtDescripcion.setText(getResources().getString(R.string.dialog_txt_descripcion_acuse_recibo));
        botonEntendido.setText(getResources().getString(R.string.dialog_btn_entendido_factura));
        botonCancelar.setText(getResources().getString(R.string.dialog_btn_cancelar_factura));

        botonEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                ReenviarAcuseRecibo();
                alertDialog.dismiss();
            }
        });
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrador.vibrate(VIBRACION_TIEMPO);
                alertDialog.dismiss();
            }
        });
    }
    private void VerAcuseRecibo(){
        Call<AcuseRecibo_retrofit> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).VerAcuseRecibo(
                "verrecibo/gao",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)
        );
        call.enqueue(new Callback<AcuseRecibo_retrofit>() {
            @Override
            public void onResponse(Call<AcuseRecibo_retrofit> call, Response<AcuseRecibo_retrofit> response) {
                if(response.isSuccessful()){
                    AcuseRecibo_retrofit acuseRecibo_retrofit= response.body();
                    Log.i("IMG Recibo",acuseRecibo_retrofit.getResp());
                    Picasso.with(AcuseRecibo.this).load(acuseRecibo_retrofit.getResp()).placeholder(R.drawable.errorconexionvertical).error(R.drawable.errorconexionvertical).into(img_acuse_recibo);
                    if(acuseRecibo_retrofit.getResp().equals("Aun no cuenta con recibo")){
                        txt_mensaje_acuse_recibo.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.GONE);
                    }else{
                        txt_mensaje_acuse_recibo.setVisibility(View.INVISIBLE);
                        fab.setVisibility(View.VISIBLE);
                    }
                }else{
                }
            }

            @Override
            public void onFailure(Call<AcuseRecibo_retrofit> call, Throwable t) {
                Log.i("ESTATUS_ERROR",t.toString());
                Intent intentErrorConexion = new Intent(AcuseRecibo.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
    private void ReenviarAcuseRecibo(){
        Call<AcuseRecibo_retrofit> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).AcuseReciboEntrega(
                "recibo/gao",MetodosSharedPreference.ObtenerCodigoEntregaPref(prs)
        );
        call.enqueue(new Callback<AcuseRecibo_retrofit>() {
            @Override
            public void onResponse(Call<AcuseRecibo_retrofit> call, Response<AcuseRecibo_retrofit> response) {
                if(response.isSuccessful()){
                    AcuseRecibo_retrofit acuseRecibo_retrofit= response.body();
                    if(acuseRecibo_retrofit.getResp().equals("Aun no cuenta con recibo")){
                        Toast.makeText(AcuseRecibo.this, "otro", Toast.LENGTH_SHORT).show();
                    }if(acuseRecibo_retrofit.getResp().equals("sincorreo")){
                        Toast.makeText(AcuseRecibo.this, "No se proporcionó un correo al registrar el pedido", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Log.i("RESPUESTA FACTURA", acuseRecibo_retrofit.getResp());
                        Toast.makeText(AcuseRecibo.this, acuseRecibo_retrofit.getResp(), Toast.LENGTH_SHORT).show();
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<AcuseRecibo_retrofit> call, Throwable t) {
                Log.i("ESTATUS_ERROR",t.toString());
                Intent intentErrorConexion = new Intent(AcuseRecibo.this, ErrorConexionActivity.class);
                intentErrorConexion.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentErrorConexion);
            }
        });
    }
}
