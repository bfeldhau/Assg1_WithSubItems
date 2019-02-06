package com.example.assg1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    EditText taskText;
    EditText descriptionText;
    ListView listView;
    ArrayList<user> arrayOfTask;
    userAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.button);
        taskText = (EditText) findViewById(R.id.taskText);
        descriptionText = (EditText) findViewById(R.id.descriptionText);

        arrayOfTask = new ArrayList<user>();
        adapter = new userAdapter(this, arrayOfTask);

        listView = (ListView) findViewById(R.id.results_listview);
        listView.setAdapter(adapter);
        newTask = new user("What", "wowowow");
        adapter.add(newTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = taskText.getText().toString();
                String descrip = descriptionText.getText().toString();

                newTask = new user(task, descrip);
                //test
                adapter.add(newTask);
                adapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayOfTask.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


    }
}
