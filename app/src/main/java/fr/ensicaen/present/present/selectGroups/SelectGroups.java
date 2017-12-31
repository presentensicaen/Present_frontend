package fr.ensicaen.present.present.selectGroups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.configureCall.ConfigureCallActivity;


public class SelectGroups extends AppCompatActivity {
    ListView listview;
    String[] foody;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_groups);


        context = this;
        listview = (ListView)findViewById(R.id.listGroup);
        //string array
        String[] foody = {"pizza", "burger", "chocolate", "ice-cream", "banana", "apple"};
        // set adapter for listview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, foody);
        listview.setAdapter(adapter);
        listview.setItemsCanFocus(false);
        // we want multiple clicks
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        Button button1 = (Button) findViewById(R.id.generateCode);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1){
                Intent intent = new Intent(SelectGroups.this, ConfigureCallActivity.class);
                startActivity(intent);
            }
        });
    }
}
