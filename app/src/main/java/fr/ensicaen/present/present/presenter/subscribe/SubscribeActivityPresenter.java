package fr.ensicaen.present.present.presenter.subscribe;

import android.view.View;

import fr.ensicaen.present.present.view.subscribe.ISubscribeView;

/**
 * Created by leymarie on 07/03/18.
 */

public class SubscribeActivityPresenter implements ISubscribePresenter {
    private ISubscribeView _view;

    public SubscribeActivityPresenter(ISubscribeView view){
        _view = view;
    }
}
