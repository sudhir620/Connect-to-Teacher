package com.skcoder.ctcenter.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.skcoder.ctcenter.R;
import com.squareup.picasso.Picasso;

public class ImageViewActivity extends AppCompatActivity {

    PhotoView GphotoView;
    String showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Intent intent = getIntent();
        showImage = intent.getStringExtra("Gimg");

        GphotoView = findViewById(R.id.gallery_photo_view);
        try {
            Picasso.get().load(showImage).into(GphotoView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}