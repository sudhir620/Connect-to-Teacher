package com.skcoder.ctcenter.ui.about;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skcoder.ctcenter.R;

public class AboutFragment extends Fragment {

    RelativeLayout profileRelativeView;
    TextView senFeedback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        profileRelativeView = view.findViewById(R.id.profileRelativeView);
        senFeedback = view.findViewById(R.id.senFeedback);

        profileRelativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                myDialogFragment.show(getFragmentManager(), "aboutFragment");
            }
        });
        senFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:sudhirsharma620@gmail.com")));
            }
        });



        return view;
    }
}