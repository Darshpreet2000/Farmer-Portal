package com.example.farmer_portalnew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.bidclass;
import com.example.farmer_portalnew.Classes.bidding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class displayproduct extends AppCompatActivity {
    TextView textView;


   EditText enterbiddingprice;
   Button addbdidding;
    DatabaseReference myRef;
    DatabaseReference myBid;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
String farmerid,price,name,quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayproduct);

        textView=findViewById(R.id.productDescreption);
       enterbiddingprice=findViewById(R.id.biddingprice);
       addbdidding=findViewById(R.id.addbidding);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        Intent intent=getIntent();
       final Addproduct addproduct=(Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
       String s="Name: "+addproduct.getCropName()+"\n"+"\n";
        s+="Product type: "+addproduct.getCategory()+"\n"+"\n";
        s+="Quantity: "+addproduct.getQuantity()+"\n"+"\n";
        s+="Price: "+addproduct.getPrice();
        farmerid=addproduct.getCropOwner();
        quantity=addproduct.getQuantity();
        textView.setText(s);
       addbdidding.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.KITKAT)
           @Override
           public void onClick(View v) {
               if(enterbiddingprice.getText()==null){
                  enterbiddingprice.setError("Price is required");
                   enterbiddingprice.requestFocus();
                   return;
               }
               price=enterbiddingprice.getText().toString();
               bidclass Bidclass=new bidclass(addproduct.getPrice(),addproduct.getCropid(),addproduct.getCropName(),addproduct.getCropOwner(),"100",price);
              // bidding Bidding=new bidding(mAuth.getUid(),price,addproduct.getCropOwner(),addproduct.getCropid());
             //Need to change here so that price gets updated only in that crop
               myBid=database.getReference("users");
               myBid.child(mAuth.getUid()).child("bids").push().setValue(Bidclass).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(displayproduct.this, "Added Successful", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(displayproduct.this, "Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

             String key=myRef.child(addproduct.getCropOwner()).child("bids").push().getKey();
               bidding Bidding=new bidding(key,mAuth.getUid(),addproduct.getCropid());
               myRef.child(addproduct.getCropOwner()).child("bids").child(key).setValue(Bidding).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(displayproduct.this, "Added Successful", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(displayproduct.this, "Failed", Toast.LENGTH_SHORT).show();
                       }
                   }
               });


           }
       });
    }

}
