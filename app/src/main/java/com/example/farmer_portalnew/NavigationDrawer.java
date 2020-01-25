package com.example.farmer_portalnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.farmer_portalnew.Classes.User;
import com.example.farmer_portalnew.cart.cart;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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

public class NavigationDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private User user;
    private FirebaseAuth mAuth;
    private  NavigationView navigationView;
    ImageView userIcon;
    public  void userprofiler(View view){
        
        Toast.makeText(this, "tapped", Toast.LENGTH_SHORT).show();
    }
    @Override
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
