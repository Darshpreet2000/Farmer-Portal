package com.example.farmer_portalnew.ui;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class add_product extends Fragment {
    DatabaseReference myRef;
    ProgressBar progressBar;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;

    public add_product() {
        // Required empty public constructor
    }
    FloatingActionButton floatingActionButton;
    ProgressBar progressBaraddproduct;
    Spinner spinner;
    EditText name,quantity,CropPrice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_product, container, false);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Products");
        spinner = (Spinner) view.findViewById(R.id.spinnercategory);
        name = (EditText) view.findViewById(R.id.productname);
        progressBaraddproduct = (ProgressBar) view.findViewById(R.id.progressBaraddproduct);
        quantity = (EditText) view.findViewById(R.id.quantity);
        CropPrice=view.findViewById(R.id.PriceOfCrop);


        setupspinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        floatingActionButton=(FloatingActionButton) view.findViewById(R.id.addbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                progressBaraddproduct.setVisibility(View.VISIBLE);
                add_product_to_database();
            }
        });
     return view;
    }
  private void setupspinner(){
      ArrayList<String> arrayList = new ArrayList<>();
      arrayList.add("Choose a Category");
      arrayList.add("Vegetable");
      arrayList.add("Pulses");
      arrayList.add("Fruits");
      arrayList.add("Dry Fruits");
      arrayList.add("Other");
      ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, arrayList){
          @Override
          public boolean isEnabled(int position) {
              if(position ==0){
                  return false;
              }
              return true;
          }
      };
      arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(arrayAdapter);
      spinner.setPrompt("Choose Category");
      spinner.setSelection(0, false);

  }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void add_product_to_database(){
         String productname=name.getText().toString();
         String productquantity=quantity.getText().toString()+" Kg";
         String spinneritem=spinner.getSelectedItem().toString();
         String s=CropPrice.getText().toString();
         if(s.isEmpty()){
             CropPrice.setError("this Field is required");
             CropPrice.requestFocus();
             if(productname.isEmpty()){
                 name.setError("this field is required");
                 name.requestFocus();
             }
             if(productquantity.isEmpty()){
                 quantity.setError("this field is required");
                 quantity.requestFocus();
             }
             if(spinneritem.equals("Choose a Category")){
                 ((TextView)spinner.getSelectedView()).setError("Error message");
                   spinner.requestFocus();
             }
         }
         else{
             if(spinneritem!="Choose a Category") {

                 int PriceOfCrop = 0 + Integer.parseInt(s);

                 String farmerid = mAuth.getUid();
                 Addproduct addproduct = new Addproduct(productname, productquantity, spinneritem, farmerid, PriceOfCrop);
                 myRef.child(Objects.requireNonNull(mAuth.getUid())).push().setValue(addproduct).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         progressBaraddproduct.setVisibility(View.GONE);
                         if (task.isSuccessful()) {
                             Toast.makeText(getContext(), "Added Successful", Toast.LENGTH_SHORT).show();

                         } else {
                             Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });

             }
             else {
                // Toast.makeText(getContext(), "please Select catagory", Toast.LENGTH_SHORT).show();
                 Snackbar.make(getView(),"please Select catagory",2000).show();

             }




         }


    }
}