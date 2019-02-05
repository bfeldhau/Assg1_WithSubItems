package com.example.assg1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    user newTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<user> arrayOfTask = new ArrayList<user>();
        final userAdapter adapter = new userAdapter(this, arrayOfTask);
        ListView listView = (ListView) findViewById(R.id.results_listview);
        listView.setAdapter(adapter);
        newTask = new user("What", "wowowow");
        adapter.add(newTask);

        /*btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //adapter.add(newTask);

            }
        });*/






    }
}
