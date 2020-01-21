package com.example.farmer_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity  implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword,editTextName,editTextStartupName,editTextWebsite,editTextPhoneNumber,editTextIndustry,editTextArea;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    private void userLogin() {
        String phonenumber= editTextEmail.getText().toString().trim();
        if (phonenumber.length() != 10) {
            editTextEmail.setError("Please Enter Valid Phone Number");
            editTextEmail.requestFocus();
            return;
        }
     Intent intent=new Intent(Login.this,PhoneNumberVerify.class);
        intent.putExtra("phone",phonenumber);
        intent.putExtra("sender","login");

        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, NavigationDrawer.class));
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this, Register.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                break;

            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
