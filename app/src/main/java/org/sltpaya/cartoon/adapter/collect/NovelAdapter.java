package org.sltpaya.cartoon.adapter.collect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.collect.NovelHolder;
import org.sltpaya.cartoon.net.entry.collect.NovelEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class NovelAdapter extends RecyclerView.Adapter<NovelHolder> {

    private NovelEntry mEntry;
    private List<NovelEntry.Datum> list;

    public void notifyDataChanged(NovelEntry entry) {
        mEntry = entry;
        list = entry.getDataList();
        notifyDataSetChanged();
    }

    @Override
    public NovelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collect_item_novel, parent, false);
        return new NovelHolder(inflate);
    }

    @Override
    public void onBindViewHolder(NovelHolder holder, int position) {
        holder.updateView(mEntry);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
