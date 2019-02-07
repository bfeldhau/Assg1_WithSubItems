package com.example.assg1;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
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
    CheckBox checkBox;
    Button btnDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        //test
        if (arrayOfTask == null){
            createlist();
        }

        btnDel = (Button) findViewById(R.id.delete);
        btnAdd = (Button) findViewById(R.id.button);
        taskText = (EditText) findViewById(R.id.taskText);
        descriptionText = (EditText) findViewById(R.id.descriptionText);
        checkBox = (CheckBox) findViewById(R.id.checkbox);


        adapter = new userAdapter(this, arrayOfTask);

        listView = (ListView) findViewById(R.id.results_listview);
        listView.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = taskText.getText().toString();
                String descrip = descriptionText.getText().toString();

                newTask = new user(task, descrip);
                adapter.add(newTask);
                adapter.notifyDataSetChanged();
                taskText.getText().clear();
                descriptionText.getText().clear();
                saveData();
                saveOut();
            }
        });

        //****end of btnAdd***\\\

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                arrayOfTask.remove(position);
                adapter.notifyDataSetChanged();
                saveData();
                return true;

            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    for (int i = 0; i < 5; i++) {
                        arrayOfTask.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


/// end of on create
    }



    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayOfTask);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<user>>() {}.getType();
        arrayOfTask = gson.fromJson(json, type);

    }

    private void createlist(){
        arrayOfTask = new ArrayList<user>();
    }

    public void saveOut(){
        String FileName = "results.txt";
        Gson gson = new Gson();
        String json = gson.toJson(arrayOfTask);
        //test
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FileName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(json.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///this is end of main activity
}