package com.w9577326.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void goToActivity2 (View view){
//        Intent intent = new Intent (this, SecondActivity.class);
//      startActivity(intent);
        finish();
    }
}