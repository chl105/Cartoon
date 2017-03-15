package org.sltpaya.cartoon.fragment.collect;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import org.sltpaya.cartoon.adapter.collect.CartoonAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.net.entry.collect.CartoonEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;
import org.sltpaya.tool.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class CartoonFragment extends BaseTabFragment{

    private CartoonAdapter adapter;

    @Override
    protected void setRecyclerView() {
        adapter = new CartoonAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        setNetEvent();
    }

    private void setNetEvent() {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRank");
        params.put("a", "get_collect_recommend_list");
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("start", "0");
        params.put("ui_id", "0");
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                if ("get_collect_recommend_list".equals(params.get("a"))) {
                    Gson gson = new Gson();
                    CartoonEntry entry = gson.fromJson(json, CartoonEntry.class);
                    adapter.notifyDataChanged(entry);
                    Toast.makeText(getContext(),"收藏--漫画数据获取成功！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Map<String, String> params) {
                Toast.makeText(getContext(),"联网失败了！收藏-漫画", Toast.LENGTH_LONG).show();
            }
        });
    }


}
