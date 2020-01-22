package com.example.farmer_portal.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.farmer_portal.Adapter.bidmycrop;
import com.example.farmer_portal.Adapter.myBidding;
import com.example.farmer_portal.Classes.Addproduct;
import com.example.farmer_portal.Classes.User;
import com.example.farmer_portal.Classes.bidding;
import com.example.farmer_portal.R;
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

    private List<Addproduct> biddingList=new ArrayList<>();
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
        myRef = database.getReference("FarmerBidding/"+mAuth.getUid());
        progressBarrecyclebidmycrop = (ProgressBar) view.findViewById(R.id.progressBarbidmycrop);
        progressBarrecyclebidmycrop.setVisibility(View.VISIBLE);
        recyclerViewbidmycrop = view.findViewById(R.id.recycleviewbidmycrop);
        recyclerViewbidmycrop.setLayoutManager(new LinearLayoutManager(getContext()));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                biddingList.clear();
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {

                       final bidding bidstomycrop=dataValues.getValue(bidding.class);
                      final String bid=bidstomycrop.getPrice();
                        productref = database.getReference("Products/"+bidstomycrop.getFarmerid());
                        productref.equalTo(bidstomycrop.name);
                        productref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                                    Log.v("", dataValues.toString());
                                    final Addproduct addproduct = dataValues.getValue(Addproduct.class);
                                    addproduct.setBid(bid);
                                    user=database.getReference("User_details/"+bidstomycrop.getBuyerid());
                                    user.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            User user=dataSnapshot.getValue(User.class);
                                            Log.v("Bidding My",""+user.getPhone());
                                            addproduct.setFarmerphone(user.getPhone());
                                            addproduct.setFarmername(user.getName());
                                            biddingList.add(addproduct);
                                            bidmycrop Product_adapter=new bidmycrop(biddingList);
                                            recyclerViewbidmycrop.setAdapter(Product_adapter);
                                            recyclerViewbidmycrop.setHasFixedSize(true);
                                            progressBarrecyclebidmycrop.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

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
