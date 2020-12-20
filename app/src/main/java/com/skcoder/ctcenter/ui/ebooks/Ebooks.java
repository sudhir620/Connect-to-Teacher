package com.skcoder.ctcenter.ui.ebooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

public class Ebooks extends AppCompatActivity {

    private RecyclerView ebookRecyclerView;
    private ArrayList<model> list;
    private EbooksAdapter adapter;

    private DatabaseReference reference;

    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebooks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Ebooks");

        reference = FirebaseDatabase.getInstance().getReference().child("Ebooks");

        ebookRecyclerView = (RecyclerView) findViewById(R.id.ebooks_recyclerview);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout = findViewById(R.id.shimmer_layout);

        ebookRecyclerView.setHasFixedSize(true);

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    model data = snapshot.getValue(model.class);
                    list.add(data);
                }

                Collections.reverse(list);
                adapter = new EbooksAdapter(Ebooks.this, list);
                ebookRecyclerView.setLayoutManager(new LinearLayoutManager(Ebooks.this));
                ebookRecyclerView.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                shimmerLayout.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Ebooks.this, "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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