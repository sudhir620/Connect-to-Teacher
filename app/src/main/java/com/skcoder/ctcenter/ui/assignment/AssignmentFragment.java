package com.skcoder.ctcenter.ui.assignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.notes.Notes;

import java.util.ArrayList;

public class AssignmentFragment extends Fragment {
    RecyclerView aRecView;
    ArrayList<AssignmentModel> list;
    AssignmentAdapter adapter;
    private DatabaseReference reference;
    FloatingActionButton fab;
    SearchView searchView;

    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);

        fab = view.findViewById(R.id.aSearch);
        searchView = view.findViewById(R.id.searchView);

        shimmerFrameLayout = view.findViewById(R.id.shimmer_assignment_container);
        linearLayout = view.findViewById(R.id.shimmer_assignment_layout);

        reference = FirebaseDatabase.getInstance().getReference().child("assignment");
        aRecView = view.findViewById(R.id.aRecView);
        aRecView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        aRecView.setHasFixedSize(true);
        getdata();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
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

            }
        });

        return view;
    }

    private void getdata() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AssignmentModel data = snapshot.getValue(AssignmentModel.class);
                    list.add(data);
                }
                adapter = new AssignmentAdapter(getContext(), list);
                adapter.notifyDataSetChanged();
                aRecView.setAdapter(adapter);
                shimmerFrameLayout.stopShimmer();
                linearLayout.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error : "+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}