package fr.ensicaen.present.present.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.generateCode.GenerateCodeActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button button1 = (Button)findViewById(R.id.launchCall);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, GenerateCodeActivity.class);
                startActivity(intent);
            }
        });
    }
}
