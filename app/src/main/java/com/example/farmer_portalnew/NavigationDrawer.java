package com.example.farmer_portalnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.farmer_portalnew.Classes.User;
import com.example.farmer_portalnew.cart.cart;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class NavigationDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private User user;
    private FirebaseAuth mAuth;
    private  NavigationView navigationView;
    private static final int CAMERA_REQUEST = 0;
    private static final int IMAGE_PICK = 1;
    ImageView userIcon;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 50, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
    public  void userprofilIcon(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, IMAGE_PICK);

       /* Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                bitmap= Bitmap.createScaledBitmap(bitmap, 160, 160, true);
                SharedPreferences myPrefrence = getApplicationContext().getSharedPreferences("USER_ICON", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefrence.edit();
                editor.putString("imagePreferance", encodeTobase64(bitmap));
                editor.commit();
                userIcon=findViewById(R.id.UserIcon);
                userIcon.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);

         navigationView = findViewById(R.id.nav_view);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        TextView farmername=(TextView) navigationView.getHeaderView(0).findViewById(R.id.farmername);
       farmername.setText( pref.getString("name","Farmer_Name"));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.crops)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        SharedPreferences shared = getSharedPreferences("USER_ICON", MODE_PRIVATE);
        String channel = (shared.getString("imagePreferance", ""));
        userIcon=navigationView.getHeaderView(0).findViewById(R.id.UserIcon);


          if(!channel.isEmpty()&&userIcon.getDrawable().getConstantState().equals
                (getResources().getDrawable(R.drawable.user_image).getConstantState())) {
              //Toast.makeText(this, "inside null"+channel, Toast.LENGTH_SHORT).show();
              Bitmap bitmap= decodeBase64(channel);
             userIcon.setImageBitmap(bitmap);
          }
       // userIcon.setImageBitmap(bitmap);

    }





    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_options,menu);
       // inflater.inflate(R.menu.activity_navigation_drawer_drawer,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     if(item.getItemId()==R.id.signout) {
         AuthUI.getInstance()
                 .signOut(this)
                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                     public void onComplete(@NonNull Task<Void> task) {
                         // user is now signed out
                         startActivity(new Intent(NavigationDrawer.this, Login.class));
                         finish();
                     }
                 });
      return true;
     }
     else if(item.getItemId()==R.id.cart){
         startActivity(new Intent(NavigationDrawer.this, cart.class));
     }
        return super.onOptionsItemSelected(item);
    }


}
