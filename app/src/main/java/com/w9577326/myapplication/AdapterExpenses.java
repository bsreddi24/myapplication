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

public class AdapterExpenses extends FirestoreRecyclerAdapter<Expense, AdapterExpenses.ExpensesViewHolder>{
    ExpensesFragment context;

    public AdapterExpenses(@NonNull FirestoreRecyclerOptions<Expense> options, ExpensesFragment context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position, @NonNull Expense expense) {
        holder.expenses_amount_v.setText(expense.amount);
        holder.expenses_details_v.setText(expense.details);
//        holder.expenses_timestamp_v.setText(expense.timestamp);

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(v.getContext(),AddExpenses.class);
            intent.putExtra("amount",expense.amount);
            intent.putExtra("details",expense.details);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",docId);
            context.startActivity(intent);

        });

    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_expenses,parent,false);
        return new ExpensesViewHolder(view);
    }


    class ExpensesViewHolder extends RecyclerView.ViewHolder{

        TextView expenses_amount_v, expenses_details_v, expenses_timestamp_v;

        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);

            expenses_amount_v = itemView.findViewById(R.id.expenses_amount_v);
            expenses_details_v = itemView.findViewById(R.id.expenses_details_v);
            expenses_timestamp_v = itemView.findViewById(R.id.expenses_timestamp_v);
        }
    }
}
