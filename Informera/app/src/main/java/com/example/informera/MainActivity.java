package com.example.informera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signupButton1 = (Button) findViewById(R.id.signupButton1);
        signupButton1.setOnClickListener(this);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        ProgressBar progressBar = findViewById(R.id.progressBar);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupButton1:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.loginButton:
                startActivity(new Intent(this, LoggingIn.class));
                break;
        }
    }

}