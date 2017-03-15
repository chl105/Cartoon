package org.sltpaya.cartoon.holder.cartoon;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.cartoon.R;
import org.sltpaya.cartoon.holder.BaseHolder;
import org.sltpaya.cartoon.net.cache.RecommendCache;
import org.sltpaya.cartoon.net.entry.AdEntry;
import org.sltpaya.cartoon.net.entry.Entry;
import java.util.List;
import static org.sltpaya.cartoon.consts.Consts.RECOMMEND_AD_TYPE;

/**
 * Author: SLTPAYA
 * Date: 2017/2/21
 */
public class AdTwoHolder extends BaseHolder {

    private AdEntry mEntry;
    private ImageView mImgOne;
    private ImageView mImgTwo;

    public AdTwoHolder(View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        mImgOne = (ImageView) itemView.findViewById(R.id.ad_img_1);
        mImgTwo = (ImageView) itemView.findViewById(R.id.ad_img_2);
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
        List<AdEntry.Datum> data = mEntry.getData();
        int nowListIndex = getItemIndex(data);
        //-------------
        int secondIndex = 6;
        if (nowListIndex == 0) {
            secondIndex = 2;
        }
        //-------------
        System.out.println("广告位置：第一个"+nowListIndex);
        AdEntry.Datum datum1 = data.get(nowListIndex);
        AdEntry.Datum datum2 = data.get(secondIndex);

        String imgUrl1 = datum1.getCover();
        String imgUrl2 = datum2.getCover();

        Picasso.with(itemView.getContext()).load(imgUrl1)
                .error(R.drawable.icon_cover_home01)
                .placeholder(R.drawable.icon_cover_home01)
                .into(mImgOne);
        Picasso.with(itemView.getContext()).load(imgUrl2)
                .error(R.drawable.icon_cover_home01)
                .placeholder(R.drawable.icon_cover_home01)
                .into(mImgTwo);
    }

    /**
     * TODO:重新修复--依赖性太强！！！
     * 确定当前索引值对应的json中的条目为那个条目
     * @param data {@link AdEntry}
     * @return json条目中对应的position
     */
    private int getItemIndex(List<AdEntry.Datum> data) {
        int[] positionMap = {5,9};
        int position = getAdapterPosition();
        //确定本Holder的索引值，从而确定本holder是第几次调用的
        int nowIndex = 0;//0,或者为1
        for (int i = 0; i < positionMap.length; i++) {
            if (positionMap[i] == position) {
                nowIndex = i;
            }
        }
        int[] typeMap = {3, 7};
        int type = typeMap[nowIndex];
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
