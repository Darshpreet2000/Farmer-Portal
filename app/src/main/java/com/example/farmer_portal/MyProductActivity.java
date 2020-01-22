package com.example.farmer_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.farmer_portal.Classes.Addproduct;

public class MyProductActivity extends AppCompatActivity {
    TextView CropName,ProductType,quantity,Price,Catagory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        CropName = findViewById(R.id.NameOfCrop);
        ProductType=findViewById(R.id.Catagory);
        quantity=findViewById(R.id.quantity);
        Price=findViewById(R.id.MyPrice);

        Intent intent = getIntent();
        Addproduct addproduct = (Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
        String s = "Name:" + addproduct.getName();
        CropName.setText(s);
        s = "product type:" + addproduct.getCategory();
        ProductType.setText(s);

        s = "quantity:" + addproduct.getQuantity();
        quantity.setText(s);

        s = "price:" + addproduct.getCropPrice();
        Price.setText(s);

    }

}
