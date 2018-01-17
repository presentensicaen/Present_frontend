package fr.ensicaen.present.present.dashboard;

/**
 * Created by jueast on 09/12/17.
 */

public interface IDashboardPresenter {

    void onDestroy();

    void onEnterCodeButtonClick();

    void onLaunchCallClick();

    void onAnswerCallClick();

    void onReviewOldCallsClick();

}
