package fr.ensicaen.present.present.presenter.reviewcall;
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

import android.os.Build;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.PreviousCallUserModel;
import fr.ensicaen.present.present.services.IPreviousCallReviewService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import fr.ensicaen.present.present.view.reviewcall.IReviewCallView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class ReviewCallPresenter implements IReviewCallPresenter{

    private IReviewCallView _view;
    private Config _config;
    private ArrayList<PreviousCallUserModel> _users;

    public ReviewCallPresenter(IReviewCallView view) {
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadConcernedUserList(String callCode) {
        IPreviousCallReviewService service = ServiceFactory
                .createRetrofitService(IPreviousCallReviewService.class, _config.property("API_URL"));

        //@TODO transform into a get ?
        service.getConcernedUsers(createPayload(callCode))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onLoadUsersStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoadUsersSuccess,
                        this::handleLoadUsersError);

    }

    private void onLoadUsersStart(Disposable disposable) {
        _view.showLoadingAnimation();
    }

    private void onVerificationComplete() {
        if(_users != null) {
            _view.loadList(_users);
            _view.hideLoadingAnimation();
        }

    }


    private void handleLoadUsersSuccess(ApiResponseModel<ArrayList<PreviousCallUserModel>> arrayListApiResponseModel) {
        _users = arrayListApiResponseModel.getData();
    }


    private boolean imageUrlPresent(PreviousCallUserModel user) {
        return user.getPhotoUrl() != null && !"".equals(user.getPhotoUrl());
    }

    private boolean sdkVersionIsHighEnough() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    private void handleLoadUsersError(Throwable throwable) {
        _view.hideLoadingAnimation();
        _view.showToast("Erreur " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }

    private JSONObject createPayload(String callCode) {
        JSONObject data = new JSONObject();
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", callCode);
            data.put("data", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
