package org.sltpaya.cartoon.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.rank.RednecksAdapter;
import org.sltpaya.cartoon.net.entry.rank.RednecksEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.HashMap;
import java.util.Map;

/**
 * SLTPAYA
 * 2017-3-13
 * 小说模块--排行--土豪榜
 */
public class RednecksRankActivity extends BaseActivity implements NetListener{

    private ActivityItem mActivityItem;
    private RednecksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rednecks_rank);
        mActivityItem = getIntent().getExtras().getParcelable("item");
        setRecyclerView();
        inflateBar();
        requestNet();
    }

    private void setRecyclerView() {
        adapter = new RednecksAdapter();
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 联网请求土豪榜的数据
     * sign为服务器返回的一串校验性字符串，必须携带，但是目前还不知道该sign从哪里生成的
     * 将会面临失效的问题
     * http://dajiaochong.517w.com/dacu_app/app/?
     * c=PayRank&a=get_user_coin_rank&sign=e90d1b29ce83fa3cc392b2dbbed65164&ui=default&userid=1357225&start=0&ui_id=0
     */
    private void requestNet() {
        String sign = "e90d1b29ce83fa3cc392b2dbbed65164";
        String[] keys = {"c", "a", "sign", "ui", "userid", "start", "ui_id"};
        String[] values = {"PayRank", "get_user_coin_rank", sign, "default", "1357225", "0", "0"};
        HashMap<String, String> params = new HashMap<>();
        for (int i = 0; i < Math.min(keys.length, values.length); i++) {
            params.put(keys[i], values[i]);
        }
        CartoonHttp http = new CartoonHttp(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    /**
     * 填充父Title
     *
     */
    private void inflateBar() {
        TextView mTitle = (TextView) findViewById(R.id.activity_title);
        mTitle.setText(mActivityItem.getActivityTitle());
        View mBackButton = findViewById(R.id.activity_back);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResponse(String json, Map<String, String> params) {
        Gson gson = new Gson();
        RednecksEntry entry = gson.fromJson(json, RednecksEntry.class);
        if (entry != null) {
            if (entry.getCode() == 13) {
                onFailed(params);
            }else {
                adapter.notifyDataChanged(entry);
            }
        }
    }

    @Override
    public void onFailed(Map<String, String> params) {
        Toast.makeText(this, "加载土豪榜的数据失败了，可能是sign失效了。", Toast.LENGTH_LONG).show();
    }

}
