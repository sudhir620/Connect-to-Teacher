package com.skcoder.ctcenter.ui.youtubeLecture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.notes.NotesModel;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {

    private Context context;
    private ArrayList<YoutubeModel> list;

    public YoutubeAdapter(Context context, ArrayList<YoutubeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.youtube_video_item_layout, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {
        final YoutubeModel data = list.get(position);
        holder.videoTitle.setText(data.getTopic());
        holder.youtubeVideoUrl.loadUrl(data.getYoutube_link());
        holder.fullScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoViewActivity.class);
                intent.putExtra("videoUrl", data.getYoutube_link());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class YoutubeViewHolder extends RecyclerView.ViewHolder{
        TextView videoTitle;
        Button fullScreenBtn;
        WebView youtubeVideoUrl;

        public YoutubeViewHolder(@NonNull View itemView) {
            super(itemView);
            videoTitle = itemView.findViewById(R.id.video_tv);
            fullScreenBtn = itemView.findViewById(R.id.Full_Screen_Btn);
            youtubeVideoUrl = itemView.findViewById(R.id.video_view);
            youtubeVideoUrl.setWebViewClient(new WebViewClient());
            youtubeVideoUrl.setWebChromeClient(new WebChromeClient());
            youtubeVideoUrl.getSettings().setJavaScriptEnabled(true);
        }
    }
}
