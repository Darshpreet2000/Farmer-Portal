package com.example.farmer_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.farmer_portal.Classes.Addproduct;

public class NewActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        textView=findViewById(R.id.textView2);
        Intent intent=getIntent();
       Addproduct addproduct=(Addproduct) intent.getSerializableExtra("class");
        //Log.d("msg",addproduct.getName());
       String s=addproduct.getName()+"\n";
        s+=addproduct.getCategory()+"\n";
        s+=addproduct.getQuantity();
        textView.setText(s);

    }
}
