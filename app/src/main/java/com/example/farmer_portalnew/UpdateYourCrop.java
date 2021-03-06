package com.example.farmer_portalnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class UpdateYourCrop extends AppCompatActivity {
    EditText UpdateName,UpdatePrice,UpdateQuantity;
    Button updateCropDetail;
    DatabaseReference myRef,userref;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_your_crop);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("crops");
       userref=database.getReference("users");
        final Intent intent = getIntent();
      final Addproduct  addproduct = (Addproduct) intent.getSerializableExtra("class");
      UpdateName =findViewById(R.id.CropNameEt);
      UpdatePrice=findViewById(R.id.UpdatePriceEt);
      UpdateQuantity=findViewById(R.id.UpdateQuantityEt);
      updateCropDetail=findViewById(R.id.UpdateCropDetail);
      final Addproduct updateProduct=addproduct;

        //Log.d("msg",addproduct.getName());

        String s = "Name:" + addproduct.getCropName();

        s = "product type:" + addproduct.getCategory();


        s = "quantity:" + addproduct.getQuantity();

        s = "price:" + addproduct.getPrice();
      //  Toast.makeText(this, addproduct.getCropid(), Toast.LENGTH_SHORT).show();
      updateCropDetail.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             String updatedCropName= UpdateName.getText().toString();
             String s=UpdatePrice.getText().toString();
             String updatedQuantity=UpdateQuantity.getText().toString();

             if(s.isEmpty()){

                 updateProduct.setPrice(addproduct.getPrice());

             }else {
                 updateProduct.setPrice(s);

             }
             if(updatedCropName.isEmpty()){
                 updateProduct.setCropName(addproduct.getCropName());
             }
             else {
                 updateProduct.setCropName(updatedCropName);
             }
             if(updatedQuantity.isEmpty()){
                 updateProduct.setQuantity(addproduct.getQuantity());
             }
             else {
                 updateProduct.setQuantity(updatedQuantity+" Kg");
             }
             updateProduct.setCategory(addproduct.getCategory());

             myRef.child(addproduct.getCropid()).setValue(updateProduct);
              Toast.makeText(UpdateYourCrop.this, "updated successfully ", Toast.LENGTH_SHORT).show();
                /*Intent intent1 =new Intent(UpdateYourCrop.this,MyProductActivity.class);
                startActivity(intent1);*/
              Intent intent =new Intent(UpdateYourCrop.this,NavigationDrawer.class);
              startActivity(intent);


          }
      });

    }
}
