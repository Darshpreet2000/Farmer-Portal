package com.example.farmer_portalnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.Classes.queryId;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class TransportFacilty extends AppCompatActivity {
    EditText source,destination,price;
    Button addTranspostFacilty;
    TransportDetail transportDetail=new TransportDetail();
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_facilty);
        source=findViewById(R.id.SourceEt);
        destination=findViewById(R.id.DestinationEt);
        price=findViewById(R.id.Price);
        addTranspostFacilty=findViewById(R.id.addTranspostFacilty);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
       myRef = database.getReference("transportQueries");


        addTranspostFacilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sourceName=source.getText().toString();
                final String destinationName=destination.getText().toString();
                final String priceValue=price.getText().toString();
                if(sourceName.isEmpty()){
                    source.setError("This feild is Required");
                    source.requestFocus();


                }
                if(destinationName.isEmpty()){
                    destination.setError("This field is Required");
                    destination.requestFocus();

                }
                if(priceValue.isEmpty()){
                    price.setError("This Field is reqired");
                    price.requestFocus();



                }
                if(!sourceName.isEmpty()&&!destinationName.isEmpty()&&!priceValue.isEmpty()){

                    transportDetail.setDropLoc(destinationName);
                    transportDetail.setPickLoc(sourceName);
                    transportDetail.setPrice(priceValue);
                    transportDetail.setQueryOwnwe(mAuth.getUid());
                   final String key=myRef.push().getKey();

                   // Toast.makeText(TransportFacilty.this, s, Toast.LENGTH_SHORT).show();
                    myRef.child(key).setValue(transportDetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           // progressBaraddproduct.setVisibility(View.GONE);


                            if (task.isSuccessful()) {
                                Toast.makeText(TransportFacilty.this, "Added Successful", Toast.LENGTH_SHORT).show();
                                    //push id should be inserted in query


                                    Log.d("ids",key);
                                    queryId queryId1=new queryId();
                                    queryId1.setQueryId(key);
                                myRef = database.getReference("users").child(mAuth.getUid()).child("queries");
                                myRef.push().setValue(queryId1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(TransportFacilty.this, "added successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent =new Intent(TransportFacilty.this,NavigationDrawer.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(TransportFacilty.this, "Add again", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                });






                            } else {
                                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    


                }


            }
        });



    }
}
