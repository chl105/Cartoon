package org.sltpaya.cartoon.fragment.cartoon;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.activity.CartoonRankActivity;
import org.sltpaya.cartoon.activity.RednecksRankActivity;
import org.sltpaya.cartoon.adapter.DataUtils;
import org.sltpaya.cartoon.fragment.BaseRankFragment;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.RankEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class RankTabFragment extends BaseRankFragment implements AdapterItemListener<ActivityItem> {

    @Override
    protected void setNetEvent() {
        adapter.addListener(this);
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(new NetListener() {
            @Override
            public void onResponse(String json, Map<String, String> params) {
                Gson gson = new Gson();
                RankEntry entry = gson.fromJson(json, RankEntry.class);
                adapter.notifyDataChanged(entry, "cartoon");
            }

            @Override
            public void onFailed(Map<String, String> params) {
                System.out.println("排行获取到了信息");
            }
        });
    }

    @Override
    public void click(View v, ActivityItem item) {
        if ("cartoon".equals(item.getType())) {
            String id = item.getCallFeature();
            int idRank = DataUtils.parseInt(id, 0);
            if (idRank != 1 && idRank != 4 && idRank != 5) {
                Intent intent = new Intent(getContext(), CartoonRankActivity.class);
                intent.putExtra("item", item);
                getActivity().startActivity(intent);
            } else if (idRank == 5) {
                Intent intent = new Intent(getContext(), RednecksRankActivity.class);
                intent.putExtra("item", item);
                getActivity().startActivity(intent);
            }
        }
    }

}
