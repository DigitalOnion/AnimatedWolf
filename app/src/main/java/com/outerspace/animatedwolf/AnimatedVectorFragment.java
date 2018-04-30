package com.outerspace.animatedwolf;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AnimatedVectorFragment extends Fragment {
    ImageView vectorImage;

    public AnimatedVectorFragment() { }     // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_animated_vector, container, false);

        vectorImage = (ImageView) view.findViewById(R.id.vector_image);
        vectorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate();
            }
        });

        return view;
    }

    private void rotate() {
        Drawable drawable = vectorImage.getDrawable();
        if(drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            animatable.start();
        }
    }

}
