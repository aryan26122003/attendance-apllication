package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowSessions extends AppCompatActivity {

    private int classID;
    private Database database;
    private ArrayList<Session> sessions;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_show_sessions);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView listView = findViewById(R.id.listview);
        classID = getIntent().getIntExtra("Claas ID",0);
        database = new Database();
        sessions =  database.getSessions(classID);
        listAdapter = new ListAdapter();
        listView.setAdapter(listAdapter);


        Button new_session = findViewById(R.id.new_session);
        new_session.setOnClickListener(v -> {
            Intent intent = new Intent(ShowSessions.this,SessionEditor.class);
            intent.putExtra("Class ID", classID);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessions = database.getSessions(classID);
        listAdapter.notifyDataSetChanged();
    }

//    @Override
    private class ListAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return sessions.size();

        }
        @Override
        public Object getItem(int i){
            return  sessions.get(i);

        }
        @Override
        public long getItemId(int i){
            return i;

        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

//            @Override
        public View getview(int i , View view, ViewGroup viewGroup){
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint("InflateParams")
            View v = inflater.inflate(R.layout.list_item ,null);
            TextView text = v.findViewById(R.id.text);
            text.setText(sessions.get(i).getSubject()+" - "+sessions.get(i).getSubject());

            text.setOnLongClickListener(v1 -> {
                Intent intent = new Intent(ShowSessions.this , SessionEditor.class);
                intent.putExtra("Class ID", classID);
                intent.putExtra("Session ID", sessions.get(i).getID());
                startActivity(intent);
                return true;
            });
            text.setOnClickListener(v1 -> {
                Intent intent = new Intent(ShowSessions.this, ViewSession.class);
                intent.putExtra("Class ID", classID);
                intent.putExtra("Session ID", sessions.get(i).getID());
                startActivity(intent);
            });
            return v;

        }
    }
}