package org.sltpaya.cartoon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 *
 * 分割线View--虚线分割线
 *
 */
public class DashView extends View {

    private Paint mPaint;
    private Path mPath;

    public DashView(Context context) {
        this(context, null);
    }

    public DashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.parseColor("#6a6a6a"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 60;
        for (int i = 1; i <= 60; i += 2) {
            int now = i * width;
            int next = (i + 1) * width;
            mPath.reset();
            mPath.moveTo(now, 0);
            mPath.lineTo(next, 0);
            canvas.drawPath(mPath, mPaint);
        }
    }

}
