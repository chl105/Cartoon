package org.sltpaya.cartoon.fragment.classify;

import android.content.Intent;
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
import org.sltpaya.cartoon.activity.BookNewDetailsActivity;
import org.sltpaya.cartoon.activity.EntryUtils;
import org.sltpaya.cartoon.activity.NovelDetailActivity;
import org.sltpaya.cartoon.adapter.classify.ClassifyPagerItemAdapter;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.classify.ClassifyItemEntry;
import org.sltpaya.cartoon.net.http.CartoonClassifyHttp;
import org.sltpaya.cartoon.net.http.NovelHttp;
import org.sltpaya.cartoon.net.observer.NetListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 * 分类中ViewPager的Fragment
 */
public class ClassifyPagerFragment extends Fragment implements NetListener{

    private String mInfo;
    private RecyclerView mRecyclerView;
    private ClassifyPagerItemAdapter adapter;
    private String classType;

    @Override
    public void setArguments(Bundle args) {
        mInfo = (String) args.get("info");
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
        adapter = new ClassifyPagerItemAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        requestNet();
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
    }

    /**
     * 请求分类具体数据
     */
    private void requestNet() {
        String[] split = mInfo.split(",");
        String tag = split[0];//分类条目对应的id
        String type = split[1];//标签对应的id
        classType = split[2];//数据来自哪个模块
        if ("cartoon".equals(classType)) {
            System.out.println("测试:请求cartoon");
            requestCartoonData(tag, type);
        }else {
            System.out.println("测试:请求novel");
            requestNovelData(tag, type);
        }
    }

    private void requestCartoonData(String tag, String type) {
        String[] keys = {"tag", "type", "start", "label_type"};
        String[] values = {tag, type, "0", "0"};
        HashMap<String, String> params = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            params.put(keys[i], values[i]);
        }
        CartoonClassifyHttp http = new CartoonClassifyHttp(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    private void requestNovelData(String tag, String type) {
        String[] keys = {"c", "a", "ui", "userid", "label_type", "type", "start", "ui_id", "tag"};
        String[] values = {"MainCategory", "get_tag_book", "NovFragmentHint", "0", "0", type,
                "0", "0", tag};
        HashMap<String, String> params = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            params.put(keys[i], values[i]);
        }
        NovelHttp http = new NovelHttp(params);
        http.request();
        http.getObserver().addNetListener(this);
    }

    @Override
    public void onResponse(String json, Map<String, String> params) {
        Gson gson = new Gson();
        ClassifyItemEntry entry = gson.fromJson(json, ClassifyItemEntry.class);
        System.out.println("测试：classtype"+classType);
        adapter.notifyDataChanged(entry, classType);
    }

    @Override
    public void onFailed(Map<String, String> params) {
    }

}
