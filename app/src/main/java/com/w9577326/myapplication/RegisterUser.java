package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://my-project-1498546804883-default-rtdb.firebaseio.com").child("getUsers");
    private FirebaseAuth mAuth;
    private TextView banner, registerHere;
    private EditText fullName, logEmail, logPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerHere = (Button) findViewById(R.id.registerHere);
        registerHere.setOnClickListener(this);

        fullName = (EditText) findViewById(R.id.fullName);
        logEmail = (EditText) findViewById(R.id.logEmail);
        logPassword = (EditText) findViewById(R.id.logPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerHere:
                registerHere();
                break;
        }

    }
    private void registerHere(){
        String enterFullName = fullName.getText().toString().trim();
        String enterEmail= logEmail.getText().toString().trim();
        String enterPassword = logPassword.getText().toString().trim();

        if (enterFullName.isEmpty()){
            fullName.setError("Full name is required!");
            fullName.requestFocus();
            return;
        }
        if (enterEmail.isEmpty()){
            logEmail.setError("Email ID is required!");
            logEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(enterEmail).matches()) {
            logEmail.setError("Please provide a valid Email Address!");
            logEmail.requestFocus();
            return;
        }
        if (enterPassword.isEmpty()){
            logPassword.setError("Password is required!");
            logPassword.requestFocus();
            return;
        }
        if(enterPassword.length() < 6){
            logPassword.setError("Min password length should be 6 characters!");
            logPassword.requestFocus();
            return;
            }
        progressBar.setVisibility(View.GONE);
        mAuth.createUserWithEmailAndPassword(enterEmail, enterPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(enterFullName, enterEmail);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
//                                                Utility.showToast(RegisterUser.this, "User has been registered successfully");
                                                finish();
                                                startActivity(new Intent(RegisterUser.this, MainActivity.class));
                                                Toast.makeText(RegisterUser.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            } else{
//                                                Utility.showToast(RegisterUser.this, "Failed to register! Try again!");
                                                Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                                }
                                        }
                                    });
                        }else{
//                            Utility.showToast(RegisterUser.this, "Failed to register! Try again!");
                            Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        }
    }
