package org.sltpaya.cartoon.adapter.cartoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.more.CartoonMoreHolder;
import org.sltpaya.cartoon.holder.more.NovelMoreHolder;
import org.sltpaya.cartoon.net.entry.more.CartoonMoreEntry;
import org.sltpaya.cartoon.net.entry.more.NovelMoreEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/8
 */
public class MoreItemAdapter extends RecyclerView.Adapter {

    private final String CARTOON = "cartoon";
    private final String NOVEL = "novel";
    private String adapterType = null;
    private CartoonMoreEntry mCartoonMoreEntry;
    private NovelMoreEntry mNovelMoreEntry;
    private int itemCount = 0;

    public MoreItemAdapter(String type) {
        adapterType = type;
    }

    //TODO:要完成更多Activity的总体布局参数
    public void notifyDataChanged(CartoonMoreEntry entry) {
        if (CARTOON.equals(adapterType)) {
            mCartoonMoreEntry = entry;
            List<CartoonMoreEntry.Datum> list = entry.getDataList();
            itemCount = list == null ? 0 : list.size();
            notifyDataSetChanged();
        }
    }

    /**
     * 要求传入的数据不为null
     *
     * @param entry NovelMoreEntry
     */
    //TODO:要完成更多Activity的总体布局参数
    public void notifyDataChanged(NovelMoreEntry entry) {
        if (NOVEL.equals(adapterType)) {
            mNovelMoreEntry = entry;
            List<NovelMoreEntry.Datum> list = entry.getDataList();
            itemCount = list == null ? 0 : list.size();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (CARTOON.equals(adapterType)) {
            View inflate = inflater.inflate(R.layout.more_cartoon, parent, false);
            return new CartoonMoreHolder(inflate);
        } else {
            View inflate = inflater.inflate(R.layout.more_novel, parent, false);
            return new NovelMoreHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (CARTOON.equals(adapterType)) {
            ((CartoonMoreHolder) holder).updateView(mCartoonMoreEntry);
        } else {
            ((NovelMoreHolder) holder).updateView(mNovelMoreEntry);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

}
