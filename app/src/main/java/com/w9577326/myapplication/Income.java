package com.w9577326.myapplication;

//import java.sql.Timestamp;

import com.google.firebase.Timestamp;

public class Income {
    String amount;
    String details;
    Timestamp timestamp;

    public Income() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

//    public void setTimestamp(Timestamp timestamp) {
//        this.timestamp = timestamp;
//    }

    public void setTimestamp(com.google.firebase.Timestamp now) {
        this.timestamp = timestamp;
    }
}
