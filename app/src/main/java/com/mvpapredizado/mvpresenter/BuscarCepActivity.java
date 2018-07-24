package com.mvpapredizado.mvpresenter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarCepActivity extends AppCompatActivity implements BuscarCepPresenter.ViewImpl {

    private Button btnBuscarCep;
    private EditText edtCep;
    private BuscarCepPresenter.PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cep);
        initComponentes();
        setPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.buscarCep(edtCep.getText().toString());
            }
        });
    }

    public void initComponentes() {
        btnBuscarCep = findViewById(R.id.btnBuscarCep);
        edtCep = findViewById(R.id.edtCep);
    }

    @Override
    public void showToastCepVazio() {
        Toast.makeText(this, "CEP vazio!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarAlertComInformacoesCep(String resultado) {
        createAlertDialog(resultado);
    }

    @Override
    public void mostrarAlertComCepInvalido() {
        createAlertDialog("Cep Invalido");
    }

    public void setPresenter() {
        this.presenter = new BucarCepImplPresenter(BuscarCepActivity.this);

    }

    public void createAlertDialog(String resultado){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Resultado");
        builder1.setMessage(resultado);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
