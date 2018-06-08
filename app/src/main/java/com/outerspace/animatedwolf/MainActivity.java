package com.outerspace.animatedwolf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
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
                new BouncingBallFragment(),
        };

        chevronNext = (ImageView) findViewById(R.id.next_animation);
        chevronNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextFragment();
            }
        });

        WolfPageAdapter adapter = new WolfPageAdapter(
                getSupportFragmentManager(),
                fragments);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_quit_title)
                .setMessage(R.string.dialog_quit_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                         MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }

    public void loadNextFragment() {
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        int page = pager.getCurrentItem();
        page++;
        if(page >= fragments.length)
            page = 0;
        pager.setCurrentItem(page);
    }

    public class WolfPageAdapter extends FragmentPagerAdapter {
        private Fragment[] fragments;

        public WolfPageAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

}
