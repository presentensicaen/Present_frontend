package fr.ensicaen.present.present.presenter.choosepreviouscall;
/* 
 *
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by The Présent ! Team and created by
 * Quentin DEBROISE, Julian EASTERLY, Jeanne LEYMARIE, Pierre NICOL, and Coline SMAGGHE.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.PreviousCallModel;
import fr.ensicaen.present.present.services.IPreviousCallReviewService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import fr.ensicaen.present.present.view.choosepreviouscall.IChoosePreviousCallView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class ChoosePreviousCallPresenter implements IChoosePreviousCallPresenter {

    private final IChoosePreviousCallView _view;
    private Config _config;
    private ArrayList<PreviousCallModel> _previousCalls;

    public ChoosePreviousCallPresenter(IChoosePreviousCallView view){
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadCallResults(int id) {

    }

    @Override
    public void loadPreviousCalls() {
        IPreviousCallReviewService service = ServiceFactory
                .createRetrofitService(IPreviousCallReviewService.class, _config.property("API_URL"));

        //@TODO transform into a get ?
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", "random id for the moment");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        service.getPreviousCalls(obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onLoadPrevousCallsStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handlePrevousCallLoadSuccessResponse,
                        this::handlePrevousCallLoadErrorResponse);


    }

    @Override
    public void onPrevousCallClick(PreviousCallModel previousCall) {
        _view.goToReviewActivity(previousCall);
    }

    public void onVerificationComplete() {
        _view.hideLoadingAnimation();
        if(_previousCalls != null){
            _view.showPreviousCalls(_previousCalls);
        }
    }

    private void onLoadPrevousCallsStart(Disposable disposable) {
        _view.showLoadingAnimation();
    }

    private void handlePrevousCallLoadSuccessResponse(ApiResponseModel<ArrayList<PreviousCallModel>> response) {
        _previousCalls = response.getData();
    }

    private void handlePrevousCallLoadErrorResponse(Throwable throwable) {
        _view.hideLoadingAnimation();
        _view.showToast("Erreur " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }
}
