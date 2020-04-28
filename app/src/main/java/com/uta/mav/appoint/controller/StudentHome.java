package com.uta.mav.appoint.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.uta.mav.appoint.R;
import com.uta.mav.appoint.util.Session;

public class StudentHome extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        button=findViewById(R.id.btn_logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session=new Session(StudentHome.this);
                session.logout();
                startActivity(new Intent(StudentHome.this, LoginActivity.class));
            }
        });
    }
}
