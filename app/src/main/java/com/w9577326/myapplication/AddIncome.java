package com.w9577326.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class AddIncome extends AppCompatActivity {
    EditText incomeAmountText, incomeDetailsText;
    ImageButton saveIncome;
    TextView deleteIncome, incomeTitle;
    String amount, details,docId;
    boolean isEditMode = false;

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
        incomeTitle = findViewById(R.id.incomeTitle);
        deleteIncome = findViewById(R.id.deleteIncome);

        deleteIncome.setVisibility(View.VISIBLE);

        amount = getIntent().getStringExtra("amount");
        details = getIntent().getStringExtra("details");
        docId = getIntent().getStringExtra("docId");

        if (docId!=null && !docId.isEmpty()) {

            isEditMode = true;
        }

        incomeAmountText.setText(amount);
        incomeDetailsText.setText(details);

        if(isEditMode) {
            incomeTitle.setText("Edit your Income Details");
        }


        saveIncome.setOnClickListener( (v)-> saveIn());
        deleteIncome.setOnClickListener((v)-> deleteNoteFromFirebase());
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
        if (isEditMode){
            //Update Income details
            documentReference = Utility.getCollectionReferenceForIncomes().document(docId);

        }else{
            //Create new Income Details
            documentReference = Utility.getCollectionReferenceForIncomes().document();

        }
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

    void deleteNoteFromFirebase(){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForIncomes().document(docId);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    //Income is added
                    Utility.showToast(AddIncome.this, "Income Details deleted Successfully");
                    finish();
                }else{
                    Utility.showToast(AddIncome.this, "Failed while deleting Income Details");
                }
            }
        });

    }



    }


