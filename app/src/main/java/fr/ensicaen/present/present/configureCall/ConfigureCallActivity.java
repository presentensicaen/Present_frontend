package fr.ensicaen.present.present.configureCall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;

public class ConfigureCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_call);


        Button button1 = (Button)findViewById(R.id.launchCall);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                findViewById(R.id.code_result_mesg_container).setVisibility(View.VISIBLE);
            }
        });
    }


}
