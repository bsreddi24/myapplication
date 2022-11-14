package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Welcome extends AppCompatActivity {
    BottomNavigationView nav;
    IncomeFragment incomeFragment = new IncomeFragment();
    ExpensesFragment expensesFragment = new ExpensesFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

/*Bottom Navigation Menu*/

        nav = findViewById(R.id.nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                    case R.id.income:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, incomeFragment).commit();
                        return true;
                    case R.id.expenses:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, expensesFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }


}