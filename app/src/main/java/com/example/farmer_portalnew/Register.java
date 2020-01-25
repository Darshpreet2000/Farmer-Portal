package com.example.farmer_portalnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.farmer_portalnew.Classes.User;

import java.util.ArrayList;

public class Register extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference myRef;
    ProgressBar progressBar;
    FirebaseDatabase database;
    EditText editTextEmail, editTextPassword,editTextName,editTextPhoneNumber,editTextIndustry,editTextArea;
    private FirebaseAuth mAuth;
    Spinner RegisterAs;
    Spinner Language;

   private void setupLanguageSpinner(){
       ArrayList<String> arrayList1 = new ArrayList<>();
       arrayList1.add("Choose Language");
       arrayList1.add("English");
       arrayList1.add("हिन्दी");
       ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList1){

           @Override
           public boolean isEnabled(int position) {
               if(position==0){
                   return  false;
               }
               else{
                   return true;
               }
           }
       };
       arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       Language.setAdapter(arrayAdapter1);
       Language.setPrompt("Choose Language");
       Language.setSelection(0, false);

   }
    private void setupspinner(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Register As");
        arrayList.add("I Am Farmer");
        arrayList.add("I Am Buyer");
        arrayList.add("I Am Driver");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arrayList){
            @Override
            public boolean isEnabled(int position) {
                if(position ==0){
                    return false;
                }
                return true;
            }
        };
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RegisterAs.setAdapter(arrayAdapter);
        RegisterAs.setPrompt("Choose Category");
        RegisterAs.setSelection(0, false);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editTextName = (EditText) findViewById(R.id.FullName);

        editTextPhoneNumber = (EditText) findViewById(R.id.Phonenumber);

        editTextArea = (EditText) findViewById(R.id.Area);
        RegisterAs=findViewById(R.id.RegisterAs);
        Language=findViewById(R.id.Language);



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        setupspinner();
        setupLanguageSpinner();

        RegisterAs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
        Language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        if(mAuth.getCurrentUser()!=null){
            Intent intent=new Intent(getApplicationContext(), NavigationDrawer.class);
            startActivity(intent);
            finish();
        }

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String phonenumber = editTextPhoneNumber.getText().toString().trim();
        String RegisteredAs=RegisterAs.getSelectedItem().toString();
        String UserLanguage=Language.getSelectedItem().toString();


        final String area = editTextArea.getText().toString().trim();
        if(RegisteredAs=="Register As"){
            Toast.makeText(this, "Please Select Register As", Toast.LENGTH_SHORT).show();

        }
        if(RegisteredAs=="Choose Language"){
            Toast.makeText(this, "Please Select Your Language", Toast.LENGTH_SHORT).show();


        }

      if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        if (phonenumber.length() != 10) {
            editTextPhoneNumber.setError("Please Enter Valid Phone Number");
            editTextPhoneNumber.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if(RegisteredAs!="Register As"&&UserLanguage!="Choose Language")
        {
            Intent intent=new Intent(Register.this,PhoneNumberVerify.class);
            User user =new User(area,email,name,password,phonenumber,RegisteredAs,UserLanguage);
     //       User user =new User(name,email,phonenumber,area,RegisteredAs);///
            intent.putExtra("User", user);
            intent.putExtra("sender","register");
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
           // finish();
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;

            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, Login.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;
        }
    }

}