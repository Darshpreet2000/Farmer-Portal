package com.example.farmer_portalnew;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Objects;

public class MyProductActivity extends AppCompatActivity {
    TextView CropName,ProductType,quantity,Price,Catagory;
    Addproduct addproduct;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    public  void DeleteThisCrop(View view){

        //Toast.makeText(this,addproduct.getCropid(), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(MyProductActivity.this);
        builder.setTitle("Alert ");
        builder.setCancelable(false);
        builder.setMessage("Are You Sure To delete this Crop ?");
        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

           //     myRef.child(addproduct.getCropid()).removeValue();
                Toast.makeText(MyProductActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MyProductActivity.this,NavigationDrawer.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),
                        "No Button Clicked",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();


    }
    public void UpdateYOurCrop(View view){
        Intent intent =new Intent(this,UpdateYourCrop.class);
        intent.putExtra("class", (Serializable) addproduct);
        startActivity(intent);


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);
        CropName = findViewById(R.id.NameOfCrop);
        ProductType=findViewById(R.id.Catagory);
        quantity=findViewById(R.id.quantity);
        Price=findViewById(R.id.MyPrice);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products").child(Objects.requireNonNull(mAuth.getUid()));

        Intent intent = getIntent();

        addproduct = (Addproduct) intent.getSerializableExtra("class");
      //  Toast.makeText(this, addproduct.getCropid().toString(), Toast.LENGTH_LONG).show();
        //Log.d("msg",addproduct.getName());
        String s = "Name:" + addproduct.getCropName();
        CropName.setText(s);
        s = "product type:" + addproduct.getCategory();
        ProductType.setText(s);

        s = "quantity:" + addproduct.getQuantity();
        quantity.setText(s);

        s = "price:" + addproduct.getPrice();
        Price.setText(s);

    }

}
