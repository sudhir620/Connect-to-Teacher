package com.skcoder.ctcenter.ui.chat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.chat.ShowImgActivity;
import com.skcoder.ctcenter.ui.chat.models.CsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CsAdapter extends RecyclerView.Adapter<CsAdapter.CsViewHolder> {
    Context context;
    ArrayList<CsModel> list;

    public CsAdapter(Context context, ArrayList<CsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_item_layout, parent, false);
        return new CsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CsViewHolder holder, int position) {
        final CsModel data = list.get(position);
        holder.senderName.setText(data.getSender_name());
        holder.message.setText(data.getMessage());

        try {
            if(data.getImgUrl() != null){
                holder.message.setVisibility(View.GONE);
                holder.msgImg.setVisibility(View.VISIBLE);
                Picasso.get().load(data.getImgUrl()).into(holder.msgImg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.msgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowImgActivity.class);
                intent.putExtra("msgimg", data.getImgUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CsViewHolder extends RecyclerView.ViewHolder{
        TextView senderName, message;
        ImageView msgImg;

        public CsViewHolder(@NonNull View itemView) {
            super(itemView);
            senderName = itemView.findViewById(R.id.sender_username);
            message = itemView.findViewById(R.id.Rmessage);
            msgImg = itemView.findViewById(R.id.msgImg);
        }
    }
}
