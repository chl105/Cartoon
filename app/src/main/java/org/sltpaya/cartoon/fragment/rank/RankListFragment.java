package org.sltpaya.cartoon.fragment.rank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.activity.EntryUtils;
import org.sltpaya.cartoon.adapter.rank.RankItemAdapter;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.rank.RankItemEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.http.NovelHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class RankListFragment extends Fragment implements NetListener {

    private RecyclerView mRecyclerView;
    private String mLabels;
    private RankItemAdapter adapter;
    private String classType;

    @Override
    public void setArguments(Bundle args) {
        mLabels = args.getString("info");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_layout, container, false);
        setRecyclerView();
        return mRecyclerView;
    }

    /**
     * 设置RecyclerView的具体信息
     */
    private void setRecyclerView() {
        adapter = new RankItemAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.addListener(new AdapterItemListener<BookState>() {
            @Override
            public void click(View v, BookState info) {
                String bookId = info.getBookId();
                String classType = info.getClassType();
                String viewType = info.getShowViewType();
                EntryUtils.Item item = new EntryUtils.Item(classType, bookId, viewType);
                EntryUtils.entry(item, getContext());
            }
        });
        requestNet();
    }

    /**
     * 该方法请求网络数据
     */
    private void requestNet() {
        String[] split = mLabels.split(",");
        String label = split[0];
        String type = split[1];
        classType = split[2];
        if ("cartoon".equals(classType)) {
            requestCartoonData(label, type);
        }else {
            requestNovelData(label, type);
        }
    }

    private void requestCartoonData(String label, String type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRank");
        params.put("a", "get_label_rank");
        params.put("label", label);
        params.put("get_type", type);
        params.put("start", "0");
        params.put("ui", "0");
        params.put("ui_id", "0");
        params.put("userid", "0");
        params.put("home", "0");
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    private void requestNovelData(String label, String type) {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRank");
        params.put("a", "get_label_rank");
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("home", "0");
        params.put("label", label);
        params.put("start", "0");
        params.put("ui_id", "0");
        params.put("get_type", type);
        NovelHttp http = new NovelHttp(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    @Override
    public void onResponse(String json, Map<String, String> params) {
        Gson gson = new Gson();
        RankItemEntry rankItemEntry = gson.fromJson(json, RankItemEntry.class);
        adapter.notifyDataChanged(rankItemEntry, classType);
    }

    @Override
    public void onFailed(Map<String, String> params) {
    }

}
