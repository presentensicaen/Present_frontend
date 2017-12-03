package fr.ensicaen.present.present;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfigureCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_call);


        Button button1 = (Button)findViewById(R.id.launchCall);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                findViewById(R.id.successCall).setVisibility(View.VISIBLE);
            }
        });
    }


}
