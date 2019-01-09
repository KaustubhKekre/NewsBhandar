package com.example.falcon.newsbhandar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailedNews extends AppCompatActivity {
    ImageView detailedImage;
    TextView detailedTitle,detailedDescription,detailedHyperlink;
    protected void onCreate(Bundle savedInstanceState) {
        String imgUrl= getIntent().getStringExtra("imgUrl");
        String title= getIntent().getStringExtra("title");
        final String description= getIntent().getStringExtra("description");
        final String hyperlink= getIntent().getStringExtra("hyperlink");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_news);
        detailedImage=findViewById(R.id.detailedImage);
        detailedTitle=findViewById(R.id.detailedTitle);
        detailedDescription=findViewById(R.id.detailedDescription);
        detailedHyperlink=findViewById(R.id.detailedHyperlink);
        detailedTitle.setText(title);
        detailedDescription.setText(description+"\n\nContinue Reading at-");
        detailedHyperlink.setText(hyperlink);
        Glide.with(this).load(imgUrl).into(detailedImage);
        detailedHyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(hyperlink));
                startActivity(i);
            }
        });

    }
}