package com.acerosocotlan.progresoacerosocotlan.Controlador;

import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Switch;

import com.acerosocotlan.progresoacerosocotlan.Modelo.MetodosSharedPreference;
import com.acerosocotlan.progresoacerosocotlan.Modelo.NetworkAdapter;
import com.acerosocotlan.progresoacerosocotlan.Modelo.StatuEntrega;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class EntregasAcerosOcotlanWidget extends AppWidgetProvider {

    static RemoteViews views;
    static SharedPreferences prs;
    static String status;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        prs = context.getSharedPreferences("usuarioDatos", Context.MODE_PRIVATE);
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        views = new RemoteViews(context.getPackageName(), R.layout.entregas_aceros_ocotlan_widget);
        switch (status) {
            case "Programado":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.progressbar_aceros_ocotlan_version_5_4);
                break;
            case "En Ruta":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.proceso1);
                break;
            case "Proximo":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.proceso2);
                break;
            case "En sitio":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.proceso3);
                break;
            case "Descargando":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.proceso4);
                Log.i("WIDGET", "CONSULTAAAAAAAAAAAA");
                break;
            case "Entregado":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.proceso5);
                break;
            case "Posponer":
                views.setImageViewResource(R.id.widget_entregas_imagen,R.drawable.progressbar_aceros_ocotlan_version_3_revision);
                break;
            default:
                break;
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        Call<List<StatuEntrega>> call = NetworkAdapter.getApiService(MetodosSharedPreference.ObtenerPruebaEntregaPref(prs)).EstatusEntrega(
                "statusentrega_g916a3/gao");
        call.enqueue(new Callback<List<StatuEntrega>>() {
            @Override
            public void onResponse(Call<List<StatuEntrega>> call, Response<List<StatuEntrega>> response) {
                if(response.isSuccessful()) {
                    List<StatuEntrega> statuEntregas = response.body();
                    status= statuEntregas.get(0).getEstatus();
                }
            }
            @Override
            public void onFailure(Call<List<StatuEntrega>> call, Throwable t) {
            }
        });
    }
    @Override
    public void onEnabled(Context context) {
    }
    @Override
    public void onDisabled(Context context) {
    }

}

