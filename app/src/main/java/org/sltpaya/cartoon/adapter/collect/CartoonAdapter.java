package org.sltpaya.cartoon.adapter.collect;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.collect.CartoonHolder;
import org.sltpaya.cartoon.net.entry.collect.CartoonEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class CartoonAdapter extends RecyclerView.Adapter<CartoonHolder> {

    private CartoonEntry mEntry;
    private List<CartoonEntry.Datum> list;

    public void notifyDataChanged(CartoonEntry entry) {
        mEntry = entry;
        list = entry.getDataList();
        notifyDataSetChanged();
    }

    @Override
    public CartoonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collect_item_cartoon, parent, false);
        return new CartoonHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CartoonHolder holder, int position) {
        holder.updateView(mEntry);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}
