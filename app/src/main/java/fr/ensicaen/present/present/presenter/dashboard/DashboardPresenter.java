package fr.ensicaen.present.present.presenter.dashboard;

import android.util.Log;

import fr.ensicaen.present.present.view.dashboard.IDashboardView;

/**
 * Created by jueast on 09/12/17.
 */

public class DashboardPresenter implements IDashboardPresenter {


    private final IDashboardView _view;

    public DashboardPresenter(IDashboardView view) {
        _view = view;
    }


    @Override
    public void onLaunchCallClick() {
        _view.goToGenerateCode();
    }

    @Override
    public void onAnswerCallClick() {
        _view.goToEnterCode();
    }

    @Override
    public void onReviewOldCallsClick() {
        _view.goToReviewCall();
    }
}
