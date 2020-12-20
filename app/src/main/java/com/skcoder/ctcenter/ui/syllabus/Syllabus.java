package com.skcoder.ctcenter.ui.syllabus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.skcoder.ctcenter.R;

public class Syllabus extends AppCompatActivity {
    CardView syllabusOld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Syllabus");

        syllabusOld = findViewById(R.id.syllabus_old);

        syllabusOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SyllabusOldActivity.class);
                startActivity(i);
            }
        });

    }
}