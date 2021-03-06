package com.skcoder.ctcenter.ui.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.skcoder.ctcenter.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PdfViewerActivity extends AppCompatActivity {
    private String url,pdfName;
    private PDFView pdfView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfName = getIntent().getStringExtra("pdfName");
        this.setTitle(pdfName);

        url = getIntent().getStringExtra("pdfUrl");

        pdfView = findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.progressBar);
        new PdfDownload().execute(url);
        progressBar.setVisibility(View.VISIBLE);
    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream>{

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
            pdfView.fromStream(inputStream).load();
            progressBar.setVisibility(View.GONE);
        }

    }
}