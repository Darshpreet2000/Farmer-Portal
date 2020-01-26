package com.example.farmer_portalnew;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmer_portalnew.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PhoneNumberVerify extends AppCompatActivity {
    EditText otp;
    Button login;
    String no;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    User user;
    String sender;
    FirebaseDatabase database;
    private String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_verify);

       sender=getIntent().getExtras().getString("sender");
        if (sender.equals("register")) {
            user= (User) getIntent().getSerializableExtra("User");

            no = user.getPhoneNo();
        }
        else{
            no=getIntent().getExtras().getString("phone");
        }
        otp = (EditText) findViewById(R.id.otp);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        sendVerificationCode(no);

        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = otp.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    otp.setError("Enter valid code");
                    otp.requestFocus();
                    return;
                }

                //verifying the code entered manually
                verifyVerificationCode(code);

            }
        });
    }

    private void sendVerificationCode(String no) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + no,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                otp.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PhoneNumberVerify.this, e.getMessage(), Toast.LENGTH_LONG).show();
           e.printStackTrace();
         }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        try{
            final PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
            //signing the user
            myRef.orderByChild("phoneNo").equalTo(no).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null && sender.equals("register")) {
                        //it means user already registered
                        //Add code to show your prompt
                        Toast.makeText(PhoneNumberVerify.this, "This Number is already registered  Please Login", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(PhoneNumberVerify.this, Login.class));
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    } else if (dataSnapshot.getValue() == null && sender.equals("login")) {
                        Toast.makeText(PhoneNumberVerify.this, "This Number is not registered  Please Register", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(PhoneNumberVerify.this, Register.class));
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    } else if (dataSnapshot.getValue() != null && sender.equals("login")) {
                        //user is exists, just do login
                        signInWithPhoneAuthCredential(credential);
                    }
                    else{
                        signInWithPhoneAuthCredential(credential);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(PhoneNumberVerify.this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            myRef.orderByChild("phoneNo").equalTo(no).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null&&sender.equals("register")){
                                        //it means user already registered
                                        //Add code to show your prompt
                                        Toast.makeText(PhoneNumberVerify.this, "This Number is already registered  Please Login", Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(PhoneNumberVerify.this, Login.class));
                                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                                    }else if(dataSnapshot.getValue() == null&&sender.equals("login")){
                                        Toast.makeText(PhoneNumberVerify.this, "This Number is not registered  Please Register", Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(new Intent(PhoneNumberVerify.this, Register.class));
                                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                                    }
                                    else if(dataSnapshot.getValue() != null&&sender.equals("login")){
                                        //user is exists, just do login
                                        myRef.child(Objects.requireNonNull(mAuth.getUid())).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               user=dataSnapshot.getValue(User.class);
                                               SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                               SharedPreferences.Editor editor = pref.edit();
                                               editor.putString("name",user.getName());
                                               editor.commit();
                                                Toast.makeText(PhoneNumberVerify.this, "Logging Successful", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(PhoneNumberVerify.this, NavigationDrawer.class));
                                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });


                                    }
                                    else{
                                        //It is new users
                                        //write an entry to your user table
                                        //writeUserEntryToDB();
                                        //do create new user
                                        myRef.child(Objects.requireNonNull(mAuth.getUid())).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                                    SharedPreferences.Editor editor = pref.edit();
                                                    editor.putString("name",user.getName());
                                                    editor.commit();
                                                    Toast.makeText(PhoneNumberVerify.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    startActivity(new Intent(PhoneNumberVerify.this, NavigationDrawer.class));
                                                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                                                } else {
                                                    Toast.makeText(PhoneNumberVerify.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                                }

                                            }

                                        });


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });





                            }

                         else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }


                        }
                    }
                });
    }
}