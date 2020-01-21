package com.example.farmer_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farmer_portal.Classes.Addproduct;

public class NewActivity extends AppCompatActivity {
    TextView textView;
    Button buyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        textView=findViewById(R.id.productDescreption);
        buyNow=findViewById(R.id.BuyNow);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewActivity.this,BuyNow.class);
                ///put something to pass by intent
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
       Addproduct addproduct=(Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
       String s="Name:"+addproduct.getName()+"\n";
        s+="product type:"+addproduct.getCategory()+"\n";
        s+="quantity:"+addproduct.getQuantity()+"\n";
        s+="price:"+addproduct.getCropPrice();
        textView.setText(s);

    }
}
