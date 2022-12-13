package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.sql.Timestamp;

public class AddExpenses extends AppCompatActivity {
    EditText expAmountText, expDetailsText;
    ImageButton saveExp;
    TextView deleteExpense, expenseTitle;
    String amount, details,docId;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        Fragment fragment = new MapFragment();

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_layout,fragment).
                commit();

        expAmountText = findViewById(R.id.expAmountText);
        expDetailsText = findViewById(R.id.expDetailsText);
        saveExp = findViewById(R.id.saveExp);
        expenseTitle = findViewById(R.id.expenseTitle);
        deleteExpense = findViewById(R.id.deleteExpense);
        deleteExpense.setVisibility(View.VISIBLE);

        amount = getIntent().getStringExtra("amount");
        details = getIntent().getStringExtra("details");
        docId = getIntent().getStringExtra("docId");

        if (docId!=null && !docId.isEmpty()) {

            isEditMode = true;
        }

        expAmountText.setText("amount");
        expDetailsText.setText("details");

        if(isEditMode) {
            expenseTitle.setText("Edit your Expense Details");
        }

        saveExp.setOnClickListener( (v)-> saveExpense());
        deleteExpense.setOnClickListener((v)-> deleteNoteFromFirebase());

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
        if (isEditMode){
            //Update Expense Details
            documentReference = Utility.getCollectionReferenceForExpenses().document();

        }else{

            //Add new Expense Details
            documentReference = Utility.getCollectionReferenceForExpenses().document();

        }


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
    void deleteNoteFromFirebase(){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForExpenses().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Income is deleted
                    Utility.showToast(AddExpenses.this, "Expense Details deleted Successfully");
                    finish();
                }else{
                    Utility.showToast(AddExpenses.this, "Failed while deleting Expense Details");
                }
            }
        });

    }



}
