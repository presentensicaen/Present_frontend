package fr.ensicaen.present.present.generateCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.configureCall.ConfigureCallActivity;
import fr.ensicaen.present.present.selectGroups.SelectGroups;


public class GenerateCodeActivity extends Activity implements IGenerateCodeView {

    private IGenerateCodePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new GenerateCodePresenter(this);
        initializeLayoutComponents();
        initializeGenerateCodeActivity();
    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_generate_code);
    }

    public void initializeGenerateCodeActivity() {
        Button button1 = findViewById(R.id.generate_without_group_button);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(GenerateCodeActivity.this, ConfigureCallActivity.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.choose_groupe_button);
        button2.setOnClickListener(v1 -> {
            Intent intent = new Intent(GenerateCodeActivity.this, SelectGroups.class);
            startActivity(intent);
        });
    }
}
