package fr.ensicaen.present.present.view.selectgroup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.launchcall.LaunchCallActivity;


public class SelectGroupActivity extends Activity implements ISelectGroupView {
    ListView _listview;
    Context _context;
    private ViewGroup _loadingAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_group);


        _context = this;
        _listview = findViewById(R.id.listGroup);
        //string array
        String[] foody = {"TP1 info 2A", "TP2 info 2A", "TD A info 2A", "TD B info 2A", "promo info 2A"};
        // set adapter for _listview
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.select_group_item, foody);
        _listview.setAdapter(adapter);
        _listview.setItemsCanFocus(false);
        // we want multiple clicks
        _listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        _loadingAnimation = findViewById(R.id.loading_animation);

        Button button1 = findViewById(R.id.generateCode);
        button1.setOnClickListener(v1 -> {
            Intent intent = new Intent(SelectGroupActivity.this, LaunchCallActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void showLoadingAnimation() { _loadingAnimation.setVisibility(View.VISIBLE); }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }
}
