package com.skcoder.ctcenter.ui.syllabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skcoder.ctcenter.R;

import java.util.ArrayList;

public class AutomobileSyllabusActivity extends AppCompatActivity {
    RecyclerView AmRecView;
    ArrayList<SyllabusModel> list;
    SyllabusAdapter adapter;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_syllabus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Automobile Syllabus");

        reference = FirebaseDatabase.getInstance().getReference().child("syllabus").child("AE");

        AmRecView = findViewById(R.id.AmRecView);
        AmRecView.setHasFixedSize(true);
        AmRecView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }
    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    SyllabusModel data = snapshot.getValue(SyllabusModel.class);
                    list.add(data);
                }

                adapter = new SyllabusAdapter(getApplicationContext(), list);
                AmRecView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error :"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}