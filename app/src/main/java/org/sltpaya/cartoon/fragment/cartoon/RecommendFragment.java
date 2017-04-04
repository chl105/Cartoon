package org.sltpaya.cartoon.fragment.cartoon;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.activity.EntryUtils;
import org.sltpaya.cartoon.adapter.cartoon.RecommendTabAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.fragment.RefreshTabFragment;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class RecommendFragment extends RefreshTabFragment {

    private static final String TAG = "RecommendFragment";
    private RecommendTabAdapter adapter;

    @Override
    protected void initViews() {
        dataChanged();
    }

    @Override
    protected void setRecyclerView() {
        adapter = new RecommendTabAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        clickEvent();
        requestNet();
    }

    @Override
    protected void setRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (System.currentTimeMillis() - lastRefreshTime > dataEffective) {
                    System.out.println("执行到了这个方法中！");
                    RecommendCache.getInstance().requestNet();
                } else {
                    mRefreshLayout.setRefreshing(false);
                    Toast.makeText(mContext, "已经是最新数据了！", Toast.LENGTH_SHORT).show();
                }
                lastRefreshTime = System.currentTimeMillis();
            }
        });
    }

    private void clickEvent() {
        adapter.setClickListener(new AdapterItemListener<BookState>() {
            @Override
            public void click(View v, BookState info) {
                String viewType = info.getShowViewType();
                String bookId = info.getBookId();
                EntryUtils.Item item = new EntryUtils.Item(EntryUtils.CARTOON, bookId, viewType);
                EntryUtils.entry(item, getContext());
            }
        });
    }

    private void requestNet() {
        RecommendCache cache = RecommendCache.getInstance();
        if (cache.getData() == null) {
            cache.requestNet();//请求网络数据
        } else {
            cache.notifyData();//使用缓存数据
        }
    }

    /**
     * 下拉刷新处理数据，调用cache.requestNet()就可以通知数据改变了！
     */
    private void dataChanged() {
        RecommendCache.getInstance().setDataListener(new NetCache.DataSuccessful() {
            @Override
            public void onResponse(SparseArray<Entry> data) {
                Toast.makeText(mContext, "联网请求！",Toast.LENGTH_SHORT).show();
                mRefreshLayout.setRefreshing(false);
                adapter.notifyDataChanged(true);
            }
        });
    }

}
