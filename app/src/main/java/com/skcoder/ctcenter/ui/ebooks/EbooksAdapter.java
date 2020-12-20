package com.skcoder.ctcenter.ui.ebooks;

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
import com.skcoder.ctcenter.ui.notification.NoticeData;

import java.util.ArrayList;

public class EbooksAdapter extends RecyclerView.Adapter<EbooksAdapter.EbooksViewHolder> implements Filterable {
    private Context context;
    private ArrayList<model> list;
    private ArrayList<model> backup;

    public EbooksAdapter(Context context, ArrayList<model> list) {
        this.context = context;
        this.list = list;
        backup = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public EbooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebooks_item_layout, parent, false);
        return new EbooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbooksViewHolder holder, final int position) {
        final model data= list.get(position);

        holder.pdfName.setText(data.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfName", list.get(position).getTitle());
                intent.putExtra("pdfUrl",list.get(position).getUrl());
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
            ArrayList<model> filtereddata= new ArrayList<>();
            if (keyword.toString().isEmpty()){
                filtereddata.addAll(backup);
            }else{
                for (model obj : backup){
                    if (obj.getTitle().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
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
            list.addAll((ArrayList<model>)results.values);
            notifyDataSetChanged();
        }
    };

    public class EbooksViewHolder extends RecyclerView.ViewHolder{

        private TextView pdfName;

        public EbooksViewHolder(@NonNull View itemView) {
            super(itemView);

            pdfName = itemView.findViewById(R.id.pdf_name);

        }
    }
}
