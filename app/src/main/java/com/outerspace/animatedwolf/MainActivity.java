package com.outerspace.animatedwolf;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";

    private FrameLayout playground;
    private ImageView wolf;
    private float screenWidth, screenHeight;
    private float wolfWidth, wolfHeight;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wolf = (ImageView) findViewById(R.id.image_wolf_face);
        playground = (FrameLayout) findViewById(R.id.playground);

        playground.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                wolfMove(x, y);
                return true;
            }
        });
    }


    public void onClickWolfFace(View view) {
        float finalX = 0;
        float finalY = 0;

        // notice that this animation is choppy. Both animations are running
        // concurrently, but not orquestrated. The WolfMove puts both animations
        // on the same AnimationSet which makes it smooth.

        ObjectAnimator animation;
        animation = ObjectAnimator.ofFloat(view, TRANSLATION_X, finalX);
        animation.setDuration(1000);
        animation.start();
        animation = ObjectAnimator.ofFloat(view, TRANSLATION_Y, finalY);
        animation.setDuration(1000);
        animation.start();
    }

    public void wolfMove(float x, float y) {
        screenWidth = playground.getWidth();
        screenHeight = playground.getHeight();
        wolfWidth = wolf.getWidth();
        wolfHeight = wolf.getHeight();

        x -= wolfWidth/2;
        y -= wolfHeight/2;

        float maxX = screenWidth - wolfWidth;
        float maxY = screenHeight - wolfHeight;
        float minX = 0.0f;
        float minY = 0.0f;

        if(x > maxX) x = maxX;
        if(x < minX) x = minX;
        if(y > maxY) y = maxY;
        if(y < minY) y = minY;

        ObjectAnimator animX = ObjectAnimator.ofFloat(wolf, TRANSLATION_X, x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(wolf, TRANSLATION_Y, y);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animX)
                .with(animY);
        animSet.start();
    }
}
