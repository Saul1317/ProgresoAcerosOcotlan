package com.acerosocotlan.progresoacerosocotlan.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.acerosocotlan.progresoacerosocotlan.BuildConfig;

import java.nio.charset.StandardCharsets;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

/**
 * Created by Saúl Alejandro Delgado Mayorga on 19/04/2018.
 * Configuración de retrofit
 */

public class NetworkAdapter {
    private static NetworkService API_SERVICE;

    public static NetworkService getApiService(String x) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(x).addConverterFactory(GsonConverterFactory.create()).build();
        API_SERVICE = retrofit.create(NetworkService.class);
        return API_SERVICE;
    }
    public static NetworkService getApiServiceAlternativo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://acerosocotlan.mx/gao/").addConverterFactory(GsonConverterFactory.create()).build();
        API_SERVICE = retrofit.create(NetworkService.class);
        return API_SERVICE;
    }

    public static NetworkService getApiServiceAlternativo2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.tecnola.com.mx/gao/").addConverterFactory(GsonConverterFactory.create()).build();
        API_SERVICE = retrofit.create(NetworkService.class);
        return API_SERVICE;
    }
}
