package org.sltpaya.cartoon.fragment.novel;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.activity.NovelRankActivity;
import org.sltpaya.cartoon.fragment.BaseRankFragment;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.RankEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.http.NovelHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 * Novel排行模块
 */
public class RankTabFragment extends BaseRankFragment implements AdapterItemListener<ActivityItem> {

    @Override
    protected void setNetEvent() {
        adapter.addListener(this);
        NovelHttp http = new NovelHttp(params);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                Gson gson = new Gson();
                RankEntry entry = gson.fromJson(json, RankEntry.class);
                adapter.notifyDataChanged(entry, "novel");
            }

            @Override
            public void onFailed(Map<String, String> params) {
            }
        });
    }

    @Override
    public void click(View v, ActivityItem info) {
        //点击Novel排行中的条目时候，将会跳转到NovelRankActivity
        if ("novel".equals(info.getType())) {
            Intent intent = new Intent(getContext(), NovelRankActivity.class);
            intent.putExtra("item", info);
            getActivity().startActivity(intent);
        }
    }

}
