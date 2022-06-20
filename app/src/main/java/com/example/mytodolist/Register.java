package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    public static final String TAG = "TAG";
    private FirebaseAuth mAuth;
    EditText firstName, lastName, email, password;
    TextView textLogin;
    Button register;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        firstName =findViewById(R.id.firstName);
        lastName =findViewById(R.id.lastName);
        email =findViewById(R.id.email);
        password = findViewById(R.id.password);
        textLogin =findViewById(R.id.textLogin);
        register =findViewById(R.id.register);
        
        mAuth = FirebaseAuth.getInstance();
        progressBar =findViewById(R.id.progressBar);
        fstore = FirebaseFirestore.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword =password.getText().toString();
                String fName =firstName.getText().toString();
                String lName =lastName.getText().toString();

                if (TextUtils.isEmpty(userEmail)) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(userPassword)) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                if (userPassword.length() < 6) {
                    password.setError("Password must be greater than 5 characters");
                    password.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // Register the user in Firease

                mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(Register.this,"User successfully Registered",Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference =fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fname",fName);
                            user.put("lname",lName);
                            user.put("email",userEmail);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG, "onSuccess: userProfile is created for" + userID);

                                }
                            });
                            Intent categories = new Intent(Register.this,Categories.class);
                            startActivity(categories);

                        }

                        else {
                            Toast.makeText(Register.this,"Error" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });



            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin = new Intent(Register.this,Login.class);
                startActivity(gotoLogin);
            }
        });

    }
}