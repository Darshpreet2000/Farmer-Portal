package com.example.farmer_portal.ui;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.farmer_portal.Classes.User;
import com.example.farmer_portal.Adapter.myBidding;
import com.example.farmer_portal.Adapter.product_adapter;
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
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBidding extends Fragment {

    ProgressBar progressBarrecyclemybidding;
    RecyclerView recyclerViewmybidding;
private List<Addproduct> biddingList=new ArrayList<>();
    public MyBidding() {
        // Required empty public constructor
    }
    DatabaseReference myRef,productref,user;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_bidding, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyBidding/"+mAuth.getUid());
        mAuth = FirebaseAuth.getInstance();
        progressBarrecyclemybidding = (ProgressBar) view.findViewById(R.id.progressBarmybidding);
        progressBarrecyclemybidding.setVisibility(View.VISIBLE);
        recyclerViewmybidding = view.findViewById(R.id.recycleviewmybidding);
        recyclerViewmybidding.setLayoutManager(new LinearLayoutManager(getContext()));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                biddingList.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
           //     biddingList.clear();
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                        Log.v("", dataValues.toString());
                        final bidding restaurantModel = dataValues.getValue(bidding.class);
                   final String bid=restaurantModel.getPrice();
                    productref = database.getReference("Products/"+restaurantModel.getFarmerid());
                    productref.equalTo(restaurantModel.name);
                    productref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                                    Log.v("", dataValues.toString());
                                    final Addproduct addproduct=dataValues.getValue(Addproduct.class);
                                    addproduct.setBid(bid);
                                  user=database.getReference("User_details/"+restaurantModel.getFarmerid());
                                  user.addValueEventListener(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            User user=dataSnapshot.getValue(User.class);
                                            Log.v("Bidding My",""+user.getPhone());
                                              addproduct.setFarmerphone(user.getPhone());
                                              addproduct.setFarmername(user.getName());
                                          biddingList.add(addproduct);
                                          myBidding Product_adapter=new myBidding(biddingList);
                                          recyclerViewmybidding.setAdapter(Product_adapter);
                                          recyclerViewmybidding.setHasFixedSize(true);
                                          progressBarrecyclemybidding.setVisibility(View.GONE);
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  });
                                    //add farmer  name and mobile from its id and cropid

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
}
