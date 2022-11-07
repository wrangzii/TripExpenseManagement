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

import com.example.tripmanagement.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserFragment extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button registerButton;
    private EditText fullNameTxt, ageTxt, emailTxt, passwordTxt, confirmPasswordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_fragment);
        mAuth = FirebaseAuth.getInstance();
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        fullNameTxt = (EditText) findViewById(R.id.fullNameTxt);
        ageTxt = findViewById(R.id.ageTxt);
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        confirmPasswordTxt = findViewById(R.id.confirmPasswordTxt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerButton:
                registerUser();
        }
    }

    private void registerUser() {
        String email = emailTxt.getText().toString().trim();
        String fullName = fullNameTxt.getText().toString().trim();
        String age = ageTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();
        String cfPassword = confirmPasswordTxt.getText().toString().trim();

        if (email.isEmpty()) {
            emailTxt.setError("Email is required!");
            emailTxt.requestFocus();
            return;
        }
        if (fullName.isEmpty()) {
            fullNameTxt.setError("Full name is required!");
            fullNameTxt.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordTxt.setError("Password is required!");
            passwordTxt.requestFocus();
            return;
        }
        if (cfPassword.isEmpty()) {
            confirmPasswordTxt.setError("Confirm password is required!");
            confirmPasswordTxt.requestFocus();
            return;
        }

        if (!password.equals(cfPassword)) {
            passwordTxt.setError("Password and confirm password don't match!");
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
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, age, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegisterUserFragment.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(RegisterUserFragment.this, LoginUserFragment.class));
                                            } else {
                                                Toast.makeText(RegisterUserFragment.this, "Fail to register, try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterUserFragment.this, "Fail to register, try again!", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}