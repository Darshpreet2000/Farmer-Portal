package com.example.farmer_portal.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.farmer_portal.Adapter.product_adapter;
import com.example.farmer_portal.Classes.Addproduct;
import com.example.farmer_portal.R;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    ProgressBar progressBarrecycle;
    RecyclerView recyclerView;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private List<Addproduct> productList = new ArrayList<>();
    public Home() {
        // Required empty public constructor
    }
    private FirebaseAuth mAuth;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products");
        progressBarrecycle=(ProgressBar) view.findViewById(R.id.progressBarrecycle);
        progressBarrecycle.setVisibility(View.VISIBLE);
        recyclerView=view.findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.v("",""+productList.size());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                productList.clear();
                List<User> UserList=new ArrayList<>(); //Moved here
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                    for (DataSnapshot messageSnapshot: dataValues.getChildren()) {
                      Log.v("",messageSnapshot.toString());
                        Addproduct restaurantModel = messageSnapshot.getValue(Addproduct.class);
                        productList.add(restaurantModel);
                    }
                }
                product_adapter Product_adapter=new product_adapter(productList);

                recyclerView.setAdapter(Product_adapter);
                recyclerView.setHasFixedSize(true);
                progressBarrecycle.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getContext(), "Error From Database", Toast.LENGTH_SHORT).show();
                progressBarrecycle.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
     return view;
    }
}
