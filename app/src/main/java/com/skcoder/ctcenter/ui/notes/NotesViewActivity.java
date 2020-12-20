package com.skcoder.ctcenter.ui.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.skcoder.ctcenter.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotesViewActivity extends AppCompatActivity {

    PDFView NotespdfView;
    String noteUrl, notestitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notestitle = getIntent().getStringExtra("notesTitle");
        this.setTitle(notestitle);

        noteUrl = getIntent().getStringExtra("notesUrl");
        NotespdfView = findViewById(R.id.notesPdfView);
        new notesPdfDownload().execute(noteUrl);
    }
    private class notesPdfDownload extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            NotespdfView.fromStream(inputStream).load();
        }

    }
}