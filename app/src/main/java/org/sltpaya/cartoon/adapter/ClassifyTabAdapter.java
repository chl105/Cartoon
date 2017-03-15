package org.sltpaya.cartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.ClassifyHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.ClassifyEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class ClassifyTabAdapter extends RecyclerView.Adapter<ClassifyHolder> {

    private ClassifyEntry mEntry;
    private List<ClassifyEntry.Datum> mData;
    private AdapterItemListener<ActivityItem> listener;
    private String classType;

    public void setListener(AdapterItemListener<ActivityItem> listener) {
        this.listener = listener;
    }

    /**
     * 通知数据进行改变
     * @param entry ClassifyEntry
     * @param classType cartoon或者novel的分类数据
     */
    public void notifyDataChanged(ClassifyEntry entry, String classType) {
        mEntry = entry;
        mData = entry.getData().getData();
        this.classType = classType;
        notifyDataSetChanged();
    }

    @Override
    public ClassifyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_classify, parent, false);
        ClassifyHolder holder = new ClassifyHolder(inflate);
        holder.setClassifyListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassifyHolder holder, int position) {
        holder.updateView(mEntry, classType);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

}
