package com.skcoder.ctcenter.ui.syllabus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.notes.NotesViewActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.SyllabusViewHolder> {
    Context context;
    ArrayList<SyllabusModel> list;

    public SyllabusAdapter(Context context, ArrayList<SyllabusModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SyllabusAdapter.SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.syllabus_layout, parent, false);
        return new SyllabusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusAdapter.SyllabusViewHolder holder, final int position) {
        SyllabusModel data = list.get(position);
        holder.semesterTv.setText(data.getSemester());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesViewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("notesTitle", list.get(position).getSemester());
                intent.putExtra("notesUrl", list.get(position).getSyllabusUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SyllabusViewHolder extends RecyclerView.ViewHolder {
        TextView semesterTv;
        public SyllabusViewHolder(@NonNull View itemView) {
            super(itemView);
            semesterTv=itemView.findViewById(R.id.semesterSyllabus);
        }
    }
}
