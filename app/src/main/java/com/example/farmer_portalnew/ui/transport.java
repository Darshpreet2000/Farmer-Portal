package com.example.farmer_portalnew.ui;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.farmer_portalnew.Adapter.TransportAdapter;
import com.example.farmer_portalnew.Adapter.myProduct_adapter;
import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.NavigationDrawer;
import com.example.farmer_portalnew.R;
import com.example.farmer_portalnew.TransportFacilty;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
public class transport extends Fragment {
    FloatingActionButton floatingActionButton;
    ProgressBar progressBaraddproduct;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private List<TransportDetail> myproductList = new ArrayList<>();

    Button button;

    public transport() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_transport, container, false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("transportQueries");
        floatingActionButton=(FloatingActionButton) view.findViewById(R.id.addtransport);
        progressBaraddproduct=view.findViewById(R.id.progressBaraddproduct);
        final RecyclerView recyclerView=view.findViewById(R.id.TransportRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final String[] s =new String[100];

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myproductList.clear();
                int i=0;
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                    TransportDetail restaurantModel = dataValues.getValue(TransportDetail.class);
                    myproductList.add(restaurantModel);




                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                TransportAdapter transportAdapter=new TransportAdapter(myproductList);
                recyclerView.setAdapter(transportAdapter);
                recyclerView.setHasFixedSize(true);

                //recyclerView.setAdapter(new TransportAdapter(s));


                progressBaraddproduct.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                progressBaraddproduct.setVisibility(View.VISIBLE);
                Intent intent =new Intent(getContext(), TransportFacilty.class);

                startActivity(intent);

            }
        });

        return  view;
    }

}
