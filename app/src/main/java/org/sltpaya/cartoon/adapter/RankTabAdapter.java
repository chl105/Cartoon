package org.sltpaya.cartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.RankHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.RankEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class RankTabAdapter extends RecyclerView.Adapter<RankHolder> {

    private RankEntry mEntry;
    private List<RankEntry.Datum> data;
    private String classType;
    private AdapterItemListener<ActivityItem> mListener;

    public void addListener(AdapterItemListener<ActivityItem> listener) {
        mListener = listener;
    }

    public void notifyDataChanged(RankEntry entry, String classType) {
        mEntry = entry;
        data = entry.getData().getData();
        this.classType = classType;
        notifyDataSetChanged();
    }

    @Override
    public RankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        RankHolder rankHolder = new RankHolder(inflate);
        rankHolder.setRankListener(mListener);
        return rankHolder;
    }

    @Override
    public void onBindViewHolder(RankHolder holder, int position) {
        holder.updateView(mEntry, classType);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

}
