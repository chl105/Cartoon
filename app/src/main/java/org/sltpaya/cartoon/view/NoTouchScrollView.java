package org.sltpaya.cartoon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Author: SLTPAYA
 * Date: 2017/4/4
 */
public class NoTouchScrollView extends ScrollView {

    public NoTouchScrollView(Context context) {
        super(context);
    }

    public NoTouchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
//        super.setOnTouchListener(l);
    }

}
