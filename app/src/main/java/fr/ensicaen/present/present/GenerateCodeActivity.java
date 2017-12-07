package fr.ensicaen.present.present;

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

        String str = getString(R.string.header_generate_code);
        String str2 = getString(R.string.empty_text);

        TextView header = (TextView) findViewById(R.id.header_generate_code);
        header.setText(str);

        TextView body = (TextView) findViewById(R.id.empty_text);
        body.setText(str2);

        Button button1 = (Button)findViewById(R.id.button_id1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                System.out.println("code");
            }
        });

        Button button2 = (Button) findViewById(R.id.button_id2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                System.out.println("groupe");
            }
        });

    }
}
