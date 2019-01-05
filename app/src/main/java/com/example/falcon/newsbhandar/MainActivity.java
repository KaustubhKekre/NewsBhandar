package com.example.falcon.newsbhandar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        txt=findViewById(R.id.txt);
        if(mUser==null)
        {
            startActivity(new Intent(MainActivity.this,signInOptions.class));
            finish();
        }
        else
        {
            String name=mUser.getDisplayName();
            Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_LONG).show();
            txt.setVisibility(View.VISIBLE);
        }


    }
}
