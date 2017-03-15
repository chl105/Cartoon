package org.sltpaya.cartoon.fragment.cartoon;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.activity.ClassifyActivity;
import org.sltpaya.cartoon.fragment.BaseClassify;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.ClassifyEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 * Cartoon分类Fragment
 */
public class ClassifyFragment extends BaseClassify implements AdapterItemListener<ActivityItem> {

    @Override
    protected void setNetEvent() {
        adapter.setListener(this);
        HashMap<String, String> params = new HashMap<>();
        String[] keys = {"c", "a", "ui", "type", "userid", "ui_id"};
        String[] values = {"MainCategory", "get_tag_list", "0", "0", "0", "0"};
        for (int i = 0; i < Math.min(keys.length, values.length); i++) {
            params.put(keys[i], values[i]);
        }
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                Gson gson = new Gson();
                ClassifyEntry entry = gson.fromJson(json, ClassifyEntry.class);
                adapter.notifyDataChanged(entry, "cartoon");
            }

            @Override
            public void onFailed(Map<String, String> params) {
            }
        });
    }

    @Override
    public void click(View v, ActivityItem info) {
        Intent intent = new Intent(getContext(), ClassifyActivity.class);
        intent.putExtra("item", info);
        getActivity().startActivity(intent);
    }

}
