package fr.ensicaen.present.present.view.choosecalltype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.choosecalltype.ChooseCallTypePresenter;
import fr.ensicaen.present.present.presenter.choosecalltype.IChooseCallTypePresenter;
import fr.ensicaen.present.present.view.launchcall.LaunchCallActivity;
import fr.ensicaen.present.present.view.selectgroup.SelectGroupActivity;


public class ChooseCallTypeActivity extends Activity implements IChooseCallTypeView {

    private IChooseCallTypePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new ChooseCallTypePresenter(this);
        initializeLayoutComponents();
        initializeGenerateCodeActivity();
    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_choose_call_type);
    }

    public void initializeGenerateCodeActivity() {
        Button button1 = findViewById(R.id.generate_without_group_button);
        button1.setOnClickListener(v -> _presenter.onLaunchCallClick());

        Button button2 = findViewById(R.id.choose_groupe_button);
        button2.setOnClickListener(v1 -> _presenter.onSelectGroupClick());
    }

    @Override
    public void goToLaunchCall() {
        Intent intent = new Intent(ChooseCallTypeActivity.this, LaunchCallActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToSelectGroup() {
        Intent intent = new Intent(ChooseCallTypeActivity.this, SelectGroupActivity.class);
        startActivity(intent);
    }
}
