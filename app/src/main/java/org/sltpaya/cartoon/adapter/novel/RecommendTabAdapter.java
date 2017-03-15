package org.sltpaya.cartoon.adapter.novel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.holder.novel.BannerHolder;
import org.sltpaya.cartoon.state.BaseState;
import org.sltpaya.cartoon.state.HolderManger;

/**
 * Author: SLTPAYA
 * Date: 2017/2/26
 */
public class RecommendTabAdapter extends RecyclerView.Adapter {

    private boolean dataChanged = false;
    private BannerHolder mBannerHolder;

    public void notifyDataChange() {
        dataChanged = true;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HolderManger manger = new HolderManger(inflater, parent, viewType);
        manger.work();
        RecyclerView.ViewHolder holder = manger.getHolder();
        if (holder instanceof BannerHolder) {
            mBannerHolder = (BannerHolder) holder;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (dataChanged) {
            ((BaseHolder) holder).updateView();
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BaseState.BANNER_TYPE;
            case 1:
                return BaseState.PREVIEW_TYPE;
            case 2:
            case 5:
                return BaseState.HORIZONTAL_SIX_TYPE;
            case 3:
            case 8:
                return BaseState.AD_TYPE;
            case 6:
            case 10:
                return BaseState.AD_TWO_TYPE;
            case 4:
                return BaseState.VERTICAL_TYPE;
            default:
                return BaseState.HORIZONTAL_FOUR_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return 13;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mBannerHolder != null) {
            mBannerHolder.stopScroll();
        }
    }

}
