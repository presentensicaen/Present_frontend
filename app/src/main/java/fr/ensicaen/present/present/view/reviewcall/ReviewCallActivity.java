package fr.ensicaen.present.present.view.reviewcall;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.adapters.PrevousCallUserListAdapter;
import fr.ensicaen.present.present.models.PreviousCallUserModel;
import fr.ensicaen.present.present.presenter.reviewcall.IReviewCallPresenter;
import fr.ensicaen.present.present.presenter.reviewcall.ReviewCallPresenter;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.choosepreviouscall.ChoosePreviousCallActivity;


public class ReviewCallActivity extends Activity implements  IReviewCallView {

    private String _previousCallCode;
    private ListView _listview;
    private ViewGroup _loadingAnimation;
    private IReviewCallPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLayout();
        _presenter = new ReviewCallPresenter(this);
        _presenter.loadConcernedUserList(_previousCallCode);
    }

    private void initializeLayout() {
        setContentView(R.layout.activity_review_call);
        _previousCallCode = getIntent().getStringExtra(ChoosePreviousCallActivity.PREVIOUS_CALL_CODE);
        _loadingAnimation = findViewById(R.id.loading_animation);
    }

    @Override
    public void loadList(ArrayList<PreviousCallUserModel> users) {
        _listview = findViewById(R.id.listGroup);
        _listview.setAdapter(
                new PrevousCallUserListAdapter(this, users, R.layout.prev_call_user_item)
        );
        _listview.setItemsCanFocus(false);
        _listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
        Toast.makeText(this, message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }
}
