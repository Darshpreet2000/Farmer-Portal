package com.example.farmer_portalnew.biddingview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.farmer_portalnew.Classes.User;
import com.example.farmer_portalnew.Classes.bidclass;
import com.example.farmer_portalnew.Classes.farmerclass;
import com.example.farmer_portalnew.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BuyerDetails extends AppCompatActivity {
    DatabaseReference myRef, search;
    FirebaseDatabase database;
    TextView farmerdetails;
    String s;
    ProgressBar farmerprogress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_details); farmerprogress=findViewById(R.id.progressfarmerdetails);
        farmerprogress.setVisibility(View.VISIBLE);
        farmerclass current = (farmerclass) getIntent().getSerializableExtra("farmerclass");
        String farmerid = current.getBuyerid();
        farmerdetails = findViewById(R.id.farmer);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        myRef.orderByKey().equalTo(farmerid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot datavalues : dataSnapshot.getChildren()) {
                    User user = datavalues.getValue(User.class);
                    s = "Name : " + user.getName() + "\n" + "\n";
                    s += "Phone No : " + user.getPhoneNo() + "\n" + "\n";
                    s += "Location : " + user.getAddress();

                    farmerdetails.setText(s);
                    farmerprogress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
