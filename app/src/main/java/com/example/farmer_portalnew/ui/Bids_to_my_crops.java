package com.example.farmer_portalnew.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.farmer_portalnew.Adapter.bidmycrop;
import com.example.farmer_portalnew.Classes.bidding;
import com.example.farmer_portalnew.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bids_to_my_crops extends Fragment {

    ProgressBar progressBarrecyclebidmycrop;
    RecyclerView recyclerViewbidmycrop;

    private List<bidding> biddingList=new ArrayList<bidding>();
    public Bids_to_my_crops() {
        // Required empty public constructor

    }
    DatabaseReference myRef,productref,user;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("crops");
        progressBarrecyclebidmycrop = (ProgressBar) view.findViewById(R.id.progressBarbidmycrop);
        progressBarrecyclebidmycrop.setVisibility(View.VISIBLE);
        recyclerViewbidmycrop = view.findViewById(R.id.recycleviewbidmycrop);
        recyclerViewbidmycrop.setLayoutManager(new LinearLayoutManager(getContext()));
     myRef.orderByChild("cropOwner").equalTo(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataValues:dataSnapshot.getChildren()) {
                            for (final DataSnapshot datamessage : dataValues.getChildren()) {
                                String key = dataSnapshot.getKey();
                                database.getReference().orderByKey().equalTo(key).addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                       for(DataSnapshot datavalues:dataSnapshot.getChildren()) {
                                           for (final DataSnapshot datamessage : datavalues.getChildren()) {
                                               bidding Bidding = datamessage.getValue(bidding.class);
                                               biddingList.add(Bidding);
                                           }
                                       }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            bidmycrop Product_adapter = new bidmycrop(biddingList);
                            recyclerViewbidmycrop.setAdapter(Product_adapter);
                            recyclerViewbidmycrop.setHasFixedSize(true);
                            progressBarrecyclebidmycrop.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bids_to_my_crops, container, false);
    }

    }
