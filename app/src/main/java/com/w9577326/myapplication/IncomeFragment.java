package com.w9577326.myapplication;

import android.app.DownloadManager;
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
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.Query;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link IncomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class IncomeFragment extends Fragment {


//    FloatingActionButton add_btnIncome;
    Button add_btnIncome;
//    RecyclerView recyclerIncome;
    AdapterIncome adapterIncome;
    RecyclerView recyclerView;
//    ImageButton menuBtn;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public IncomeFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment IncomeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static IncomeFragment newInstance(String param1, String param2) {
//        IncomeFragment fragment = new IncomeFragment();
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
        View r = inflater.inflate(R.layout.fragment_income, container, false);

        return r;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView(view);
        add_btnIncome = view.findViewById(R.id.add_btnIncome);
        View recyclerIncome = view.findViewById(R.id.recycler_income);
//        IncomeAdapter incomeAdapter;


        add_btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddIncome.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView(View r) {
        recyclerView = r.findViewById(R.id.recycler_income);
//        menuBtn = r.findViewById(R.id.menu_btn);
//        menuBtn.setOnClickListener((v)->showMenu());

        Query query = Utility.getCollectionReferenceForIncomes().orderBy("amount", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Income> options = new FirestoreRecyclerOptions.Builder<Income>()
                .setQuery(query,Income.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        adapterIncome = new AdapterIncome(options, this);
        recyclerView.setAdapter(adapterIncome);




//        Utility.showToast(getContext(), String.valueOf(options));

    }
//    void showMenu(){
//        // TODO Display Menu
//    }




    @Override
    public void onStart() {
        super.onStart();

        adapterIncome.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterIncome.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterIncome.notifyDataSetChanged();
    }
}