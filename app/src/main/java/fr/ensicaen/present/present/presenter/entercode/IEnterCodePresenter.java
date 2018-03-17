package fr.ensicaen.present.present.presenter.entercode;

/**
 * Created by Jeanne on 14/12/2017.
 */

public interface IEnterCodePresenter {
    void sendCode(String text);

    boolean getMessage();

    void onPermissionResponseReceieved(int requestCode, int[] grantResults);
}
