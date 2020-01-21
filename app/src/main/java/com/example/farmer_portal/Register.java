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
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editTextName = (EditText) findViewById(R.id.FullName);

        editTextPhoneNumber = (EditText) findViewById(R.id.Phonenumber);

        editTextArea = (EditText) findViewById(R.id.Area);

        mAuth = FirebaseAuth.getInstance();
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

        Intent intent=new Intent(Register.this,PhoneNumberVerify.class);
        User user =new User(name,email,phonenumber,area);
        intent.putExtra("User", user);
        intent.putExtra("sender","register");
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
        startActivity(intent);
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