package fr.ensicaen.present.present.view.reviewcall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.view.choosepreviouscall.ChoosePreviousCallActivity;


public class ReviewCallActivity extends Activity {

    private String _previousCallCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_call);
        _previousCallCode = getIntent().getStringExtra(ChoosePreviousCallActivity.PREVIOUS_CALL_CODE);
        Toast.makeText(this, _previousCallCode, Toast.LENGTH_LONG).show();

    }

}
