package com.skcoder.ctcenter.ui.assignment;

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
import com.skcoder.ctcenter.ui.notes.NotesModel;
import com.skcoder.ctcenter.ui.notes.NotesViewActivity;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> implements Filterable {
    Context context;
    ArrayList<AssignmentModel> list;
    ArrayList<AssignmentModel> backup;

    public AssignmentAdapter(Context context, ArrayList<AssignmentModel> list) {
        this.context = context;
        this.list = list;
        backup = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assignment_item_layout, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, final int position) {
        AssignmentModel data = list.get(position);
        holder.assignmentTitle.setText(data.getAssignmentTitle());
        holder.assignmentDepartment.setText(data.getDepartment());
        holder.assignmentSemester.setText(data.getSemester());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotesViewActivity.class);
                intent.putExtra("notesTitle", list.get(position).getAssignmentTitle());
                intent.putExtra("notesUrl", list.get(position).getAssignmentUrl());
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
            ArrayList<AssignmentModel> filtereddata= new ArrayList<>();
            if (keyword.toString().isEmpty()){
                filtereddata.addAll(backup);
            }else{
                for (AssignmentModel obj : backup){
                    if (obj.getAssignmentTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase())
                            || obj.getDepartment().toString().toLowerCase().contains(keyword.toString().toLowerCase())
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
            list.addAll((ArrayList<AssignmentModel>)results.values);
            notifyDataSetChanged();
        }
    };

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{
        TextView assignmentTitle, assignmentDepartment, assignmentSemester;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assignmentTitle = itemView.findViewById(R.id.assignment_title);
            assignmentDepartment = itemView.findViewById(R.id.assignment_department);
            assignmentSemester = itemView.findViewById(R.id.assignment_semester);

        }
    }
}
