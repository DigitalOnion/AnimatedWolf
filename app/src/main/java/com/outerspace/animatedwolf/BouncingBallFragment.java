package com.outerspace.animatedwolf;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.util.Log;
import android.util.Property;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * BouncingBall shows the programmatic use of the Property Animation
 */

public class BouncingBallFragment extends Fragment {

    private FloatingActionButton fab;
    private float screenW;      // whole screen
    private float screenH;
    private float arenaW;       // BouncingBallFragment is built on a FrameLayout id=bouncing_arena
    private float arenaH;
    private float fabDiameter;


    public BouncingBallFragment() { }     // Required empty public constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(
                R.layout.fragment_bouncing_ball,
                container,
                false);

    }

    @SuppressLint("ClickableViewAccessibility")  // this is so it does not ask to override performClick
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d("BOUNCING BALL FRAGMENT", "view class:"  + view.getClass().getName());

        // to get the size of the Display
        Display display = view.getDisplay();
        Rect rect = new Rect();
        display.getRectSize(rect);
        screenW = rect.width();
        screenH = rect.height();

        // To my eyes, getHeight() and getWidth() are buggy. They always return zero, except
        // when the view is drawn. So to retrieve the dimensions we use a ViewTreeObserver
        // we can add a listener to view, which is the top group-view in the xml fragment

        final View topView = view;
        ViewTreeObserver observer = view.getViewTreeObserver();
        if(observer.isAlive()) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    arenaW = topView.getWidth();
                    arenaH = topView.getHeight();
                    topView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        // the animation is done in a FAB, we start it by clicking on it...

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFabAnimation();
            }
        });

        // The fab.getHeight is buggy (as I see it). Its value is always zero except
        // when it is being drawn - or something like that - and we can only get it
        // with a ViewTreeObserver like this:

        observer = fab.getViewTreeObserver();
        if(observer.isAlive()) {
            observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    fabDiameter = fab.getHeight();
                    fab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        Rect rect = new Rect();
        View view = getActivity().findViewById(R.id.bouncing_arena);
        view.getDrawingRect(rect);
        screenH = rect.height();
        screenW = rect.width();

        super.onResume();
    }

    public void startFabAnimation() {

        // very simple one dimension bouncing animation.

//        ObjectAnimator fabAnimator = ObjectAnimator.ofFloat(fab, View.Y,  arenaH - fabDiameter);
//        fabAnimator.setDuration(1000);
//        fabAnimator.setRepeatCount(1);
//        fabAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        fabAnimator.start();

        // Back and forth continuous bouncing animation

        // X component
        ObjectAnimator fabXAnimator = ObjectAnimator.ofFloat(fab, View.X,  arenaW - fabDiameter);
        fabXAnimator.setDuration(1000);
        fabXAnimator.setRepeatCount(ValueAnimator.INFINITE);
        fabXAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // Y component
        ObjectAnimator fabYAnimator = ObjectAnimator.ofFloat(fab, View.Y,  arenaH - fabDiameter);
        fabYAnimator.setDuration(1000);
        fabYAnimator.setRepeatCount(ValueAnimator.INFINITE);
        fabYAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // AnimatorSet will orquestrate the animation of the two (X, Y) components
        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(fabXAnimator, fabYAnimator);
        animSet.start();
    }


}
