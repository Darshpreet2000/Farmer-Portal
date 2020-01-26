package com.example.farmer_portalnew.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.farmer_portalnew.Adapter.product_adapter;
import com.example.farmer_portalnew.Classes.Cart;
import com.example.farmer_portalnew.R;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class displaycategorycrop extends AppCompatActivity {
    ProgressBar progressBarrecycle;
    RecyclerView recyclerView;
    DatabaseReference myRef,cartRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private List<Cart> productList = new ArrayList<Cart>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaycategorycrop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         Intent i= getIntent();
         String category=i.getStringExtra("category");
        getSupportActionBar().setTitle(category);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("crops");
        cartRef=database.getReference("users");
        progressBarrecycle=(ProgressBar) findViewById(R.id.progressBarrecycle);
        progressBarrecycle.setVisibility(View.VISIBLE);
        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.v("",""+productList.size());
        myRef.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                productList.clear();
                List<User> UserList = new ArrayList<>(); //Moved here
                for (DataSnapshot dataValues : dataSnapshot.getChildren()) {
                    Log.v("", dataValues.toString());
                    Cart restaurantModel = dataValues.getValue(Cart.class);
                    restaurantModel.setCropid(dataValues.getKey());
                    //     restaurantModel.(dataValues.getKey());
                    productList.add(restaurantModel);
                }
                //Following data was inserted for debugging  purpose
/*
                Addproduct restaurantModel=new Addproduct();
                restaurantModel.setQuantity("2");
                restaurantModel.setCategory("g");
                restaurantModel.setName("name");
                productList.add(restaurantModel);
                Addproduct restaurantModel1=new Addproduct();
                restaurantModel1.setQuantity("21");
                restaurantModel1.setCategory("g1");
                restaurantModel1.setName("name1");
                productList.add(restaurantModel1);*/
                product_adapter Product_adapter = new product_adapter(productList, new product_adapter.OnItemClicked() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onbuttonclicked(int position) {
                        progressBarrecycle.setVisibility(View.VISIBLE);
                        Cart current=productList.get(position);
                        EditText text= recyclerView.findViewHolderForLayoutPosition(position).itemView.findViewById(R.id.amountobuy);
                        if(text.getText().toString().isEmpty()) {
                        text.setError("This is required");
                         text.requestFocus();
                          progressBarrecycle.setVisibility(View.GONE);
                         return;
                        }

                        current.setBuyQuantity(text.getText().toString()+" Kg");
                        cartRef.child(Objects.requireNonNull(mAuth.getUid())).child("cart").push().setValue(current).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(displaycategorycrop.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                                progressBarrecycle.setVisibility(View.GONE);
                            }
                        });
                      //startActivity(new Intent(displaycategorycrop.this, cart.class));
                    }


                });

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
}
