package com.outerspace.animatedwolf;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * AnimatedVectorFragment uses an Animated Vector to spin a drawable image.
 */

public class AnimatedVectorFragment extends Fragment {
    ImageView vectorImage;

    public AnimatedVectorFragment() { }     // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // fragment_animated_vector has an ImageView whose source (src) is a vector drawable
        // into a vector_animator xml file.
        // the vector_animator describes the animation to perform. In this case it is a
        // rotation.
        // the Duration, Angle, Center, etc is defined in the vector_animator XML which
        // is is used to create an Animatable object, we just call start() on this one to
        // have the animation.

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
