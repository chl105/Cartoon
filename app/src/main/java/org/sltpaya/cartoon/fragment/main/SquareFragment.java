package org.sltpaya.cartoon.fragment.main;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.SquareAdapter;
import org.sltpaya.cartoon.net.cache.NetCache;
import org.sltpaya.cartoon.net.cache.SquareCache;
import org.sltpaya.cartoon.net.entry.Entry;

/**
 * Author: SLTPAYA
 * Date: 2017/2/28
 */
public class SquareFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private SquareAdapter adapter;

    @Override
    protected void inflateAppBar(ViewGroup appbarContainer) {
        getLayoutInflater().inflate(R.layout.square_bar, appbarContainer, true);
    }

    @Override
    protected void inflateContent(ViewGroup contentContainer) {
        View inflate = getLayoutInflater().inflate(R.layout.recycler_layout, contentContainer, true);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recycler);
        setRecyclerView();
    }

    private void setRecyclerView() {
        adapter = new SquareAdapter();
        mRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        setNetEvent();
    }

    private void setNetEvent() {
        SquareCache cache = SquareCache.newInstance();
        cache.requestNet();
        cache.setDataListener(new NetCache.DataSuccessful() {
            @Override
            public void onResponse(SparseArray<Entry> data) {
                adapter.notifyDataChanged(data);
            }
        });
    }

}
