package fr.ensicaen.present.present.view.choosepreviouscall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.adapters.PrevousCallListAdapter;
import fr.ensicaen.present.present.models.PreviousCallModel;
import fr.ensicaen.present.present.presenter.choosepreviouscall.ChoosePreviousCallPresenter;
import fr.ensicaen.present.present.presenter.choosepreviouscall.IChoosePreviousCallPresenter;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.reviewcall.ReviewCallActivity;

public class ChoosePreviousCallActivity extends Activity implements  IChoosePreviousCallView {

    private ListView _listview;
    private IChoosePreviousCallPresenter _presenter;
    private ViewGroup _loadingAnimation;
    public static final String PREVIOUS_CALL_CODE = "PREVOUS_CALL_MODEL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLayoutComponents();

        _presenter = new ChoosePreviousCallPresenter(this);
        _presenter.loadPreviousCalls();

        setListViewItemListener();

    }

    private void setListViewItemListener() {
        _listview.setOnItemClickListener((parent, view, position, id) -> {
            Object obj = _listview.getItemAtPosition(position);
            PreviousCallModel previousCall = (PreviousCallModel) obj;
            _presenter.onPrevousCallClick(previousCall);
        });
    }


    private void initializeLayoutComponents() {
        setContentView(R.layout.activity_choose_previous_call);
        _loadingAnimation = findViewById(R.id.loading_animation);
        _listview = findViewById(R.id.listGroup);
    }


    @Override
    public void showLoadingAnimation() {
        _loadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message,toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    @Override
    public void showPreviousCalls(ArrayList<PreviousCallModel> prevousCalls) {
        _listview.setAdapter(new PrevousCallListAdapter(this, prevousCalls, R.layout.select_previous_call_item));
        _listview.setItemsCanFocus(false);
        _listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void goToReviewActivity(PreviousCallModel model) {
        Intent reviewItent = new Intent(ChoosePreviousCallActivity.this, ReviewCallActivity.class);
        reviewItent.putExtra(PREVIOUS_CALL_CODE, model.getCode());
        startActivity(reviewItent);
    }
}
