package fr.ensicaen.present.present.v2layouttests;


import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.ensicaen.present.present.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        setCustomActionBar(this);
    }

    private void initializeActivity() {
        setTheme(R.style.AppTheme);

       /* only for inital login activity
       getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);*/
/*
        setContentView(R.layout.v2_entercode);
*/
        setContentView(R.layout.v2_take_attendence);

        setActiveButton();
    }

    //@todo need to refactor
    private void setCustomActionBar(AppCompatActivity activity) {
        TextView textView = new TextView(activity);
        textView.setText(getSupportActionBar().getTitle());
        textView.setTextColor(getResources().getColor(R.color.v2_colorPrimary));
        textView.setTextSize(27);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Painter_PERSONAL_USE_ONLY.ttf");
        textView.setTypeface(font);
        activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        activity.getSupportActionBar().setCustomView(textView);


        //this is just for the title of the card...this has to be done elsewhere
       /*
        TextView responseTitle = findViewById(R.id.present_message_title);
        responseTitle.setTextColor(getResources().getColor(R.color.v2_colorPrimary));
        responseTitle.setTypeface(font);
        responseTitle.setTextSize(40);
        */


    }

    private void setActiveButton() {
        ImageView signalPresenceButtonView = findViewById(R.id.attendance_button);
        TextView buttonText = findViewById(R.id.attendence_button_text);
        buttonText.setTextColor(getResources().getColor(R.color.v2_colorPrimary));
        signalPresenceButtonView.setColorFilter(getResources().getColor(R.color.v2_colorPrimary), PorterDuff.Mode.SRC_IN);
    }

}
