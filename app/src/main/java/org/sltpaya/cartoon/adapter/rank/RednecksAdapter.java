package org.sltpaya.cartoon.adapter.rank;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.ActivityItem;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.rank.RednecksHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.rank.RednecksEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/13
 */
public class RednecksAdapter extends RecyclerView.Adapter<RednecksHolder> {

    private AdapterItemListener<ActivityItem> listener;
    private List<RednecksEntry.Datum> list;

    public void notifyDataChanged(RednecksEntry entry) {
        list = entry.getData().getData();
        notifyDataSetChanged();
    }

    public void addListener(AdapterItemListener<ActivityItem> listener) {
        this.listener = listener;
    }

    @Override
    public RednecksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_rednecks_item, parent, false);
        RednecksHolder holder = new RednecksHolder(inflate);
        holder.setRankListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RednecksHolder holder, int position) {
        holder.updateView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
