package com.w9577326.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdapterIncome extends FirestoreRecyclerAdapter<Income, AdapterIncome.IncomeViewHolder> {
    IncomeFragment context;

    public AdapterIncome(@NonNull FirestoreRecyclerOptions<Income> options, IncomeFragment context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull IncomeViewHolder holder, int position, @NonNull Income income) {
        holder.income_amount_v.setText(income.amount);
        holder.income_details_v.setText(income.details);
//        holder.income_timestamp_v.setText(Utility.timestampToString(income.timestamp));

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(v.getContext(),AddIncome.class);
            intent.putExtra("amount",income.amount);
            intent.putExtra("details",income.details);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);

        });

    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_income,parent,false);
        return new IncomeViewHolder(view);
    }

    class IncomeViewHolder extends RecyclerView.ViewHolder{

        TextView income_amount_v, income_details_v, income_timestamp_v;


        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            income_amount_v = itemView.findViewById(R.id.income_amount_v);
            income_details_v = itemView.findViewById(R.id.income_details_v);
            income_timestamp_v = itemView.findViewById(R.id.income_timestamp_v);
        }
    }
}
