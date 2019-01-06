package com.example.falcon.newsbhandar;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView name, email;
    ImageView img;
    String uName, uEmail;
    private DrawerLayout main_nav_drawer;
    Uri uImg;
    NavigationView nav_View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        main_nav_drawer = findViewById(R.id.main_nav_drawer);
        nav_View = findViewById(R.id.nav_view);
        View headerView = nav_View.getHeaderView(0);
        name = headerView.findViewById(R.id.name);
        email = headerView.findViewById(R.id.email);
        img = headerView.findViewById(R.id.img);
        if (mUser == null) {
            startActivity(new Intent(MainActivity.this, signInOptions.class));
            finish();
        } else {
            android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(this, main_nav_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            main_nav_drawer.addDrawerListener(toggle);
            toggle.syncState();
            uName = mUser.getDisplayName();
            if(!uName.isEmpty()){
                name.setText(uName);
            }
            else
            {
                name.setText("User");
            }
            uEmail = mUser.getEmail();
            if(!uEmail.isEmpty())
            {
                email.setText(uEmail);
            }
            else
            {
                email.setVisibility(View.GONE);
            }
            uImg = mUser.getPhotoUrl();
            if(uImg!=null){
                Glide.with(MainActivity.this)
                        .load(uImg)
                        .into(img);

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (main_nav_drawer.isDrawerOpen(GravityCompat.START)) {
            main_nav_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
