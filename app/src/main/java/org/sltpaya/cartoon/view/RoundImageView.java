package org.sltpaya.cartoon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/3/4
 */
public class RoundImageView extends AppCompatImageView {

    private Paint mPaint;
    private Rect rectSrc;
    private Rect rectDest;
    private float mRadius;
    private Paint mStrokePaint;
    private float mStrokeWidth;
    private int mStrokeColor;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mStrokeWidth = array.getDimension(R.styleable.RoundImageView_mStrokeWidth, 0);
        mRadius = array.getDimension(R.styleable.RoundImageView_radius, 20);
        mStrokeColor = array.getColor(R.styleable.RoundImageView_strokeColor, Color.RED);
        array.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectSrc = new Rect();
        rectDest = new Rect();

        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setColor(mStrokeColor);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 设置外边框的宽度
     *
     * @param mStrokeWidth int 宽度
     */
    public void setmStrokeWidth(int mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
    }

    /**
     * 设置外边框颜色
     * @param mStrokeColor 颜色值16进制
     */
    public void setmStrokeColor(int mStrokeColor) {
        this.mStrokeColor = mStrokeColor;
    }

    public float getmStrokeWidth() {
        return mStrokeWidth;
    }

    public int getmStrokeColor() {
        return mStrokeColor;
    }

    /**
     * 绘制圆角矩形图片
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //调用了ImgeVIew的getDrawable方法
        Drawable drawable = getDrawable();
        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();//将Drawable转为ImageView
            Bitmap newBitmap = getRoundBitmap(bitmap);//调用我们自己的方法，将这个bitmap转为圆角的
            rectSrc.set(0, 0, getWidth(), getHeight());//图片的位置
            rectDest.set(0, 0, getWidth(), getHeight());//画在屏幕上的位置
            mPaint.reset();
            mPaint.setAntiAlias(true);
            canvas.drawBitmap(newBitmap, rectSrc, rectDest, mPaint);
            drawStroke(canvas);
        } else {
            super.onDraw(canvas);
        }
    }


    /**
     * 绘制外部的边框
     */
    private void drawStroke(Canvas canvas) {
        if (mStrokeWidth != 0) {
            Rect rect = canvas.getClipBounds();
            rect.left += mStrokeWidth;
            rect.top += mStrokeWidth;
            rect.bottom -= mStrokeWidth;
            rect.right -= mStrokeWidth;
            RectF rectF = new RectF(rect);
            canvas.drawRoundRect(rectF, mRadius, mRadius, mStrokePaint);
        }
    }

    /**
     * 获取圆角矩形图片方法
     *
     * @return Bitmap
     */
    private Bitmap getRoundBitmap(Bitmap bitmap) {
        //复制一个空白的bitmap，宽高和原来的是一样的
        Bitmap output = Bitmap.createBitmap(getWidth(),
                getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Rect rect = new Rect(0, 0, getWidth(), getHeight());
        RectF rectF = new RectF(rect);

        canvas.drawARGB(0, 0, 0, 0);

        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffffffff);

        //绘制出一个圆角矩形
        canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, mPaint);
        return output;
    }


}
