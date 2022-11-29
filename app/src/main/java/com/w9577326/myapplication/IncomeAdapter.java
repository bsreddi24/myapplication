package com.w9577326.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class IncomeAdapter extends FirestoreRecyclerAdapter<Income, IncomeAdapter.IncomeViewHolder> {
    Context context;

    public IncomeAdapter(@NonNull FirestoreRecyclerOptions<Income> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull IncomeViewHolder holder, int position, @NonNull Income income) {


        holder.amountTextView.setText(income.amount);
        holder.detailsTextView.setText(income.details);
//        holder.timestampTextView.setText(Utility.timestamptoString(income.timestamp));

    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_income,parent,false);
        return new IncomeViewHolder(view);
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder{
        TextView amountTextView,detailsTextView,timestampTextView;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            amountTextView = itemView.findViewById(R.id.income_amount_v);
            detailsTextView = itemView.findViewById(R.id.income_details_v);
            timestampTextView = itemView.findViewById(R.id.income_timestamp_v);
           // Utility.showToast(itemView.getContext(), String.valueOf(amountTextView.getText()));
        }
    }
}
