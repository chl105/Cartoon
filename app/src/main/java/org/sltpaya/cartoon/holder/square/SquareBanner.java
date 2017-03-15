package org.sltpaya.cartoon.holder.square;

import android.view.View;
import org.sltpaya.cartoon.adapter.SquareBannerAdapter;
import org.sltpaya.cartoon.holder.BannerHolder;
import org.sltpaya.cartoon.net.cache.SquareCache;
import org.sltpaya.cartoon.net.entry.square.BannerEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/3
 */
public class SquareBanner extends BannerHolder {

    private BannerEntry entry;

    public SquareBanner(View itemView) {
        super(itemView);
    }

    @Override
    public void updateView() {
        SquareCache cache = SquareCache.newInstance();
        if (cache.getData() != null) {
            entry = (BannerEntry) cache.getData().get(0);
            setData();
            createDotView();
        }
    }

    private void setData() {
        if (entry.getData() != null) {
            pagerCount = entry.getData().size();
            int pagerStartIndex = 10000 * pagerCount;
            List<BannerEntry.Datum> list = entry.getData();
            mBanner.setAdapter(new SquareBannerAdapter(list));
            mBanner.setCurrentItem(pagerStartIndex, false);
            timingRoll();
        }
    }

}
