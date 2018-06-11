package com.outerspace.animatedwolf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class RuledFrameLayout extends FrameLayout {
    private int ZERO = 0;
    private int width = 0;
    private int height = 0;

    private Paint paint = null;

    public RuledFrameLayout(@NonNull Context context) { super(context); }

    public RuledFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RuledFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RuledFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Resources.Theme theme = getContext().getTheme();
        int colorLines = getResources().getColor(R.color.colorPrimary, theme);

        if(paint == null) {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(colorLines);
            paint.setStrokeWidth(1);
        }

        for(int x = 100; x < width; x += 100) {
            canvas.drawLine( x, ZERO, x, height, paint );
        }

        for(int y = 100; y < height; y += 100) {
            canvas.drawLine( ZERO, y, width, y, paint);
        }

        super.onDraw(canvas);
    }
}
