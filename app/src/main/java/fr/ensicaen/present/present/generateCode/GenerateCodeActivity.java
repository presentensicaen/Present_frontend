package fr.ensicaen.present.present.generateCode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.selectGroups.SelectGroups;
import fr.ensicaen.present.present.configureCall.ConfigureCallActivity;

public class GenerateCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);

        Button button1 = (Button)findViewById(R.id.generate_without_group_button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(GenerateCodeActivity.this, ConfigureCallActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.choose_groupe_button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1){
                Intent intent = new Intent(GenerateCodeActivity.this, SelectGroups.class);
                startActivity(intent);
            }
        });

    }
}
