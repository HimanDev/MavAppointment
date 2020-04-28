package com.uta.mav.appoint.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uta.mav.appoint.R;
import com.uta.mav.appoint.dto.User;
import com.uta.mav.appoint.http.LoginService;
import com.uta.mav.appoint.util.Session;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button login;
    LoginService loginService;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session=new Session(this);
        if(!session.get(Session.KEY_ROLE).isEmpty()){
            Intent homepage = new Intent(LoginActivity.this, StudentHome.class);
            startActivity(homepage);
        }
        loadUiElements();
        loginService=new LoginService(this);
        setListeners();
    }

    private void setListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname= String.valueOf(userName.getText());
                String pass= String.valueOf(password.getText());
                loginService.validateUser(uname,pass, new User.UserResponse() {
                    @Override
                    public void addUser(User user) {
                       if(user!=null){
                           session.setUser(user);
                           Intent homepage = new Intent(LoginActivity.this, StudentHome.class);
                           startActivity(homepage);
                       }else {
                           Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                       }
                    }
                });
               // loginService.validateUser(uname,pass);
            }
        });
    }

    private void loadUiElements() {
        userName=(EditText)findViewById(R.id.username);
        userName.setText("javieralexcastro95@gmail.com");
        password=(EditText)findViewById(R.id.password);
        password.setText("5dum3vj5dp");
        login=(Button)findViewById(R.id.login);
    }

    public void startRegistration(View view)
    {
        startActivity(new Intent( LoginActivity.this, Register.class));
    }

}
