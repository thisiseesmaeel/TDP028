package com.example.informera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView create_acc_text, already_registered_text;
    private Button signupButton2;
    private EditText editTextTextPersonName, editTextTextEmailAddress, editTextTextPassword;
    private ProgressBar progressBar2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        //create_acc_text = (TextView) findViewById(R.id.create_acc_text);

        //already_registered_text = findViewById(R.id.loginButton);
        //already_registered_text.setOnClickListener(this);

        signupButton2 = (Button) findViewById(R.id.signupButton2);
        signupButton2.setOnClickListener(this);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        progressBar2 = findViewById(R.id.progressBar2);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.already_registered_text:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.signupButton2:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = editTextTextPersonName.getText().toString().trim();
        String email = editTextTextEmailAddress.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();

        if (name.isEmpty()) {
            editTextTextPersonName.setError("Make sure you have entered your name!");
            editTextTextPersonName.requestFocus();
        }
        if (email.isEmpty()) {
            editTextTextEmailAddress.setError("Make sure you have entered your email address!");
            editTextTextEmailAddress.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextTextEmailAddress.setError("Please enter a valid email address!");
            editTextTextEmailAddress.requestFocus();
        }
        if (password.isEmpty()) {
            editTextTextPassword.setError("Make sure you have entered your password!");
            editTextTextPassword.requestFocus();
        }
        if (password.length() < 6) {
            editTextTextPassword.setError("Password must be minimum 6 characters!");
            editTextTextPassword.requestFocus();
        }
    }
}

