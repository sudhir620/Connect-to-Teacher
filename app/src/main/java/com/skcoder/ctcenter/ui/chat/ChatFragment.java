package com.skcoder.ctcenter.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.skcoder.ctcenter.R;

public class ChatFragment extends Fragment {
    CardView csDepartment, meDepartment, ciDepartment, elDepartment, amDepartment, eceDepartment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        csDepartment = view.findViewById(R.id.cs_department);
        meDepartment = view.findViewById(R.id.me_department);
        ciDepartment = view.findViewById(R.id.ci_department);
        elDepartment = view.findViewById(R.id.elDepartment);
        amDepartment = view.findViewById(R.id.amDepartment);
        eceDepartment = view.findViewById(R.id.eceDepartment);

        csDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CsDepartmentActivity.class);
                startActivity(i);
            }
        });

        meDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MeDepartmentActivity.class);
                startActivity(i);
            }
        });

        ciDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CiDepartmentActivity.class);
                startActivity(i);
            }
        });

        elDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ElDepartmentActivity.class);
                startActivity(i);
            }
        });

        amDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AmDepartmentActivity.class);
                startActivity(i);
            }
        });

        eceDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EceDepartmentActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}