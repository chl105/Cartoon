package org.sltpaya.cartoon.adapter.rank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.rank.RankItemHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;
import org.sltpaya.cartoon.net.entry.rank.RankItemEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class RankItemAdapter extends RecyclerView.Adapter<RankItemHolder> {

    private RankItemEntry mEntry;
    private List<RankItemEntry.Datum> list;
    private String classType;
    private AdapterItemListener<BookState> listener;


    public void addListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    public void notifyDataChanged(RankItemEntry entry, String classType) {
        mEntry = entry;
        this.classType = classType;
        list = mEntry.getData().getData();
        notifyDataSetChanged();
    }

    @Override
    public RankItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.rank_novel_item;
        if ("cartoon".equals(classType)) {
            layoutId = R.layout.rank_cartoon_item;
        }
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        RankItemHolder holder = new RankItemHolder(inflate);
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RankItemHolder holder, int position) {
        holder.updateView(mEntry, classType);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
