package com.w9577326.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.net.ContentHandler;
import java.text.SimpleDateFormat;

public class Utility {
    static void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    static CollectionReference getCollectionReferenceForIncomes(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("incomes")
                .document(currentUser.getUid()).collection("my_income");

    }
    static CollectionReference getCollectionReferenceForExpenses(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("expenses")
                .document(currentUser.getUid()).collection("my_expenses");

    }
    static String timestamptoString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
}
