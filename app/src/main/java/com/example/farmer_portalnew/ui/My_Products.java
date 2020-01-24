package com.example.farmer_portalnew.ui;


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
import android.widget.Toast;

import com.example.farmer_portalnew.Adapter.myProduct_adapter;
import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.R;
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
public class My_Products extends Fragment {
    ProgressBar progressBarrecyclemy;
    RecyclerView recyclerViewmy;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    private List<Addproduct> myproductList = new ArrayList<>();
    public My_Products() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products").child(Objects.requireNonNull(mAuth.getUid()));
        progressBarrecyclemy=(ProgressBar) view.findViewById(R.id.progressBarmyproducts);
        progressBarrecyclemy.setVisibility(View.VISIBLE);
        recyclerViewmy=view.findViewById(R.id.recycleviewmyproducts);
        Log.v("",""+ myproductList.size());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                myproductList.clear();
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                    Addproduct restaurantModel = dataValues.getValue(Addproduct.class);
                    restaurantModel.setCropid(dataValues.getKey());
                    myproductList.add(restaurantModel);
                }

                recyclerViewmy.setLayoutManager(new LinearLayoutManager(getContext()));
                myProduct_adapter Product_adapter=new myProduct_adapter( myproductList);
                recyclerViewmy.setAdapter(Product_adapter);
                recyclerViewmy.setHasFixedSize(true);
                progressBarrecyclemy.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getContext(), "Error From Database", Toast.LENGTH_SHORT).show();
                progressBarrecyclemy.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my__products, container, false);
    }

}
