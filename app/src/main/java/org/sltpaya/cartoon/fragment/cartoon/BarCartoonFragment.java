package org.sltpaya.cartoon.fragment.cartoon;

import android.support.v7.widget.LinearLayoutManager;
import com.google.gson.Gson;
import org.sltpaya.cartoon.adapter.cartoon.BarCartoonTabAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.net.entry.BarCartoonEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;
import org.sltpaya.tool.Utils;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class BarCartoonFragment extends BaseTabFragment {

    private BarCartoonTabAdapter adapter;

    @Override
    protected void setRecyclerView() {
        int px = Utils.dpToPx(10);
        adapter = new BarCartoonTabAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setPadding(px, px, px, 0);
        setNetEvent();
    }

    private void setNetEvent() {
        HashMap<String, String> params = new HashMap<>();
        params.put("c", "MainRecommend");
        params.put("a", "get_tiaoman_recommend");
        params.put("start", "0");
        params.put("userid", "0");
        params.put("ui", "default");
        params.put("ui_id", "0");
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                if ("get_tiaoman_recommend".equals(params.get("a"))) {
                    System.out.println("条目网络数据获取到了！");
                    Gson gson = new Gson();
                    BarCartoonEntry entry = gson.fromJson(json, BarCartoonEntry.class);
                    adapter.notifyDataChanged(entry);
                }
            }

            @Override
            public void onFailed(Map<String, String> params) {
                if ("get_tiaoman_recommend".equals(params.get("a"))) {
                    System.out.println("条目网络数据获取失败了！");
                }
            }
        });
    }


}
