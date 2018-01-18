package fr.ensicaen.present.present.enterCode;

/**
 * Created by Jeanne on 14/12/2017.
 */

interface IEnterCodePresenter {
    void onEnterCodeButtonClick(String id, String code);

    boolean getMessage();

    void verifyCode(String id, String code);
}
