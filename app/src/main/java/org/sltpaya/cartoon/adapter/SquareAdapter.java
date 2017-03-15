package org.sltpaya.cartoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.holder.square.FollowerHolder;
import org.sltpaya.cartoon.holder.square.HotHolder;
import org.sltpaya.cartoon.holder.square.NormalHolder;
import org.sltpaya.cartoon.holder.square.SquareBanner;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.cartoon.net.entry.square.NormalEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/1
 */
public class SquareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int BANNER_TYPE = 1;
    private final int HOT_TYPE = 2;
    private final int FOLLOW_TYPE = 3;
    private final int NORMAL_TYPE = 4;

    private List<NormalEntry.Datum> list;
    private SquareBanner mBannerHolder;


    public void notifyDataChanged(SparseArray<Entry> array) {
        NormalEntry entry = (NormalEntry) array.get(3);
        list = entry.getData().getData();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate;
        switch (viewType) {
            case BANNER_TYPE:
                inflate = inflater.inflate(R.layout.banner_layout, parent, false);
                return new SquareBanner(inflate);
            case HOT_TYPE:
                inflate = inflater.inflate(R.layout.square_item, parent, false);
                return new HotHolder(inflate);
            case FOLLOW_TYPE:
                inflate = inflater.inflate(R.layout.square_item, parent, false);
                return new FollowerHolder(inflate);
            default:
                inflate = inflater.inflate(R.layout.square_normal, parent, false);
                return new NormalHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SquareBanner) {
            mBannerHolder = (SquareBanner) holder;
        }
        ((BaseHolder) holder).updateView();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_TYPE;
            case 1:
                return HOT_TYPE;
            case 2:
                return FOLLOW_TYPE;
            default:
                return NORMAL_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 3;
    }


    /**
     * 重写该方法，前三项占整个布局
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (position >= 0 && position < 3) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
            }
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mBannerHolder != null) {
            mBannerHolder.stopScroll();
        }
    }

}
