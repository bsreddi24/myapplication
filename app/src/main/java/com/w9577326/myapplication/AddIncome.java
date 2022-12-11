package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class AddIncome extends AppCompatActivity {
    EditText incomeAmountText, incomeDetailsText;
    ImageButton saveIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        Fragment fragment = new MapFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_layout,fragment).
                commit();

        incomeAmountText = findViewById(R.id.incomeAmountText);
        incomeDetailsText = findViewById(R.id.incomeDetailsText);
        saveIncome = findViewById(R.id.saveIncome);

        saveIncome.setOnClickListener( (v)-> saveIn());
    }
    void saveIn(){

        String incomeAmount = incomeAmountText.getText().toString();
        String incomeDetails = incomeDetailsText.getText().toString();
        if(incomeAmount==null | incomeAmount.isEmpty()){
            incomeAmountText.setError("Amount details is required!");
            return;
        }
        Income income = new Income();
        income.setAmount(incomeAmount);
        income.setDetails(incomeDetails);
        income.setTimestamp(Timestamp.now());
        saveIncomeToFirebase(income);

    }

    void saveIncomeToFirebase(Income income){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForIncomes().document();

        documentReference.set(income).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Income is added
                    Utility.showToast(AddIncome.this, "Income added Successfully");
                    finish();
                }else{
                    Utility.showToast(AddIncome.this, "Failed while adding Income");
                }
            }
        });

    }

}