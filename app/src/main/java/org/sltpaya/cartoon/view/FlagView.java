package org.sltpaya.cartoon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class FlagView extends TextView {

    private Paint mPaint;
    private RectF rectF;
    private Path mPath;
    private PaintFlagsDrawFilter filter;

    private int strokeLeft;
    private int strokeTop;
    private int mRadui;
    private int strokeWidth;

    public FlagView(Context context) {
        this(context, null);
    }

    public FlagView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTools();
    }

    private void initTools() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mPaint.setColor(Color.parseColor("#b3b3b3"));
        mPaint.setStyle(Paint.Style.STROKE);
        strokeWidth = 2;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);
        rectF = new RectF();
        mPath = new Path();

        strokeLeft = dpToPx(6);
        strokeTop = dpToPx(2);
        mRadui = 30;
    }

    public void setStrokeWidth(int width) {
        strokeWidth = width;
    }

    public void setStrokePadding(int left, int top) {
        strokeLeft = dpToPx(left);
        strokeTop = dpToPx(top);
    }

    public void setRadui(int radui) {
        mRadui = radui;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.out.println("获取到了view的宽度为"+getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPadding(strokeLeft, strokeTop, strokeLeft, strokeTop);
        canvas.setDrawFilter(filter);
        mPath.reset();
        rectF.set(strokeWidth, strokeWidth+1-1, getWidth()-strokeWidth, getHeight()-strokeWidth);
        mPath.addRoundRect(rectF, mRadui, mRadui, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
    }

    private int dpToPx(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp);
    }

    public int getStrokeLeft() {
        return strokeLeft;
    }

    public int getStrokeTop() {
        return strokeTop;
    }

    public int getmRadui() {
        return mRadui;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

}
