package org.sltpaya.cartoon.state;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class BaseState implements HolderState {

    int mType;
    private LayoutInflater mInflater;
    private ViewGroup mParent;

    public static final int BANNER_TYPE = 1;
    public static final int PREVIEW_TYPE = 2;
    public static final int HORIZONTAL_SIX_TYPE = 3;
    public static final int HORIZONTAL_FOUR_TYPE = 4;
    public static final int AD_TYPE = 5;
    public static final int AD_TWO_TYPE = 6;
    public static final int VERTICAL_TYPE = 7;

    @Override
    public void work(HolderManger manger) {
        mInflater = manger.getInflater();
        mParent = manger.getParent();
        mType = manger.getType();
    }

    protected View infalte(@LayoutRes int id) {
        return mInflater.inflate(id, mParent, false);
    }

}
