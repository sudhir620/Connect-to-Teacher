package com.skcoder.ctcenter.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.notification.ShowImageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private Context context;
    private ArrayList<GalleryModel> list;

    public GalleryAdapter(Context context, ArrayList<GalleryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_item_layout, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        final GalleryModel data = list.get(position);

        try{
            Picasso.get().load(data.getImgURL()).into(holder.imgView);
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageViewActivity.class);
                intent.putExtra("Gimg", data.getImgURL());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }
}
