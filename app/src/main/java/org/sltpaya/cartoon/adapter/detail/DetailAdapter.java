package org.sltpaya.cartoon.adapter.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.detail.DetailHolder;
import org.sltpaya.cartoon.net.entry.detail.AuthorEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/3/7
 */
public class DetailAdapter extends RecyclerView.Adapter {

    private AuthorEntry mEntry;

    public DetailAdapter(AuthorEntry entry) {
        this.mEntry = entry;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_author_item, parent, false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
