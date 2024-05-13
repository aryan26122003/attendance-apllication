package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SessionEditor extends AppCompatActivity {

    private int classID;

    private Database database;
    private int sessionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_session_editor);
        StrictMode.ThreadPolicy policy = new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView id = findViewById(R.id.id);
        EditText subject = findViewById(R.id.subject);
        EditText date = findViewById(R.id.date);
        Button sumbit = findViewById(R.id.sumbit);
        TextView delete = findViewById(R.id.delete);
        TextView editStudents = findViewById(R.id.editStudent);

        classID = getIntent().getIntExtra("Class ID",0);

        database = new Database();

        if (getIntent().hasExtra("Session ID")) {

            delete.setVisibility(View.VISIBLE);
            editStudents.setVisibility(View.VISIBLE);
            sessionID = getIntent().getIntExtra("Session ID", sessionID);
            id.setText(String.valueOf(sessionID));
            Session session = database.getSession(classID, sessionID);
            subject.setText(session.getSubject());
            date.setText(session.getDate());


            sumbit.setOnClickListener(v -> {
                session.setSubject(subject.getText().toString());
                session.setDate(date.getText().toString());
                database.updateSessionDate(classID,session);
                Toast.makeText(this, "Session update successfully",Toast.LENGTH_SHORT).show();
                finish();
            });

            delete.setOnClickListener(v -> {
                database.deleteSessionByID(classID, sessionID);
                Toast.makeText(this, "Session deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            });


            editStudents.setOnClickListener(v -> {
                Intent intent = new Intent(SessionEditor.this, EditSessionStudent.class);
                intent.putExtra("Class ID",classID);
                intent.putExtra("Session",sessionID);
                startActivity(intent);
                finish();
            });
        }else{
                sessionID = database.getNextSessionID(classID);
                id.setText(String.valueOf(sessionID));

                sumbit.setOnClickListener(v -> {
                    Session session = new Session();
                    session.setID(sessionID);
                    session.setSubject(subject.getText().toString());
                    session.setDate(date.getText().toString());
                    database.addSession(classID, session);
                    Toast.makeText(this,"Session added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                });
        }





    }
}