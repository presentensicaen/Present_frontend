package fr.ensicaen.present.present.presenter.choose_call_type;

import fr.ensicaen.present.present.view.choose_call_type.IChooseCallTypeView;

/**
 * Created by Jeanne on 25/12/2017.
 */

public class ChooseCallType implements IChooseCallType {

    private IChooseCallTypeView _view;

    public ChooseCallType(IChooseCallTypeView view) {
        _view = view;
    }
}
