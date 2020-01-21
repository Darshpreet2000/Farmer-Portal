package com.example.farmer_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmer_portal.Classes.Addproduct;
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
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        textView=findViewById(R.id.textView2);
       enterbiddingprice=findViewById(R.id.biddingprice);
       addbdidding=findViewById(R.id.addbidding);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products");
        Intent intent=getIntent();
       Addproduct addproduct=(Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
       String s=addproduct.getName()+"\n";
        s+=addproduct.getCategory()+"\n";
        s+=addproduct.getQuantity();
        textView.setText(s);
       addbdidding.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(enterbiddingprice.getText()==null){
                  enterbiddingprice.setError("Price is required");
                   enterbiddingprice.requestFocus();
                   return;
               }
             //Need to change here so that price gets updated only in that crop
               myRef.child(Objects.requireNonNull(mAuth.getUid())).setValue(enterbiddingprice.getText()).addOnCompleteListener(new OnCompleteListener<Void>() {
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
