package fr.ensicaen.present.present.enterCode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodeActivity extends Activity implements IEnterCodeView {
    private IEnterCodePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeLayoutComponents();
        initializeEnterCodeActivity();
    }
    public void initializeEnterCodeActivity() {
        Button button1 = (Button)findViewById(R.id.enter_code);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.success_message_container).setVisibility(View.VISIBLE);
            }
        });
    }

    public void initializeLayoutComponents(){
        setContentView(R.layout.activity_enter_code);
    }

}
