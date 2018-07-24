package com.mvpapredizado.mvpresenter;

/**
 * Created by rodrigo on 24/07/18.
 */
public interface BuscarCepPresenter {

    interface PresenterImpl{
        void buscarCep(String cep);

    }

    interface ViewImpl{
        void showToastCepVazio();
        void mostrarAlertComInformacoesCep(String resultado);
        void mostrarAlertComCepInvalido();
    }
}
