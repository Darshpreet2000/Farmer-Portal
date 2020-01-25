package com.example.farmer_portalnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.Classes.User;
import com.firebase.ui.auth.data.model.PhoneNumber;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.jar.Attributes;

public class DisplayTransportDetail extends AppCompatActivity {
    TransportDetail transportDetail=new TransportDetail();
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    TextView textView;
    String name ;
    String PhoneNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_transport_detail);
        Intent intent=getIntent();
        transportDetail=(TransportDetail)intent.getSerializableExtra("class");
        String s=transportDetail.getQueryOwnwe();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        textView=findViewById(R.id.displaytransport);


        myRef = database.getReference("users").child(Objects.requireNonNull(mAuth.getUid()));
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                s=dataSnapshot.getKey();


                    Log.d("item value ",s+ ": "+dataSnapshot.getValue().toString());
                    name+=dataSnapshot.getValue().toString();
                    if(s=="name"){
                        Toast.makeText(DisplayTransportDetail.this,s+" "+dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                        name=s;
                        Log.d("item value name ",s+ ": "+dataSnapshot.getValue().toString());
                    }
                    if(s=="phoneNo"){
                        Log.d("item value  phone number",s+ ": "+dataSnapshot.getValue().toString());
                        PhoneNumber=dataSnapshot.getValue().toString();
                    }

                        ///Set Here Name PHoneNumber and adress of tranport

                   // Log.d("item desc",dataSnapshot.getValue().toString());




            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
     ///  String phoneNo= myRef.child("phoneNo").toString();

       String text= "Name "+name +'\n'+"Phone Number " +PhoneNumber;
       textView.setText(text);



    }
}
