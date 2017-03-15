package org.sltpaya.cartoon.adapter.novel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.novel.UpdateDayHolder;
import org.sltpaya.cartoon.net.entry.UpdateDayEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/25
 */
public class UpdateDayAdapter extends RecyclerView.Adapter<UpdateDayHolder> {

    private UpdateDayEntry mEntry;
    private List<UpdateDayEntry.Conrhtml> list;

    public void notifyDataChanged(UpdateDayEntry entry) {
        mEntry = entry;
        list = entry.getConrhtml();
        notifyDataSetChanged();
    }

    @Override
    public UpdateDayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_update_day, parent, false);
        return new UpdateDayHolder(inflate);
    }

    @Override
    public void onBindViewHolder(UpdateDayHolder holder, int position) {
        holder.updateView(mEntry);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
