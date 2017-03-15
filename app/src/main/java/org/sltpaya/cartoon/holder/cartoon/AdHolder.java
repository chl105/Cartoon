package org.sltpaya.cartoon.holder.cartoon;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.AdEntry;
import org.sltpaya.cartoon.net.entry.Entry;
import org.sltpaya.tool.Utils;
import java.util.List;

import static org.sltpaya.cartoon.consts.Consts.RECOMMEND_AD_TYPE;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class AdHolder extends BaseHolder {

    private AdEntry mEntry;
    private ImageView imgView;
    private TextView textView;

    public AdHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        imgView = (ImageView) itemView.findViewById(R.id.ad_img);
        textView = (TextView) itemView.findViewById(R.id.ad_title);
    }

    @Override
    public void updateView() {
        RecommendCache cache = RecommendCache.newInstance();
        SparseArray<Entry> data = cache.getData();
        mEntry = (AdEntry) data.get(RECOMMEND_AD_TYPE);
        if (mEntry != null) {
            setData();
        }
    }

    private void setData() {
        showTopTitle();
        List<AdEntry.Datum> data = mEntry.getData();
        int nowListIndex = getItemIndex(data);
        AdEntry.Datum datum = data.get(nowListIndex);

        String imgUrl = datum.getCover();
        String title = datum.getTitle();

        textView.setText(title);
        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.icon_cover_home01)
                .placeholder(R.drawable.icon_cover_home01)
                .into(imgView);
    }

    /**
     * 根据布局的特点来决定是否显示广告位的上方标题
     * 并且动态的设置广告位大图的padding值
     */
    private void showTopTitle() {
        View view = itemView.findViewById(R.id.ad_top);
        System.out.println("索引值为："+getAdapterPosition());
        if (getAdapterPosition() == 11) {
            view.setVisibility(View.GONE);
            imgView.setPadding(0, Utils.dpToPx(15), 0, 0);
        }else {
            view.setVisibility(View.VISIBLE);
            imgView.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 确定当前索引值对应的json中的条目为那个条目
     * @param data {@link AdEntry}
     * @return json条目中对应的position
     */
    private int getItemIndex(List<AdEntry.Datum> data) {
        int[] positionMap = {3, 7, 11};//广告位出现的索引值位置
        int position = getAdapterPosition();
        //确定本Holder的索引值，从而确定本holder是第几次调用的
        int nowIndex = 0;
        for (int i = 0; i < positionMap.length; i++) {
            if (positionMap[i] == position) {
                nowIndex = i;
            }
        }
        int type = position - (nowIndex + 1);
        //获取type对应的json条目
        int nowListIndex = 0;
        for (int i = 0; i < data.size(); i++) {
            int s = Integer.parseInt(data.get(i).getAdvertiseType());
            if (s == type) {
                nowListIndex = i;
            }
        }
        return nowListIndex;
    }

}
