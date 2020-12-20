package com.skcoder.ctcenter.ui.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.ebooks.model;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> implements Filterable {
    private Context context;
    private ArrayList<NotesModel> list;
    private ArrayList<NotesModel> backup;

    public NotesAdapter(Context context, ArrayList<NotesModel> list) {
        this.context = context;
        this.list = list;
        backup = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_item_layout, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        NotesModel data = list.get(position);
        holder.notesTitle.setText(data.getNotesTitle());
        holder.notesDepartment.setText(data.getDepartment());
        holder.notesSemester.setText(data.getSemester());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesViewActivity.class);
                intent.putExtra("notesTitle", list.get(position).getNotesTitle());
                intent.putExtra("notesUrl", list.get(position).getNotesUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<NotesModel> filtereddata= new ArrayList<>();
            if (keyword.toString().isEmpty()){
                filtereddata.addAll(backup);
            }else{
                for (NotesModel obj : backup){
                    if (obj.getDepartment().toString().toLowerCase().contains(keyword.toString().toLowerCase())
                    || obj.getNotesTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase())
                    || obj.getSemester().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=filtereddata;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<NotesModel>)results.values);
            notifyDataSetChanged();
        }
    };


    public class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView notesTitle, notesDepartment, notesSemester;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            notesTitle = itemView.findViewById(R.id.notesTitle);
            notesDepartment = itemView.findViewById(R.id.notesDepartment);
            notesSemester = itemView.findViewById(R.id.notesSemester);

        }
    }
}
