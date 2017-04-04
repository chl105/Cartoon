package org.sltpaya.cartoon.fragment.novel;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;

import org.sltpaya.cartoon.adapter.novel.RecommendTabAdapter;
import org.sltpaya.cartoon.fragment.RefreshTabFragment;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class RecommendFragment extends RefreshTabFragment {

    private RecommendTabAdapter adapter;

    @Override
    protected void setRecyclerView() {
        adapter = new RecommendTabAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        dataChanged();
        requestNet();
    }

    @Override
    protected void setRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (System.currentTimeMillis() - lastRefreshTime > dataEffective) {
                    System.out.println("执行到了这个方法中！");
                    NovelCache.getInstance().requestNet();
                } else {
                    mRefreshLayout.setRefreshing(false);
                    Toast.makeText(mContext, "已经是最新数据了！", Toast.LENGTH_SHORT).show();
                }
                lastRefreshTime = System.currentTimeMillis();
            }
        });
    }

    private void requestNet() {
        NovelCache cache = NovelCache.getInstance();
        if (cache.getData() == null) {
            cache.requestNet();//请求网络数据
        } else {
            cache.notifyData();//使用缓存数据
        }
    }

    private void dataChanged() {
        NovelCache.getInstance().setDataListener(new NetCache.DataSuccessful() {
            @Override
            public void onResponse(SparseArray<Entry> data) {
                Toast.makeText(mContext, "小说联网请求！", Toast.LENGTH_SHORT).show();
                mRefreshLayout.setRefreshing(false);
                adapter.notifyDataChanged(true);//通过数据成功获取到了，要求设置数据并且改变
            }
        });
    }
}
