package org.sltpaya.cartoon.fragment;

import android.support.v7.widget.LinearLayoutManager;

import org.sltpaya.cartoon.adapter.RankTabAdapter;

import java.util.HashMap;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class BaseRankFragment extends BaseTabFragment {

    protected RankTabAdapter adapter;
    protected HashMap<String, String> params;

    @Override
    protected void setRecyclerView() {
        adapter = new RankTabAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        addParams();
        setNetEvent();
    }

    private void addParams() {
        params = new HashMap<>();
        params.put("c", "MainRank");
        params.put("a", "get_label_rank_pic");
        params.put("userid", "0");
        params.put("type", "0");
        params.put("ui", "0");
        params.put("ui_id", "0");
    }

    /**
     * 设置网络数据监听
     */
    protected void setNetEvent() {
    }


}
