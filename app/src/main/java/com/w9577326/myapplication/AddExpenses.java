package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.sql.Timestamp;

public class AddExpenses extends AppCompatActivity {
    EditText expAmountText, expDetailsText;
    ImageButton saveExp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        expAmountText = findViewById(R.id.expAmountText);
        expDetailsText = findViewById(R.id.expDetailsText);
        saveExp = findViewById(R.id.saveExp);

        saveExp.setOnClickListener( (v)-> saveExpense());

    }
    void saveExpense(){
        String expAmount = expAmountText.getText().toString();
        String expDetails = expDetailsText.getText().toString();
        if(expAmount==null | expAmount.isEmpty()){
            expAmountText.setError("Amount details is required!");
            return;
        }
        Expense expense = new Expense();
        expense.setAmount(expAmount);
        expense.setDetails(expDetails);
//        expense.setTimestamp(Timestamp.now);
        saveExpenseToFirebase(expense);

    }
    void saveExpenseToFirebase(Expense expense){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForExpenses().document();

        documentReference.set(expense).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Expense is added
                    Utility.showToast(AddExpenses.this, "Expense added Successfully");
                    finish();
                }else{
                    Utility.showToast(AddExpenses.this, "Failed while adding Expense");
                }
            }
        });
    }
}