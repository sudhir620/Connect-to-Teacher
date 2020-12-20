package com.skcoder.ctcenter.ui.gallery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skcoder.ctcenter.R;

import java.util.ArrayList;

public class Gallery extends AppCompatActivity {
    RecyclerView GalleryRecView;
    ArrayList<GalleryModel> list;
    GalleryAdapter adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Gallery");

        reference = FirebaseDatabase.getInstance().getReference().child("Gallery");

        GalleryRecView = findViewById(R.id.gallery_recview);
        GalleryRecView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        GalleryRecView.setHasFixedSize(true);

        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    GalleryModel data = snapshot.getValue(GalleryModel.class);
                    list.add(data);
                }

                adapter = new GalleryAdapter(Gallery.this, list);
                adapter.notifyDataSetChanged();
                GalleryRecView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Gallery.this, "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}