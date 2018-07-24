package com.mvpapredizado.mvpresenter.API;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rodrigo on 24/07/18.
 */
public interface APIBuscaCEP {
    @GET("ws/{cep}/json/")
    Call<JsonObject> buscaCEP(@Path("cep") String cep);
}
