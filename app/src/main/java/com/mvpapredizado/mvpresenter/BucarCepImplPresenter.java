package com.mvpapredizado.mvpresenter;

import android.app.ProgressDialog;

import com.google.gson.JsonObject;
import com.mvpapredizado.mvpresenter.API.APIBuscaCEP;
import com.mvpapredizado.mvpresenter.API.APIModulo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rodrigo on 24/07/18.
 */
public class BucarCepImplPresenter implements BuscarCepPresenter.PresenterImpl {
    ProgressDialog progressDoalog;
    private BuscarCepActivity buscarCepActivity;

    public BucarCepImplPresenter(BuscarCepActivity buscarCepActivity) {
        this.buscarCepActivity = buscarCepActivity;
    }

    @Override
    public void buscarCep(String cep) {

        if (cep.isEmpty()) {
            buscarCepActivity.showToastCepVazio();
        } else {
            createProgressDialog();
            APIBuscaCEP apiBuscaCEP = APIModulo.getService(APIBuscaCEP.class, "https://viacep.com.br/");
            Call<JsonObject> call = apiBuscaCEP.buscaCEP(cep);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        progressDoalog.dismiss();

                        if (response.body().get("erro") == null) {
                            buscarCepActivity.mostrarAlertComInformacoesCep(
                                    "Cidade: " + response.body().get("localidade").getAsString() + "\n" +
                                            "IBGE: " + response.body().get("ibge").getAsString());
                        }else {
                            buscarCepActivity.mostrarAlertComCepInvalido();
                        }

                    } else if (response.code() == 404)  {
                        progressDoalog.dismiss();
                        buscarCepActivity.mostrarAlertComCepInvalido();

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }

    }


    public void createProgressDialog() {
        progressDoalog = new ProgressDialog(buscarCepActivity);
        progressDoalog.setMessage("Um momento....");
        progressDoalog.setTitle("Buscando...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
    }
}
