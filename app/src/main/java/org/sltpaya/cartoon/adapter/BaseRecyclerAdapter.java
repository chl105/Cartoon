package org.sltpaya.cartoon.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Author: SLTPAYA
 * Date: 2017/4/4
 */
public abstract class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<T> {

    /**
     * 是否加载布局内容
     */
    protected boolean mSuccessful = false;

    public void notifyDataChanged(boolean successful) {
        mSuccessful = successful;
        if (successful) {
            notifyDataSetChanged();
        }
    }

}
