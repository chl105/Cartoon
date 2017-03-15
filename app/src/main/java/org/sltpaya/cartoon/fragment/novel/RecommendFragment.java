package org.sltpaya.cartoon.fragment.novel;

import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import org.sltpaya.cartoon.adapter.novel.RecommendTabAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.cache.NovelCache;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class RecommendFragment extends BaseTabFragment {

    private RecommendTabAdapter adapter;

    @Override
    protected void setRecyclerView() {
        adapter = new RecommendTabAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        dataChanged();
    }

    private void dataChanged() {
        NovelCache.DataSuccessful dataSuccessful = new NetCache.DataSuccessful() {
            @Override
            public void onResponse(SparseArray<Entry> data) {
                String tos = "小说获取数据成功了！！" + data.toString();
                System.out.println(tos);
                Toast.makeText(getContext(), tos, Toast.LENGTH_LONG).show();
                adapter.notifyDataChange();//通过数据成功获取到了，要求设置数据并且改变
            }
        };
        NovelCache.newInstance().setDataListener(dataSuccessful);
    }

}
