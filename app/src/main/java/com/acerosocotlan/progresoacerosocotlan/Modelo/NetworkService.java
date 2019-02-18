package com.acerosocotlan.progresoacerosocotlan.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

import com.acerosocotlan.progresoacerosocotlan.Controlador.VerOferta;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Saul on 15/02/2018.
 */

public interface NetworkService {
    @POST
    Call<List<StatuEntrega>> EstatusEntrega(@Url String url);
    @POST
    Call<List<DetalleEntrega_retrofit>> detalleEntrega(@Url String url);

    @FormUrlEncoded
    @POST
    Call<List<VerOfertas_retrofit>> VerOfEntrega(@Url String url, @Field("rastreo") String codigorastreoRespuesta);

    @FormUrlEncoded
    @POST
    Call<List<String>> EnviarRespuestas(
                                     @Url String url,
                                     @Field("codigorastreo") String codigorastreoRespuesta,
                                     @Field("vendedor") String vendedorRespuesta,
                                     @Field("chofer") String choferRespuesta,
                                     @Field("material") String materialRespuesta,
                                     @Field("tiempo") String tiempoRespuesta,
                                     @Field("aplicacion") String aplicacionRespuesta,
                                     @Field("movil") String tipo_movil);
    @FormUrlEncoded
    @POST
    Call<Factura_retrofit> EnviarFactura(@Url String fileUrl, @Field("rastreo") String codigoRastreo);

    @FormUrlEncoded
    @POST
    Call<DirectorioTelefonos> SolicitarTelefono(@Url String fileUrl, @Field("rastreo") String codigoRastreo);

    @FormUrlEncoded
    @POST
    Call<Prueba_retrofit> Solicitarprueba(@Url String fileUrl,
                                              @Field("user") String user,
                                              @Field("pass") String pass);

    @FormUrlEncoded
    @POST
    Call<List<Historial_retrofit>> HistorialEntrega(@Url String fileUrl, @Field("rastreo") String codigoRastreo);

    @FormUrlEncoded
    @POST
    Call<AcuseRecibo_retrofit> AcuseReciboEntrega(@Url String fileUrl, @Field("rastreo") String codigoRastreo);

    @FormUrlEncoded
    @POST
    Call<AcuseRecibo_retrofit> VerAcuseRecibo(@Url String fileUrl, @Field("rastreo") String codigoRastreo);
}