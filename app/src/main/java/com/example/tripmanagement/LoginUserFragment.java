package com.example.tripmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserFragment extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword;
    private Button loginButton;
    private EditText emailTxt, passwordTxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_fragment);
        register = (TextView) findViewById(R.id.registerTxt);
        register.setOnClickListener(this);

        loginButton = findViewById(R.id.forgotPasswordButton);
        loginButton.setOnClickListener(this);

        forgotPassword = (TextView) findViewById(R.id.forgotPasswordTxt);
        forgotPassword.setOnClickListener(this);

        emailTxt = findViewById(R.id.usernameTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerTxt:
                startActivity(new Intent(this, RegisterUserFragment.class));
                break;
            case R.id.forgotPasswordButton:
                userLogin();
                break;
            case R.id.forgotPasswordTxt:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }
    }

    private void userLogin() {
        String email = emailTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();

        if (email.isEmpty()) {
            emailTxt.setError("Email is required!");
            emailTxt.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordTxt.setError("Password is required!");
            passwordTxt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError("Please insert valid email!");
            emailTxt.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordTxt.setError("Min password length should be 6 characters!");
            passwordTxt.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginUserFragment.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginUserFragment.this, "Fail to login, please check your info again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}