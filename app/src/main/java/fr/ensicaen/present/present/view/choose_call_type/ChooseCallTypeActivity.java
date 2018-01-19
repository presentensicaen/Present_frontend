package fr.ensicaen.present.present.view.choose_call_type;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.choose_call_type.ChooseCallType;
import fr.ensicaen.present.present.presenter.choose_call_type.IChooseCallType;
import fr.ensicaen.present.present.view.launch_call.LaunchCallActivity;
import fr.ensicaen.present.present.view.select_group.SelectGroupActivity;


public class ChooseCallTypeActivity extends Activity implements IChooseCallTypeView {

    private IChooseCallType _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new ChooseCallType(this);
        initializeLayoutComponents();
        initializeGenerateCodeActivity();
    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_choose_call_type);
    }

    public void initializeGenerateCodeActivity() {
        Button button1 = findViewById(R.id.generate_without_group_button);
        button1.setOnClickListener(v -> {
            Intent intent = new Intent(ChooseCallTypeActivity.this, LaunchCallActivity.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.choose_groupe_button);
        button2.setOnClickListener(v1 -> {
            Intent intent = new Intent(ChooseCallTypeActivity.this, SelectGroupActivity.class);
            startActivity(intent);
        });
    }
}
