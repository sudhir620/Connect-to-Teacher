package com.skcoder.ctcenter.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skcoder.ctcenter.ui.ebooks.Ebooks;
import com.skcoder.ctcenter.ui.gallery.Gallery;
import com.skcoder.ctcenter.ui.notes.Notes;
import com.skcoder.ctcenter.R;
import com.skcoder.ctcenter.ui.syllabus.Syllabus;
import com.skcoder.ctcenter.ui.teachers.TeacherDetails;
import com.skcoder.ctcenter.ui.youtubeLecture.YoutubeLacture;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import static com.skcoder.ctcenter.R.drawable.firstimg;
import static com.skcoder.ctcenter.R.drawable.secondimg;
import static com.skcoder.ctcenter.R.drawable.thirdimg;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    CardView notes, syllabus, teachers, gallery, ebooks, youtube;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        notes = (CardView) view.findViewById(R.id.notes);
        syllabus = (CardView) view.findViewById(R.id.syllabus);
        teachers = (CardView) view.findViewById(R.id.teacherDetails);
        gallery = (CardView) view.findViewById(R.id.gallery);
        ebooks = (CardView) view.findViewById(R.id.ebooks_card);
        youtube = (CardView) view.findViewById(R.id.youtube_card);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Notes.class);
                startActivity(intent);
            }
        });
        ebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Ebooks.class);
                startActivity(intent);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), YoutubeLacture.class);
                startActivity(intent);
            }
        });
        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Syllabus.class);
                startActivity(intent);
            }
        });
        teachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherDetails.class);
                startActivity(intent);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Gallery.class);
                startActivity(intent);
            }
        });

        setSliderViews();

        return view;
    }

    private void setSliderViews() {
        for (int i = 0; i < 3; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            switch (i) {
                case 0:
                    sliderView.setImageDrawable(firstimg);
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    break;
                case 1:
                    sliderView.setImageDrawable(secondimg);
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                case 2:
                    sliderView.setImageDrawable(thirdimg);
                    sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                    break;
            }

            //sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderLayout.addSliderView(sliderView);

        }
    }

}