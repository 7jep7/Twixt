package com.nyxxstudios.jonas.twixt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Field extends View {

    boolean touchOn;
    int idX = 0; //default
    int idY = 0; //default

    public Field(Context context, int x, int y) {
        super(context);
        idX = x;
        idY = y;
        init();
    }

    private void init() {
        touchOn = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (touchOn) {
            canvas.drawColor(Color.RED);
        } else {
            canvas.drawColor(Color.GRAY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                touchOn = !touchOn;
                invalidate();

                return true;

            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }

    public int getIdX(){
        return idX;
    }

    public int getIdY(){
        return idY;
    }

}