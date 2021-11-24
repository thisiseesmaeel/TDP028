package com.example.informera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggingIn extends AppCompatActivity implements  View.OnClickListener {

    private EditText editTextTextEmailAddress, editTextTextPassword;

    private Button loginButton;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging_in);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        progressBar3 = findViewById(R.id.progressBar3);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                userLogin();
                break;
        }
    }


    //VALIDATIONS OF A USER WHEN SIGNING IN.
    private void userLogin() {
        String email = editTextTextEmailAddress.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextTextEmailAddress.setError("Email is required!");
            editTextTextEmailAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextEmailAddress.setError("Please enter a valid email!");
            editTextTextEmailAddress.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextTextPassword.setError("Password is required!");
            editTextTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6) {
            editTextTextPassword.setError("Minimum password length is 6 characters!");
            editTextTextPassword.requestFocus();
            return;
        }


        progressBar3.setVisibility(View.GONE);


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        //Skickar tillbaka till användarprofilen.
                        startActivity(new Intent(LoggingIn.this, ProfileActivity.class));
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(LoggingIn.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoggingIn.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}