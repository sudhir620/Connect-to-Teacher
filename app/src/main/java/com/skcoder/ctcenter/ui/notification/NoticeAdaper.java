package com.skcoder.ctcenter.ui.notification;

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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdaper extends RecyclerView.Adapter<NoticeAdaper.NoticeViewHolder> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdaper(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_item_layout, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, final int position) {
        final NoticeData currentItem = list.get(position);

        holder.noticeTitle.setText(currentItem.getTitle());
        holder.noticeTime.setText(currentItem.getTime());
        holder.noticeDate.setText(currentItem.getDate());

        try {
            if (currentItem.getImage() != null)
            Picasso.get().load(currentItem.getImage()).into(holder.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("img", currentItem.getImage());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {
        private TextView noticeTitle;
        private TextView noticeTime;
        private TextView noticeDate;
        private ImageView noticeImage;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);

            noticeTitle = itemView.findViewById(R.id.notice_title);
            noticeTime = itemView.findViewById(R.id.notice_time);
            noticeDate = itemView.findViewById(R.id.notice_date);
            noticeImage = itemView.findViewById(R.id.notice_img);
        }
    }
}
