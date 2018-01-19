package fr.ensicaen.present.present.presenter.enter_code;

import fr.ensicaen.present.present.view.enter_code.IEnterCodeView;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodePresenter implements IEnterCodePresenter {

    private IEnterCodeView _view;

    public EnterCodePresenter(IEnterCodeView view) {
        _view = view;
    }

    public void sendCode() {

    }

    public boolean getMessage() {
        return false;
    }

}
