package com.skcoder.ctcenter.ui.youtubeLecture;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.notification.NoticeAdaper;
import com.skcoder.ctcenter.ui.notification.NoticeData;
import com.skcoder.ctcenter.ui.notification.Notification;

import java.util.ArrayList;
import java.util.Collections;

public class YoutubeLacture extends AppCompatActivity {

    RecyclerView youtubeRecyclerView;
    private ArrayList<YoutubeModel> list;
    private YoutubeAdapter adaper;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_lacture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Video Lectures");

        reference = FirebaseDatabase.getInstance().getReference().child("Youtube_Link");

        youtubeRecyclerView = findViewById(R.id.youtube_recyclerView);

        youtubeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        youtubeRecyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    YoutubeModel data = snapshot.getValue(YoutubeModel.class);
                    list.add(data);
                }

                Collections.reverse(list);
                adaper = new YoutubeAdapter(YoutubeLacture.this, list);
                adaper.notifyDataSetChanged();
                youtubeRecyclerView.setAdapter(adaper);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(YoutubeLacture.this, "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}