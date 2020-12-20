package com.skcoder.ctcenter.ui.syllabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.chat.EceDepartmentActivity;

public class SyllabusOldActivity extends AppCompatActivity {

    CardView cse_syllabus, mechanical_syllabus, civil_syllabus, electrical_syllabus, automobile_syllabus, ece_syllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_old);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("2011 Onwards Syllabus");

        cse_syllabus = findViewById(R.id.cse_syllabus);
        mechanical_syllabus = findViewById(R.id.mechanical_syllabus);
        civil_syllabus = findViewById(R.id.civil_syllabus);
        electrical_syllabus = findViewById(R.id.electrical_syllabus);
        automobile_syllabus = findViewById(R.id.automobile_syllabus);
        ece_syllabus = findViewById(R.id.ece_syllabus);

        cse_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CseSyllabusActivity.class );
                startActivity(i);
            }
        });

        mechanical_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MechanicalSyllabusActivity.class );
                startActivity(i);
            }
        });

        civil_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CivilSyllabusActivity.class );
                startActivity(i);
            }
        });

        automobile_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AutomobileSyllabusActivity.class );
                startActivity(i);
            }
        });

        electrical_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ElectricalSyllabusActivity.class );
                startActivity(i);
            }
        });

        ece_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EceSyllabusActivity.class );
                startActivity(i);
            }
        });

    }
}