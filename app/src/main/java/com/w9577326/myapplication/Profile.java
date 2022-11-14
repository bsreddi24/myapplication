package com.w9577326.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

public class Profile extends AppCompatActivity {

    ImageView profilePicture;
    Button updateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }


}