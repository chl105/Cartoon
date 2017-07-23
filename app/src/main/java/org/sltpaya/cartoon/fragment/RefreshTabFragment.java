package org.sltpaya.cartoon.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;

/**
 * Author: SLTPAYA
 * Date: 2017/4/4
 *
 * 可下拉刷新的Fragment基类
 */
public abstract class RefreshTabFragment extends BaseTabFragment {

    /**下拉刷新控件布局*/
    protected SwipeRefreshLayout mRefreshLayout;
    /* 上一次下拉刷新的值 */
    protected long lastRefreshTime = 0;
    /*数据有效性时间，为60秒*/
    protected long dataEffective = 60000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_layout, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler);
        mRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.refresh_layout);
        initRefreshLayout();
        setRecyclerView();
        initViews();
        return mRootView;
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(0xffffc107);
        mRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        setRefreshLayout();
    }

    protected abstract void setRefreshLayout();

}
