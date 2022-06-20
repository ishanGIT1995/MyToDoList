package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText loginEmail ,loginPassword;
    TextView RegInLogin;
    Button login;
    ProgressBar loginProBar;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginEmail =findViewById(R.id.userEmail);
        loginPassword =findViewById(R.id.userPassword);
        RegInLogin =findViewById(R.id.textRegister);
        login =findViewById(R.id.login);
        loginProBar =findViewById(R.id.loginProgressBar);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = loginEmail.getText().toString();
                String userPassword =loginPassword.getText().toString();

                if (TextUtils.isEmpty(userEmail)) {
                    loginEmail.setError("Email is required");
                    loginEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPassword)) {
                    loginPassword.setError("Password is required");
                    loginPassword.requestFocus();
                    return;
                }
                if (userPassword.length() < 6) {
                    loginPassword.setError("Password must be greater than 5 characters");
                    loginPassword.requestFocus();
                    return;
                }
                loginProBar.setVisibility(View.VISIBLE);

                // Authenticate the User

                mAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(Login.this,"User logged In successfully ",Toast.LENGTH_SHORT).show();
                            Intent categories = new Intent(Login.this,Categories.class);
                            startActivity(categories);

                        }
                        else {
                            Toast.makeText(Login.this,"Error" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            loginProBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        RegInLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoRegister = new Intent(Login.this,Register.class);
                startActivity(gotoRegister);
            }
        });

    }


    // Show Exit Alert Dialog
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Login.super.onBackPressed();
                        finishAffinity();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    // End of Alert Dialog

}