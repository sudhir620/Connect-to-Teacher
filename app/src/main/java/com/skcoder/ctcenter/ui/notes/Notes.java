package com.skcoder.ctcenter.ui.notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.ebooks.Ebooks;
import com.skcoder.ctcenter.ui.ebooks.EbooksAdapter;
import com.skcoder.ctcenter.ui.ebooks.model;

import java.util.ArrayList;
import java.util.Collections;

public class Notes extends AppCompatActivity {
    RecyclerView notesRecView;
    private ArrayList<NotesModel> list;
    private NotesAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Notes");

        reference = FirebaseDatabase.getInstance().getReference().child("Notes");

        shimmerFrameLayout = findViewById(R.id.shimmer_note_container);
        shimmerLayout = findViewById(R.id.shimmer_note_layout);

        notesRecView = findViewById(R.id.notesRecView);
        notesRecView.setHasFixedSize(true);
        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NotesModel data = snapshot.getValue(NotesModel.class);
                    list.add(data);
                }

                Collections.reverse(list);
                adapter = new NotesAdapter(Notes.this, list);
                notesRecView.setLayoutManager(new LinearLayoutManager(Notes.this));
                notesRecView.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Notes.this, "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}