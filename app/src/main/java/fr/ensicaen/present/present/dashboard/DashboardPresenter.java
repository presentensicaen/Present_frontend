package fr.ensicaen.present.present.dashboard;

/**
 * Created by jueast on 09/12/17.
 */

public class DashboardPresenter implements IDashboardPresenter {


    private final IDashboardView _view;

    public DashboardPresenter(IDashboardView view) {
        _view = view;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onEnterCodeButtonClick() {

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

    }
}
