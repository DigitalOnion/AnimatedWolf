package com.outerspace.animatedwolf;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    ImageView vectorImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        vectorImage = (ImageView) findViewById(R.id.vector_image);
        vectorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate();
            }
        });
    }

    private void rotate() {
        Drawable drawable = vectorImage.getDrawable();
        if(drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            animatable.start();
        }
    }
}
