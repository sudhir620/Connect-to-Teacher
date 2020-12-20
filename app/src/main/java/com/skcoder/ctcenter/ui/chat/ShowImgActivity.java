package com.skcoder.ctcenter.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.skcoder.ctcenter.R;
import com.squareup.picasso.Picasso;

public class ShowImgActivity extends AppCompatActivity {
    String showImage;
    PhotoView ShowImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);

        Intent intent = getIntent();
        showImage = intent.getStringExtra("msgimg");

        ShowImg = findViewById(R.id.photo_view);

        try {
            Picasso.get().load(showImage).into(ShowImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}