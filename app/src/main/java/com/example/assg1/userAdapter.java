package com.example.assg1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

public class userAdapter extends ArrayAdapter<user> {
    public  userAdapter(Context context, ArrayList<user> users){
        super(context,0,users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        user User = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView tvTask = (TextView) convertView.findViewById(R.id.task);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.description);

        tvTask.setText(User.task);
        tvDescription.setText(User.description);

        return convertView;
    }

}
