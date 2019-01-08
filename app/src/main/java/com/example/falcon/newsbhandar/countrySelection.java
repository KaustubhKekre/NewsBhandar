package com.example.falcon.newsbhandar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class countrySelection extends AppCompatActivity {
    RecyclerView recyclerView;
    Button countrySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_selection);
        recyclerView=findViewById(R.id.recyclerView);
        countrySelect=findViewById(R.id.countrySelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CountryAdapter());
        countrySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(countrySelection.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}


