package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//public class StudentEditor extends AppCompatActivity {
//    private int classID;
//    private Database database;
//    private int studentID;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        setContentView(R.layout.activity_student_editor);
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        TextView id = findViewById(R.id.id);
//        EditText firstName = findViewById(R.id.firstName);
//        EditText lastname = findViewById(R.id.lastName);
//        EditText email = findViewById(R.id.email);
//        EditText tel = findViewById(R.id.tel);
//        Button sumbit = findViewById(R.id.sumbit);
//        TextView delete = findViewById(R.id.delete);
//
//        classID = getIntent().getIntExtra( "classID",0);
//
//        database = new Database();
//
//        if (getIntent().hasExtra("Student ID"));{
//            delete.setVisibility(View.VISIBLE);
//            studentID =  getIntent().getIntExtra("StudentID" , 0 );
//            Student student = database.getStudent(classID , studentID);
//            id.setText(String.valueOf(student.getID()));
//            firstName.setText(student.getFirstName());
//            lastname.setText(student.getLastName());
//            email.setText(student.getEmail());
//            tel.setText(student.getTel());
//            sumbit.setOnClickListener(v -> {
//                student.setFirstName(firstName.getText().toString());
//                student.setLastName(lastname.getText().toString());
//                student.setEmail(email.getText().toString());
//                student.setTel(tel.getText().toString());
//                database.updateStudent(classID, student);
//                Toast.makeText(this,"Student update succesfully"
//                , Toast.LENGTH_SHORT).show();
//
//                finish();
//
//
//            });
//
//            delete.setOnClickListener(v -> {
//                database.deleteStudent(classID , studentID);
//                Toast.makeText(this, "Student deleted successfully"
//                , Toast.LENGTH_SHORT).show();
//                finish();
//            });
//        }else {
//            studentID = database.getNextStudentID(classID);
//            id.setText(String.valueOf(studentID));
//
//
//            sumbit.setOnClickListener(v -> {
//                Student student = new Student();
//                student.setID(studentID);
//                student.setFirstName(firstName.getText().toString());
//                student.setLastName(lastname.getText().toString());
//                student.setEmail(email.getText().toString());
//                student.setTel(tel.getText().toString());
//                database.addStudent();
//
//            });
//
//        }
//    }
//}


public class StudentEditor extends AppCompatActivity {
    private int classID;
    private Database database;
    private int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_student_editor);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView id = findViewById(R.id.id);
        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);
        EditText tel = findViewById(R.id.tel);
        Button sumbit = findViewById(R.id.sumbit);
        TextView delete = findViewById(R.id.delete);

        classID = getIntent().getIntExtra("classID", 0);

        database = new Database();

        if (getIntent().hasExtra("Student ID")) {
            delete.setVisibility(View.VISIBLE);
            studentID = getIntent().getIntExtra("Student ID", 0);
            Student student = database.getStudent(classID, studentID);
            id.setText(String.valueOf(student.getID()));
            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
            email.setText(student.getEmail());
            tel.setText(student.getTel());

            sumbit.setOnClickListener(v -> {
                student.setFirstName(firstName.getText().toString());
                student.setLastName(lastName.getText().toString());
                student.setEmail(email.getText().toString());
                student.setTel(tel.getText().toString());
                database.updateStudent(classID, student);
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            });

            delete.setOnClickListener(v -> {
                database.deleteStudent(classID, studentID);
                Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        } else {
            studentID = database.getNextStudentID(classID);
            id.setText(String.valueOf(studentID));

            sumbit.setOnClickListener(v -> {
                Student student = new Student();
                student.setID(studentID);
                student.setFirstName(firstName.getText().toString());
                student.setLastName(lastName.getText().toString());
                student.setEmail(email.getText().toString());
                student.setTel(tel.getText().toString());
                database.addStudent(classID, student); // Missing classID argument here
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }
}
