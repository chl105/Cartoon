package org.sltpaya.cartoon.fragment.novel;

import android.support.v7.widget.LinearLayoutManager;
import com.google.gson.Gson;
import org.sltpaya.cartoon.adapter.novel.UpdateDayAdapter;
import org.sltpaya.cartoon.fragment.BaseTabFragment;
import org.sltpaya.cartoon.net.entry.UpdateDayEntry;
import org.sltpaya.cartoon.net.http.NovelHttp;
import org.sltpaya.cartoon.net.observer.NetListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class UpdateDayFragment extends BaseTabFragment {

    private UpdateDayAdapter adapter;

    @Override
    protected void setRecyclerView() {
        adapter = new UpdateDayAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        setNetEvent();
    }

    private void setNetEvent() {
        HashMap<String, String> params = new HashMap<>();
        params.put("m", "web_app");
        params.put("c", "index");
        params.put("a", "ajax_dw_list");
        params.put("id", "0");
        NovelHttp http = new NovelHttp(params);
        http.setFlag(true);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                if ("ajax_dw_list".equals(params.get("a"))) {
                    System.out.println("日更获取到了信息");
                    Gson gson = new Gson();
                    UpdateDayEntry entry = gson.fromJson(json, UpdateDayEntry.class);
                    adapter.notifyDataChanged(entry);
                }
            }

            @Override
            public void onFailed(Map<String, String> params) {
                System.out.println("日更获取到了信息");
            }
        });
    }


}
