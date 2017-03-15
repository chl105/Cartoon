package org.sltpaya.cartoon.fragment;

import android.support.v7.widget.GridLayoutManager;

import org.sltpaya.cartoon.adapter.ClassifyTabAdapter;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class BaseClassify extends BaseTabFragment {

    protected ClassifyTabAdapter adapter;

    @Override
    protected void setRecyclerView() {
        adapter = new ClassifyTabAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(adapter);
        setNetEvent();
    }

    /**
     * 设置网络数据监听
     */
    protected void setNetEvent() {
    }


}
