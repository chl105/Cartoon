package org.sltpaya.cartoon.adapter.cartoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.NotifyDataChanged;
import org.sltpaya.cartoon.holder.cartoon.BarCartoonHolder;
import org.sltpaya.cartoon.net.entry.BarCartoonEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/22
 */
public class BarCartoonTabAdapter extends RecyclerView.Adapter<BarCartoonHolder>
        implements NotifyDataChanged<BarCartoonEntry> {

    private BarCartoonEntry mEntry;
    private List<BarCartoonEntry.Datum> data;

    /**
     * 要保证传入的Entry不为null，本方法不做数据检查
     *
     * @param data BarCartoonEntry
     */
    @Override
    public void notifyDataChanged(BarCartoonEntry data) {
        mEntry = data;
        this.data = data.getDataList();
        notifyDataSetChanged();
    }

    @Override
    public BarCartoonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bar_cartoon, parent, false);
        return new BarCartoonHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BarCartoonHolder holder, int position) {
        holder.updateView(mEntry);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

}
