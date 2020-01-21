package com.example.farmer_portal.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.farmer_portal.Classes.Addproduct;
import com.example.farmer_portal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBidding extends Fragment {

    ProgressBar progressBarrecyclemybidding;
    RecyclerView recyclerViewmybidding;

    public MyBidding() {
        // Required empty public constructor
    }
    private List<Addproduct> mybiddingList = new ArrayList<>();
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_bidding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products").child(Objects.requireNonNull(mAuth.getUid()));
        progressBarrecyclemybidding=(ProgressBar) view.findViewById(R.id.progressBarmyproducts);
        progressBarrecyclemybidding.setVisibility(View.VISIBLE);
        recyclerViewmybidding=view.findViewById(R.id.recycleviewmybidding);
    }
}
