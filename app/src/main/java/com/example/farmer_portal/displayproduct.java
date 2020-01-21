package com.example.farmer_portal;

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

import com.example.farmer_portal.Classes.Addproduct;
import com.example.farmer_portal.Classes.bidding;
import com.example.farmer_portal.ui.MyBidding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class displayproduct extends AppCompatActivity {
    TextView textView;

    Button buyNow;


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
        buyNow=findViewById(R.id.BuyNow);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(displayproduct.this,BuyNow.class);
                ///put something to pass by intent
                startActivity(intent);
            }
        });

       enterbiddingprice=findViewById(R.id.biddingprice);
       addbdidding=findViewById(R.id.addbidding);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("FarmerBidding");
        myBid = database.getReference("MyBidding");
        Intent intent=getIntent();
       final Addproduct addproduct=(Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
       String s="Name:"+addproduct.getName()+"\n";
        s+="product type:"+addproduct.getCategory()+"\n";
        s+="quantity:"+addproduct.getQuantity()+"\n";
        s+="price:"+addproduct.getCropPrice();
        farmerid=addproduct.getFarmerid();
        name=addproduct.getCropid();
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
               bidding Bidding=new bidding(farmerid,price,name,quantity);
             //Need to change here so that price gets updated only in that crop
               myRef.child(farmerid).child(name).setValue(Bidding).addOnCompleteListener(new OnCompleteListener<Void>() {
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
               myBid.child(Objects.requireNonNull(mAuth.getUid())).child(name).setValue(Bidding).addOnCompleteListener(new OnCompleteListener<Void>() {
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
