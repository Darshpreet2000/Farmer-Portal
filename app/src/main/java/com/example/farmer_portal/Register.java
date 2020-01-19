package com.example.farmer_portal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.farmer_portal.Classes.User;

import java.util.Objects;

public class Register extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference myRef;
    ProgressBar progressBar;
    FirebaseDatabase database;
    EditText editTextEmail, editTextPassword,editTextName,editTextPhoneNumber,editTextIndustry,editTextArea;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Register.this, PhoneNumberVarification.class));
            finish();
        }
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editTextName = (EditText) findViewById(R.id.FullName);

        editTextPhoneNumber = (EditText) findViewById(R.id.Phonenumber);

        editTextArea = (EditText) findViewById(R.id.Area);

        //mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User_details");

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);

    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();
        final String phonenumber = editTextPhoneNumber.getText().toString().trim();

        final String area = editTextArea.getText().toString().trim();

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

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    User user =new User(name,email,phonenumber,area);
                    myRef.child(Objects.requireNonNull(mAuth.getUid())).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Register.this,PhoneNumberVarification.class));
                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            }
                            else{
                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {

                    task.getException();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

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