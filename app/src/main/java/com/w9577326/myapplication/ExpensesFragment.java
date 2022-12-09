package com.w9577326.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ExpensesFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ExpensesFragment extends Fragment {
    FloatingActionButton add_btnExp;
    //    RecyclerView recyclerIncome;
    AdapterExpenses adapterExpenses;
    RecyclerView recyclerView;
    ImageButton menuBtn;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ExpensesFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ExpensesFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ExpensesFragment newInstance(String param1, String param2) {
//        ExpensesFragment fragment = new ExpensesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View r =  inflater.inflate(R.layout.fragment_expenses, container, false);
        return r;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);
        add_btnExp = view.findViewById(R.id.add_btnExp);
        View recyclerExpenses = view.findViewById(R.id.recycler_expenses);

        add_btnExp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), AddExpenses.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView(View r){
        recyclerView = r.findViewById(R.id.recycler_expenses);
        menuBtn = r.findViewById(R.id.menu_btn);
        menuBtn.setOnClickListener((v)->showMenu());
        Query query = Utility.getCollectionReferenceForExpenses().orderBy("amount", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Expense> options = new FirestoreRecyclerOptions.Builder<Expense>()
                .setQuery(query,Expense.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapterExpenses = new AdapterExpenses(options, this);
        recyclerView.setAdapter(adapterExpenses);
    }
    void showMenu(){
        // TODO Display Menu
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterExpenses.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterExpenses.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterExpenses.notifyDataSetChanged();
    }
}