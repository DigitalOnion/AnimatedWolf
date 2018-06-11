package com.outerspace.animatedwolf;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CollisionFragment extends Fragment implements CollisionContract.TossCallback {

    private CollisionFAB[] fabs;

    public CollisionFragment() { }     // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collision,
                container,
                false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        fabs = new CollisionFAB[2];
        fabs[0] = getActivity().findViewById(R.id.fab_a);
        fabs[1] = getActivity().findViewById(R.id.fab_b);

        for(final CollisionContract.Collisional fab : fabs) {
            fab.setCallback(this);
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onToss(float angle, float distance, long millisecs) {

    }


}
