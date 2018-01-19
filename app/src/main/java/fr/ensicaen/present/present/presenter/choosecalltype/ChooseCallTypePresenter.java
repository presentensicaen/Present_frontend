package fr.ensicaen.present.present.presenter.choosecalltype;

import fr.ensicaen.present.present.view.choosecalltype.IChooseCallTypeView;

/**
 * Created by Jeanne on 25/12/2017.
 */

public class ChooseCallTypePresenter implements IChooseCallTypePresenter {

    private IChooseCallTypeView _view;

    public ChooseCallTypePresenter(IChooseCallTypeView view) {
        _view = view;
    }

    @Override
    public void onLaunchCallClick() {
        _view.goToLaunchCall();
    }

    @Override
    public void onSelectGroupClick() {
        _view.goToSelectGroup();
    }

}
