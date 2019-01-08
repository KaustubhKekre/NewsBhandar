package com.example.falcon.newsbhandar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class errorConnect extends AppCompatActivity {
    Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_connect);
        retry=findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(errorConnect.this,MainActivity.class);
               startActivity(intent);
               finish();
            }
        });
    }
}
