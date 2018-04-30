package com.outerspace.animatedwolf;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int idxFragment = 0;
    private Fragment[] fragments;
    private ImageView chevronNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[] {
                new ObjectAnimatorFragment(),
                new AnimatedVectorFragment(),
        };

        chevronNext = (ImageView) findViewById(R.id.next_animation);
        chevronNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextFragment();
            }
        });

        loadNextFragment();
    }

    public void loadNextFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_frame, fragments[idxFragment]);
        transaction.commit();

        if(++idxFragment >= fragments.length)
            idxFragment = 0;
    }
}
