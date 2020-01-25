package com.example.farmer_portalnew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class DisplayTransportDetail extends AppCompatActivity {
    TransportDetail transportDetail=new TransportDetail();
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    TextView textView,userdetails;
   ImageView callNowBtn;

    String PhoneNumber ;
String details;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transport_detail);
        Intent intent = getIntent();
        transportDetail = (TransportDetail) intent.getSerializableExtra("class");
        String s = transportDetail.getQueryOwnwe();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        textView = findViewById(R.id.displaytransport);
        callNowBtn=findViewById(R.id.CallNow);


        userdetails = findViewById(R.id.displaytransport);




        myRef = database.getReference("users").child(Objects.requireNonNull(mAuth.getUid()));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                details = "Name:" + user.getName() + "\n";
                details += "Phone No:" + user.getPhoneNo();
                PhoneNumber=user.getPhoneNo();

                userdetails.setText(details);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        callNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int k=Integer.parseInt(PhoneNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+PhoneNumber));
                startActivity(intent);

            }
        });
    }
}
