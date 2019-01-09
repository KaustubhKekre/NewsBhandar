package com.example.falcon.newsbhandar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Business.OnFragmentInteractionListener, Entertainment.OnFragmentInteractionListener, General.OnFragmentInteractionListener, Headlines.OnFragmentInteractionListener, Health.OnFragmentInteractionListener, Science.OnFragmentInteractionListener, Sports.OnFragmentInteractionListener, Technology.OnFragmentInteractionListener {

    static retrofitNews rt;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView name, email, countryNameDraw;
    ImageView img;
    String uName, uEmail;
    private DrawerLayout main_nav_drawer;
    Uri uImg;
    boolean connection;
    NavigationView nav_View;
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    String selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        super.onCreate(savedInstanceState);
        connection = haveNetworkConnection();
        if (connection == false) {
            Toast.makeText(MainActivity.this, "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, errorConnect.class);
            startActivity(intent);
            finish();
        } else {
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
            countryNameDraw = headerView.findViewById(R.id.countryNameDraw);

            if (mUser == null) {
                startActivity(new Intent(MainActivity.this, signInOptions.class));
                finish();
            } else {
                selectedCountry = pref.getString("country", null);
                if (selectedCountry == null) {
                    Intent intent = new Intent(MainActivity.this, countrySelection.class);
                    startActivity(intent);
                    finish();
                } else {
                    TabLayout layout = findViewById(R.id.TabLayout);
                    layout.addTab(layout.newTab().setText("Headlines"));
                    layout.addTab(layout.newTab().setText("General"));
                    layout.addTab(layout.newTab().setText("Science"));
                    layout.addTab(layout.newTab().setText("Technology"));
                    layout.addTab(layout.newTab().setText("Entertainment"));
                    layout.addTab(layout.newTab().setText("Business"));
                    layout.addTab(layout.newTab().setText("Health"));
                    layout.addTab(layout.newTab().setText("Sports"));

                    final ViewPager viewPager = findViewById(R.id.ViewPager);
                    final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), layout.getTabCount());
                    viewPager.setAdapter(adapter);
                    viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));
                    layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());

                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });


                    android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(this, main_nav_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                    main_nav_drawer.addDrawerListener(toggle);
                    toggle.syncState();
                    uName = mUser.getDisplayName();
                    if (uName != null) {
                        name.setText(uName);
                    } else {
                        name.setText("User");
                    }
                    uEmail = mUser.getEmail();
                    if (uEmail != null) {
                        email.setText(uEmail);
                    } else {
                        email.setVisibility(View.GONE);
                    }
                    uImg = mUser.getPhotoUrl();
                    countryNameDraw.setText(selectedCountry);
                    if (uImg != null) {
                        Glide.with(MainActivity.this)
                                .load(uImg)
                                .into(img);

                    }



                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).build();
                    rt = retrofit.create(retrofitNews.class);

                }
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

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}