package com.outerspace.animatedwolf;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CollisionFAB extends FloatingActionButton implements View.OnTouchListener, CollisionContract.Collisional {
    private float xOffset, x0, dx;
    private float yOffset, y0, dy;
    private float radius;

    private float impulseAngle;
    private float impulseDistance;
    private long impulseDuration;

    private long timeDownEvent;
    private long timeUpEvent;

    private CollisionContract.TossCallback callback;

    public CollisionFAB(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public CollisionFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public CollisionFAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.max(w, h);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            timeDownEvent = System.currentTimeMillis();
            x0 = view.getX();
            y0 = view.getY();
            xOffset = event.getRawX() - view.getX();
            yOffset = event.getRawY() - view.getY();
            //Log.d("ACTION_DOWN X:", " x0 = " + x0 + " = event.getRawX=" + event.getRawX() + " view.getX=" + view.getX());
            //Log.d("ACTION_DOWN Y:", " y0 = " + y0 + " = event.getRawY=" + event.getRawY() + " view.getY=" + view.getY());
            return true;

        case MotionEvent.ACTION_MOVE:
            setX(event.getRawX() - xOffset);
            setY(event.getRawY() - yOffset);
            //Log.d("ACTION_MOVE:", " moveTo = " + (event.getRawX() - xOffset) + " event.getRawX = " + event.getRawX() + " view.getX = " + view.getX());
            return true;

        case MotionEvent.ACTION_UP:
            timeUpEvent = System.currentTimeMillis();
            dx = view.getX() - x0;
            dy = view.getY() - y0;
            impulseDistance = (float) Math.sqrt(dx * dx + dy * dy);
            impulseAngle = (float) Math.atan2( dy, dx );
            impulseDuration = timeUpEvent - timeDownEvent;
            //Log.d("ACTION UP", " DISTANCE=" + impulseDistance + " ANGLE= atan(" + dy + "/" + dx + ")="+ impulseAngle);

            callback.onToss(impulseAngle, impulseDistance, impulseDuration);

            return true;
        }

        return false;
    }

    @Override
    public void setCallback(CollisionContract.TossCallback callback) {
        this.callback = callback;
    }
}
