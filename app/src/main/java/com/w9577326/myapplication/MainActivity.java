package com.w9577326.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*View v = findViewById(R.id.button);
        v.setOnClickListener(this);*/
    }
    public void goToWelcome (View view){
        Intent intent = new Intent (this, Welcome.class);
        startActivity(intent);
    }

}