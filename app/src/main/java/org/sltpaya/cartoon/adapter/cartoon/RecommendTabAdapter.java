package org.sltpaya.cartoon.adapter.cartoon;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.cartoon.BookState;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.adapter.BaseRecyclerAdapter;
import org.sltpaya.cartoon.holder.BannerHolder;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.holder.cartoon.AdHolder;
import org.sltpaya.cartoon.holder.cartoon.AdTwoHolder;
import org.sltpaya.cartoon.holder.cartoon.HorizontalHolder;
import org.sltpaya.cartoon.holder.cartoon.PreviewHolder;
import org.sltpaya.cartoon.holder.cartoon.VerticalHolder;
import org.sltpaya.cartoon.listener.AdapterItemListener;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class RecommendTabAdapter extends BaseRecyclerAdapter<BaseHolder> {

    //所有的布局类型
    private final int BANNER_TYPE = 1;
    private final int PREVIEW_TYPE = 2;
    private final int HORIZONTAL_TYPE = 3;
    private final int VERTICAL_TYPE = 4;
    private final int AD_TYPE = 5;
    private final int AD_TWO_TYPE = 6;
    private AdapterItemListener<BookState> listener;
    private ViewGroup mParent;
    private BannerHolder mBannerHolder;

    @Override
    public void notifyDataChanged(boolean successful) {
        super.notifyDataChanged(successful);
    }

    public void setClickListener(AdapterItemListener<BookState> listener) {
        this.listener = listener;
    }

    private View inflateView(@LayoutRes int layoutId) {
        return LayoutInflater.from(mParent.getContext()).inflate(layoutId, mParent, false);
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder holder;
        mParent = parent;
        View inflate;
        switch (viewType) {
            case BANNER_TYPE:
                inflate = inflateView(R.layout.banner_layout);
                holder = new BannerHolder(inflate);
                mBannerHolder = (BannerHolder) holder;
                break;
            case PREVIEW_TYPE:
                inflate = inflateView(R.layout.preview_layout);
                holder = new PreviewHolder(inflate);
                break;
            case HORIZONTAL_TYPE:
                inflate = inflateView(R.layout.cartoon_group);
                holder = new HorizontalHolder(inflate);
                break;
            case AD_TYPE:
                inflate = inflateView(R.layout.ad_one);
                holder = new AdHolder(inflate);
                break;
            case AD_TWO_TYPE:
                inflate = inflateView(R.layout.ad_two);
                holder = new AdTwoHolder(inflate);
                break;
            default://默认为竖直页面
                inflate = inflateView(R.layout.cartoon_group);
                holder = new VerticalHolder(inflate);
                break;
        }
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (mSuccessful) {
            holder.updateView();
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_TYPE;
            case 1:
                return PREVIEW_TYPE;
            case 2:
                return HORIZONTAL_TYPE;
            case 3:
            case 7:
            case 11:
                return AD_TYPE;
            case 5:
            case 9:
                return AD_TWO_TYPE;
            default:
                return VERTICAL_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return 14;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mBannerHolder != null) {
            mBannerHolder.stopScroll();
        }
    }

}
