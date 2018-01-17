package fr.ensicaen.present.present.generateCode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.configureCall.ConfigureCallActivity;
import fr.ensicaen.present.present.selectGroups.SelectGroups;


public class GenerateCodeActivity extends AppCompatActivity implements IGenerateCodeView {

    private IGenerateCodePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new GenerateCodePresenter(this);
        initializeLayoutComponents();
        initializeGenerateCodeActivity();
    }

    public void initializeLayoutComponents (){
        setContentView(R.layout.activity_generate_code);
    }

    public void initializeGenerateCodeActivity(){
        Button button1 = (Button)findViewById(R.id.generate_without_group_button);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(GenerateCodeActivity.this, ConfigureCallActivity.class);
            startActivity(intent);
        });

        Button button2 = (Button) findViewById(R.id.choose_groupe_button);
        button2.setOnClickListener(v1 -> {
            Intent intent = new Intent(GenerateCodeActivity.this, SelectGroups.class);
            startActivity(intent);
        });
    }
}
