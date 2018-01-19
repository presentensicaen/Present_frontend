package fr.ensicaen.present.present.generate_code;

/**
 * Created by Jeanne on 25/12/2017.
 */

public class GenerateCodePresenter implements IGenerateCodePresenter {

    private IGenerateCodeView _view;

    public GenerateCodePresenter(IGenerateCodeView view) {
        _view = view;
    }
}
