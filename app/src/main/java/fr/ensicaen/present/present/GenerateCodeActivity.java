package fr.ensicaen.present.present;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GenerateCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        Button button1 = (Button)findViewById(R.id.button_id1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(GenerateCodeActivity.this, ConfigureCall.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button_id2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1){
                Intent intent = new Intent(GenerateCodeActivity.this, SelectGroups.class);
                startActivity(intent);
            }
        });

    }
}
