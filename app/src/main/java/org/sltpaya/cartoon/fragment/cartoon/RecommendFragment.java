package org.sltpaya.cartoon.fragment.cartoon;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.activity.EntryUtils;
import org.sltpaya.cartoon.adapter.cartoon.RecommendTabAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class RecommendFragment extends BaseTabFragment {

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
        //请求网络数据
        RecommendCache cache = RecommendCache.newInstance();
        if (cache.getData() == null) {
            cache.requestNet();
        } else {
            Toast.makeText(mContext, "漫画-推荐数据存在!", Toast.LENGTH_SHORT).show();
            cache.notifyData();
        }
    }

    /**
     * 下拉刷新处理数据，传入True就可以通知数据改变了！
     */
    private void dataChanged() {
        RecommendCache.DataSuccessful dataSuccessful = new RecommendCache.DataSuccessful() {
            @Override
            public void onResponse(SparseArray<Entry> data) {
                String toast = "联网请求漫画-推荐 数据全部获取成功！" + data.toString();
                Log.i(TAG, toast);
                Log.i(TAG, "onResponse: 正在通知适配器传递数据！");
                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
                adapter.notifyDataChanged(true);//通过数据成功获取到了，要求设置数据并且改变
            }
        };
        RecommendCache.newInstance().setDataListener(dataSuccessful);
    }

}
