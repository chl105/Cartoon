package org.sltpaya.cartoon.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.cartoon.MoreItemAdapter;
import org.sltpaya.cartoon.net.entry.more.CartoonMoreEntry;
import org.sltpaya.cartoon.net.entry.more.NovelMoreEntry;
import org.sltpaya.cartoon.net.http.CartoonHttp;
import org.sltpaya.cartoon.net.http.NovelHttp;
import org.sltpaya.cartoon.net.observer.NetListener;

import java.util.HashMap;
import java.util.Map;

public class BookMoreActivity extends AppCompatActivity {

    private String[] mModuleInfo;//所显示的类型，可能是cartoon或者novel两个模块的最新
    private MoreItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_more);
        //string数组，0为标题名，1位类型（cartoon/novel),2为type的id
        mModuleInfo = getIntent().getExtras().getStringArray("info");
        initViews();
        requestNet();
    }

    private void initViews() {
        initBar();
        adapter = new MoreItemAdapter(mModuleInfo[1]);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 初始化Activity的Bar
     */
    private void initBar() {
        TextView activitTitle = (TextView) findViewById(R.id.activity_title);
        activitTitle.setText(mModuleInfo[0]);
        View backButton = findViewById(R.id.activity_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//关闭本activity
            }
        });
    }

    /**
     * 请求相应的网络数据
     */
    private void requestNet() {
        final Gson gson= new Gson();
        if ("cartoon".equals(mModuleInfo[1])) {
            HashMap<String, String> params = new HashMap<>();
            params.put("c","MainRecommend");
            params.put("a","get_main_recommend");
            params.put("userid","0");
            params.put("ui","default");
            params.put("type",mModuleInfo[2]);
            params.put("start","0");
            params.put("ui_id","0");

            CartoonHttp cartoonHttp = new CartoonHttp(params);
            cartoonHttp.request();
            cartoonHttp.getObserver().addNetListener(new NetListener() {
                @Override
                public void onResponse(String json, Map<String, String> params) {
                    CartoonMoreEntry moreEntry = gson.fromJson(json, CartoonMoreEntry.class);
                    adapter.notifyDataChanged(moreEntry);
                }

                @Override
                public void onFailed(Map<String, String> params) {
                }
            });
        }else {
            HashMap<String, String> params = new HashMap<>();
            params.put("c","MainRecommend");
            params.put("a","get_main_recommend");
            params.put("userid","0");
            params.put("ui","NovelRecommendActivity");
            params.put("type",mModuleInfo[2]);
            params.put("start","0");
            params.put("ui_id","0");

            NovelHttp novelHttp = new NovelHttp(params);
            novelHttp.request();
            novelHttp.getObserver().addNetListener(new NetListener() {
                @Override
                public void onResponse(String json, Map<String, String> params) {
                    NovelMoreEntry entry = gson.fromJson(json, NovelMoreEntry.class);
                    adapter.notifyDataChanged(entry);
                }

                @Override
                public void onFailed(Map<String, String> params) {
                }
            });
        }
    }


}
